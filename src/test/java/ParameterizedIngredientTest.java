import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class ParameterizedIngredientTest {
    private final IngredientType expectedType;
    private final String expectedName;
    private final float expectedPrice;

    public ParameterizedIngredientTest(IngredientType expectedType, String expectedName, float expectedPrice) {
        this.expectedType = expectedType;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Ингредиент: {1}, тип: {0}, цена: {2}")
    public static Object[] getIngredientData() {
        return new Object[][]{
                {SAUCE, "hot sauce", 100},
                {SAUCE, "sour cream", 200},
                {SAUCE, "chili sauce", 300},
                {FILLING, "cutlet", 100},
                {FILLING, "dinosaur", 200},
                {FILLING, "sausage", 300},
        };
    }

    @Test
    public void testGetIngredientPrice() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        assertEquals(expectedPrice, ingredient.getPrice(), 0.001);
    }

    @Test
    public void testGetIngredientName() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        assertEquals(expectedName, ingredient.getName());
    }

    @Test
    public void testGetIngredientType() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        assertEquals(expectedType, ingredient.getType());
    }
}

