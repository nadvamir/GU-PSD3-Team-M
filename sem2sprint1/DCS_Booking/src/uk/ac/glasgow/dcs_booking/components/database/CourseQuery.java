package uk.ac.glasgow.dcs_booking.components.database;

public interface CourseQuery {
	/**
	 * Gets a course with a given ID
	 */
	public Course getCourse(String id);
}
