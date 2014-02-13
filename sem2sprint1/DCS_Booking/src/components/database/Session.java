package components.database;

import java.util.ArrayList;

public class Session {

	private String id;
	private boolean compulsory;
	private ArrayList<TimetableSlot> slots;
	
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
