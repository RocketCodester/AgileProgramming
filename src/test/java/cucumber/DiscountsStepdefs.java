package cucumber;

import apt.CourseCoster;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testsupport.DbSupport;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountsStepdefs {
    private DbSupport db;
    private double actualCost;

    private DbSupport getDb() throws ClassNotFoundException {
        if (db == null)
            db = new DbSupport();
        return db;
    }

    @Given("the course {string} costs ${double}")
    public void theCourseCosts$(String courseCode, double courseCost) throws SQLException, ClassNotFoundException {
        getDb().setCourseCost(courseCode, courseCost);
    }

    @And("the code {string} gives a {double}% discount")
    public void theCodeGivesADiscount(String promotionCode, double percentage) throws SQLException, ClassNotFoundException {
        getDb().setDiscount(promotionCode, percentage);
    }

    @When("the cost of {string} with the code {string} is calculated")
    public void theCostOfWithTheCodeIsCalculated(String courseCode, String promotionCode) throws ClassNotFoundException {
        CourseCoster coster = new CourseCoster(getDb());
        actualCost = coster.getCost(courseCode, promotionCode);
    }

    @Then("the cost should be ${double}")
    public void theCostShouldBe$(double expectedCost) {
        assertEquals(expectedCost, actualCost);
    }
}
