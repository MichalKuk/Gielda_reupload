package main.java;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Inwestor implements Serializable {

	private String imie;
	private String nazwisko;
	private List<Akcja> lWykupioneAkcje;
	private List<Integer> lLiczbaAkcji;// dla aktywa lwykupioneAktywa[i], obiekt listy lLiczbaAktywow[i] przechowuje liczbê posiadanych "sztuk" tego aktywa
	
	private List<Waluta> lWykupioneWaluty;
	private List<Integer> lLiczbaWalut;
	
	private List<Surowiec> lWykupioneSurowce;
	private List<Integer> lLiczbaSurowcow;
	
	public Inwestor(String imie, String nazwisko) {
		this.imie=imie;
		this.nazwisko=nazwisko;
		lWykupioneAkcje = new LinkedList<>();
		lLiczbaAkcji= new LinkedList<>();
		lWykupioneWaluty = new LinkedList<>();
		lLiczbaWalut = new LinkedList<>();
		lWykupioneSurowce = new LinkedList<>();
		lLiczbaSurowcow = new LinkedList<>();
	}
	public Inwestor() {
		lWykupioneAkcje = new LinkedList<>();
		lLiczbaAkcji= new LinkedList<>();
		lWykupioneWaluty = new LinkedList<>();
		lLiczbaWalut = new LinkedList<>();
		lWykupioneSurowce = new LinkedList<>();
		lLiczbaSurowcow = new LinkedList<>();
	}
	
	
//	public void kupAktywo(Aktywo aktywo, int liczba) {}//definicja w podklasach
	
//	public void sprzedajAktywo(Aktywo aktywo, int liczba) {}//definicja w podklasach
	
//	public void wyswietlWykupioneAktywa() {
//		for (Aktywo aktywo : lwykupioneAktywa) {
//			System.out.println(aktywo.getNazwa()+"\n");
//		}
//	}

	
	public static String[] bazaImion = {"Wildo","Bandolee","Comeret","Isumar","Redhal","Lycan","Ketim","Aric","Nadino","Marris","Grimson",
		    "Dan","Baldlay","Escmeri","Sanchellda","Ferummann","Cygrim","Sutom","Diccome","Adeltol","Ferdfred","Thonas","Uwaru","Geadel",
		    "Gifuhew","Brandchellead","Peald","Ceovid","Fledtine","Crowsam","Retcwen","Vidmann","Sungven","Tondra","Lenjen","Manmark",
		    "Wen'das","Ferthever","Herela","Aldcas","Bet","Ingni","Lin'vid","Sananne","Forthga","Eschew","Burret","Zasa","Anceo","Aldward",
		    "Barand","Don","Riani","Sa","Livid","Wilfrea","Grim-sam","Leris","Ne","Burdon","Lauleof","Bertham","Redja","Dracrow","Thasdon",
		    "Fridferth","Misha","Cuthbras","Sanegel","Wardregin","Theodhere","Lada","Josa","Retbeo","Sungta","Comecen","Magar","Hereniel",
		    "Loclee","Anna"};
	public static String[] bazaNazwisk = {"Kaliñski","Kasza","Jarzêbski","Jachimowski","Stefanowski","Dudziñski","Dembiñski","Goraj","Karwat",
			"Piaskowski","Krogulec","Jaworski","Prus","Niewiarowski","Grochulski","Burczyk","Ziemski","Buda","Markiewicz","Æwikliñski",
			"Woch","Waga","£obodziñski","Chrobok","Richter","Bereza","Zaborski","Adler","Œlusarek","Duchnowski","Gdaniec","Mitka","Kuraœ",
			"Wójcik","Dudziak","Kaca³a","Markuszewski","Bobek","Talarczyk","Fijo³ek","Kalinowski","Ligocki","Biskupski","Foltyn","Szczuka",
			"Kie³b","Gmyrek","Dziurzyñski","Otto","Bolek","Roso³owski","Szreder","Tymoszuk","Jaskólski","Walczuk","Sitkowski","Ma³kiewicz",
			"Karolczak","Sójka","Bargiel","Dziêgiel","Polkowski","Iwañski","Brzozowski","Talarek","Tobolski","Staszak","Grochowina","Nowok",
			"Parol","Ska³ecki","Lenard","W³odkowski","Sobkowicz","Wysoczañski","Danilewicz","Wasik","Dworak","P¹czek","S³upski","Magdziak",
			"Ginter","Wojnar","Krok","Kie³kowski","Zachariasz","Rusiecki","Jêdrzejewski","O¿arowski","Furmaniak","Siwik","Mielnicki",
			"Szychowski","Langer","S³oma","Szczepaniak","B³achnio","Strzy¿ewski","Komorek","Dobosz","Danecki","Próchniak","Gawrych",
			"Naskrêt","Topolewski","Deja","Obrêbski","Harasim","Palmowski","Bystrzycki","Dyga","Buchwald"};
	
	
	//gettery settery
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public List<Akcja> getlWykupioneAkcje() {
		return lWykupioneAkcje;
	}
	public void setlWykupioneAkcje(List<Akcja> lWykupioneAkcje) {
		this.lWykupioneAkcje = lWykupioneAkcje;
	}
	public List<Integer> getlLiczbaAkcji() {
		return lLiczbaAkcji;
	}
	public void setlLiczbaAkcji(List<Integer> lLiczbaAkcji) {
		this.lLiczbaAkcji = lLiczbaAkcji;
	}
	public List<Waluta> getlWykupioneWaluty() {
		return lWykupioneWaluty;
	}
	public void setlWykupioneWaluty(List<Waluta> lWykupioneWaluty) {
		this.lWykupioneWaluty = lWykupioneWaluty;
	}
	public List<Integer> getlLiczbaWalut() {
		return lLiczbaWalut;
	}
	public void setlLiczbaWalut(List<Integer> lLiczbaWalut) {
		this.lLiczbaWalut = lLiczbaWalut;
	}
	public List<Surowiec> getlWykupioneSurowce() {
		return lWykupioneSurowce;
	}
	public void setlWykupioneSurowce(List<Surowiec> lWykupioneSurowce) {
		this.lWykupioneSurowce = lWykupioneSurowce;
	}
	public List<Integer> getlLiczbaSurowców() {
		return lLiczbaSurowcow;
	}
	public void setlLiczbaSurowców(List<Integer> lLiczbaSurowców) {
		this.lLiczbaSurowcow = lLiczbaSurowców;
	}

	
	
}
