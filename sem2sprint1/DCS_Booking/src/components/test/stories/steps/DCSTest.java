package components.test.stories.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.jbehave.core.annotations.Then;


public class DCSTest {

    @Then("one is $expected")
    public void oneIsOne(Integer expected) {
    	assertThat(1, equalTo(expected));
    }
    	

}
