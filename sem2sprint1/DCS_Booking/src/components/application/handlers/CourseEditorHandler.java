package components.application.handlers;

import components.application.Handler;
import components.coursemanager.CourseEditor;
import components.database.User;

public class CourseEditorHandler extends Handler {
	private CourseEditor courseEditor;
	
	public CourseEditorHandler(CourseEditor courseEditor,  User.Type... allowed) {
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
