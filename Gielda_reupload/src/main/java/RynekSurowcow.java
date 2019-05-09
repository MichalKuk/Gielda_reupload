package main.java;

import main.java.Rynek;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RynekSurowcow extends Rynek implements Serializable {

	public static List<Surowiec> listaWszystkichSurowcow;// = new LinkedList<>();
	
	public RynekSurowcow(String nazwa, String kraj, String miasto, String adres, double marza) {
		super(nazwa, kraj, miasto, adres, marza);	
		listaWszystkichSurowcow = new LinkedList<>();
	}
	public RynekSurowcow() {
		listaWszystkichSurowcow = new LinkedList<>();
	};
	
	public void wyswietlWszystkieSurowce() {
		for (Surowiec surowiec : listaWszystkichSurowcow) { System.out.println(surowiec.getNazwa()+"\n");}								
	}
	

	
	
}
