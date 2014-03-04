package uk.ac.glasgow.dcs_booking.components.database;

public interface SessionAdd {
	/**
	 * Adds a session to the database linked to a given course
	 */
	public void addSession(Session c, String cid);
}
