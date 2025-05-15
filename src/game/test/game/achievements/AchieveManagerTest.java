package game.achievements;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class AchieveManagerTest {

    @Test
    public void addOneAchieve() {
        AchievementManager mgr = new AchievementManager(new FileHandler());
        Achievement a = new GameAchievement("First", "Do something");
        mgr.addAchievement(a);
        List<Achievement> all = mgr.getAchievements();
        assertEquals(1, all.size());
    }

    @Test
    public void sameNameNotAllowed() {
        AchievementManager mgr = new AchievementManager(new FileHandler());
        Achievement a = new GameAchievement("Unique", "test");
        mgr.addAchievement(a);
        try {
            mgr.addAchievement(a);
            fail("should not add twice");
        } catch (IllegalArgumentException e) {
            // pass
        }
    }

    @Test
    public void progressUpdateWorks() {
        AchievementManager mgr = new AchievementManager(new FileHandler());
        Achievement a = new GameAchievement("Goal", "Track it");
        mgr.addAchievement(a);
        mgr.updateAchievement("Goal", 0.9);
        assertEquals(0.9, a.getProgress(), 0.001);
    }
}
