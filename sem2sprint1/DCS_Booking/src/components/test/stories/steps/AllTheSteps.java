package components.test.stories.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import components.database.User;
import components.database.User.Type;

public class AllTheSteps {

  private User user;
  private Exception e = null;
  private Boolean boolAnsw = false;
	
  //--------------------------------------------------------------
  // 1st story
	@Given("a user is a $utype")
	public void userType(String utype) {
    if (utype.equals("admin"))
  		this.user = new User("admin", Type.ADMIN);
    else if (utype.equals("lecturer"))
  		this.user = new User("lecturer", Type.LECTURER);
    else if (utype.equals("tutor"))
      this.user = new User("tutor", Type.TUTOR);
    else if (utype.equals("student"))
  		this.user = new User("student", Type.STUDENT);
    else
  		this.user = new User("guest", Type.GUEST);
	}
	
  @When("he asks to import \"$course\" course from MyCampus")
	public void importCourse(String course) {
		//TODO
	}

  @Then("\"$course\" course exists in the database")
	public void checkCourse(String course) {
		//TODO
	}

  @Then("an exception is thrown")
  public void excepionThrown() {
    assertThat(e != null);
  }

  //--------------------------------------------------------------
  // creating sessions
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
	
	@When("the student submits the session")
	public void studentSubmitSession() {
		//TODO
	}
	
  //--------------------------------------------------------------
  // addig time slots
	@When("they create a new slot")
	public void createSlot() {
		//TODO
	}
	
	@When("the slot is valid and unique")
	public void populateValidAndUniqueSlot() {
		//TODO
	}
	
	@When("the slot is nonsense")
	public void populateNonsenseSlot() {
		//TODO
	}
	
	@When("the slot already exists")
	public void populateExistingSlot() {
		//TODO
	}
	
	@When ("the admin submits the slot")
	public void submitSession() {
		//TODO
	}
	
	@Then("the slot exists in the database")
	// public void slotExists(ResultSet results)
	public void slotExists() {
		//TODO
	}
	
	@When("the student submits the slot")
	public void studentSubmitSlot() {
		//TODO
	}
	
  //--------------------------------------------------------------
  // 12 story
  @When("\"$course\" is compulsory")
  public void courseIsCompulsory(String course) {
    // TODO
  }

  @When("a user signed up for \"$course\"")
  public void userSignedForCourse(String course) {
    // TODO
  }

  @When("When he asks if he has signed up for all compulsory courses")
  public void checkForSignedUp() {
    // TODO
  }

  @Then("a \"$answer\" answer is given")
  public void answerIsGiven(String answer) {
    // TODO
  }
  //--------------------------------------------------------------
  //--------------------------------------------------------------
  //--------------------------------------------------------------
  //--------------------------------------------------------------
  //--------------------------------------------------------------
}
