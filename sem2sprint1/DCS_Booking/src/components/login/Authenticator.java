package components.login;

import components.database.User;
import components.database.User.Type;

public class Authenticator implements Login {

	@Override
	public User loginCheck(String username, String password) {
		if (username.toLowerCase().equals("admin"))
			return new User(username, Type.ADMIN);
		else if (username.toLowerCase().equals("lecturer"))
			return new User(username, Type.LECTURER);
		else if (username.toLowerCase().equals("tutor"))
			return new User(username, Type.TUTOR);
		return new User(username, Type.STUDENT);
	}
	
	

}
