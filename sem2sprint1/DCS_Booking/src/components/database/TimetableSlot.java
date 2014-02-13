package components.database;

import java.util.ArrayList;
import java.util.Date;

public class TimetableSlot {
	
	private Date date;
	private int capacity;
	private String room;
	private ArrayList<User> students;
	private User tutor;
	
	public TimetableSlot(Date date, int capacity, String room) {
		this.date = date;
		this.capacity = capacity;
		this.room = room;
		this.students = new ArrayList<User>();
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	
	public ArrayList<User> getStudents() {
		return students;
	}
	
	public User getTutor() {
		return tutor;
	}
	public void setTutor(User t) {
		tutor = t;
	}
	

}
