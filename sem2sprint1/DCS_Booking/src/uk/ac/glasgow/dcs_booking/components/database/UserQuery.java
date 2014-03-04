package uk.ac.glasgow.dcs_booking.components.database;

public interface UserQuery {
	/**
	 * Gets an user by unique username from the database
	 */
	public User getUser(String name);
}
