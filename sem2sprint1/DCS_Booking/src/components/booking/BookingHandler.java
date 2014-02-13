package components.booking;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean book(User s, TimetableSlot ts) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Course[] getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSignedUpFor(User student, Course[] courses) {
		return false;
	}

}
