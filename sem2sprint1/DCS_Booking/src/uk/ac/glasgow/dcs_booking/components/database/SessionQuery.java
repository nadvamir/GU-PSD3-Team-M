package uk.ac.glasgow.dcs_booking.components.database;

import java.util.ArrayList;

public interface SessionQuery {
	/**
	 * Gets a session with a given ID
	 */
	public ArrayList<Session> getSession(String id);
}
