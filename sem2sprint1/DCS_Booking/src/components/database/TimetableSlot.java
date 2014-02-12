package components.database;

import java.util.Date;

public class TimetableSlot {
	
	private Date date;
	private int capacity;
	private String room;
	
	public TimetableSlot(Date date, int capacity, String room) {
		this.date = date;
		this.capacity = capacity;
		this.room = room;
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
	
	

}
