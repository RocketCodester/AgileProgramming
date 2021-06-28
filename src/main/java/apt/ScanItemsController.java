package apt;

import java.sql.SQLException;

public class ScanItemsController {
    private IDbLayer db;
    private ShoppingCart cart;

    public ScanItemsController(IDbLayer db) {
        this.db = db;
        cart = new ShoppingCart();
    }

    public void addItem(String barcode) throws SQLException {
        Product product = db.getProductByBarcode(barcode);
        cart.addItem(product);
    }

    public double getTotalCost() {
        return 5;
    }
}
