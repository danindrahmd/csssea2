package game.achievements;

import org.junit.Test;
import static org.junit.Assert.*;

public class AchieveNameDescTest {

    @Test
    public void getNameTest() {
        Achievement a = new GameAchievement("Jump", "Jump high");
        assertEquals("Jump", a.getName());
    }

    @Test
    public void getDescTest() {
        Achievement a = new GameAchievement("Jump", "Jump high");
        assertEquals("Jump high", a.getDescription());
    }
}
