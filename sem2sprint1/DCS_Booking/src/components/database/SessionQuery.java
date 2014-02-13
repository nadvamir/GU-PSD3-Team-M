package components.database;

import java.util.ArrayList;

public interface SessionQuery {
	/**
	 * Gets an user by unique username from the database
	 */
	public ArrayList<Session> getSession(String id);
}
