package uk.ac.glasgow.dcs_booking.components.test.stories.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.sql.SQLException;
import java.util.Date;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.glasgow.dcs_booking.components.admincontrols.impl.TimetableSlotManagerImpl;
import uk.ac.glasgow.dcs_booking.components.database.DBMS;
import uk.ac.glasgow.dcs_booking.components.database.TimetableSlot;
import uk.ac.glasgow.dcs_booking.components.database.User;
import uk.ac.glasgow.dcs_booking.components.database.User.Type;
import uk.ac.glasgow.dcs_booking.components.database.impl.TSHandler;
import uk.ac.glasgow.dcs_booking.components.database.impl.UserHandler;


public class Story_8 {

  private DBMS dbms = new DBMS();
  private TSHandler tsHandler;

  private User user;

  private String room;
  private TimetableSlot timetableSlot;
  private boolean roomAssigned;


  public Story_8() {
    try {
      tsHandler = new TSHandler(dbms, new UserHandler(dbms));
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

	@Given("a user is an admin")
	public void userIsAdmin() {
  		this.user = new User("admin", Type.ADMIN);
	}

	@Given("a user is not an admin")
	public void userNotAdmin() {
		this.user = new User("lecturer", Type.LECTURER);
	}

	@Given("a room is available")
	public void roomIsAvailable() {
		room = "room407";
	}

	@Given("a room is not available")
	public void roomIsNotAvailable() {
		room = null;
	}

	@Given("a timetable slot exists")
	public void timetableSlotExists() {
		timetableSlot = new TimetableSlot(new Date(), 100, "");
	}	

	@Given("a timetable slot does not exist")
	public void timetableSlotDoesNotExist() {
		timetableSlot = null;
	}

	@When("assigns a room to a timetable slot")
	public void assignRoomToSlot() {
		TimetableSlotManagerImpl manager = new TimetableSlotManagerImpl(tsHandler, tsHandler);
		roomAssigned = manager.assignRoom(timetableSlot, room);
		// This test fails for user != admin because there is no user check on the level of manager. This will work correctly if tested under the APP. 
	}

	@Then("a room is assigned")
	public void roomAssigned() {
		assertThat(roomAssigned, equalTo(true));
	}

	@Then("a room is not assigned")
	public void roomNotAssigned() {
		assertThat(roomAssigned, equalTo(false));
	}	
 
}
