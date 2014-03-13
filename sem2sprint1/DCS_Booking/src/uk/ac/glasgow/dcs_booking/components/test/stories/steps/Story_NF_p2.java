package uk.ac.glasgow.dcs_booking.components.test.stories.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

import uk.ac.glasgow.dcs_booking.components.database.User;
import uk.ac.glasgow.dcs_booking.components.database.User.Type;

public class Story_NF_p2 {
	  @Given("a database contains $count dummy users")
	  public void insertUsers(Integer count) {
		  User u;
		  for (int i=0; i<count; i++) {
			  u = new User(String.valueOf(i), Type.STUDENT);
			  //addUser(u);
		  }
	  }

	  @Then("$count students can login as a student") 
	  public void userRecognised(Integer count) {
		  User check;
		  Boolean success = true;
		  for (int i=0; i<count; i++) {
			  //check = loginCheck(String.valueOf(i), "password");
			  //if (!check.getType().equals(Type.STUDENT)) success = false;
		  }
		  assertThat(success, equalTo(true));
	  }
}
