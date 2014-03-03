package uk.ac.glasgow.psd3.components.database;

public interface UserQuery {
	/**
	 * Gets an user by unique username from the database
	 */
	public User getUser(String name);
}
