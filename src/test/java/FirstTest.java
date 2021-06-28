import static org.junit.jupiter.api.Assertions.assertEquals;

import apt.Rectangle;
import org.junit.jupiter.api.Test;

public class FirstTest {

    @Test
    public void testGetArea() {

        Rectangle rectangle = new Rectangle(3, 5);
        int area = rectangle.getArea();
        assertEquals(area, 15);
    }


}
