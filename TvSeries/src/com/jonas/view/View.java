package com.jonas.view;

import java.util.Collection;

public class View {
	
	public View() {
	}
	
	//Skriver ut generic objekt/s toString
	public void printToString(Object ob) {
		System.out.println(ob.toString());
	}
	public void printListToString(Collection<?> list) {
		System.out.println("All ");
		
		for(Object ob: list) {
			printToString(ob);
		}
	}
	
	//åter användings
	public void areYouShore() {
		System.out.println("Are you shore y/n");
	}
	
	public void allCrudChoises() {
		System.out.println("1.C reate");
		System.out.println("2.R ead");
		System.out.println("3.U pdate");
		System.out.println("4.D elete");
		
	}
	
	public void allClassOptions() {
		System.out.println("1: Categories \n2: Series \n3: Episodes");
	}
	public void allUppdateOptions() {
		System.out.println("1: Categories \n2: Series \n3: Episodes\n4: Category to a Series");
	}
	
	public void space() {
		System.out.println("\n\n");
	}
	
	public void youHaveChosen(String choise) {
		System.out.println("Du har valt att "+ choise);
	}
	
	public void updateObject(Object o) {
		System.out.println(o.toString());
		System.out.println("Change these values");
	}
	
	public void deleteObject(Object o) {
		System.out.println("Deleting "+ o.toString());
	}
	
	public void enterDate() {
		space();
		System.out.println("Put in 0 If the series haven't ended yet\nOr follo this -> Year enter\nmonth enter,\nday enter");
	}
	
	public void enterRating() {
		space();
		System.out.println("Your Rating enter");
	}
	
	public void enterName() {
		space();
		System.out.println("Name of the thing");
	}

	public void belongsToSeries() {
		System.out.println("write series id");
	}

	public void seasonNumber() {
		System.out.println("Season number?");
	}

	public void episodeNumber() {
		System.out.println("Episode number?");
	}

	public void choseId() {
		System.out.println("Chose id of: ");
	}
}
