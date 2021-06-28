package unit;

import apt.CourseCoster;
import apt.DbLayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CourseCosterTest {
	@Test
	public void testDiscount() {
		DbLayer db = mock(DbLayer.class);
		when(db.getCourseCost(anyString())).thenReturn(1000.0);
		when(db.getDiscount("SAVE")).thenReturn(10.0);

		CourseCoster coster = new CourseCoster(db);

		assertEquals(900, coster.getCost("TDD", "SAVE"), 0.005);
	}
}
