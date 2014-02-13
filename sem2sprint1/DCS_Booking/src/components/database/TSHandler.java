package components.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A wrapper around the raw database management system for
 * reporting and querying temperature readings.
 * 
 * @author tws
 * 
 */
public class TSHandler implements TSAdd, TSQuery {
	
	private String tableName = "timetableslot";
		
	private DBMS dbms;
	
	public TSHandler (DBMS dbms) throws SQLException {
		
		this.dbms = dbms;
		if (!TSTableExists())
			createTSTable();
	}
	
	@Override
	public ArrayList<TimetableSlot> getTS(String sessionID) {
		ArrayList<TimetableSlot> wanted = new ArrayList<TimetableSlot>();
		ResultSet resultSet = null;
		
		try {
			
			String condition = "sid='"+sessionID+"'";
			
			resultSet =	dbms.getRows(tableName, condition);
			while (resultSet.next()) {
				TimetableSlot currentTS = parseRow(resultSet);
				wanted.add(currentTS);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				resultSet.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
		return wanted;
		
	}

	private TimetableSlot parseRow(ResultSet resultSet) throws SQLException {
		
		Date date = resultSet.getDate("date");
		
		int capacity = resultSet.getInt("capacity");
		
		String room = resultSet.getString("compulsory");
		
		String tutor = resultSet.getString("tutor");
		
		//TODO: get tutor user and add it in
		
		TimetableSlot r = new TimetableSlot(date,capacity,room);

		return r;
	}
	

	@Override
	public void	addTS(TimetableSlot s, String sid){		
		try {		
			Map<String, Object> values = new HashMap<String,Object>();
			
			values.put("sid", sid);
			values.put("date", s.getDate());
			values.put("capacity", s.getCapacity());
			values.put("room", s.getRoom());
			values.put("tutor", s.getTutor().getUsername());
			dbms.insertRow(tableName,values);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Boolean TSTableExists() throws SQLException {
		return dbms.tableExists(tableName);
	}
	
	private void createTSTable() throws SQLException {
		String fields =
			"sid VARCHAR(100) NOT NULL," +
			"date DATE NOT NULL," +
			"capacity INT," +
			"room VARCHAR(100)," +
			"tutor VARCHAR(100)," +
			"PRIMARY KEY(name)";	
		dbms.createTable(tableName, fields);
	}
	
}
