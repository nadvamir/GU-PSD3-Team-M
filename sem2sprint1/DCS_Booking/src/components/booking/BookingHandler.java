package components.booking;

import java.util.ArrayList;

import components.database.Course;
import components.database.Session;
import components.database.TimetableSlot;
import components.database.User;

public class BookingHandler implements Booker {
	
	/* GET ACCESS TO DATABASE
	 *
	private DBMS dbms;
	
	public BookingHandler (DBMS dbms) {
		this.dbms = dbms;
	}
	 */

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

}
