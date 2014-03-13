package uk.ac.glasgow.dcs_booking.components.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * A wrapper around the raw database management system for
 * adding and getting users.
 */
public class UserHandler implements UserAdd, UserQuery {
	
	private final String tableName = "USERT";
		
	private DBMS dbms;
	
	public UserHandler (DBMS dbms) throws SQLException {
		
		this.dbms = dbms;
		if (!userTableExists())
			createUserTable();
	}
	
	@Override
	public User	getUser(String name) {
		User wanted = null;
		ResultSet resultSet = null;
		
		try {
			
			String condition = "name='"+name+"'";
			
			resultSet =	dbms.getRows(tableName, condition);
			while (resultSet.next()) {
				wanted = parseRow(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public User parseRow(ResultSet resultSet) throws SQLException {
		
		String name = resultSet.getString("name");
		
		int type = resultSet.getInt("type");
		
		User r = new User(name,User.IntToType(type));
		
		return r;
	}
	

	@Override
	public void	addUser(User u){		
		try {		
			Map<String, Object> values = new HashMap<String,Object>();
			
			values.put("name", u.getUsername());
			values.put("type", User.TypeToInt(u.getType()));
			
			dbms.insertRow(tableName,values);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Boolean userTableExists() throws SQLException {
		return dbms.tableExists(tableName);
	}
	
	private void createUserTable() throws SQLException {
		String fields =
			"name VARCHAR(100) NOT NULL," +
			"type INT NOT NULL," +
			"PRIMARY KEY(name)";	
		dbms.createTable(tableName, fields);
	}
	
}
