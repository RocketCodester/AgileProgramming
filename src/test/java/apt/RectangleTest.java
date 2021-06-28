package apt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    private Rectangle rectangle;

    @BeforeEach
    public void setup() {
        rectangle = new Rectangle(3, 5);
    }

    @Test
    void getWidth_returnsTheWidth() {
        int width = rectangle.getWidth();
        assertEquals(3, width);
    }

    @Test
    void getHeight_returnsTheHeight() {
        int height = rectangle.getHeight();
        assertEquals(5, height);
    }

    @Test
    void getArea_returnsTheProductOfLengthAndWidth() {
        int area = rectangle.getArea();
        assertEquals(area, 15);
    }

    @Test
    void getPerimeter_returnsDoubleTheSumOfWidthAndHeight() {
        int perimeter = rectangle.getPerimeter();
        assertEquals(16, perimeter);
    }

    @Test
    void getOrientation_returnsPortraitWhenLengthIsGreaterThanHeight() {
        String orientation = rectangle.getOrientation();
        assertEquals("Portrait", orientation);
    }

    @Test
    void getOrientation_returnsLandscapeWhenLengthIsEqualToHeight() {
        String orientation = rectangle.getOrientation();
        assertEquals("Square", orientation);
    }

    @Test
    void getOrientation_returnsPortraitWhenWidthIsLessTheHeight() {
        String orientation = rectangle.getOrientation();
        assertEquals("Portrait", orientation);
    }

    @Test
    void isSquare_returnsFalseWhenTheLengthDoesNotEqualTheHeight() {
        boolean isSquare = rectangle.isSquare();
        assertFalse(isSquare);
    }

    @Test
    void isSquare_returnsTrueWhenTheLengthDoesEqualsTheHeight() {
        Rectangle rectangle = new Rectangle(3, 3);
        boolean isSquare = rectangle.isSquare();
        assertTrue(isSquare);
    }

    @Test
    void testEquals_returnsTrueWhenRectanglesHaveTheSameWidthAndHeight() {
        Rectangle firstRectangle = new Rectangle(3, 3);
        Rectangle secondRectangle = new Rectangle(3, 3);

        boolean areRectanglesEqual = firstRectangle.equals(secondRectangle);
        assertTrue(areRectanglesEqual);
    }

    @Test
    void testEquals_returnsFalseWhenRectanglesHaveDifferentWidth() {
        Rectangle firstRectangle = new Rectangle(3, 3);
        Rectangle secondRectangle = new Rectangle(3, 4);

        boolean areRectanglesEqual = firstRectangle.equals(secondRectangle);
        assertFalse(areRectanglesEqual);
    }

    @Test
    void testEquals_returnsFalseWhenRectanglesHaveDifferentHeight() {
        Rectangle firstRectangle = new Rectangle(3, 3);
        Rectangle secondRectangle = new Rectangle(3, 4);

        boolean areRectanglesEqual = firstRectangle.equals(secondRectangle);
        assertFalse(areRectanglesEqual);
    }

    @Test
    void testEquals_returnsFalseWhenRectangleToCompareWithIsNull() {
        Rectangle firstRectangle = new Rectangle(3, 3);

        boolean areRectanglesEqual = firstRectangle.equals(null);
        assertFalse(areRectanglesEqual);
    }

    @Test
    void testEquals_returnsFalseWhenObjectToCompareWithIsNotARectangle() {
        Rectangle firstRectangle = new Rectangle(3, 3);

        boolean areRectanglesEqual = firstRectangle.equals(new Object());
        assertFalse(areRectanglesEqual);
    }

    @Test
    public void negativeDimensionsShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-3, 5));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-3, -5));
    }

    private void assertRectanglesEqual(Rectangle e, Rectangle a) {
        assertEquals(e.getWidth(), a.getWidth());
        assertEquals(e.getHeight(), a.getHeight());
    }

    void demo() {
        Rectangle r1 = new Rectangle(3, 5);
        Rectangle r2 = new Rectangle(3, 5);
        assertEquals(r1, r2);   // returns true, r1 == r2
        assertSame(r1, r2);     // returns false, two different objects
    }
}