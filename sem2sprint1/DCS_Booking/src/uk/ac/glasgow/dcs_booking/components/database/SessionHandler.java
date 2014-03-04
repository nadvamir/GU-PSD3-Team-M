package uk.ac.glasgow.dcs_booking.components.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A wrapper around the raw database management system for
 * adding and getting sessions
 */
public class SessionHandler implements SessionAdd, SessionQuery {

	private String tableName = "session";

	private static DBMS dbms;

	public SessionHandler (DBMS dbms) throws SQLException {

		SessionHandler.dbms = dbms;
		if (!sessionTableExists())
			createSessionTable();
	}

	@Override
	public ArrayList<Session> getSession(String courseID) {
		ArrayList<Session> wanted = new ArrayList<Session>();
		ResultSet resultSet = null;

		try {

			String condition = "cid='"+courseID+"'";

			resultSet =	dbms.getRows(tableName, condition);
			while (resultSet.next()) {
				Session currentSession = parseRow(resultSet);
				wanted.add(currentSession);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				resultSet.close();
			} catch(SQLException e){
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return wanted;

	}

	public static Session parseRow(ResultSet resultSet) throws SQLException {
		ArrayList<TimetableSlot> slots = new ArrayList<TimetableSlot>();
		String id = resultSet.getString("id");

		String comp = resultSet.getString("compulsory");

		Session r = new Session();
		r.setId(id);
		if (comp.equals("Y")) {
			r.setCompulsary(true);
		} else {
			r.setCompulsary(false);
		}
		try {
			String condition = "sid='"+id+"'";
			resultSet =	dbms.getRows("timetableslot", condition);
			while (resultSet.next()) {
				TimetableSlot currentTimetableSlot = TSHandler.parseRow(resultSet);
				slots.add(currentTimetableSlot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				resultSet.close();
			} catch(SQLException e){
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		r.setSlots(slots);
		return r;
	}


	@Override
	public void	addSession(Session s, String cid){		
		try {		
			Map<String, Object> values = new HashMap<String,Object>();

			values.put("id", s.getId());
			values.put("cid", cid);
			String comp = "N";
			if (s.isCompulsory()) {
				comp = "Y";
			}
			values.put("compulsory", comp);

			dbms.insertRow(tableName,values);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Boolean sessionTableExists() throws SQLException {
		return dbms.tableExists(tableName);
	}

	private void createSessionTable() throws SQLException {
		String fields =
				"id VARCHAR(100) NOT NULL," +
						"cid VARCHAR(100) NOT NULL," +
						"compulsory CHAR(1) NOT NULL," +
						"PRIMARY KEY(name)";	
		dbms.createTable(tableName, fields);
	}

}
