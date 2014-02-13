package components.login;

import components.database.User;

public interface Login {
	
	public User loginCheck(String username, String password);

}
