package components.database;

public interface CourseQuery {
	/**
	 * Gets an user by unique username from the database
	 */
	public Course getCourse(String id);
}
