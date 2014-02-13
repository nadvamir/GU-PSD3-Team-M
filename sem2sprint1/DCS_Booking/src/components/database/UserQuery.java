package components.database;

public interface UserQuery {
	/**
	 * Gets an user by unique username from the database
	 */
	public User getUser(String name);
}
