package components.test.stories.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import components.database.User;
import components.database.User.Type;

public class AddSessionToCourseSteps {

private User user;
	
	@Given("a lecturer")
	public void aLecturer() {
		this.user = new User("lecturer", Type.LECTURER);
	}
	
	@When("they create a new session")
	public void createSession() {
		//TODO
	}
	
	@When("the session is valid and unique")
	public void populateValidAndUniqueSession() {
		//TODO
	}
	
	@When("the session is nonsense")
	public void populateNonsenseSession() {
		//TODO
	}
	
	@When("the session already exists")
	public void populateExistingSession() {
		//TODO
	}
	
	@When ("the lecturer submits the session")
	public void submitSession() {
		//TODO
	}
	
	@Then("the session exists in the database")
	// public void sessionExists(ResultSet results)
	public void sessionExists() {
		//TODO
	}
	
	@Given("a student")
	public void aStudent() {
		this.user = new User("student", Type.STUDENT);
	}
	
	@When("the student submits the session")
	public void studentSubmitSession() {
		//TODO
	}
	
	@Then("an exception is thrown")
	public void throwException() {
		//TODO
	}
	
}
