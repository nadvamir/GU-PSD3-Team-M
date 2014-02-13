package components.application;

import components.coursemanager.CourseManager;
import components.login.User;

public class CourseEditorHandler extends Handler {
	private CourseManager courseEditor;
	
	public CourseEditorHandler(CourseManager courseEditor,  User.Type... allowed) {
        super(allowed);
        this.courseEditor = courseEditor;
	}

	@Override
	public void run(User u, String... args) throws Exception {
		checkAccess(u);
		
		String command = args[0];
		switch (command) {
			case "getAllCourses":
				courseEditor.getAllCourses();
		}
		
	}

}
