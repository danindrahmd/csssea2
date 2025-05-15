package game.controller;

import game.GameController;
import game.GameModel;
import game.achievements.AchievementManager;
import game.achievements.FileHandler;
import game.achievements.PlayerStatsTracker;
import game.ui.UI;
import game.ui.Tickable;
import game.ui.KeyHandler;
import game.core.SpaceObject;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class GameControllerPauseTest {

    // Mock implementation of the UI interface
    private static class MockUI implements UI {
        private boolean isPaused = false;
        public boolean toggleCalled = false;

        @Override
        public void pause() {
            isPaused = !isPaused;
            toggleCalled = true;
        }

        @Override public void start() {}
        @Override public void stop() {}
        @Override public void onStep(Tickable tickable) {}
        @Override public void onKey(KeyHandler key) {}
        @Override public void render(List<SpaceObject> objects) {}
        @Override public void log(String message) {}
        @Override public void setStat(String label, String value) {}
        @Override public void logAchievementMastered(String message) {}
        @Override public void logAchievements(List<game.achievements.Achievement> achievements) {}
        @Override public void setAchievementProgressStat(String name, double percent) {}
    }

    @Test
    public void testPauseGameIsCalled() {
        MockUI mockUI = new MockUI();
        GameModel model = new GameModel(mockUI::log, new PlayerStatsTracker());
        AchievementManager manager = new AchievementManager(new FileHandler());
        GameController controller = new GameController(mockUI, model, manager);

        controller.pauseGame();
        assertTrue("pause() should toggle UI pause state", mockUI.toggleCalled);
    }
}
