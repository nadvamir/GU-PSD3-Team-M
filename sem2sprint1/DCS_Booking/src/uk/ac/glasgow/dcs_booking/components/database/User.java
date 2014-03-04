package uk.ac.glasgow.dcs_booking.components.database;

public class User {
	
	private String username;
	public enum Type {ADMIN, LECTURER, TUTOR, STUDENT, GUEST};
	private Type type;
	
	public User(String username, Type type) {
		this.username = username;
		this.type = type;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		else if (o.getClass() != getClass()) return false;
		else {
			User u = (User) o;
			if (!u.username.equals(this.username)) return false;
		}
		return true;
	}
	
	public String toString() {
		return username;
	}
	
	public static Type IntToType (int t) {
		switch (t) {
		case 0: return Type.ADMIN;
		case 1: return Type.LECTURER;
		case 2: return Type.TUTOR;
		case 3: return Type.STUDENT;
		case 4: return Type.GUEST;
		default: return Type.GUEST;
		}
	}
	
	public static int TypeToInt (Type t){
		switch (t) {
		case ADMIN		: 	return 0;
		case LECTURER	: 	return 1;
		case TUTOR		: 	return 2;
		case STUDENT	: 	return 3;
		case GUEST		: 	return 4;
		default			: 	return 4;
		}
	}

}
