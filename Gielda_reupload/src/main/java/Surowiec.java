package main.java;

import java.io.Serializable;

public class Surowiec extends Aktywo implements Serializable {

	private String jednostkaHandlowa;
//	private String waluta;
	private Waluta waluta;
	private double aktualnaWartosc;
	private double minWartosc;
	private double maxWartosc;
	
	public Surowiec(String nazwa, String jednostkaHandlowa, Waluta waluta, double aktualnaWartosc,double minWartosc, double maxWartosc) {
		super(nazwa);
		this.jednostkaHandlowa=jednostkaHandlowa;
		this.waluta=waluta;
		this.aktualnaWartosc=aktualnaWartosc;//przy pojawieniu si� nowego surowca min i max wart. s� pocz�tkow� warto�ci� 
		this.minWartosc=minWartosc;
		this.maxWartosc=maxWartosc;
	}
	public Surowiec() {}

	
	public void przeliczWartosci(double nowaWartosc) {//wst�pnie przyjmuje now� aktualn� warto�� jako parametr
		//potem nie b�dzie parametru, a w ciele jaki� algorytm obliczaj�cy aktualn� warto��
		//na podstawie ilo�ci zakup�w tego surowca na rynku
		
		//aktualizacja max i min warto�ci
		aktualnaWartosc=nowaWartosc;
		if(aktualnaWartosc>maxWartosc) {maxWartosc=aktualnaWartosc;}
		else if(aktualnaWartosc<minWartosc) {minWartosc=aktualnaWartosc;}
	}
	
	
	public static String[] bazaJednostekHandlowych = {"miligram","gram","dekagram","kilogram","tona","centymetr","metr","metr kwadratowy",
			"metr sze�cienny","funt","uncja","karat","stopa","cal","mol","mililitr","litr","hektolitr"};
			
			
	//getter settery
	public String getJednostkaHandlowa() {
		return jednostkaHandlowa;
	}
	public void setJednostkaHandlowa(String jednostkaHandlowa) {
		this.jednostkaHandlowa = jednostkaHandlowa;
	}
	
	public Waluta getWaluta() {
		return waluta;
	}
	public void setWaluta(Waluta waluta) {
		this.waluta = waluta;
	}
	
	public double getAktualnaWartosc() {
		return aktualnaWartosc;
	}
	public void setAktualnaWartosc(double aktualnaWartosc) {
		this.aktualnaWartosc = aktualnaWartosc;
	}
	public double getMinWartosc() {
		return minWartosc;
	}
	public void setMinWartosc(double minWartosc) {
		this.minWartosc = minWartosc;
	}
	public double getMaxWartosc() {
		return maxWartosc;
	}
	public void setMaxWartosc(double maxWartosc) {
		this.maxWartosc = maxWartosc;
	}
	
	
}
