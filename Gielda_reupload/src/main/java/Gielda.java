package main.java;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Gielda extends Rynek implements Serializable {
	private String waluta;
	private List<Indeks> listaIndeksow;
	
	public static List<Spolka> listaWsyztskichSpolek;// = new LinkedList<>();

	public Gielda(String nazwa, String kraj, String miasto, String adres, double marza, String waluta) {
		super(nazwa, kraj, miasto, adres, marza);
		this.waluta=waluta;
		listaWsyztskichSpolek = new LinkedList<>();
		listaIndeksow = new LinkedList<>();
	}
	
	public Gielda() {
		listaWsyztskichSpolek = new LinkedList<>();
		listaIndeksow = new LinkedList<>();
	};
	
	public void wyswietlListeIndeksow() {
		for (Indeks indeks : listaIndeksow) {
			System.out.println(indeks.getNazwa()+"\n");
		}
	}
	public void wyswietlWszystkieAkcje() {
		for (Spolka spolka : listaWsyztskichSpolek) {
			if(spolka.getLiczbaAkcji()>0) {
				System.out.println(spolka.getNazwa()+"  "+spolka.getLiczbaAkcji()+" akcji");
			}
		}
	}
	
	//getter settery
	public String getWaluta() {
		return waluta;
	}	
	public void setWaluta(String waluta) {
		this.waluta = waluta;
	}	
	public List<Indeks> getListaIndeksow() {
		return listaIndeksow;
	}
	public void setListaIndeksow(List<Indeks> listaIndeksow) {
		this.listaIndeksow = listaIndeksow;
	}
	public static List<Spolka> getListaWsyztskichSpolek() {
		return listaWsyztskichSpolek;
	}
	public static void setListaWsyztskichSpolek(List<Spolka> listaWsyztskichSpolek) {
		Gielda.listaWsyztskichSpolek = listaWsyztskichSpolek;
	}
		
}
