package uk.ac.glasgow.dcs_booking.components.coursemanager;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glasgow.dcs_booking.components.database.Course;
import uk.ac.glasgow.dcs_booking.components.database.Session;
import uk.ac.glasgow.dcs_booking.components.mcwrapper.MyCampusCourse;


public interface CourseManager {

	public List<Course> getAllCourses();
	public Course getCourse(String id);
	public boolean addCourse(Course course);
	public boolean removeCourse(String id);
	public boolean updateCourseTitle(String id, String title);
	public boolean updateCourseSessions(String id, ArrayList<Session> sessions);
	
	public List<MyCampusCourse> getAllMyCampusCourses();
	public MyCampusCourse getMyCampusCourse(String title);
}
