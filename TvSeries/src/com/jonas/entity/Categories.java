package com.jonas.entity;

import java.io.Serializable;
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

@Entity(name="categories")
public class Categories implements Serializable{

	private static final long serialVersionUID = 4784813842379715763L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="category_name")
	private String categoryName;
	
	@ManyToMany(fetch= FetchType.LAZY, cascade= 
			{CascadeType.PERSIST, CascadeType.MERGE
			,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="has_categories",
			joinColumns=@JoinColumn(name="category_has_series"),
			inverseJoinColumns=@JoinColumn(name="series_has_category"))
	private Collection<Series> series;
	
	public Categories() {
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Collection<Series> getSeries() {
		return series;
	}

	public void setSeries(Collection<Series> series) {
		this.series = series;
	}

	public void addSeries(Series serie) {
		if(series == null) {
			series = new ArrayList<>();
		}
		series.add(serie);
	}
	
	
	@Override
	public String toString() {
		return "idNr: " + categoryId + " | "+ categoryName;
	}
	
}
