package components.coursemanager;

import java.util.ArrayList;
import java.util.List;

import components.database.Course;

public class CourseEditorHandler implements CourseEditor {

	@Override
	public List<Course> getAllCourses() {
		List<Course> list = new ArrayList<Course>();
		return list;
	}
	
	@Override
	public String toString() {
		return "Hello, I am a Course Editor";
	}

}
