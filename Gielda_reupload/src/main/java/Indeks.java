package main.java;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Indeks implements Serializable {
	
	private String nazwa;
	private List<Spolka> listaSpolek;//nalezacych do tego indeksu
	private double wynikIndeksu;
	
	public Indeks(String nazwa, double wynikIndeksu) {
		this.nazwa=nazwa;
		this.wynikIndeksu=wynikIndeksu;
		listaSpolek=new LinkedList<>();
	}
	public Indeks() {
		listaSpolek=new LinkedList<>();
	}

	public void wyswietlListeSpolek() {//nalezacych do tego indeksu
		for (Spolka spolka : listaSpolek) {
				System.out.println(spolka.getNazwa()+"\n");
			}
	}
	//przypisywanie/usuwanie spó³ek do/z listy - metody klasy List
	
	
	//gettery settery
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public double getWynikIndeksu() {
		return wynikIndeksu;
	}
	public void setWynikIndeksu(double wynikIndeksu) {
		this.wynikIndeksu = wynikIndeksu;
	}
	public List<Spolka> getListaSpolek() {
		return listaSpolek;
	}
	public void setListaSpolek(List<Spolka> listaSpolek) {
		this.listaSpolek = listaSpolek;
	}
	
	
	
}
