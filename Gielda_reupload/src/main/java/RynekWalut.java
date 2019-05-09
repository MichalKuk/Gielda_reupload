package main.java;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RynekWalut extends Rynek implements Serializable {

	public static List<Waluta> listaWszytskichWalut;// = new LinkedList<>();
	
	public RynekWalut(String nazwa, String kraj, String miasto, String adres, double marza) {
		super(nazwa, kraj, miasto, adres, marza);
		listaWszytskichWalut = new LinkedList<>();
	}
	public RynekWalut() {
		listaWszytskichWalut = new LinkedList<>();
	};
	
	public void wyswietlWszystkieWaluty() {
		for (Waluta waluta : listaWszytskichWalut) {System.out.println(waluta.getNazwa()+"\n");}
	}

	
	//gettery settery
	public static List<Waluta> getListaWszytskichWalut() {
		return listaWszytskichWalut;
	}
	public static void setListaWszytskichWalut(List<Waluta> listaWszytskichWalut) {
		RynekWalut.listaWszytskichWalut = listaWszytskichWalut;
	}
	
	
}
