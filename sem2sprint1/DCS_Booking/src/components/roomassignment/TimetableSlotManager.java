package components.roomassignment;

import java.util.ArrayList;
import java.util.Date;

import components.database.Session;
import components.database.TimetableSlot;
import components.database.User;

public interface TimetableSlotManager {
	
	/**
	 * Create a Timetable Slot and add it to a session
	 * @param session
	 * @param date
	 * @param capacity
	 * @param room
	 * @param students
	 * @param tutor
	 * @return true/false if successful/unsuccessful
	 */
	public boolean createTimetableSlot(Session session, Date date, int capacity, String room, ArrayList<User> students, User tutor);

	/**
	 * Assign a room to a timetable slot
	 * @param ts is TimetableSlot
	 * @param room is String form of room
	 * @return true/false if successful/unsuccessful
	 */
	public boolean assignRoom(TimetableSlot ts, String room);
	
}
