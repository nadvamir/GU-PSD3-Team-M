package uk.ac.glasgow.dcs_booking.components.studentcontrols;

import java.util.ArrayList;
import java.util.Date;

import uk.ac.glasgow.dcs_booking.components.database.Course;
import uk.ac.glasgow.dcs_booking.components.database.Session;
import uk.ac.glasgow.dcs_booking.components.database.TimetableSlot;
import uk.ac.glasgow.dcs_booking.components.database.User;


public interface Booker {
	
	/**
	 * 
	 * @param sid is Session ID of session where the Timetable Slot exists
	 * @param room is Timetable Slot's room
	 * @param data is Timetable Slot's date
	 * @return true/false if slot is full/not, will return true if TimetableSlot doesn't exist
	 */
	public boolean slotIsFull(String sid, String room, Date date);
	
	/**
	 * 
	 * @param student is student to add
	 * @param sid is Session ID of session where the Timetable Slot exists
	 * @param room is Timetable Slot's room
	 * @param data is Timetable Slot's date
	 * @param ts is TimetableSlot to add to
	 * @return true/false is successful/unsuccessful
	 */
	public boolean book(User student, String sid, String room, Date date);
	
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
	 * @param sid is Session ID of session where the Timetable Slot exists
	 * @param room is Timetable Slot's room
	 * @param data is Timetable Slot's date
	 * @return a string containing time, location, students and tutor
	 */
	public String getInfo(String sid, String room, Date date);

}
