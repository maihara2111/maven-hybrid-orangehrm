package javaSDET;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.testng.AssertJUnit.assertEquals;

public class Topic_15_Assert {
    @Test
    public void testHamcrest() {
        Biscuit theBiscuit = new Biscuit("Ginger");
        Biscuit myBiscuit = new Biscuit("Ginger");
        //assertThat(theBiscuit, equalTo(myBiscuit));
    }

    @Test
    public void testAssertJ() {
        assertThat("The Lord of the Rings")
                .isNotNull()
                .startsWith("The")
                .contains("Lord")
                .endsWith("Rings");
    }

    @Test
    @DisplayName("Custom test name containing spaces")
    public void testJUnit5() {
        String text = "Hello, World!";

        assertEquals(2, 3);
        assertEquals(text, "Mai Hara");
    }
}
