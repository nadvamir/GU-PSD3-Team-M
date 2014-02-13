package components.database;

import java.util.ArrayList;

public interface TSQuery {
	/**
	 * Gets a timetable slot by the session it is associated with
	 */
	public ArrayList<TimetableSlot> getTS(String sid);
}
