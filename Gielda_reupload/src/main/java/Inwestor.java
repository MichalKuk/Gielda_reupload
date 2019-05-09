package main.java;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Inwestor implements Serializable {

	private String imie;
	private String nazwisko;
	private List<Akcja> lWykupioneAkcje;
	private List<Integer> lLiczbaAkcji;// dla aktywa lwykupioneAktywa[i], obiekt listy lLiczbaAktywow[i] przechowuje liczb� posiadanych "sztuk" tego aktywa
	
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
	public static String[] bazaNazwisk = {"Kali�ski","Kasza","Jarz�bski","Jachimowski","Stefanowski","Dudzi�ski","Dembi�ski","Goraj","Karwat",
			"Piaskowski","Krogulec","Jaworski","Prus","Niewiarowski","Grochulski","Burczyk","Ziemski","Buda","Markiewicz","�wikli�ski",
			"Woch","Waga","�obodzi�ski","Chrobok","Richter","Bereza","Zaborski","Adler","�lusarek","Duchnowski","Gdaniec","Mitka","Kura�",
			"W�jcik","Dudziak","Kaca�a","Markuszewski","Bobek","Talarczyk","Fijo�ek","Kalinowski","Ligocki","Biskupski","Foltyn","Szczuka",
			"Kie�b","Gmyrek","Dziurzy�ski","Otto","Bolek","Roso�owski","Szreder","Tymoszuk","Jask�lski","Walczuk","Sitkowski","Ma�kiewicz",
			"Karolczak","S�jka","Bargiel","Dzi�giel","Polkowski","Iwa�ski","Brzozowski","Talarek","Tobolski","Staszak","Grochowina","Nowok",
			"Parol","Ska�ecki","Lenard","W�odkowski","Sobkowicz","Wysocza�ski","Danilewicz","Wasik","Dworak","P�czek","S�upski","Magdziak",
			"Ginter","Wojnar","Krok","Kie�kowski","Zachariasz","Rusiecki","J�drzejewski","O�arowski","Furmaniak","Siwik","Mielnicki",
			"Szychowski","Langer","S�oma","Szczepaniak","B�achnio","Strzy�ewski","Komorek","Dobosz","Danecki","Pr�chniak","Gawrych",
			"Naskr�t","Topolewski","Deja","Obr�bski","Harasim","Palmowski","Bystrzycki","Dyga","Buchwald"};
	
	
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
	public List<Integer> getlLiczbaSurowc�w() {
		return lLiczbaSurowcow;
	}
	public void setlLiczbaSurowc�w(List<Integer> lLiczbaSurowc�w) {
		this.lLiczbaSurowcow = lLiczbaSurowc�w;
	}

	
	
}
