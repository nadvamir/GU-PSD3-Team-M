package uk.ac.glasgow.psd3.components.coursemanager;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glasgow.psd3.components.database.Course;
import uk.ac.glasgow.psd3.components.database.Session;
import uk.ac.glasgow.psd3.components.mcwrapper.MyCampusCourse;
import uk.ac.glasgow.psd3.components.mcwrapper.MyCampusWrapper;


public class CourseManagerImpl implements CourseManager {
	private MyCampusWrapper myCampusController;
	
	public CourseManagerImpl(MyCampusWrapper myCampusController) {
		this.myCampusController = myCampusController;
		
		
		System.out.println(getAllMyCampusCourses());
	}

	@Override
	public List<Course> getAllCourses() {
		List<Course> list = new ArrayList<Course>();
		return list;
	}

	@Override
	public Course getCourse(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCourse(Course course) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCourse(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCourseTitle(String id, String title) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCourseSessions(String id, ArrayList<Session> sessions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MyCampusCourse> getAllMyCampusCourses() {
		List<MyCampusCourse> courses;
		try {
			courses = myCampusController.getAllCourses();
		} catch (Exception e) {
			e.printStackTrace();
			
			courses = new ArrayList<MyCampusCourse>();
		} 
		
		return courses;
	}

	@Override
	public MyCampusCourse getMyCampusCourse(String title) {
		MyCampusCourse course;
		
		try {
			course = myCampusController.getCourse(title);
		} catch (Exception e) {
			e.printStackTrace();
			
			course = null;
		}
		
		return course;
	}

}
