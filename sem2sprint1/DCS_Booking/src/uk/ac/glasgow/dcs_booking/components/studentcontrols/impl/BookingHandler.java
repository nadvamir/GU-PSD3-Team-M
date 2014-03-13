package uk.ac.glasgow.dcs_booking.components.studentcontrols.impl;

import java.util.ArrayList;
import java.util.Date;

import uk.ac.glasgow.dcs_booking.components.database.Course;
import uk.ac.glasgow.dcs_booking.components.database.CourseQuery;
import uk.ac.glasgow.dcs_booking.components.database.Session;
import uk.ac.glasgow.dcs_booking.components.database.SessionQuery;
import uk.ac.glasgow.dcs_booking.components.database.TSQuery;
import uk.ac.glasgow.dcs_booking.components.database.TimetableSlot;
import uk.ac.glasgow.dcs_booking.components.database.User;
import uk.ac.glasgow.dcs_booking.components.database.UserQuery;
import uk.ac.glasgow.dcs_booking.components.studentcontrols.Booker;


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
	
	//  My own helper function, simplifying future functions that rely on obtaining a specific timetable slot
	private TimetableSlot getSlot(String sid, String room, Date date) {
		ArrayList<TimetableSlot> slots = tsquery.getTS(sid);
		for (TimetableSlot slot : slots) {
			if (slot.getDate().equals(date) && slot.getRoom().equalsIgnoreCase(room)) {
				return slot;
			}
		}
		// Slot doesn't exist
		return null;
	}

	@Override
	public boolean slotIsFull(String sid, String room, Date date) {
		TimetableSlot slot = getSlot(sid, room, date);
		if (slotIsFull(slot)) return true;
		else return false;
	}
	
	// My own helper function, simplifying future functions that rely on knowing if a timetable slot is full
	private boolean slotIsFull(TimetableSlot ts) {
		return (ts.getStudents().size()==ts.getCapacity());
	}

	@Override
	public boolean book(User s, String sid, String room, Date date) {
		TimetableSlot slot = getSlot(sid, room, date);
		if (slot == null || slotIsFull(slot)) return false;
		else {
			// Add new student to timetable slot
			slot.getStudents().add(s);
			return true;
		}
	}
	
	// Cannot properly implement with current Database interface
	// Need a method to return all courses
	// (perhaps based on level/degree to make more useful)
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
	public String getInfo(String sid, String room, Date date) {
		TimetableSlot ts = getSlot(sid, room, date);
		ArrayList<User> students = ts.getStudents();
		User tutor = ts.getTutor();
		
		String report = "---DATE---\n";
		if (date == null) report += "   Not known\n";
		else report += "   "+date+"\n";
		
		report += "---LOCATION---\n";
		if (room == null) report += "   Not known\n";
		else report += "   "+room+"\n";
		
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
