package components.booking;

import java.util.ArrayList;
import java.util.Date;

import components.database.Course;
import components.database.CourseQuery;
import components.database.Session;
import components.database.SessionQuery;
import components.database.TSQuery;
import components.database.TimetableSlot;
import components.database.User;
import components.database.UserQuery;

public class BookingHandler implements Booker {

	private CourseQuery coursequery;
	private SessionQuery sessionquery;
	private TSQuery tsquery;
	private UserQuery userquery;
	
	public BookingHandler (CourseQuery coursequery, SessionQuery sessionquery, TSQuery tsquery, UserQuery userquery) {
		this.coursequery = coursequery;
		this.sessionquery = sessionquery;
		this.tsquery = tsquery;
		this.userquery = userquery;
	}

	@Override
	public boolean slotIsFull(TimetableSlot ts) {
		if (ts.getStudents().size() == ts.getCapacity()) return true;
		else return false;
	}

	@Override
	public boolean book(User s, TimetableSlot ts) {
		// Slot is full, can't book
		if (slotIsFull(ts)) return false;
		
		else {
			// Add new student to timetable slot
			ts.getStudents().add(s);
			return true;
		}
	}

	@Override
	public Course[] getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Session> getSessionsNeeded(User student, Course[] courses) {
		
		ArrayList<Session> neededSessions = new ArrayList<Session>();
		
		for (Course course : courses) {
			for (Session session : course.getSessions()) {
				// If session isn't compulsory, don't worry
				if (!session.isCompulsory()) continue;
				
				boolean signedUp = false;
				for (TimetableSlot slot : session.getSlots()) {
					// Found slot that student is in
					if (slot.getStudents().contains(student)) {
						signedUp = true;
						break;
					}
				}
				// If student not found, add needed session 
				if (!signedUp) neededSessions.add(session);
			}
		}
		
		if (neededSessions.size() == 0) return null;
		else return neededSessions;
	}
	
	@Override
	public String getInfo(TimetableSlot ts) {
		Date date = ts.getDate();
		String location = ts.getRoom();
		ArrayList<User> students = ts.getStudents();
		User tutor = ts.getTutor();
		
		String report = "---DATE---\n";
		if (date == null) report += "   Not known\n";
		else report += "   "+date+"\n";
		
		report += "---LOCATION---\n";
		if (location == null) report += "   Not known\n";
		else report += "   "+location+"\n";
		
		report += "---STUDENTS---\n";
		if (students == null) report += "  Not known\n";
		else if (students.size() == 0) report += "   No students\n";
		else for (User student : students) report += "   "+student+"\n";
		
		report += "---TUTOR---\n";
		if (tutor == null) report += "   not known\n";
		else report += "   "+tutor+"\n";
		
		
		return report;
		
	}

}
