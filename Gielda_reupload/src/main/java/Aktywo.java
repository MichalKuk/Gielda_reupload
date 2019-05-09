package main.java;

public abstract class Aktywo {

	private String nazwa;
	
		
	public Aktywo(String nazwa) {
		this.nazwa=nazwa;
	}
	public Aktywo() {}

	public String toString() {
		return this.nazwa;
	}
	

	//gettery settery
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	
	
}
