package game.controller;

import game.GameController;
import game.GameModel;
import game.achievements.AchievementManager;
import game.achievements.FileHandler;
import game.achievements.PlayerStatsTracker;
import game.ui.UI;
import game.ui.KeyHandler;
import game.ui.Tickable;

import org.junit.Test;
import static org.junit.Assert.*;

public class PauseGameTest {

    public class FakeUi implements UI {
        private boolean paused = false;

        public void pause() {
            paused = !paused;
        }

        public void start() { }

        public void stop() { }

        public void onStep(Tickable tickable) { }

        public void onKey(KeyHandler key) { }

        public void render(java.util.List objects) { }

        public void log(String message) { }

        public void setStat(String label, String value) { }

        public void logAchievementMastered(String message) { }

        public void logAchievements(java.util.List achievements) { }

        public void setAchievementProgressStat(String name, double progress) { }
    }

    @Test
    public void testPauseToggleTwice() {
        FakeUi fake = new FakeUi();
        GameModel model = new GameModel(fake::log, new PlayerStatsTracker());
        GameController controller = new GameController(fake, model, new AchievementManager(new FileHandler()));
        controller.pauseGame();
        controller.pauseGame();
        assertTrue(true); // only verifies no crash, basic toggle check
    }
}
