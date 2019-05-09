package main.java;

import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Swiat implements Serializable {

	public static List<Gielda> listaWsyztskichGield = new LinkedList<Gielda>();
	public static List<RynekSurowcow> listaWsyztskichRynkowSurowcow = new LinkedList<>();
	public static List<RynekWalut> listaWsyztskichRynkowWalut = new LinkedList<>();
	public static List<InwestorIndywidualny> listaWszytskichInwestorowIndywidualnych = new LinkedList<>();
	public static List<FunduszInwestycyjny> listaWszytskichFunduszyInwestycyjnych = new LinkedList<>();
	
	public static long przyrostAktywowNaRynkach=0;//inwestorzy i fundusze pojawiaj¹ siê proporcjonalnie do liczby aktywów
	//jest sum¹ liczby akcji na rynku, oraz walut*40.000 i surowców*40.000 (ka¿da waluta i surowiec liczy siê jak 40.000 akcji), zeruje siê przy
	//pojawieniu siê inwestorów i funduszy
	
	public static Object monitor = new Object();
	public static boolean stop=false;//do koñczenia w¹tków

	
	public static void zapisz() throws FileNotFoundException, IOException {
		String nazwaPliku = "D:\\listy1.ser";
		
		ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                  new FileOutputStream(nazwaPliku)));
		out.writeObject(listaWsyztskichGield);
		out.writeObject(listaWsyztskichRynkowSurowcow);
		out.writeObject(listaWsyztskichRynkowWalut);
		out.writeObject(listaWszytskichInwestorowIndywidualnych);
		out.writeObject(listaWszytskichFunduszyInwestycyjnych);
		out.close();
	}
	
	public static void wczytaj() throws FileNotFoundException, IOException, ClassNotFoundException {
		String nazwaPliku = "D:\\listy1.ser";
		
		ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(
                  new FileInputStream(nazwaPliku)));
		
		listaWsyztskichGield = (LinkedList<Gielda>) in.readObject();
		listaWsyztskichRynkowSurowcow = (LinkedList<RynekSurowcow>) in.readObject();
		listaWsyztskichRynkowWalut = (LinkedList<RynekWalut>) in.readObject();
		listaWszytskichInwestorowIndywidualnych = (LinkedList<InwestorIndywidualny>) in.readObject();
		listaWszytskichFunduszyInwestycyjnych = (LinkedList<FunduszInwestycyjny>) in.readObject();
		in.close();
		
	}
	
	
	public static double zaokraglijDo2miejsc(double d) {
		d *=100;
		d = Math.round(d);
		d /=100;
		return d;
	}
	
	
	public static void main(String[] args) {
		
		
//		Gielda.listaWsyztskichSpolek = new LinkedList<Spolka>();
//		RynekSurowcow.listaWszystkichSurowcow = new LinkedList<Surowiec>();
//		RynekWalut.listaWszytskichWalut = new LinkedList<Waluta>();
		
		
		
//			//TESTOWE
//			Gielda g = new Gielda("Gielda 1", "Polska", "Poznañ", "os.Abc", 0.0425, "PLN");
//			Swiat.listaWsyztskichGield.add(g);
//			
//			RynekSurowcow rs = new RynekSurowcow("Ryneczek surowców 1", "Uzbegistan", "Miasteczko", "ul. D³uga 12", 0.05);
//			Swiat.listaWsyztskichRynkowSurowcow.add(rs);
//			
//			RynekWalut rw = new RynekWalut("Ryneczek Walut 1", "Srilanka", "Sridziajawardanapurakotte", "os. Takowe", 0.0315);
//			Swiat.listaWsyztskichRynkowWalut.add(rw);
		
		
		EventQueue.invokeLater(new Runnable() {//odpala okno g³ówne
			public void run() {
				try {
					OknoGlowne frame = new OknoGlowne();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
//		//TEST KUPNA AKCJI
//		InwestorIndywidualny inwestor1 = new InwestorIndywidualny("Micha³", "Kukie³a", "97040107411", 100500);
//		Swiat.listaWszytskichInwestorowIndywidualnych.add(inwestor1);
//		if(inwestor1.getlWykupioneAkcje().isEmpty()) System.out.println("Brak wykupionych akcji");
//		if(inwestor1.getlLiczbaAkcji().isEmpty()) System.out.println("Brak liczby wykupionych akcji");
//		
//		FunduszInwestycyjny fundusz1 = new FunduszInwestycyjny("Patryk", "Dolata", "Fundusz Patryka");
//		Swiat.listaWszytskichFunduszyInwestycyjnych.add(fundusz1);
//		
//		
//		Random random = new Random();
//		
//	for (int i = 0; i < 4; i++) {//4 * kupno
//			
//		int indeksLosowejSpolki = random.nextInt(Gielda.listaWsyztskichSpolek.size());
//		Spolka wylosowanaSpolka = Gielda.listaWsyztskichSpolek.get(indeksLosowejSpolki);//losowa spó³ka z rynku
//			System.out.println("Wylosowana spó³ka: "+wylosowanaSpolka);
//			
//		if(wylosowanaSpolka.getLiczbaAkcji()>0) {//je¿eli spó³ka ma akcje na sprzeda¿
//				System.out.println("Spó³ka ma akcji na sprzeda¿: "+wylosowanaSpolka.getLiczbaAkcji());
//				
////			int maxLiczbaAkcjiDoWykupuZaBud¿et = (int)(inwestor1.getBudzet()/wylosowanaSpolka.getAkcja().getAktualnyKurs());
////				System.out.println("Mo¿na wykupiæ do "+maxLiczbaAkcjiDoWykupuZaBud¿et+" akcji");
//				
//			//kupuje akcje za max 25% bud¿etu
//			double budzet25procent = inwestor1.getBudzet()*0.25;
//				System.out.println("25% bud¿etu = "+budzet25procent);
//				
//			double kwotaPrzeznaczonaNaKupno = Swiat.zaokraglijDo2miejsc( Math.random()*
//					(budzet25procent - wylosowanaSpolka.getAkcja().getAktualnyKurs()) + wylosowanaSpolka.getAkcja().getAktualnyKurs() );
//					//losowa liczba od ceny 1 akcji do 25% bud¿etu
//				System.out.println("Wylosowana kwota na zakup, to "+kwotaPrzeznaczonaNaKupno);
//				
//			int liczbaAkcjiDoKupienia = (int)(kwotaPrzeznaczonaNaKupno/wylosowanaSpolka.getAkcja().getAktualnyKurs());
//			System.out.println("Za kwotê kupiê tyle akcji: "+liczbaAkcjiDoKupienia);
//			
//			if(liczbaAkcjiDoKupienia<=wylosowanaSpolka.getLiczbaAkcji()) {//je¿eli spó³ka ma tyle akcji ile chcesz kupiæ lub wiêcej
//				System.out.println("Spó³ka ma tyle akcji");
//				
//				//kupno tylu akcji, ile chcesz
//				inwestor1.setBudzet(inwestor1.getBudzet() - liczbaAkcjiDoKupienia*wylosowanaSpolka.getAkcja().getAktualnyKurs());//zap³ata					
//				Gielda.listaWsyztskichSpolek.get(indeksLosowejSpolki).setLiczbaAkcji( wylosowanaSpolka.getLiczbaAkcji()-liczbaAkcjiDoKupienia );
//				//zmiejsza siê liczba dostêpnych akcji
//				
//				//sprawdzanie, czy inwestor ma ju¿ akcje tej spó³ki
//				boolean czyInwestorMaJuzTakieAkcje = false;
//				for (Akcja a : inwestor1.getlWykupioneAkcje()) {
//					if(wylosowanaSpolka.getAkcja().equals(a)) {//je¿eli ma
//						czyInwestorMaJuzTakieAkcje = true;
//							System.out.println("Inwestor ma ju¿ takie akcje");
//						int indeksAkcjiUInwestora = inwestor1.getlWykupioneAkcje().indexOf(a);
//						int nowaLiczbaAkcji = inwestor1.getlLiczbaAkcji().get(indeksAkcjiUInwestora) + liczbaAkcjiDoKupienia;//oblicza l. akcji
//						inwestor1.getlLiczbaAkcji().set(indeksAkcjiUInwestora, nowaLiczbaAkcji);//ustala now¹ liczbê akcji
//						break;
//					}
//				}						
//				if(czyInwestorMaJuzTakieAkcje==false) {//je¿eli nie ma jeszcze takich akcji, to dodaje element do listy
//						System.out.println("Inwestor nie ma takihc akcji, dodanie do listy");
//					inwestor1.getlWykupioneAkcje().add(wylosowanaSpolka.getAkcja());
//					inwestor1.getlLiczbaAkcji().add(liczbaAkcjiDoKupienia);
//				}
//				
//				System.out.println("Zakupionych akcji: "+liczbaAkcjiDoKupienia+"  Bud¿et po zakupie = "+inwestor1.getBudzet());
//				System.out.println("Aktywa tego inwestora: ");
//				if(!inwestor1.getlWykupioneAkcje().isEmpty()) {
//					for (int j = 0; j<inwestor1.getlWykupioneAkcje().size(); j++ ) {
//						System.out.println("Aktywo: "+inwestor1.getlWykupioneAkcje().get(j)+"  Liczba sztuk: "+inwestor1.getlLiczbaAkcji().get(j));						
//					}
//				}
//				
//			}
//			else {//je¿eli spó³ka ma mniej akcji chcesz kupiæ, to kup wszytskie
//				System.out.println("Spó³ka ma mniej akcji");
//				
//				liczbaAkcjiDoKupienia = wylosowanaSpolka.getLiczbaAkcji();//do wykupienie awszytskie akcje
//				inwestor1.setBudzet(inwestor1.getBudzet() - liczbaAkcjiDoKupienia*wylosowanaSpolka.getAkcja().getAktualnyKurs());				
//				Gielda.listaWsyztskichSpolek.get(indeksLosowejSpolki).setLiczbaAkcji( wylosowanaSpolka.getLiczbaAkcji()-liczbaAkcjiDoKupienia );
//				//zmiejsza siê liczba dostêpnych akcji
//				
//				//sprawdzanie, czy inwestor ma ju¿ akcje tej spó³ki
//				boolean czyInwestorMaJuzTakieAkcje = false;
//				for (Akcja a : inwestor1.getlWykupioneAkcje()) {
//					if(wylosowanaSpolka.getAkcja().equals(a)) {//je¿eli ma
//						czyInwestorMaJuzTakieAkcje = true;
//							System.out.println("Inwestor ma ju¿ takie akcje");
//						int indeksAkcjiUInwestora = inwestor1.getlWykupioneAkcje().indexOf(a);
//						int nowaLiczbaAkcji = inwestor1.getlLiczbaAkcji().get(indeksAkcjiUInwestora) + liczbaAkcjiDoKupienia;//oblicza l. akcji
//						inwestor1.getlLiczbaAkcji().set(indeksAkcjiUInwestora, nowaLiczbaAkcji);//ustala now¹ liczbê akcji
//						break;
//					}
//				}						
//				if(czyInwestorMaJuzTakieAkcje==false) {//je¿eli nie ma jeszcze takich akcji, to dodaje element do listy
//						System.out.println("Inwestor nie ma takich akcji, dodanie do listy");
//					inwestor1.getlWykupioneAkcje().add(wylosowanaSpolka.getAkcja());
//					inwestor1.getlLiczbaAkcji().add(liczbaAkcjiDoKupienia);
//				}
//				
//				System.out.println("Bud¿et po zakupie = "+inwestor1.getBudzet());
//				System.out.println("Aktywa tego inwestora: ");
//				if(!inwestor1.getlWykupioneAkcje().isEmpty()) {
//					for (int j = 0; j<inwestor1.getlWykupioneAkcje().size(); j++ ) {
//						System.out.println("Aktywo: "+inwestor1.getlWykupioneAkcje().get(j)+"  Liczba sztuk: "+inwestor1.getlLiczbaAkcji().get(j));						
//					}
//				}
//			}
//		}else System.out.println("Spó³ka nie ma akcji"); //je¿eli spó³ka nie ma akcji, to nic siê nie wykonuje	
//		System.out.println("");
//	}
//		
//		
//		
//		//TEST SPRZED¯Y AKCJI
//	for (int i = 0; i < 3; i++) {
//	
//		if(!inwestor1.getlWykupioneAkcje().isEmpty()) {
//			int indeksLosowejPosiadanejAkcji = random.nextInt( inwestor1.getlWykupioneAkcje().size() );//indeks akcji na listach:
//			//this.getlWykupioneAkcje()  i  this.getlLiczbaAkcji()
//				System.out.println("Wylosowany indeks akcji spoœród posiadanych: "+indeksLosowejPosiadanejAkcji);
//			Akcja losowaAkcja = inwestor1.getlWykupioneAkcje().get(indeksLosowejPosiadanejAkcji);
//				System.out.println("Indeks odpowiada akcji: "+losowaAkcja);
//			int liczbaAkcjiDoSprzedania = random.nextInt(inwestor1.getlLiczbaAkcji().get(indeksLosowejPosiadanejAkcji));// od 0 do liczby posiadanych akcji
//				System.out.println("Inwestor sprzeda tyle akcji: "+liczbaAkcjiDoSprzedania);
//			Spolka spolkaLosowejAkcji = null;					
//			for (Spolka spolka : Gielda.listaWsyztskichSpolek) {//znajdowanie spó³ki której to s¹ akcje
//				if(spolka.getAkcja().equals(losowaAkcja)) {
//					spolkaLosowejAkcji = spolka;
//						System.out.println("Akcje nale¿¹ do spó³ki: "+spolka);
//					break;
//				}
//			}
//			int indeksSpolki = Gielda.listaWsyztskichSpolek.indexOf(spolkaLosowejAkcji);//indeks spó³ki na Gielda.listaWsyztskichSpolek
//				System.out.println("Indeks tej spó³ki na liœcie wszytskich spó³ek: "+indeksSpolki);
//			//sprzeda¿
//			inwestor1.setBudzet( inwestor1.getBudzet() + liczbaAkcjiDoSprzedania*losowaAkcja.getAktualnyKurs() );
//				System.out.println("Bud¿et wzrós³ o "+liczbaAkcjiDoSprzedania*losowaAkcja.getAktualnyKurs()+"  i wynosi "+inwestor1.getBudzet());
//			
//				System.out.println("Przed sprzeda¿¹ na rynku spó³ka mia³a akcji: "+spolkaLosowejAkcji.getLiczbaAkcji());
//			Gielda.listaWsyztskichSpolek.get(indeksSpolki).setLiczbaAkcji( spolkaLosowejAkcji.getLiczbaAkcji()+liczbaAkcjiDoSprzedania );
//				System.out.println("Po sprzeda¿y: "+spolkaLosowejAkcji.getLiczbaAkcji());
//			int liczbaAkcji = inwestor1.getlLiczbaAkcji().get(indeksLosowejPosiadanejAkcji);
//				System.out.println("Przed sprzeda¿¹ Inwestor mia³ akcji: "+liczbaAkcji);
//			int nowaLiczbaAkcji = liczbaAkcji-liczbaAkcjiDoSprzedania;
//			inwestor1.getlLiczbaAkcji().set(indeksLosowejPosiadanejAkcji, nowaLiczbaAkcji);//ustawia now¹ liczbê akcji na stara liczba - sprzedane akcje
//				System.out.println("Po sprzeda¿y: "+inwestor1.getlLiczbaAkcji().get(indeksLosowejPosiadanejAkcji));
//			if(nowaLiczbaAkcji==0) {
//					System.out.println("Inwestor sprzeda³ wszytskie akcje spó³ki");
//				inwestor1.getlWykupioneAkcje().remove(indeksLosowejPosiadanejAkcji);
//				inwestor1.getlLiczbaAkcji().remove(indeksLosowejPosiadanejAkcji);
//			}
//		}
//		
//		System.out.println("Akcje tego inwestora: ");
//		if(!inwestor1.getlWykupioneAkcje().isEmpty()) {
//			for (int j = 0; j<inwestor1.getlWykupioneAkcje().size(); j++ ) {
//				System.out.println("Akcja: "+inwestor1.getlWykupioneAkcje().get(j)+"  Liczba sztuk: "+inwestor1.getlLiczbaAkcji().get(j));						
//			}
//		}else System.out.println("Inwestor nie posiada Akcji");
//		System.out.println("");
//	}
//		
//		
//		
//		//TEST KUPNA WALUTY	
//	for (int i = 0; i < 3; i++) {//3 razy
//		
//		int indeksLosowejWaluty = random.nextInt( RynekWalut.listaWszytskichWalut.size() );
//		Waluta wylosowanaWaluta = RynekWalut.listaWszytskichWalut.get(indeksLosowejWaluty);
//			System.out.println("Indeks losowej waluty: "+indeksLosowejWaluty+"  Odpowiada walucie: "+wylosowanaWaluta);
//		double budzet25procent = inwestor1.getBudzet()*0.25;
//			System.out.println("25% bud¿etu = "+budzet25procent);
//		double kwotaPrzeznaczonaNaKupno = Swiat.zaokraglijDo2miejsc( Math.random()*(budzet25procent - wylosowanaWaluta.getCenaSprzedazy())
//				+wylosowanaWaluta.getCenaSprzedazy());//liczba od ceny 1 sztuki waluty do 25% bud¿etu
//			System.out.println("Kwota przeznaczona na kupno: "+kwotaPrzeznaczonaNaKupno);
//		int liczbaWalutDoKupienia = (int)(kwotaPrzeznaczonaNaKupno/wylosowanaWaluta.getCenaSprzedazy());
//			System.out.println("Kupi siê za ni¹: "+liczbaWalutDoKupienia+" szt waluty po cenie: "+wylosowanaWaluta.getCenaSprzedazy());
//		//kupno
//			System.out.println("Bud¿et przed zakupem: "+inwestor1.getBudzet());
//		inwestor1.setBudzet(inwestor1.getBudzet() - liczbaWalutDoKupienia*wylosowanaWaluta.getCenaSprzedazy());//zap³ata	
//			System.out.println("Bud¿et po zakupie: "+inwestor1.getBudzet());
//		
//		//sprawdzanie, czy inwestor ma ju¿ tê walutê
//		boolean czyInwestorMaJuzTakaWalute = false;
//		for (Waluta w : inwestor1.getlWykupioneWaluty()) {
//			if(wylosowanaWaluta.equals(w)) {//je¿eli ma
//					System.out.println("Inwestor ma ju¿ tê walutê");
//				czyInwestorMaJuzTakaWalute = true;
//				int indeksWalutyUInwestora = inwestor1.getlWykupioneWaluty().indexOf(w);
//					System.out.println("Indeks waluty u inwestora: "+indeksWalutyUInwestora+"  Dotychczasowa liczba szt waluty:"+
//					inwestor1.getlLiczbaWalut().get(indeksWalutyUInwestora));
//				int nowaLiczbaWalut = inwestor1.getlLiczbaWalut().get(indeksWalutyUInwestora) + liczbaWalutDoKupienia;//oblicza l. waluty
//				inwestor1.getlLiczbaWalut().set(indeksWalutyUInwestora, nowaLiczbaWalut);//ustala now¹ liczbê waluty
//					System.out.println("Nowa liczba szt waluty: "+inwestor1.getlLiczbaWalut().get(indeksWalutyUInwestora));
//				break;
//			}
//		}						
//		if(czyInwestorMaJuzTakaWalute==false) {//je¿eli nie ma jeszcze takiej waluty, to dodaje element do listy
//				System.out.println("Inwestor nie ma jeszcze takiej waluty, dodanie do listy");
//			inwestor1.getlWykupioneWaluty().add(wylosowanaWaluta);
//			inwestor1.getlLiczbaWalut().add(liczbaWalutDoKupienia);
//		}
//		
//		System.out.println("Waluty tego inwestora: ");
//		if(!inwestor1.getlWykupioneWaluty().isEmpty()) {
//			for (int j = 0; j<inwestor1.getlWykupioneWaluty().size(); j++ ) {
//				System.out.println("Waluta: "+inwestor1.getlWykupioneWaluty().get(j)+"  Liczba sztuk: "+inwestor1.getlLiczbaWalut().get(j));						
//			}
//		}else System.out.println("Inwestor nie posiada Walut");
//		System.out.println("");
//	}
//	
//	//TEST SPRZEDA¯Y WALUTY
//	if(!inwestor1.getlWykupioneWaluty().isEmpty()) {
//		int indeksLosowejPosiadanejWaluty = random.nextInt( inwestor1.getlWykupioneWaluty().size() );//indeks waluty na listach:
//			System.out.println("indeksLosowejPosiadanejWaluty: "+indeksLosowejPosiadanejWaluty);
//		//this.getlWykupioneWaluty()  i  this.getlLiczbaWalut()
//		Waluta losowaWaluta = inwestor1.getlWykupioneWaluty().get(indeksLosowejPosiadanejWaluty);
//			System.out.println("Wylosowana waluta: "+losowaWaluta);
//		int liczbaWalutyDoSprzedania = random.nextInt(inwestor1.getlLiczbaWalut().get(indeksLosowejPosiadanejWaluty));// od 0 do liczby posiadanych akcji
//			System.out.println("Tyle waluty zostanie sprzedanej: "+liczbaWalutyDoSprzedania+"  po kursie "+losowaWaluta.getCenaKupna());
//		//sprzeda¿
//			System.out.println("Bud¿et przed sprzeda¿¹: "+inwestor1.getBudzet()+"  sprzedano za "+liczbaWalutyDoSprzedania*losowaWaluta.getCenaKupna());
//		inwestor1.setBudzet( inwestor1.getBudzet() + liczbaWalutyDoSprzedania*losowaWaluta.getCenaKupna() );
//			System.out.println("Bud¿et po sprzeda¿y: "+inwestor1.getBudzet());
//		int liczbaWaluty = inwestor1.getlLiczbaWalut().get(indeksLosowejPosiadanejWaluty);
//			System.out.println("Sztuk waluty przed sprzeda¿¹: "+liczbaWaluty);
//		int nowaLiczbaWaluty = liczbaWaluty-liczbaWalutyDoSprzedania;
//		inwestor1.getlLiczbaWalut().set(indeksLosowejPosiadanejWaluty, nowaLiczbaWaluty);//ustawia now¹ liczbê waluty na stara liczba-sprzedane
//			System.out.println("I po sprzeda¿y: "+inwestor1.getlLiczbaWalut().get(indeksLosowejPosiadanejWaluty));
//		if(nowaLiczbaWaluty==0) {
//				System.out.println("Sprzedano ca³¹ walutê");
//			inwestor1.getlWykupioneWaluty().remove(indeksLosowejPosiadanejWaluty);
//			inwestor1.getlLiczbaWalut().remove(indeksLosowejPosiadanejWaluty);
//		}
//	}
//	System.out.println("Waluty tego inwestora: ");
//	if(!inwestor1.getlWykupioneWaluty().isEmpty()) {
//		for (int j = 0; j<inwestor1.getlWykupioneWaluty().size(); j++ ) {
//			System.out.println("Waluta: "+inwestor1.getlWykupioneWaluty().get(j)+"  Liczba sztuk: "+inwestor1.getlLiczbaWalut().get(j));						
//		}
//	}else System.out.println("Inwestor nie posiada Walut");
//	System.out.println("");
//	
//	
//	//TEST KUPNA SUROWCA
//	for (int i = 0; i < 3; i++) {
//		
//		int indeksLosowegoSurowca = random.nextInt( RynekSurowcow.listaWszystkichSurowcow.size() );
//		Surowiec wylosowanySurowiec = RynekSurowcow.listaWszystkichSurowcow.get(indeksLosowegoSurowca);
//			System.out.println("Wylosowany do kupna surowiec: "+wylosowanySurowiec);
//		
//		//przeliczenie waluty (wartoœæ surowca podana jest w konkretnej walucie)
//		Waluta walutaSurowca = wylosowanySurowiec.getWaluta();
//		double wartoœæSurowca = wylosowanySurowiec.getAktualnaWartosc()*walutaSurowca.getCenaSprzedazy();//wartoœæ surowca w walucie rynkowej
//			System.out.println("Waluta surowca: "+walutaSurowca+"  wartoœæ surowca po przewalutowaniu: "+wartoœæSurowca);
//		
//		double budzet25procent = inwestor1.getBudzet()*0.25;
//		double kwotaPrzeznaczonaNaKupno = Swiat.zaokraglijDo2miejsc( Math.random()*(budzet25procent - wartoœæSurowca)+wartoœæSurowca);
//			System.out.println("Bud¿et inwestora: "+inwestor1.getBudzet()+"  25%: "+budzet25procent);
//
//		int liczbaSurowcaDoKupienia = (int)(kwotaPrzeznaczonaNaKupno/wartoœæSurowca);
//			System.out.println("Liczba surowca do kupienia: "+liczbaSurowcaDoKupienia);
//		//kupno
//		inwestor1.setBudzet(inwestor1.getBudzet()-liczbaSurowcaDoKupienia*wartoœæSurowca);	
//			System.out.println("Koszt zakupu: "+liczbaSurowcaDoKupienia*wartoœæSurowca+"  Bud¿et po zakupie: "+inwestor1.getBudzet());
//		//sprawdzanie, czy inwestor ma ju¿ ten surowiec
//		boolean czyInwestorMaJuzTenSurowiec = false;
//		for (Surowiec s : inwestor1.getlWykupioneSurowce()) {//STWORZYÆ LISTÊ SUROWCÓW
//			if(wylosowanySurowiec.equals(s)) {//je¿eli ma
//					System.out.println("Inwestor ma ju¿ ten surowiec");
//				czyInwestorMaJuzTenSurowiec = true;
//				int indeksSurowcaUInwestora = inwestor1.getlWykupioneSurowce().indexOf(s);
//				int nowaLiczbaSurowca = inwestor1.getlLiczbaSurowców().get(indeksSurowcaUInwestora) + liczbaSurowcaDoKupienia;//oblicza l. surowca
//				inwestor1.getlLiczbaSurowców().set(indeksSurowcaUInwestora, nowaLiczbaSurowca);//ustala now¹ liczbê surowca
//				break;
//			}
//		}						
//		if(czyInwestorMaJuzTenSurowiec==false) {//je¿eli nie ma jeszcze takiej waluty, to dodaje element do listy
//				System.out.println("Inwestor nie ma surowca, dodanie do listy");
//			inwestor1.getlWykupioneSurowce().add(wylosowanySurowiec);
//			inwestor1.getlLiczbaSurowców().add(liczbaSurowcaDoKupienia);
//		}
//		System.out.println("Surowce tego inwestora: ");
//		if(!inwestor1.getlWykupioneSurowce().isEmpty()) {
//			for (int j = 0; j<inwestor1.getlWykupioneSurowce().size(); j++ ) {
//				System.out.println("Surowiec: "+inwestor1.getlWykupioneSurowce().get(j)+"  Liczba sztuk: "+inwestor1.getlLiczbaSurowców().get(j));						
//			}
//		}else System.out.println("Inwestor nie posiada Surowcow");
//		System.out.println("");
//		
//		
//	}
//	
//	System.out.println("marza= "+g.getMarza()+" ;  123,5 x (1+marza) = "+123.5*(1+g.getMarza()));
	

	}//koniec main'a

}
