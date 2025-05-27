import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {
    @Test
    public void testSauceType() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }
    @Test
    public void testFillingType() {
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
