package main.java;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class InwestorIndywidualny extends Inwestor implements Runnable {

	private String pesel;
	private double budzet;
	
	private List<FunduszInwestycyjny> lWykupioneJednostkiFunduszy;
	private List<Integer> lLiczbaJednostekFunduszy;
	
	public InwestorIndywidualny(String imie, String nazwisko, String pesel, double budzet) {
		super(imie, nazwisko);
		this.pesel=pesel;
		this.budzet=budzet;
		lWykupioneJednostkiFunduszy = new LinkedList<>();
		lLiczbaJednostekFunduszy = new LinkedList<>();
	}
	public InwestorIndywidualny() {
		super();
		lWykupioneJednostkiFunduszy = new LinkedList<>();
		lLiczbaJednostekFunduszy = new LinkedList<>();
	}
	
	
	public String toString(){//nadpisanie metody toString
		return (this.getImie()+" "+this.getNazwisko());
	}
	
	
	
	@Override
	public void run() {//losuje sprzeda�/kupno aktyw�w lub zwi�kszenie bud�etu w losowych odst�pach czasu
		
		Random random = new Random();
		int czasDoZyskuZJednostekFunduszy = 0;
		try {		
			while(true) {
				if(!Swiat.stop) {
					
					int czasCzekania = (random.nextInt(8)+2)*1000;// od 2 do 9 sekund		
					//System.out.println("W�tek inwestora "+toString()+" czas czekania: "+czasCzekania+" ms");
					Thread.sleep(czasCzekania);	
					
					czasDoZyskuZJednostekFunduszy += czasCzekania/1000;
					if(czasDoZyskuZJednostekFunduszy>=30) {//co mniej wi�cej 30 sekund generuje zysk z jednostek funduszy
						synchronized (Swiat.monitor) {
							if(!lWykupioneJednostkiFunduszy.isEmpty()) {//je�li ma jakie� jednostki
								int i=0;
								for (FunduszInwestycyjny funduszInwestycyjny : lWykupioneJednostkiFunduszy) {//przegl�da wsyztskie jednostki i generuje zysk
									if (funduszInwestycyjny.getZysk()>0) {//o ile fundusz ma jaki� zysk
										double zysk = Swiat.zaokraglijDo2miejsc(lLiczbaJednostekFunduszy.get(i)*0.1*funduszInwestycyjny.getZysk());
										budzet = Swiat.zaokraglijDo2miejsc(budzet+zysk);
										System.out.println(toString()+"generuje zysk: "+zysk+" z "+lLiczbaJednostekFunduszy.get(i)+" jednostek funduszu "+funduszInwestycyjny);
									}
									i++;
								}
							}
						}
						czasDoZyskuZJednostekFunduszy = czasDoZyskuZJednostekFunduszy%30;
					}
					int los1 = random.nextInt(100)+1;//losuje 1-100 do okre�lania co kupi�/sprzeda�
					if(this.budzet<6000 && los1<=40) {los1 = random.nextInt(100)+1;}//je�eli ma�y bud�et i wylosowano kupno, to losuje jeszcze raz
					//�eby zwi�kszy� szanse na sprzeda�/zwi�kszenie bud�etu
						
					
					if(los1>=1 && los1<=10) {//1-10 kup akcje
						synchronized (Swiat.monitor) {
							if(!Gielda.listaWsyztskichSpolek.isEmpty()) {
								int indeksLosowejSpolki = random.nextInt(Gielda.listaWsyztskichSpolek.size());
								Spolka wylosowanaSpolka = Gielda.listaWsyztskichSpolek.get(indeksLosowejSpolki);//losowa sp�ka z rynku
								if(wylosowanaSpolka.getLiczbaAkcji()>0) {//je�eli sp�ka ma akcje na sprzeda�
									double marza = Swiat.listaWsyztskichGield.get(0).getMarza();
									
									//kupuje akcje za max 25% bud�etu
									double budzet25procent = this.budzet*0.25;
									
									if( budzet25procent>= ((wylosowanaSpolka.getAkcja().getAktualnyKurs())*(1+marza)) ) {//je�eli starczy na cho� 1 akcj�, to kupuje
										double kwotaPrzeznaczonaNaKupno = Swiat.zaokraglijDo2miejsc( Math.random()*(budzet25procent - wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza) ) + 
												wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza) );
												//losowa liczba od ceny 1 akcji do 25% bud�etu
										int liczbaAkcjiDoKupienia = (int)(kwotaPrzeznaczonaNaKupno/(wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza)));
										
										if(liczbaAkcjiDoKupienia<=wylosowanaSpolka.getLiczbaAkcji()) {//je�eli sp�ka ma tyle akcji ile chcesz kupi� lub wi�cej
											//kupno tylu akcji, ile chcesz
											budzet = Swiat.zaokraglijDo2miejsc(budzet- (liczbaAkcjiDoKupienia*wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza)) );//zap�ata					
											Gielda.listaWsyztskichSpolek.get(indeksLosowejSpolki).setLiczbaAkcji( wylosowanaSpolka.getLiczbaAkcji()-liczbaAkcjiDoKupienia );
											//zmiejsza si� liczba dost�pnych akcji
											
											//sprawdzanie, czy inwestor ma ju� akcje tej sp�ki
											boolean czyInwestorMaJuzTakieAkcje = false;
											for (Akcja a : this.getlWykupioneAkcje()) {
												if(wylosowanaSpolka.getAkcja().equals(a)) {//je�eli ma
													czyInwestorMaJuzTakieAkcje = true;
													int indeksAkcjiUInwestora = this.getlWykupioneAkcje().indexOf(a);
													int nowaLiczbaAkcji = this.getlLiczbaAkcji().get(indeksAkcjiUInwestora) + liczbaAkcjiDoKupienia;//oblicza l. akcji
													this.getlLiczbaAkcji().set(indeksAkcjiUInwestora, nowaLiczbaAkcji);//ustala now� liczb� akcji
													break;
												}
											}						
											if(czyInwestorMaJuzTakieAkcje==false) {//je�eli nie ma jeszcze takich akcji, to dodaje element do listy
												this.getlWykupioneAkcje().add(wylosowanaSpolka.getAkcja());
												this.getlLiczbaAkcji().add(liczbaAkcjiDoKupienia);
											}
										}
										else {//je�eli sp�ka ma mniej akcji chcesz kupi�, to kup wszytskie
											liczbaAkcjiDoKupienia = wylosowanaSpolka.getLiczbaAkcji();//do wykupienie awszytskie akcje
											budzet = Swiat.zaokraglijDo2miejsc(budzet- (liczbaAkcjiDoKupienia*wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza)) );//zap�ata						
											Gielda.listaWsyztskichSpolek.get(indeksLosowejSpolki).setLiczbaAkcji( wylosowanaSpolka.getLiczbaAkcji()-liczbaAkcjiDoKupienia );
											//zmiejsza si� liczba dost�pnych akcji
											
											//sprawdzanie, czy inwestor ma ju� akcje tej sp�ki
											boolean czyInwestorMaJuzTakieAkcje = false;
											for (Akcja a : this.getlWykupioneAkcje()) {
												if(wylosowanaSpolka.getAkcja().equals(a)) {//je�eli ma
													czyInwestorMaJuzTakieAkcje = true;
													int indeksAkcjiUInwestora = this.getlWykupioneAkcje().indexOf(a);
													int nowaLiczbaAkcji = this.getlLiczbaAkcji().get(indeksAkcjiUInwestora) + liczbaAkcjiDoKupienia;//oblicza l. akcji
													this.getlLiczbaAkcji().set(indeksAkcjiUInwestora, nowaLiczbaAkcji);//ustala now� liczb� akcji
													break;
												}
											}						
											if(czyInwestorMaJuzTakieAkcje==false) {//je�eli nie ma jeszcze takich akcji, to dodaje element do listy
												this.getlWykupioneAkcje().add(wylosowanaSpolka.getAkcja());
												this.getlLiczbaAkcji().add(liczbaAkcjiDoKupienia);
											}
										}
										
										System.out.println(this.toString()+" kupi� "+liczbaAkcjiDoKupienia+" akcji "+wylosowanaSpolka+" za "+
												Swiat.zaokraglijDo2miejsc(liczbaAkcjiDoKupienia*wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza))+"(z mar��)");
										
									}//je�eli nie starczy na zakup nawet 1 akcji, to nic si� nie dzieje
								}//je�eli sp�ka nie ma akcji, to nic si� nie wykonuje	
							}
						}
					}
					
					else if(los1>=11 && los1<=20) {//11-20 kup walut�
						synchronized (Swiat.monitor) {
							if(!RynekWalut.listaWszytskichWalut.isEmpty()) {
								int indeksLosowejWaluty = random.nextInt( RynekWalut.listaWszytskichWalut.size() );
								Waluta wylosowanaWaluta = RynekWalut.listaWszytskichWalut.get(indeksLosowejWaluty);
								double marza = Swiat.listaWsyztskichRynkowWalut.get(0).getMarza();
								double budzet25procent = this.budzet*0.25;
								
								if(budzet25procent>=(wylosowanaWaluta.getCenaSprzedazy()*(1+marza))) {//musi by� sta� na cho� jedn� sztuk�
									double kwotaPrzeznaczonaNaKupno = Swiat.zaokraglijDo2miejsc( Math.random()*(budzet25procent - wylosowanaWaluta.getCenaSprzedazy()*(1+marza))
											+wylosowanaWaluta.getCenaSprzedazy()*(1+marza));//liczba od ceny 1 sztuki waluty do 25% bud�etu
									int liczbaWalutDoKupienia = (int)(kwotaPrzeznaczonaNaKupno/wylosowanaWaluta.getCenaSprzedazy()*(1+marza));
									//kupno
									budzet=Swiat.zaokraglijDo2miejsc(budzet - (liczbaWalutDoKupienia*wylosowanaWaluta.getCenaSprzedazy()*(1+marza)) );//zap�ata
									
									//sprawdzanie, czy inwestor ma ju� t� walut�
									boolean czyInwestorMaJuzTakaWalute = false;
									for (Waluta w : this.getlWykupioneWaluty()) {
										if(wylosowanaWaluta.equals(w)) {//je�eli ma
											czyInwestorMaJuzTakaWalute = true;
											int indeksWalutyUInwestora = this.getlWykupioneWaluty().indexOf(w);
											int nowaLiczbaWalut = this.getlLiczbaWalut().get(indeksWalutyUInwestora) + liczbaWalutDoKupienia;//oblicza l. waluty
											this.getlLiczbaWalut().set(indeksWalutyUInwestora, nowaLiczbaWalut);//ustala now� liczb� waluty
											break;
										}
									}						
									if(czyInwestorMaJuzTakaWalute==false) {//je�eli nie ma jeszcze takiej waluty, to dodaje element do listy
										this.getlWykupioneWaluty().add(wylosowanaWaluta);
										this.getlLiczbaWalut().add(liczbaWalutDoKupienia);
									}
									
									System.out.println(this.toString()+" kupi� "+liczbaWalutDoKupienia+" szt waluty "+wylosowanaWaluta+" za "+
											Swiat.zaokraglijDo2miejsc(liczbaWalutDoKupienia*wylosowanaWaluta.getCenaSprzedazy()*(1+marza))+"(z mar��)");
									
								}//je�eli nie sta�, nic si� nie dzieje
							}	
						}
					}
					
					else if(los1>=21 && los1<=30) {// kup surowiec
						synchronized (Swiat.monitor) {
							if(!RynekSurowcow.listaWszystkichSurowcow.isEmpty()) {
								int indeksLosowegoSurowca = random.nextInt( RynekSurowcow.listaWszystkichSurowcow.size() );
								Surowiec wylosowanySurowiec = RynekSurowcow.listaWszystkichSurowcow.get(indeksLosowegoSurowca);
								double marza = Swiat.listaWsyztskichRynkowSurowcow.get(0).getMarza();
								
								//przeliczenie waluty (warto�� surowca podana jest w konkretnej walucie)
								Waluta walutaSurowca = wylosowanySurowiec.getWaluta();
								double warto��Surowca =Swiat.zaokraglijDo2miejsc( wylosowanySurowiec.getAktualnaWartosc()*walutaSurowca.getCenaSprzedazy()*(1+marza) );//warto�� surowca w walucie rynkowej
								
								double budzet25procent = this.budzet*0.25;
								
								if(budzet25procent>=(warto��Surowca*(1+marza))) {//je�elis sta� go na cho� 1 surowiec
									double kwotaPrzeznaczonaNaKupno = Swiat.zaokraglijDo2miejsc( Math.random()*(budzet25procent - warto��Surowca)+warto��Surowca);
									//liczba od ceny 1 jednostki surowca do 25% bud�etu
									int liczbaSurowcaDoKupienia = (int)(kwotaPrzeznaczonaNaKupno/warto��Surowca);
									//kupno
									budzet=Swiat.zaokraglijDo2miejsc(budzet - liczbaSurowcaDoKupienia*warto��Surowca );//zap�ata
									
									//sprawdzanie, czy inwestor ma ju� ten surowiec
									boolean czyInwestorMaJuzTenSurowiec = false;
									for (Surowiec s : this.getlWykupioneSurowce()) {
										if(wylosowanySurowiec.equals(s)) {//je�eli ma
											czyInwestorMaJuzTenSurowiec = true;
											int indeksSurowcaUInwestora = this.getlWykupioneSurowce().indexOf(s);
											int nowaLiczbaSurowca = this.getlLiczbaSurowc�w().get(indeksSurowcaUInwestora) + liczbaSurowcaDoKupienia;//oblicza l. surowca
											this.getlLiczbaSurowc�w().set(indeksSurowcaUInwestora, nowaLiczbaSurowca);//ustala now� liczb� waluty
											break;
										}
									}						
									if(czyInwestorMaJuzTenSurowiec==false) {//je�eli nie ma jeszcze takiej waluty, to dodaje element do listy
										this.getlWykupioneSurowce().add(wylosowanySurowiec);
										this.getlLiczbaSurowc�w().add(liczbaSurowcaDoKupienia);
									}
									
									System.out.println(this.toString()+" kupi� "+liczbaSurowcaDoKupienia+" szt surowca "+wylosowanySurowiec+" za "+
											Swiat.zaokraglijDo2miejsc(liczbaSurowcaDoKupienia*warto��Surowca)+"(z mar�� i po przewalutowaniu)");
									
								}
							}
						}
					}
					else if(los1>=31 && los1<=40) {// kup 1 jednostk� funduszy 
						synchronized (Swiat.monitor) {
							if(!Swiat.listaWszytskichFunduszyInwestycyjnych.isEmpty()) {
								int indeksLosowegoFunduszu = random.nextInt( Swiat.listaWszytskichFunduszyInwestycyjnych.size() );
								FunduszInwestycyjny wylosowanyFundusz = Swiat.listaWszytskichFunduszyInwestycyjnych.get(indeksLosowegoFunduszu);
								//double budzet25procent = this.budzet*0.25;
								
								if(budzet>=wylosowanyFundusz.getKosztJednostki() && wylosowanyFundusz.getDostepneJednostkiFunduszu()>0) {
									budzet=Swiat.zaokraglijDo2miejsc(budzet - wylosowanyFundusz.getKosztJednostki() );//zap�ata
									wylosowanyFundusz.setDostepneJednostkiFunduszu( wylosowanyFundusz.getDostepneJednostkiFunduszu()-1 );
									
									//sprawdzanie, czy inwestor ma ju� jednostki tego funduszu
									boolean czyInwestorMaJuzTenFundusz = false;
									for (FunduszInwestycyjny f : this.getlWykupioneJednostkiFunduszy()) {
										if(wylosowanyFundusz.equals(f)) {//je�eli ma
											czyInwestorMaJuzTenFundusz = true;
											int indeksFunduszuUInwestora = this.getlWykupioneJednostkiFunduszy().indexOf(f);
											int nowaLiczbaJednostek = this.getlLiczbaJednostekFunduszy().get(indeksFunduszuUInwestora)+1;//oblicza l. jednostek
											this.getlLiczbaJednostekFunduszy().set(indeksFunduszuUInwestora, nowaLiczbaJednostek);//ustala now� liczb� jednostek
											break;
										}
									}						
									if(czyInwestorMaJuzTenFundusz==false) {//je�eli nie ma jeszcze takiej jednostki, to dodaje element do listy
										this.getlWykupioneJednostkiFunduszy().add(wylosowanyFundusz);
										this.getlLiczbaJednostekFunduszy().add(1);
									}
									
									System.out.println(this.toString()+" kupi� jednostk� funduszu "+wylosowanyFundusz+" za "+wylosowanyFundusz.getKosztJednostki());
									
								}//jak nie starczy, to nie kupuje
							}
						}
					}
					
					else if(los1>=41 && los1<=50) {// sprzedaj akcje (je�li posiada)
						synchronized (Swiat.monitor) {
							if(!this.getlWykupioneAkcje().isEmpty()) {
								double marza = Swiat.listaWsyztskichGield.get(0).getMarza();
								int indeksLosowejPosiadanejAkcji = random.nextInt( this.getlWykupioneAkcje().size() );//indeks akcji na listach:
								//this.getlWykupioneAkcje()  i  this.getlLiczbaAkcji()
								Akcja losowaAkcja = this.getlWykupioneAkcje().get(indeksLosowejPosiadanejAkcji);
								int liczbaAkcjiDoSprzedania = random.nextInt(this.getlLiczbaAkcji().get(indeksLosowejPosiadanejAkcji))+1;// od 0 do liczby posiadanych akcji
								Spolka spolkaLosowejAkcji = null;					
								for (Spolka spolka : Gielda.listaWsyztskichSpolek) {//znajdowanie sp�ki kt�rej to s� akcje
									if(spolka.getAkcja().equals(losowaAkcja)) {
										spolkaLosowejAkcji = spolka;
										break;
									}
								}
								int indeksSpolki = Gielda.listaWsyztskichSpolek.indexOf(spolkaLosowejAkcji);//indeks sp�ki na Gielda.listaWsyztskichSpolek
								//sprzeda�
								budzet=Swiat.zaokraglijDo2miejsc(budzet + (liczbaAkcjiDoSprzedania*losowaAkcja.getAktualnyKurs()*(1-marza)) );
								Gielda.listaWsyztskichSpolek.get(indeksSpolki).setLiczbaAkcji( spolkaLosowejAkcji.getLiczbaAkcji()+liczbaAkcjiDoSprzedania );
								int liczbaAkcji = this.getlLiczbaAkcji().get(indeksLosowejPosiadanejAkcji);
								int nowaLiczbaAkcji = liczbaAkcji-liczbaAkcjiDoSprzedania;
								this.getlLiczbaAkcji().set(indeksLosowejPosiadanejAkcji, nowaLiczbaAkcji);//ustawia now� liczb� akcji na stara liczba - sprzedane akcje
								if(nowaLiczbaAkcji==0) {
									this.getlWykupioneAkcje().remove(indeksLosowejPosiadanejAkcji);
									this.getlLiczbaAkcji().remove(indeksLosowejPosiadanejAkcji);
								}
								
								System.out.println(this.toString()+" sprzeda� "+liczbaAkcjiDoSprzedania+" akcji "+spolkaLosowejAkcji+" za "+
										Swiat.zaokraglijDo2miejsc( liczbaAkcjiDoSprzedania*losowaAkcja.getAktualnyKurs()*(1-marza) )+"(z mar��)");
								
							}
						}
					}
					
					else if(los1>=51 && los1<=60) {// sprzedaj walut� (je�li posiada)
						synchronized (Swiat.monitor) {
							if(!this.getlWykupioneWaluty().isEmpty()) {
								double marza = Swiat.listaWsyztskichRynkowWalut.get(0).getMarza();
								int indeksLosowejPosiadanejWaluty = random.nextInt( this.getlWykupioneWaluty().size() );//indeks waluty na listach:
								//this.getlWykupioneWaluty()  i  this.getlLiczbaWalut()
								Waluta losowaWaluta = this.getlWykupioneWaluty().get(indeksLosowejPosiadanejWaluty);
								int liczbaWalutyDoSprzedania = random.nextInt(this.getlLiczbaWalut().get(indeksLosowejPosiadanejWaluty))+1;// od 0 do liczby posiadanych akcji
								
								//sprzeda�
								budzet=Swiat.zaokraglijDo2miejsc(budzet + (liczbaWalutyDoSprzedania*losowaWaluta.getCenaKupna()*(1-marza)) );
								int liczbaWaluty = this.getlLiczbaWalut().get(indeksLosowejPosiadanejWaluty);
								int nowaLiczbaWaluty = liczbaWaluty-liczbaWalutyDoSprzedania;
								this.getlLiczbaWalut().set(indeksLosowejPosiadanejWaluty, nowaLiczbaWaluty);//ustawia now� liczb� waluty na stara liczba-sprzedane
								if(nowaLiczbaWaluty==0) {
									this.getlWykupioneWaluty().remove(indeksLosowejPosiadanejWaluty);
									this.getlLiczbaWalut().remove(indeksLosowejPosiadanejWaluty);
								}
								
								System.out.println(this.toString()+" sprzeda� "+liczbaWalutyDoSprzedania+" szt waluty "+losowaWaluta+" za "+
										Swiat.zaokraglijDo2miejsc(liczbaWalutyDoSprzedania*losowaWaluta.getCenaKupna()*(1-marza))+"(z mar��)");
								
							}
						}
					}
					
					else if(los1>=61 && los1<=70) {// sprzedaj surowiec (je�li posiada)
						synchronized (Swiat.monitor) {
							if(!this.getlWykupioneSurowce().isEmpty()) {
								double marza = Swiat.listaWsyztskichRynkowSurowcow.get(0).getMarza();
								int indeksLosowegoPosiadanegoSurowca = random.nextInt( this.getlWykupioneSurowce().size() );
								Surowiec losowySurowiec = this.getlWykupioneSurowce().get(indeksLosowegoPosiadanegoSurowca);
								int liczbaSurowcaDoSprzedania = random.nextInt(this.getlLiczbaSurowc�w().get(indeksLosowegoPosiadanegoSurowca))+1;
								
								//przeliczenie waluty (warto�� surowca podana jest w konkretnej walucie)
								Waluta walutaSurowca = losowySurowiec.getWaluta();
								double warto��Surowca =Swiat.zaokraglijDo2miejsc( losowySurowiec.getAktualnaWartosc()*walutaSurowca.getCenaKupna()*(1-marza) );//warto�� surowca w walucie rynkowej
								
								budzet=Swiat.zaokraglijDo2miejsc(budzet + liczbaSurowcaDoSprzedania*warto��Surowca );
								int liczbaSurowca = this.getlLiczbaSurowc�w().get(indeksLosowegoPosiadanegoSurowca);
								int nowaLiczbaSurowca = liczbaSurowca-liczbaSurowcaDoSprzedania;
								this.getlLiczbaSurowc�w().set(indeksLosowegoPosiadanegoSurowca, nowaLiczbaSurowca);
								if(nowaLiczbaSurowca==0) {
									this.getlWykupioneSurowce().remove(indeksLosowegoPosiadanegoSurowca);
									this.getlLiczbaSurowc�w().remove(indeksLosowegoPosiadanegoSurowca);
								}
								
								System.out.println(this.toString()+" sprzeda� "+liczbaSurowcaDoSprzedania+" szt surowca "+losowySurowiec+" za "+
										Swiat.zaokraglijDo2miejsc(liczbaSurowcaDoSprzedania*warto��Surowca)+"(z mar��)");
								
							}
						}
					}
					
					else if(los1>=71 && los1<=80) {// sprzedaj 1 jednostk� funduszu z powrotem funduszowi po 2000 (kupono po 4000)
						synchronized (Swiat.monitor) {
							if(!this.getlWykupioneJednostkiFunduszy().isEmpty()) {
								int indeksLosowegoPosiadanegoFunduszu = random.nextInt( this.getlWykupioneJednostkiFunduszy().size() );
								FunduszInwestycyjny losowyFundusz = this.getlWykupioneJednostkiFunduszy().get(indeksLosowegoPosiadanegoFunduszu);
								int liczbaJednostek = this.getlLiczbaJednostekFunduszy().get(indeksLosowegoPosiadanegoFunduszu);
								
								budzet=Swiat.zaokraglijDo2miejsc(budzet+2000);
								int nowaLiczbaJednostek = liczbaJednostek-1;//zmniejsza si� l posiadanych jednostek
								this.getlLiczbaJednostekFunduszy().set(indeksLosowegoPosiadanegoFunduszu, nowaLiczbaJednostek);
								losowyFundusz.setDostepneJednostkiFunduszu( losowyFundusz.getDostepneJednostkiFunduszu()+1 );
								if(nowaLiczbaJednostek==0) {
									this.getlWykupioneJednostkiFunduszy().remove(indeksLosowegoPosiadanegoFunduszu);
									this.getlLiczbaJednostekFunduszy().remove(indeksLosowegoPosiadanegoFunduszu);
								}
								
								System.out.println(this.toString()+" sprzeda� jednostk� funduszu "+losowyFundusz+" za 2000, ma teraz "+nowaLiczbaJednostek+
										"jednostek tego funduszu");
							}
						}
					}			
					
					else if(los1>=81 && los1<=90) {//81-90 zwi�ksz bud�et
						synchronized (Swiat.monitor) {
							if(this.budzet<2000000) {
								int przyrostBudzetu = random.nextInt(75000-1000+1)+1000;// 1k-75k
								budzet=Swiat.zaokraglijDo2miejsc(budzet+przyrostBudzetu);
								
								System.out.println(this.toString()+" zwi�kszy� sw�j bud�et o "+przyrostBudzetu+", ma teraz "+this.budzet);
							}
						}
					}
					//91-100 - inwestor nic nie robi
				} else break;
			}		
		} catch (InterruptedException e) {e.printStackTrace();}
		
	}//koniec run()
	
	
	
	//gettery settery
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public double getBudzet() {
		return budzet;
	}
	public void setBudzet(double budzet) {
		this.budzet = budzet;
	}
	public List<FunduszInwestycyjny> getlWykupioneJednostkiFunduszy() {
		return lWykupioneJednostkiFunduszy;
	}
	public void setlWykupioneJednostkiFunduszy(List<FunduszInwestycyjny> lWykupioneJednostkiFunduszy) {
		this.lWykupioneJednostkiFunduszy = lWykupioneJednostkiFunduszy;
	}
	public List<Integer> getlLiczbaJednostekFunduszy() {
		return lLiczbaJednostekFunduszy;
	}
	public void setlLiczbaJednostekFunduszy(List<Integer> lLiczbaJednostekFunduszy) {
		this.lLiczbaJednostekFunduszy = lLiczbaJednostekFunduszy;
	}
	
	
}
