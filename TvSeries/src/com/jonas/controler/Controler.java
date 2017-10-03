package com.jonas.controler;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jonas.connection.SessionFactorySingelton;
import com.jonas.view.View;

public class Controler {
	
	
	public Scanner scan;
	private SessionFactory sessionFactory;
	public Session session;
	public CategoriesContoler categoriesCon = new CategoriesContoler();
	public SeriesControler seriesCon = new SeriesControler();
	public EpisodeControler episodeCon = new EpisodeControler();
	public View view = new View();
	public boolean exitProgram = false;
	
	public Controler() {
		initiateSessionFactory();
		scan = new Scanner(System.in);
		
		
		while(!exitProgram) {
			
			int firstChoise = 0;
			view.allCrudChoises();	
		
			firstChoise = getIntScan();
				
					
			crudChoise(firstChoise);
			
		}
		
		closeSessionFactory();
	}
	
	private int getIntScan() {
		scan = new Scanner(System.in);
		int index = 0;
			if(scan.hasNextInt()) {
				index = scan.nextInt();
			}
		return index;
	}

	private void crudChoise(int firstChoise) {
		
		switch(firstChoise) {
		
		case(1):
			createOption();
			System.out.println(1);
			break;
		case(2):
			readOption();
			System.out.println(2);
			break;
		case(3):
			updateOption();
			System.out.println(3);
			break;
		case(4):
			deleteOption();
			System.out.println(4);
			break;
		default:
			exitProgram = true;
			break;
		}
	}
	//1 = Categories | 2 = Series | 3 = Episodes
	private void createOption() {
		view.allClassOptions();
		scan = new Scanner(System.in);
		int choise = scan.nextInt();
		switch(choise){
		
		case(1):
			categoriesCon.readAllCatigories();
			categoriesCon.createCategories();
			break;
		case(2):
			seriesCon.readAllSeries();
			seriesCon.createSeries();
			break;
		case(3):
			episodeCon.readAllEpisodes();
			episodeCon.createEpisode(seriesCon);
			break;
		}
		
	}
	
	private void readOption() {
		view.allUppdateOptions();
		scan = new Scanner(System.in);
		int id = 0 ;
		int choise = scan.nextInt();
		switch(choise){
		
		case(1):
			categoriesCon.readAllCatigories();
			break;
		case(2):
			seriesCon.readAllSeries();
			break;
		case(3):
			seriesCon.readAllSeries();
			id = scan.nextInt();
			
			episodeCon.readAllEpisodesBelonging(id);
			break;
		case(4):
			categoriesCon.readAllCatigories();
			view.choseId();
			id = scan.nextInt();
			categoriesCon.readSeriesByCategory(id);
			break;
		}
	}
	
	private void updateOption() {
		view.allUppdateOptions();
		scan = new Scanner(System.in);
		
		int id = 0;
		int choise = scan.nextInt();
		
		switch(choise){
		
		case(1):
			categoriesCon.readAllCatigories();
			view.choseId();
			id = scan.nextInt();
			categoriesCon.updateCategoriesById(id);
			break;
		case(2):
			seriesCon.readAllSeries();
			view.choseId();
			id = scan.nextInt();
			seriesCon.updateCategoriesById(id);
			break;
		case(3):
			episodeCon.readAllEpisodes();
			view.choseId();
			id = scan.nextInt();
			episodeCon.updateEpisodeById(id);
			break;
		case(4):
			seriesCon.readAllSeries();
			view.choseId();
			id = scan.nextInt();
			categoriesCon.addSeriesToCategorie(id);
			break;
		}
	}
	
	private void deleteOption() {
		view.allClassOptions();
		scan = new Scanner(System.in);
		int id = 0;
		
		int choise = scan.nextInt();

		switch(choise){
		
		case(1):
			categoriesCon.readAllCatigories();
			view.choseId();
			id = scan.nextInt();
			categoriesCon.deleteCategorieById(id);
			break;
		case(2):
			seriesCon.readAllSeries();
			view.choseId();
			id = scan.nextInt();
			seriesCon.deleteCategorieById(id);
			break;
		case(3):
			episodeCon.readAllEpisodes();
			view.choseId();
			id = scan.nextInt();
			episodeCon.deleteEpisodeById(id);
			break;
		}
	}
	
	//Open/close SESSIONFACTORY
	private void initiateSessionFactory() {
		sessionFactory = SessionFactorySingelton.getInstance().getSessionFactory();
	}

	private void closeSessionFactory() {
		sessionFactory.close();		
	}

}
