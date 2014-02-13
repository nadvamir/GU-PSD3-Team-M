package components.booking;

import components.database.Course;
import components.database.TimetableSlot;
import components.database.User;

public interface Booker {
	
	public boolean slotIsFull(TimetableSlot ts);
	
	public boolean book(User student, TimetableSlot ts);
	
	public Course[] getCourses();
	
	public boolean isSignedUpFor(User student, Course[] courses);

}
