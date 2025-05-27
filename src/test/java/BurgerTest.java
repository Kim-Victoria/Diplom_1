import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
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
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getName()).thenReturn("hot sauce");
        when(mockSauce.getPrice()).thenReturn(100f);

        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockFilling.getName()).thenReturn("cutlet");
        when(mockFilling.getPrice()).thenReturn(100f);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }
    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockFilling);
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
        assertEquals(java.util.Arrays.asList(secondIng, firstIng), burger.ingredients);
    }
    @Test
    public void testGetPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        float expectedPrice = 2 * 100 + 100 + 100;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        String receipt = burger.getReceipt();
        String expectedPrice = String.format("Price: %.1f", burger.getPrice());
        assertThat(receipt, allOf(containsString("(==== black bun ====)"), containsString("= sauce hot sauce ="), containsString("= filling cutlet ="), containsString(expectedPrice)));
    }
}
