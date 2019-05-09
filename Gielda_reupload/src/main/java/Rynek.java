package main.java;

import java.io.Serializable;

public abstract class Rynek implements Serializable {
	private String nazwa;
	private String kraj;
	private String miasto;
	private String adres;
	private double marza;//mar¿a
	
	//static public List<Aktywo> listaWszystkichAktywow = Collections.emptyList();//jedna lista dla wszytskich klas, deklarowana jako pusta
	
	
	public Rynek(String nazwa, String kraj, String miasto, String adres, double marza) {
		this.nazwa=nazwa;
		this.kraj=kraj;
		this.miasto=miasto;
		this.adres=adres;
		this.marza=marza;
	}
	
	public Rynek() {};
	
//	public void wyswietlListeAktywow() {
//		for (Aktywo a : listaWszystkichAktywow) {
//			System.out.println(a.getNazwa()+"\n");
//		}
//	}
	
	
	//gettery settery
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getKraj() {
		return kraj;
	}
	public void setKraj(String kraj) {
		this.kraj = kraj;
	}
	public String getMiasto() {
		return miasto;
	}
	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public double getMarza() {
		return marza;
	}
	public void setMarza(double marza) {
		this.marza = marza;
	}
	
//	public static List<Aktywo> getListaWszystkichAktywow() {
//		return listaWszystkichAktywow;
//	}
//	public static void setListaWszystkichAktywow(List<Aktywo> listaWszystkichAktywow) {
//		Rynek.listaWszystkichAktywow = listaWszystkichAktywow;
//	}

	
	
}
