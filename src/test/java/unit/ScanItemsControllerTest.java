package unit;

import apt.IDbLayer;
import apt.Product;
import apt.ScanItemsController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScanItemsControllerTest {

    @Test
    public void singleItemShouldBeScanned() throws SQLException {
        IDbLayer db = mock(IDbLayer.class);
        when(db.getProductByBarcode("123")).thenReturn(new Product("123", 5, ""));
        when(db.getProductByBarcode("456")).thenReturn(new Product("123", 5, ""));
        ScanItemsController cart = new ScanItemsController(db);
        cart.addItem("123");
        cart.addItem("456");
        assertEquals(12.13, cart.getTotalCost(), 0.0005);
    }

    @Test
    public void multipleItemsOfDifferentTypesShouldBeScanned() throws SQLException {
        IDbLayer db = mock(IDbLayer.class);
        when(db.getProductByBarcode("123")).thenReturn(new Product("123", 5, ""));
        when(db.getProductByBarcode("456")).thenReturn(new Product("123", 5, ""));
        ScanItemsController cart = new ScanItemsController(db);
        cart.addItem("123");
        cart.addItem("456");
        assertEquals(12.13, cart.getTotalCost(), 0.0005);
    }

    @Test
    public void multipleItemsOfTheSameTypeShouldBeScanned() throws SQLException {
        IDbLayer db = mock(IDbLayer.class);
        when(db.getProductByBarcode("123")).thenReturn(new Product("123", 5, ""));
        when(db.getProductByBarcode("456")).thenReturn(new Product("123", 5, ""));
        ScanItemsController cart = new ScanItemsController(db);
        cart.addItem("123");
        cart.addItem("456");
        assertEquals(12.13, cart.getTotalCost(), 0.0005);
    }
}
