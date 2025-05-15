package game.achievements;

import org.junit.Test;
import static org.junit.Assert.*;

public class AchieveNameDescTest {

    @Test
    public void testGetName() {
        GameAchievement a = new GameAchievement("Stars", "Collect 5 stars");
        assertEquals("Stars", a.getName());
    }

    @Test
    public void testGetDesc() {
        GameAchievement a = new GameAchievement("Stars", "Collect 5 stars");
        assertEquals("Collect 5 stars", a.getDescription());
    }
}
