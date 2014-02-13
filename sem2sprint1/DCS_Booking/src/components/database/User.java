package components.database;

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

}
