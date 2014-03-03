package uk.ac.glasgow.psd3.components.database;

import java.util.ArrayList;
import java.util.Date;

public interface TSQuery {
	/**
	 * Gets a timetable slot by the session it is associated with
	 */
	public TimetableSlot getTS(String sid);
	
	public ArrayList<TimetableSlot> getTSBetween(Date start, Date end);
}
