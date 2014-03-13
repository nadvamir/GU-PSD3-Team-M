package uk.ac.glasgow.dcs_booking.components.admincontrols.impl;

import java.util.ArrayList;
import java.util.Date;

import uk.ac.glasgow.dcs_booking.components.admincontrols.TimetableSlotManager;
import uk.ac.glasgow.dcs_booking.components.database.Session;
import uk.ac.glasgow.dcs_booking.components.database.TSAdd;
import uk.ac.glasgow.dcs_booking.components.database.TSQuery;
import uk.ac.glasgow.dcs_booking.components.database.TimetableSlot;
import uk.ac.glasgow.dcs_booking.components.database.User;


public class TimetableSlotManagerImpl implements TimetableSlotManager {
	private TSAdd adder;
	private TSQuery getter;
	
  public TimetableSlotManagerImpl() {
    // create adder/getter by yourself
  }
	public TimetableSlotManagerImpl(TSAdd adder, TSQuery getter) {
		this.adder = adder;
		this.getter = getter;
	}
	
	
	@Override
	public boolean createTimetableSlot(Session session, Date date,
			int capacity, String room, ArrayList<User> students, User tutor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean assignRoom(TimetableSlot ts, String room) {
		if (ts == null) {
			return false;
		}
		if (room == null) {
			return false;
		}
		//assign
		return true;
	}

  public ArrayList<String[]> checkClashesForLevel(int level) {
    // select all courses, order by ID
    // for each one of them, get timetable slots, order doesn't matter
    // for all the distinct pairs of courses (N*(N-1) of them) check for the
    // clashes, populate the results array list.
    return new ArrayList<String[]>();
  }

}
