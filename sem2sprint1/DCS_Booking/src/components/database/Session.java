package components.database;

import java.util.ArrayList;

public class Session {

	private String id;
	private boolean compulsary;
	private ArrayList<TimetableSlot> slots;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isCompulsary() {
		return compulsary;
	}
	public void setCompulsary(boolean compulsary) {
		this.compulsary = compulsary;
	}
	public ArrayList<TimetableSlot> getSlots() {
		return slots;
	}
	public void setSlots(ArrayList<TimetableSlot> slots) {
		this.slots = slots;
	}
	
	
	
}
