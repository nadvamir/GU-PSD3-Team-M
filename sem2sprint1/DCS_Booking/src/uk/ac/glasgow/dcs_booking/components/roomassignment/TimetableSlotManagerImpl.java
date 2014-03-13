package uk.ac.glasgow.dcs_booking.components.roomassignment;

import java.util.ArrayList;
import java.util.Date;

import uk.ac.glasgow.dcs_booking.components.database.Session;
import uk.ac.glasgow.dcs_booking.components.database.TSAdd;
import uk.ac.glasgow.dcs_booking.components.database.TSQuery;
import uk.ac.glasgow.dcs_booking.components.database.TimetableSlot;
import uk.ac.glasgow.dcs_booking.components.database.User;


public class TimetableSlotManagerImpl implements TimetableSlotManager {
	private TSAdd adder;
	private TSQuery getter;
	
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
		// TODO Auto-generated method stub
		return false;
	}

}