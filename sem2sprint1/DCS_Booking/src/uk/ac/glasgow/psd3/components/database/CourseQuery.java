package uk.ac.glasgow.psd3.components.database;

public interface CourseQuery {
	/**
	 * Gets a course with a given ID
	 */
	public Course getCourse(String id);
}
