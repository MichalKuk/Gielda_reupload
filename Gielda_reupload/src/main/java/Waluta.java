package main.java;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Waluta extends Aktywo implements Serializable {

	private double cenaKupna;
	private double cenaSprzedazy;
	private List<String> listaKrajow;
	
	public Waluta(String nazwa, double cenaKupna, double cenaSprzedazy) {
		super(nazwa);
		this.cenaKupna=cenaKupna;
		this.cenaSprzedazy=cenaSprzedazy;
		listaKrajow = new LinkedList<>();
	}
	public Waluta() {
		listaKrajow = new LinkedList<>();
	}

	
	public static String bazaKraj�w[] = {"Polska","Albania","Andora","Austria","Belgia","Bia�oru�","Bo�nia i Hercegowina","Bu�garia",
			 "Chorwacja","Czarnog�ra","Czechy","Dania","Estonia","Finlandia","Francja","Gibraltar","Grecja","Guernsey","Hiszpania","Holandia",
			 "Irlandia","Irlandia P�nocna","Islandia","Jersey","Kosowo","Liechtenstein","Litwa","Luksemburg","�otwa","Macedonia","Malta",
			 "Mo�dawia","Monako","Niemcy","Norwegia","Portugalia","Rosja","Rumunia","San Marino","Serbia","S�owacja","S�owenia","Szwajcaria",
			 "Szwecja","Ukraina","Watykan","W�gry","Wielka Brytania","W�ochy","Wyspa Man","Wyspy Owcze"};
	
	//getttery settery
	public double getCenaKupna() {
		return cenaKupna;
	}
	public void setCenaKupna(double cenaKupna) {
		this.cenaKupna = cenaKupna;
	}
	public double getCenaSprzedazy() {
		return cenaSprzedazy;
	}
	public void setCenaSprzedazy(double cenaSprzedazy) {
		this.cenaSprzedazy = cenaSprzedazy;
	}
	public List<String> getListaKrajow() {
		return listaKrajow;
	}
	public void setListaKrajow(List<String> listaKrajow) {
		this.listaKrajow = listaKrajow;
	}
	
	
}
