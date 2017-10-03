package com.jonas.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity (name="episodes")
public class Episodes implements Serializable{
	
	private static final long serialVersionUID = 5785311203467786062L;

	@Id
	@Column(name="episode_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer episodeId;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE
						,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="belong_to")
	private Series belongTo;
	
	@Column(name="episode_name")
	private String episodeName;
	
	@Column(name="season_num")
	private Integer seasonNumber;
	
	@Column(name="episode_num")
	private Integer episodeNumber;
	
	@Column(name="release_date")
	private LocalDate releaseDate;
	
	
	
	public Episodes(){
	}

	public int getEpisodeId() {
		return episodeId;
	}
	
	public void setEpisodeId(int episodeId) {
		this.episodeId = episodeId;
	}

	public Series getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(Series belongTo) {
		this.belongTo = belongTo;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		
		return "idNr: " + episodeId + " | of: " + belongTo + " | Name: " + episodeName + " | s"
				+ seasonNumber + "e" + episodeNumber + " | Air Date: " + releaseDate;
		
	}
	
	
}
