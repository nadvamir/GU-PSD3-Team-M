package uk.ac.glasgow.psd3.components.database;

import java.util.ArrayList;

public class Course {
	
	private String id;
	private String title;
	private ArrayList<Session> sessions;
	
	public Course(String id, String title){
		this.id = id;
		this.title = title;
		this.sessions = new ArrayList<Session>();
	}
	
	public Course(String id, String title, ArrayList<Session> sessions) {
		this.id = id;
		this.title = title;
		this.sessions = sessions;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<Session> getSessions() {
		return sessions;
	}
	public void setSessions(ArrayList<Session> sessions) {
		this.sessions = sessions;
	}
	
	

}
