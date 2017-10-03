package com.jonas.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="series")
public class Series implements Serializable{

	private static final long serialVersionUID = 5248489452155258808L;

	@Id
	@Column(name="series_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seriesId;
	
	@Column(name="series_name")
	private String seriesName;
	
	@Column(name="my_rating")
	private int myRating;
	
	
	@Column(name="start_date")
	private LocalDate startDate;
	
		
	@Column(name="end_date")	
	private LocalDate endDate;
	
	@OneToMany(mappedBy="belongTo", cascade= 
			{CascadeType.PERSIST, CascadeType.MERGE
			,CascadeType.DETACH, CascadeType.REFRESH})
	private Collection<Episodes> episodes;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= 
			{CascadeType.PERSIST, CascadeType.MERGE
			,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="has_categories",
			joinColumns=@JoinColumn(name="series_has_category"),
			inverseJoinColumns=@JoinColumn(name="category_has_series"))
	private Collection<Categories> categories;
	
	public Series() {
	}
	
	public int getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	public String getSeriesName() {
		return seriesName;
	}


	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}


	public int getMyRating() {
		return myRating;
	}


	public void setMyRating(int myRating) {
		this.myRating = myRating;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public Collection<Episodes> getEpisode() {
		return episodes;
	}


	public void setEpisode(Collection<Episodes> episodes) {
		this.episodes = episodes;
	}


	public Collection<Categories> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Categories> categories) {
		this.categories = categories;
	}

	public void add(Episodes episode) {
		if(episodes == null) {
			episodes = new ArrayList<>();
		}
		episodes.add(episode);
		
		episode.setBelongTo(this);
	}
	
	public void addCategories(Categories category) {
		if(categories == null) {
			categories = new ArrayList<>();
		}
		categories.add(category);
	}

	@Override
	public String toString() {
		return "idNr: " + seriesId + " | " + seriesName + " | rating: " + myRating + " | Start Date: " + startDate
				+ " | End Date: " + endDate;
	}
	
	
}
