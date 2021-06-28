package unit;

import org.junit.jupiter.api.Test;

import static apt.InsuranceQuoter.getQuote;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsuranceQuoterTest {
    @Test
    public void testMasonry() {
        double quote = getQuote(200000, "terraced", 3, "1971", "masonry");
        assertEquals(416.12, quote, 0.005);
    }

    @Test
    public void testTimber() {
        // This test is correct.  Do not change it!
        double quote = getQuote(200000, "terraced", 3, "1971", "timber");
        assertEquals(457.73, quote, 0.005);
    }
}
