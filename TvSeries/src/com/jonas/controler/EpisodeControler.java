package com.jonas.controler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jonas.connection.SessionFactorySingelton;
import com.jonas.entity.Categories;
import com.jonas.entity.Episodes;
import com.jonas.entity.Series;
import com.jonas.view.View;

public class EpisodeControler {

	
	private Scanner scan;
	public SessionFactory sessionFactory =
			SessionFactorySingelton.getInstance().getSessionFactory();
	private Session session;
	private View view = new View();
	
	public EpisodeControler() {
	}
	
	public void deleteEpisodeById(int id) {
		openSessionFlow();
		Episodes ep = readOneEpisodeById(id);
		view.deleteObject(ep);
		session.delete(ep);
		
		closeSessionFlow(session);
	}
	public void updateEpisodeById(int id) {
		scan = new Scanner(System.in);
		
		openSessionFlow();
		Episodes ep = readOneEpisodeById(id);
		
		view.updateObject(ep);
		
		view.enterName();
		String episodeName = scan.nextLine();
		ep.setEpisodeName(episodeName);
		
		view.seasonNumber();
		int seasonNumber = scan.nextInt();
		ep.setSeasonNumber(seasonNumber);
		
		view.episodeNumber();
		int episodeNumber = scan.nextInt();
		ep.setEpisodeNumber(episodeNumber);
	
		view.enterDate();
		int year = scan.nextInt();
		int month = scan.nextInt();
		int day = scan.nextInt();
		
		LocalDate date = LocalDate.of(year, month, day);
		ep.setReleaseDate(date);
		
		closeSessionFlow(session);
	}

	public void createEpisode(SeriesControler seriesCon) {
		scan = new Scanner(System.in);
		Episodes episodes = new Episodes();
		
		
			openSessionFlow();
			
			seriesCon.readAllSeries();
			view.belongsToSeries();
			int id = scan.nextInt();
			Series belongTo = session.find(Series.class, id);
			episodes.setBelongTo(belongTo);
			scan.nextLine();
			
			view.enterName();
			String episodeName = scan.nextLine();
			episodes.setEpisodeName(episodeName);
			
			view.seasonNumber();
			int seasonNumber = scan.nextInt();
			episodes.setSeasonNumber(seasonNumber);
			
			view.episodeNumber();
			int episodeNumber = scan.nextInt();
			episodes.setEpisodeNumber(episodeNumber);
			
			view.enterDate();
			int year = scan.nextInt();
			int month = scan.nextInt();
			int day = scan.nextInt();
			
			LocalDate date = LocalDate.of(year, month, day);
			episodes.setReleaseDate(date);
			
			session.save(episodes);
			
			
			closeSessionFlow(session);
			scan.close();	
	}
	
	private Episodes readOneEpisodeById(int id) {
		Episodes ep = session.find(Episodes.class, id);
		view.space();
		return ep;
	}
	
	public void readAllEpisodesBelonging(int id) {
		List<Episodes> list = new ArrayList<>();
		openSessionFlow();
		
		Series series = session.get(Series.class, id);
		
		list = (List<Episodes>) series.getEpisode();
		
		view.space();
		//view.printToString(list);
		view.printListToString(list);
		view.space();
		
		closeSessionFlow(session);		
	}
	public void readAllEpisodes() {
		List<Episodes> list = new ArrayList<>();
		openSessionFlow();
		
		
		list = session.createQuery("from episodes").getResultList();
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

