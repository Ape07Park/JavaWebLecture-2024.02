package ch07_dao.kpop;

import java.time.LocalDate;

public class Kpop {
	private int aid;
	private String name;
	private LocalDate debutDate;
	private int sid;
	private String title;
	private String lyrics;
	
	public Kpop() {}
	
	
	public Kpop(int aid, String name, LocalDate debutDate, String title, String lyrics) {
		super();
		this.aid = aid;
		this.name = name;
		this.debutDate = debutDate;
		this.title = title;
		this.lyrics = lyrics;
	}

	public Kpop(int aid, String name, LocalDate debutDate, int sid, String title, String lyrics) {
		super();
		this.aid = aid;
		this.name = name;
		this.debutDate = debutDate;
		this.sid = sid;
		this.title = title;
		this.lyrics = lyrics;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDebutDate() {
		return debutDate;
	}

	public void setDebutDate(LocalDate debutDate) {
		this.debutDate = debutDate;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	@Override
	public String toString() {
		return "Kpop [aid=" + aid + ", name=" + name + ", debutDate=" + debutDate + ", sid=" + sid + ", title=" + title
				+ ", lyrics=" + lyrics + "]";
	}
	
	
}	
