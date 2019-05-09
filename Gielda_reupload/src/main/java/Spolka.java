package main.java;

import java.io.Serializable;
import java.util.Random;

public class Spolka extends Thread implements Serializable {

	private String nazwa;
	private int liczbaAkcji;//liczba akcji spó³ki na rynku, tych które inwestorzy kupili i tych niekupionych
	private double zysk;//generuje siê losowo, nie wiêkszy ni¿ przychód
	private double przychód;// generuje sie losowo co jakiœ czas
	private double kapitalWlasny;//zwieksza sie o wartoœæ wypuszczonych akcji, gdy spó³ka wypuszcza nowe akcje; = kapita³ zak³¹dowy + inne kapita³y
	private double kapitalZalkadowy;//sta³a
	private int wolumen;//liczba akcji, która zmieni³a w³aœciciela
	private double obroty;//wartoœæ transakcji wykonanych na akcjach, roœnie gdy ktokolwiek kupi akcje spó³ki	
	private Akcja akcja;
	
	public Spolka(String nazwa, int liczbaAkcji, double zysk, double przychód, double kapitalWlasny, double kapitalZalkadowy, int wolumen, 
			double obroty, Akcja akcja) {
		this.nazwa=nazwa;
		this.liczbaAkcji=liczbaAkcji;
		this.zysk=zysk;
		this.przychód=przychód;
		this.kapitalWlasny=kapitalWlasny;
		this.kapitalZalkadowy=kapitalZalkadowy;
		this.wolumen=wolumen;
		this.obroty=obroty;
		this.akcja=akcja;
	}
	public Spolka() {}
	
	public String toString(){//nadpisanie metody toString, np syso(spolka1); wyœwietli spolka1.nazwa
		return this.nazwa;
		}
	
	
	public void run() {//dzia³anie w¹tku - aktualizacja kursu, wypuszczanie akcji, generacja zysku i przychodu (w regularnych odstêpach)	
		int licz = 0;
		Random random =  new Random();
		int los;
		int poprzedniaLiczbaAkcji;
		double nowyKursAkcji=0;
		try {
			while(true) {
				if(!Swiat.stop) {
				
					poprzedniaLiczbaAkcji = liczbaAkcji;
					sleep(5000);
					
					synchronized (Swiat.monitor) {
						//AKTUALIZACJA KURSU
						los = random.nextInt(10)+1;
						if(los<=5) {
							nowyKursAkcji = Swiat.zaokraglijDo2miejsc( akcja.getAktualnyKurs() * poprzedniaLiczbaAkcji/liczbaAkcji );
							//je¿eli wykupiono akcje, kurs wzroœnie, je¿eli sprzedano - zmaleje
						}else if(los>=6) {
							nowyKursAkcji = Swiat.zaokraglijDo2miejsc( akcja.getAktualnyKurs() * liczbaAkcji/poprzedniaLiczbaAkcji );
							//je¿eli wykupiono akcje, kurs zmaleje, je¿eli sprzedano - wzroœnie
						}
						
						if(nowyKursAkcji!=akcja.getAktualnyKurs()) System.out.println(nazwa+" zmienia kurs akcji z "+akcja.getAktualnyKurs()+" na "+nowyKursAkcji);
						
						akcja.setAktualnyKurs(nowyKursAkcji);
						akcja.przeliczKursy(nowyKursAkcji);
						
						if(licz==4) {//po 5 przejœciach pêtli (0-4) generowanie przychodu i zysku
							przychód = Swiat.zaokraglijDo2miejsc(Math.random() * 10000000);//losuje liczbê od 0 do 10.000.000
							zysk = Swiat.zaokraglijDo2miejsc( Math.random() * przychód);// od 0 do "przychód"
							licz=0;
							//System.out.println(nazwa+" generuje przychód "+przychód+" i zysk "+zysk);
						}
						los = random.nextInt(100)+1;//losuje liczbê 1-100
						if((los >= 1) && (los <= 15)) {//15% szans na wypuszczenie akcji przy ka¿dym przejœciu pêtli
							int staraLiczbaAkcji = liczbaAkcji;
							liczbaAkcji += random.nextInt(5000)+1;// wypuszcza od 1 do 5000 akcji 
							System.out.println(nazwa+" wypuszcza "+(liczbaAkcji-staraLiczbaAkcji)+" akcji, ma teraz ich "+liczbaAkcji);
						}
						licz++;
					}
				}else break;
			}
		}catch (InterruptedException e) {}			
	}
	
	
//	public void generujPrzychodIZysk() {}
//	public void wypuscNoweAkcje() {
//		//zwieksza sie liczbaAkcji
//		//pojawiaja sie akcje/zwiêksza siê ich liczba na Rynek.listaWszystkichAktywow
//		//kapita³W³asny zwiêksza siê o wartoœæ wypuszczonych akcji
//	}
//	public void wykupAkcje(double cenaWykupu) {
//		//odejmuje liczbe wykupionych akcji od liczbaAkcji
//		//z Rynek.listaWszystkichAktywow usuwa swoje akcje
//		//kapital wlasny sie zmniejsza
//	}//tylko swoje akcje

	
	//do losowania nazwy
	public static String[] bazaNazw1 = {"Klucz","Bruk","Dom","Super","Extra","Mega","Auto","Bus","Kafel","Drew","Beton","Rur","Funda","Blok",
			"Gaz","Kamieñ","Grób","£upek","Granit","¯wir"};
	public static String[] bazaNazw2 = {"bud","ex"," Company","bor","inex","er"," SA"," spó³ka z.o.o"};
	
	public static String losujNazweSpolki() {
		Random random = new Random();
		int poczatek = random.nextInt(bazaNazw1.length);
		int koniec = random.nextInt(bazaNazw2.length);
		String nazwa = bazaNazw1[poczatek]+bazaNazw2[koniec];
		return nazwa;
	}
	
	
	//gettery settery
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public int getLiczbaAkcji() {
		return liczbaAkcji;
	}
	public void setLiczbaAkcji(int liczbaAkcji) {
		this.liczbaAkcji = liczbaAkcji;
	}
	public double getZysk() {
		return zysk;
	}
	public void setZysk(double zysk) {
		this.zysk = zysk;
	}
	public double getPrzychód() {
		return przychód;
	}
	public void setPrzychód(double przychód) {
		this.przychód = przychód;
	}
	public double getKapitalWlasny() {
		return kapitalWlasny;
	}
	public void setKapitalWlasny(double kapitalWlasny) {
		this.kapitalWlasny = kapitalWlasny;
	}
	public double getKapitalZalkadowy() {
		return kapitalZalkadowy;
	}
	public void setKapitalZalkadowy(double kapitalZalkadowy) {
		this.kapitalZalkadowy = kapitalZalkadowy;
	}
	public int getWolumen() {
		return wolumen;
	}
	public void setWolumen(int wolumen) {
		this.wolumen = wolumen;
	}
	public double getObroty() {
		return obroty;
	}
	public void setObroty(double obroty) {
		this.obroty = obroty;
	}

	public Akcja getAkcja() {
		return akcja;
	}
	public void setAkcja(Akcja akcja) {
		this.akcja = akcja;
	}
	
	
}
