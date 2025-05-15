package game.achievements;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Simple tests for AchievementManager with mock file.
 */
public class AchieveManagerTest {

    // Fake file handler to avoid writing to disk
    private static class AchieveFileMock implements AchievementFile {

        @Override
        public void save(String line) {
            // nothing saved in test
        }

        @Override
        public List<String> read() {
            return new ArrayList<>();
        }

        @Override
        public void setFileLocation(String location) {
            // ignore in test
        }

        @Override
        public String getFileLocation() {
            return "test.log";
        }
    }

    @Test
    public void addsOneAchieve() {
        AchievementManager manager = new AchievementManager(new AchieveFileMock());
        Achievement a = new GameAchievement("Intro", "Start game");
        manager.addAchievement(a);
        assertEquals(1, manager.getAchievements().size());
    }

    @Test
    public void updatesProgressNormally() {
        AchievementManager manager = new AchievementManager(new AchieveFileMock());
        Achievement a = new GameAchievement("Progress", "Try to win");
        manager.addAchievement(a);
        manager.updateAchievement("Progress", 0.4);
        assertEquals(0.4, a.getProgress(), 0.0001);
    }

    @Test
    public void duplicateFails() {
        AchievementManager manager = new AchievementManager(new AchieveFileMock());
        Achievement a = new GameAchievement("Once", "Only once");
        manager.addAchievement(a);
        try {
            manager.addAchievement(a);
            fail("Should not allow same achievement twice");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
