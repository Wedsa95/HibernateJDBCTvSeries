package com.jonas.controler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jonas.connection.SessionFactorySingelton;
import com.jonas.entity.Categories;
import com.jonas.entity.Series;
import com.jonas.view.View;

public class CategoriesContoler {
	
	
	private Scanner scan;
	public SessionFactory sessionFactory =
			SessionFactorySingelton.getInstance().getSessionFactory();
	private  Session session;
	private View view = new View();
	
	
	public CategoriesContoler() {
	}
	public void deleteCategorieById(int id) {
		scan = new Scanner(System.in);
		
		openSessionFlow();
		Categories cat = readOneCatigoriyById(id);
		view.deleteObject(cat);
		session.delete(cat);
		
		scan.close();
		closeSessionFlow(session);
	}
	
	public void addSeriesToCategorie(int id) {
		scan = new Scanner(System.in);
		openSessionFlow();
		
		Series serie = session.get(Series.class, id);
		view.choseId();
		
		readCategoriesSessionFree();
		int index = scan.nextInt();
		Categories category = session.get(Categories.class, index);
		category.addSeries(serie);
		
		session.save(category);
		scan.close();
		closeSessionFlow(session);
	}
	
	public void updateCategoriesById(int id) {
		scan = new Scanner(System.in);
		openSessionFlow();
		
		Categories cat = readOneCatigoriyById(id);
		
		view.updateObject(cat);
		
		String categoryName = scan.nextLine();
		cat.setCategoryName(categoryName);
		
		scan.close();
		closeSessionFlow(session);
	}

	public void createCategories() {
		scan = new Scanner(System.in);
		Categories categories = new Categories();
		
		System.out.println("Skriv in namn på Katigorin");
		String catName = scan.nextLine();
		
			openSessionFlow();
			
			
			categories.setCategoryName(catName);
			
			session.save(categories);
			
			System.out.println(categories.getCategoryName());
			closeSessionFlow(session);
			scan.close();	
	}
	public void readSeriesByCategory(int id) {
		openSessionFlow();
		
		Categories categories = session.get(Categories.class, id);
		Collection<Series> list = categories.getSeries();
		
		view.printListToString(list);
		closeSessionFlow(session);
	}
	
	private Categories readOneCatigoriyById(int id) {
		Categories cat = session.find(Categories.class, id);
		view.space();
		return cat;
	}
	
	public void readCategoriesSessionFree() {
		List<Categories> list = new ArrayList<>();

		list = session.createQuery("from categories").getResultList();
		view.space();
		view.printListToString(list);
		view.space();
	}
	
	public void readAllCatigories() {
		List<Categories> list = new ArrayList<>();
		openSessionFlow();
		
		
		list = session.createQuery("from categories").getResultList();
		view.space();
		view.printListToString(list);
		view.space();
		
		closeSessionFlow(session);		
	}
	
	//Open/close SESSION
	private void openSessionFlow() {
		session = sessionFactory.openSession();
		session.beginTransaction();	
	}
	private void closeSessionFlow(Session session) {
		session.getTransaction().commit();
		session.close();
	}
}
