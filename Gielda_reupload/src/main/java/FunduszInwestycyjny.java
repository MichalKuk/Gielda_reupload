package main.java;

import java.io.Serializable;
import java.util.Random;

public class FunduszInwestycyjny extends Inwestor implements Runnable, Serializable {

	private String nazwa;
	
	private double zysk;//inwestor dostaje jego %, je�eli jest dodatni, na start ka�dy fundusz ma 50k zysku
	private int dostepneJednostkiFunduszu;//ka�dy fundusz 10 jednostek, gdy inwestor kupi, liczba si� zmniejsza, inwestor okresowo dostaje 10% zysku
	//funduszu za jednostk�
	private double kosztJednostki;// = 4000
		
	public FunduszInwestycyjny(String imie, String nazwisko, String nazwa) {//imie i nazwisko osoby zarzadzajacej
		super(imie, nazwisko);
		this.nazwa=nazwa;
		this.zysk=50000;
		this.dostepneJednostkiFunduszu=10;
		this.kosztJednostki=4000;
	}
	public FunduszInwestycyjny() {
		super();
		this.zysk=50000;
		this.dostepneJednostkiFunduszu=10;
		this.kosztJednostki=4000;
	}
	
	
	public String toString(){//nadpisanie metody toString
		return (this.nazwa);
	}
	
	

