package uk.ac.glasgow.psd3.components.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A wrapper around the raw database management system for
 * adding and getting courses
 */
public class CourseHandler implements CourseAdd, CourseQuery {
	
	private String tableName = "course";
		
	private DBMS dbms;
	
	public CourseHandler (DBMS dbms) throws SQLException {
		
		this.dbms = dbms;
		if (!courseTableExists())
			createCourseTable();
	}
	
	@Override
	public Course getCourse(String id) {
		Course wanted = null;
		ResultSet resultSet = null;
		
		try {
			
			String condition = "id='"+id+"'";
			
			resultSet =	dbms.getRows(tableName, condition);
			while (resultSet.next()) {
				wanted = parseRow(resultSet);
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

	private Course parseRow(ResultSet resultSet) throws SQLException {
		
		String id = resultSet.getString("id");
		
		String name = resultSet.getString("name");
		
		//TODO: get sessions
		
		Course r = new Course(id,name);
		ArrayList<Session> sessions = new ArrayList<Session>();
		try {
			String condition = "cid='"+id+"'";
			resultSet =	dbms.getRows("session", condition);
			while (resultSet.next()) {
				Session currentSession = SessionHandler.parseRow(resultSet);
				sessions.add(currentSession);
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
		r.setSessions(sessions);
		return r;
	}
	

	@Override
	public void	addCourse(Course c){		
		try {		
			Map<String, Object> values = new HashMap<String,Object>();
			
			values.put("id", c.getId());
			values.put("name", c.getTitle());
			
			dbms.insertRow(tableName,values);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Boolean courseTableExists() throws SQLException {
		return dbms.tableExists(tableName);
	}
	
	private void createCourseTable() throws SQLException {
		String fields =
			"id VARCHAR(100) NOT NULL," +
			"title VARCHAR(250) NOT NULL," +
			"PRIMARY KEY(id)";	
		dbms.createTable(tableName, fields);
	}
	
}
