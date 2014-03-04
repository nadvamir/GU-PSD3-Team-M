package uk.ac.glasgow.dcs_booking.components.database;

import java.util.ArrayList;

public class Session {

	private String id;
	private boolean compulsory;
	private ArrayList<TimetableSlot> slots;
	//0 = one-off, 1 = weekly, 2 = fortnightly
	private int freq;
	
	public Session() {
		id = null;
		compulsory = false;
		slots = new ArrayList<TimetableSlot>();
	}
	
	public void specifyoneoff(){
		this.freq = 0;
	}
	
	public void specifyweekly(){
		this.freq = 1;
	}
	
	public void specifyfortnightly(){
		this.freq = 2;
	}
	
	public Integer getfreq(){
		return freq;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isCompulsory() {
		return compulsory;
	}
	public void setCompulsary(boolean compulsory) {
		this.compulsory = compulsory;
	}
	public ArrayList<TimetableSlot> getSlots() {
		return slots;
	}
	public void setSlots(ArrayList<TimetableSlot> slots) {
		this.slots = slots;
	}
	
	
	
}
