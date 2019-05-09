package main.java;

import java.io.Serializable;

public class Akcja extends Aktywo implements Serializable {

	private String dataPierwszejWyceny;//wstêpnie, potem typu: Date dataPierwszejWyceny;
	private double kursOtwarcia;
	private double aktualnyKurs;
	private double minKurs;
	private double maxKurs;
	
	public Akcja(String nazwa, String dataPierwszejWyceny, double kursOtwarcia,double aktualnyKurs, double minKurs, double maxKurs) {
		super(nazwa);
		this.dataPierwszejWyceny=dataPierwszejWyceny;
		this.kursOtwarcia=kursOtwarcia;
		this.aktualnyKurs=aktualnyKurs;
		this.minKurs=minKurs;
		this.maxKurs=maxKurs;
	}
	public Akcja() {}
	
	
	public void przeliczKursy(double nowyKurs) {
		//aktualizacja max i min kursu
		aktualnyKurs=nowyKurs;
		if(aktualnyKurs>maxKurs) {maxKurs=aktualnyKurs;}
		else if(aktualnyKurs<minKurs) {minKurs=aktualnyKurs;}
	}

	
	//gettery settery
	public String getDataPierwszejWyceny() {
		return dataPierwszejWyceny;
	}
	public void setDataPierwszejWyceny(String dataPierwszejWyceny) {
		this.dataPierwszejWyceny = dataPierwszejWyceny;
	}
	public double getKursOtwarcia() {
		return kursOtwarcia;
	}
	public void setKursOtwarcia(double kursOtwarcia) {
		this.kursOtwarcia = kursOtwarcia;
	}
	public double getAktualnyKurs() {
		return aktualnyKurs;
	}
	public void setAktualnyKurs(double aktualnyKurs) {
		this.aktualnyKurs = aktualnyKurs;
	}
	public double getMinKurs() {
		return minKurs;
	}
	public void setMinKurs(double minKurs) {
		this.minKurs = minKurs;
	}
	public double getMaxKurs() {
		return maxKurs;
	}
	public void setMaxKurs(double maxKurs) {
		this.maxKurs = maxKurs;
	}
	
	
	
}
