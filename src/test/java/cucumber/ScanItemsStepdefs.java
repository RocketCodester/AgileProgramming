package cucumber;

import apt.ScanItemsController;
import apt.ShoppingCart;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testsupport.DbSupport;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScanItemsStepdefs {
    private DbSupport db;
    private ScanItemsController controller;

    @Before
    public void setup() throws ClassNotFoundException {
        db = new DbSupport();
        controller = new ScanItemsController(db);
    }

    @Given("the product with barcode {string} cost ${double}")
    public void theProductWithBarcodeCost$(String barcode, Double price) throws ClassNotFoundException, SQLException {
        db.setProductPrice(barcode, price);
    }

    @When("I scan the barcode {string}")
    public void iScanTheBarcode(String barcode) {
        controller.addItem(barcode);
    }

    @Then("the total amount should be ${double}")
    public void theTotalAmountShouldBe$(Double expectedAmount) {
        assertEquals(20, expectedAmount);
    }

}
