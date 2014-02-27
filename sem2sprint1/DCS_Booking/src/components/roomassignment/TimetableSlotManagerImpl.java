package components.roomassignment;

import java.util.ArrayList;
import java.util.Date;

import components.database.Session;
import components.database.TSAdd;
import components.database.TSQuery;
import components.database.TimetableSlot;
import components.database.User;

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
