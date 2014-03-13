package uk.ac.glasgow.dcs_booking.components.studentcontrols;

import java.util.ArrayList;

import uk.ac.glasgow.dcs_booking.components.database.Course;
import uk.ac.glasgow.dcs_booking.components.database.Session;
import uk.ac.glasgow.dcs_booking.components.database.TimetableSlot;
import uk.ac.glasgow.dcs_booking.components.database.User;


public interface Booker {
	
	/**
	 * 
	 * @param ts is TimeTable slot being checked
	 * @return true/false if slot is full/not
	 */
	public boolean slotIsFull(TimetableSlot ts);
	
	/**
	 * 
	 * @param student is student to add
	 * @param ts is TimetableSlot to add to
	 * @return true/false is successful/unsuccessful
	 */
	public boolean book(User student, TimetableSlot ts);
	
	/**
	 *
	 * @return an array of courses from the database
	 */
	public Course[] getCourses();
	
	/**
	 * 
	 * @param student is student to check for
	 * @param courses is courses to be checked for compulsory sessions
	 * @return ArrayList of compulsory Sessions that student hasn't signed up for or null if there are none
	 */
	public ArrayList<Session> getSessionsNeeded(User student, Course[] courses);
	
	/**
	 * 
	 * @param ts is TimetableSlot being queried for info
	 * @return a string containing time, location, students and tutor
	 */
	public String getInfo(TimetableSlot ts);

}
