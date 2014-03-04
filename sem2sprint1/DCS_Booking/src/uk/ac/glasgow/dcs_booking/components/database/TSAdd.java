package uk.ac.glasgow.dcs_booking.components.database;

public interface TSAdd {
	/**
	 * Adds a timetable slot to the database for a certain session
	 */
	public void addTS(TimetableSlot t, String sid);
}
