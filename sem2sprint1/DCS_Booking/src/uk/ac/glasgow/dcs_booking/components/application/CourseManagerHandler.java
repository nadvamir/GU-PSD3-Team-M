package uk.ac.glasgow.dcs_booking.components.application;

import uk.ac.glasgow.dcs_booking.components.database.User;
import uk.ac.glasgow.dcs_booking.components.lecturercontrols.CourseManager;

public class CourseManagerHandler extends Handler {
	private CourseManager courseEditor;
	
	public CourseManagerHandler(CourseManager courseEditor,  User.Type... allowed) {
        super(allowed);
        this.courseEditor = courseEditor;
	}

	@Override
	public void run(User u, String... args) throws Exception {
		checkAccess(u);
		
		String command = args[0];
		
		if (command.equals("getAllCourses")) {
			courseEditor.getAllCourses();
		}
		
	}

}
