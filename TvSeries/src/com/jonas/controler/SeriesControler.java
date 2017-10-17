package com.jonas.controler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.jonas.connection.SessionFactorySingelton;
import com.jonas.entity.Series;
import com.jonas.view.View;

public class SeriesControler {

	
	private Scanner scan;
	public SessionFactory sessionFactory =
			SessionFactorySingelton.getInstance().getSessionFactory();
	private Session session;
	private View view = new View();
	
	public SeriesControler() {
	}
	public void deleteCategorieById(int id) {
		scan = new Scanner(System.in);
		
		openSessionFlow();
		Series se = readOneSeriesById(id);
		view.deleteObject(se);
		session.delete(se);
		
		scan.close();
		closeSessionFlow(session);
	}

	public void updateCategoriesById(int id) {
		scan = new Scanner(System.in);
		
		openSessionFlow();
		Series se = readOneSeriesById(id);
		
		view.updateObject(se);
		
		view.enterName();
		String seriesName = scan.nextLine();
		se.setSeriesName(seriesName);
		
		view.enterRating();
		int myRating = scan.nextInt();
		se.setMyRating(myRating);
		
		view.enterDate();
		int startYear = scan.nextInt();
		int startMonth = scan.nextInt();
		int startDay = scan.nextInt();
		
		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		se.setStartDate(startDate);
		
		view.enterDate();
		int endYear = scan.nextInt();
		int endMonth = scan.nextInt();
		int endDay = scan.nextInt();
		
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
		se.setEndDate(endDate);
		
		scan.close();
		closeSessionFlow(session);
	}

	public void createSeries() {
		scan = new Scanner(System.in);
		Series series = new Series();
		
		openSessionFlow();
			
			view.enterName();
			String seriesName = scan.nextLine();
			series.setSeriesName(seriesName);
			
			view.enterRating();
			int myRating = scan.nextInt();
			series.setMyRating(myRating);
			
			view.enterDate();
			int startYear = scan.nextInt();
			int startMonth = scan.nextInt();
			int startDay = scan.nextInt();
			
			LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
			series.setStartDate(startDate);
			
			view.enterDate();
			int endYear = scan.nextInt();
			LocalDate endDate = setNullDate(endYear);
			
			series.setEndDate(endDate);
			
			session.save(series);
			
			System.out.println(series.getSeriesName());
			closeSessionFlow(session);
			scan.close();	
	}
	
	public LocalDate setNullDate(int endYear) {
		LocalDate endDate = null;
		if(!(endYear == 0)) {
			int endMonth = scan.nextInt();
			int endDay = scan.nextInt();
			
			endDate = LocalDate.of(endYear, endMonth, endDay);
		
		}else if(endYear == 0){
			endDate = null;
		}
		
		return endDate;
	}
	public Series readOneSeriesById(int id) {
		Series series = session.get(Series.class, id);
		view.space();
		return series;
	}
	
	public void readAllSeries() {
		List<?> list = new ArrayList<>();
		openSessionFlow();
		
		Query<?> query = session.createQuery("from series");
		list = query.list();
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

