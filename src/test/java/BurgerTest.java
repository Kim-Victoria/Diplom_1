import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockSauce;
    @Mock
    private Ingredient mockFilling;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }
    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockFilling);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }
    @Test
    public void testMoveIngredient() {
        Ingredient firstIng = mockSauce;
        Ingredient secondIng = mockFilling;
        burger.addIngredient(firstIng);
        burger.addIngredient(secondIng);

        burger.moveIngredient(0, 1);
        assertEquals(firstIng, burger.ingredients.get(1));
        assertEquals(secondIng, burger.ingredients.get(0));
    }
    @Test
    public void testGetPrice() {
        Bun bun = new Bun("black bun", 100);
        Ingredient sauce = new Ingredient(SAUCE, "hot sauce", 100F);
        Ingredient filling = new Ingredient(FILLING, "cutlet", 100F);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float expectedPrice = 2 * 100 + 100 + 100;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceipt() {
        Bun bun = new Bun("black bun", 100);
        Ingredient sauce = new Ingredient(SAUCE, "hot sauce", 100F);
        Ingredient filling = new Ingredient(FILLING, "cutlet", 100F);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("= sauce hot sauce ="));
        assertTrue(receipt.contains("= filling cutlet ="));
        String expectedPrice = String.format("Price: %.1f", burger.getPrice());
        assertTrue(receipt.contains(expectedPrice));
    }
}