	@Override
	public void run() {//dzia�anie w�tku funduszu	
		Random random = new Random();
		try {		
			while(true) {
				if(!Swiat.stop) {
					
					int czasCzekania = (random.nextInt(8)+2)*1000;// od 2 do 9 sekund				
					Thread.sleep(czasCzekania);	
					
					int los1 = random.nextInt(70)+1;//losuje 1-70 do okre�lania co kupi�/sprzeda�
					
					if(los1>=1 && los1<=10) {//1-10 kup akcje
						synchronized (Swiat.monitor) {
						
							if(!Gielda.listaWsyztskichSpolek.isEmpty()) {
								int indeksLosowejSpolki = random.nextInt(Gielda.listaWsyztskichSpolek.size());
								Spolka wylosowanaSpolka = Gielda.listaWsyztskichSpolek.get(indeksLosowejSpolki);//losowa sp�ka z rynku
								if(wylosowanaSpolka.getLiczbaAkcji()>0) {//je�eli sp�ka ma akcje na sprzeda�
									double marza = Swiat.listaWsyztskichGield.get(0).getMarza();
							
									double kwotaPrzeznaczonaNaKupno = Swiat.zaokraglijDo2miejsc( Math.random()*
											(80000-wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza)+1)+
											wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza));//kupuje za kwot� od ceny 1 akcji do 80.000
									
									int liczbaAkcjiDoKupienia = (int)(kwotaPrzeznaczonaNaKupno/(wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza)));
										
									if(liczbaAkcjiDoKupienia<=wylosowanaSpolka.getLiczbaAkcji()) {//je�eli sp�ka ma tyle akcji ile chcesz kupi� lub wi�cej
										zysk=Swiat.zaokraglijDo2miejsc(zysk - (liczbaAkcjiDoKupienia*wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza)) );
										Gielda.listaWsyztskichSpolek.get(indeksLosowejSpolki).setLiczbaAkcji( wylosowanaSpolka.getLiczbaAkcji()-liczbaAkcjiDoKupienia );
											
										//sprawdzanie, czy fundusz ma ju� akcje tej sp�ki
										boolean czyFunduszMaJuzTakieAkcje = false;
										for (Akcja a : this.getlWykupioneAkcje()) {
											if(wylosowanaSpolka.getAkcja().equals(a)) {//je�eli ma
												czyFunduszMaJuzTakieAkcje = true;
												int indeksAkcjiUInwestora = this.getlWykupioneAkcje().indexOf(a);
												int nowaLiczbaAkcji = this.getlLiczbaAkcji().get(indeksAkcjiUInwestora) + liczbaAkcjiDoKupienia;//oblicza l. akcji
												this.getlLiczbaAkcji().set(indeksAkcjiUInwestora, nowaLiczbaAkcji);//ustala now� liczb� akcji
												break;
											}
										}						
										if(czyFunduszMaJuzTakieAkcje==false) {//je�eli nie ma jeszcze takich akcji, to dodaje element do listy
											this.getlWykupioneAkcje().add(wylosowanaSpolka.getAkcja());
											this.getlLiczbaAkcji().add(liczbaAkcjiDoKupienia);
										}
									}
									else {//je�eli sp�ka ma mniej akcji chcesz kupi�, to kup wszytskie
										liczbaAkcjiDoKupienia = wylosowanaSpolka.getLiczbaAkcji();//do wykupienie awszytskie akcje
										zysk=Swiat.zaokraglijDo2miejsc(zysk- (liczbaAkcjiDoKupienia*wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza)) );//zap�ata					
										Gielda.listaWsyztskichSpolek.get(indeksLosowejSpolki).setLiczbaAkcji( wylosowanaSpolka.getLiczbaAkcji()-liczbaAkcjiDoKupienia );
										//zmiejsza si� liczba dost�pnych akcji
											
										//sprawdzanie, czy fundusz ma ju� akcje tej sp�ki
										boolean czyFunduszMaJuzTakieAkcje = false;
										for (Akcja a : this.getlWykupioneAkcje()) {
											if(wylosowanaSpolka.getAkcja().equals(a)) {//je�eli ma
												czyFunduszMaJuzTakieAkcje = true;
												int indeksAkcjiUInwestora = this.getlWykupioneAkcje().indexOf(a);
												int nowaLiczbaAkcji = this.getlLiczbaAkcji().get(indeksAkcjiUInwestora) + liczbaAkcjiDoKupienia;//oblicza l. akcji
												this.getlLiczbaAkcji().set(indeksAkcjiUInwestora, nowaLiczbaAkcji);//ustala now� liczb� akcji
												break;
											}
										}						
										if(czyFunduszMaJuzTakieAkcje==false) {//je�eli nie ma jeszcze takich akcji, to dodaje element do listy
											this.getlWykupioneAkcje().add(wylosowanaSpolka.getAkcja());
											this.getlLiczbaAkcji().add(liczbaAkcjiDoKupienia);
										}
									}
									
									System.out.println(this.toString()+" kupi� "+liczbaAkcjiDoKupienia+" akcji "+wylosowanaSpolka+" za "+
											Swiat.zaokraglijDo2miejsc(liczbaAkcjiDoKupienia*wylosowanaSpolka.getAkcja().getAktualnyKurs()*(1+marza))+"(z mar��)");
									
								}//je�eli sp�ka nie ma akcji, to nic si� nie wykonuje	
							}
						}
					}
					
					else if(los1>=11 && los1<=20) {//11-20 kup walut�
						synchronized (Swiat.monitor) {
						
							if(!RynekWalut.listaWszytskichWalut.isEmpty()) {
								double marza = Swiat.listaWsyztskichRynkowWalut.get(0).getMarza();
								int indeksLosowejWaluty = random.nextInt( RynekWalut.listaWszytskichWalut.size() );
								Waluta wylosowanaWaluta = RynekWalut.listaWszytskichWalut.get(indeksLosowejWaluty);
								
								double kwotaPrzeznaczonaNaKupno = Swiat.zaokraglijDo2miejsc( Math.random()*(80000-wylosowanaWaluta.getCenaSprzedazy()*(1+marza)+1)+
										wylosowanaWaluta.getCenaSprzedazy()*(1+marza) );//kupuje za kwot� od ceny 1 szt do 80.000
								int liczbaWalutDoKupienia = (int)(kwotaPrzeznaczonaNaKupno/wylosowanaWaluta.getCenaSprzedazy()*(1+marza) );
								//kupno
								zysk=Swiat.zaokraglijDo2miejsc(zysk - (liczbaWalutDoKupienia*wylosowanaWaluta.getCenaSprzedazy()*(1+marza)) );//zap�ata
									
								//sprawdzanie, czy inwestor ma ju� t� walut�
								boolean czyFunduszMaJuzTakaWalute = false;
								for (Waluta w : this.getlWykupioneWaluty()) {
									if(wylosowanaWaluta.equals(w)) {//je�eli ma
										czyFunduszMaJuzTakaWalute = true;
										int indeksWalutyUInwestora = this.getlWykupioneWaluty().indexOf(w);
										int nowaLiczbaWalut = this.getlLiczbaWalut().get(indeksWalutyUInwestora) + liczbaWalutDoKupienia;//oblicza l. waluty
										this.getlLiczbaWalut().set(indeksWalutyUInwestora, nowaLiczbaWalut);//ustala now� liczb� waluty
										break;
									}
								}						
								if(czyFunduszMaJuzTakaWalute==false) {//je�eli nie ma jeszcze takiej waluty, to dodaje element do listy
									this.getlWykupioneWaluty().add(wylosowanaWaluta);
									this.getlLiczbaWalut().add(liczbaWalutDoKupienia);
								}
								
								System.out.println(this.toString()+" kupi� "+liczbaWalutDoKupienia+" szt waluty "+wylosowanaWaluta+" za "+
										Swiat.zaokraglijDo2miejsc(liczbaWalutDoKupienia*wylosowanaWaluta.getCenaSprzedazy()*(1+marza))+"(z mar��)");
								
							}
						}
					}
					
					else if(los1>=21 && los1<=30) {// kup surowiec
						synchronized (Swiat.monitor) {
							
							if(!RynekSurowcow.listaWszystkichSurowcow.isEmpty()) {
								double marza = Swiat.listaWsyztskichRynkowSurowcow.get(0).getMarza();
								int indeksLosowegoSurowca = random.nextInt( RynekSurowcow.listaWszystkichSurowcow.size() );
								Surowiec wylosowanySurowiec = RynekSurowcow.listaWszystkichSurowcow.get(indeksLosowegoSurowca);
								
								//przeliczenie waluty (warto�� surowca podana jest w konkretnej walucie)
								Waluta walutaSurowca = wylosowanySurowiec.getWaluta();
								double warto��Surowca =Swiat.zaokraglijDo2miejsc( wylosowanySurowiec.getAktualnaWartosc()*walutaSurowca.getCenaSprzedazy()*(1+marza) );//warto�� surowca w walucie rynkowej
								
								double kwotaPrzeznaczonaNaKupno = Swiat.zaokraglijDo2miejsc( Math.random()*(100000-warto��Surowca+1)+warto��Surowca );
								//kupuje za kwot�: od cena 1 szt surowca do 100.000
								int liczbaSurowcaDoKupienia = (int)(kwotaPrzeznaczonaNaKupno/warto��Surowca);
								//kupno
								zysk=Swiat.zaokraglijDo2miejsc(zysk - liczbaSurowcaDoKupienia*warto��Surowca );//zap�ata
									
								//sprawdzanie, czy inwestor ma ju� ten surowiec
								boolean czyFunduszMaJuzTenSurowiec = false;
								for (Surowiec s : this.getlWykupioneSurowce()) {//STWORZY� LIST� SUROWC�W
									if(wylosowanySurowiec.equals(s)) {//je�eli ma
										czyFunduszMaJuzTenSurowiec = true;
										int indeksSurowcaUInwestora = this.getlWykupioneSurowce().indexOf(s);
										int nowaLiczbaSurowca = this.getlLiczbaSurowc�w().get(indeksSurowcaUInwestora) + liczbaSurowcaDoKupienia;//oblicza l. surowca
										this.getlLiczbaSurowc�w().set(indeksSurowcaUInwestora, nowaLiczbaSurowca);//ustala now� liczb� waluty
										break;
									}
								}						
								if(czyFunduszMaJuzTenSurowiec==false) {//je�eli nie ma jeszcze takiej waluty, to dodaje element do listy
									this.getlWykupioneSurowce().add(wylosowanySurowiec);
									this.getlLiczbaSurowc�w().add(liczbaSurowcaDoKupienia);
								}
								
								System.out.println(this.toString()+" kupi� "+liczbaSurowcaDoKupienia+" szt surowca "+wylosowanySurowiec+" za "+
										Swiat.zaokraglijDo2miejsc(liczbaSurowcaDoKupienia*warto��Surowca)+"(z mar�� i po przewalutowaniu)");
								
							}
						}
					}
					
					else if(los1>=31 && los1<=40) {// sprzedaj akcje (je�li posiada)
						synchronized (Swiat.monitor) {
							
							if(!this.getlWykupioneAkcje().isEmpty()) {
								double marza = Swiat.listaWsyztskichGield.get(0).getMarza();
								int indeksLosowejPosiadanejAkcji = random.nextInt( this.getlWykupioneAkcje().size() );
								Akcja losowaAkcja = this.getlWykupioneAkcje().get(indeksLosowejPosiadanejAkcji);
								int liczbaAkcjiDoSprzedania = random.nextInt(this.getlLiczbaAkcji().get(indeksLosowejPosiadanejAkcji))+1;// od 1 do liczby posiadanych akcji
								Spolka spolkaLosowejAkcji = null;					
								for (Spolka spolka : Gielda.listaWsyztskichSpolek) {//znajdowanie sp�ki kt�rej to s� akcje
									if(spolka.getAkcja().equals(losowaAkcja)) {
										spolkaLosowejAkcji = spolka;
										break;
									}
								}
								int indeksSpolki = Gielda.listaWsyztskichSpolek.indexOf(spolkaLosowejAkcji);//indeks sp�ki na Gielda.listaWsyztskichSpolek
								//sprzeda�
								zysk=Swiat.zaokraglijDo2miejsc(zysk + (liczbaAkcjiDoSprzedania*losowaAkcja.getAktualnyKurs()*(1-marza)) );
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
					
					else if(los1>=41 && los1<=50) {// sprzedaj walut� (je�li posiada)
						synchronized (Swiat.monitor) {
							if(!this.getlWykupioneWaluty().isEmpty()) {
								double marza = Swiat.listaWsyztskichRynkowWalut.get(0).getMarza();
								int indeksLosowejPosiadanejWaluty = random.nextInt( this.getlWykupioneWaluty().size() );
								Waluta losowaWaluta = this.getlWykupioneWaluty().get(indeksLosowejPosiadanejWaluty);
								int liczbaWalutyDoSprzedania = random.nextInt(this.getlLiczbaWalut().get(indeksLosowejPosiadanejWaluty))+1;// od 1 do liczby posiadanych
								//sprzeda�
								zysk=Swiat.zaokraglijDo2miejsc(zysk + (liczbaWalutyDoSprzedania*losowaWaluta.getCenaKupna()*(1-marza)) );
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
					
					else if(los1>=51 && los1<=60) {// sprzedaj surowiec (je�li posiada)
						synchronized (Swiat.monitor) {
							
							if(!this.getlWykupioneSurowce().isEmpty()) {
								double marza = Swiat.listaWsyztskichRynkowSurowcow.get(0).getMarza();
								int indeksLosowegoPosiadanegoSurowca = random.nextInt( this.getlWykupioneSurowce().size() );
								Surowiec losowySurowiec = this.getlWykupioneSurowce().get(indeksLosowegoPosiadanegoSurowca);
								int liczbaSurowcaDoSprzedania = random.nextInt(this.getlLiczbaSurowc�w().get(indeksLosowegoPosiadanegoSurowca))+1;
								
								//przeliczenie waluty (warto�� surowca podana jest w konkretnej walucie)
								Waluta walutaSurowca = losowySurowiec.getWaluta();
								double warto��Surowca =Swiat.zaokraglijDo2miejsc( losowySurowiec.getAktualnaWartosc()*walutaSurowca.getCenaKupna()*(1-marza));//warto�� surowca w walucie rynkowej
								
								zysk=Swiat.zaokraglijDo2miejsc(zysk + liczbaSurowcaDoSprzedania*warto��Surowca );
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
					//61-70 fundusz nic nie kupuje ani nie sprzedaje
				} else break;
			}//koniec while(true)
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	
	
	//gettery settery
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public double getZysk() {
		return zysk;
	}
	public void setZysk(double zysk) {
		this.zysk = zysk;
	}
	public int getDostepneJednostkiFunduszu() {
		return dostepneJednostkiFunduszu;
	}
	public void setDostepneJednostkiFunduszu(int dostepneJednostkiFunduszu) {
		this.dostepneJednostkiFunduszu = dostepneJednostkiFunduszu;
	}
	public double getKosztJednostki() {
		return kosztJednostki;
	}
	public void setKosztJednostki(double kosztJednostki) {
		this.kosztJednostki = kosztJednostki;
	}
	
}
