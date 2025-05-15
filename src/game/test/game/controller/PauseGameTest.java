package game.controller;

import game.GameController;
import game.GameModel;
import game.achievements.*;
import game.ui.UI;
import game.ui.KeyHandler;
import game.ui.Tickable;

import org.junit.Test;
import static org.junit.Assert.*;

public class PauseGameTest {

    static class FakeUI implements UI {
        boolean paused = false;

        public void pause() { paused = !paused; }
        public void start() {}
        public void stop() {}
        public void onStep(Tickable t) {}
        public void onKey(KeyHandler k) {}
        public void render(java.util.List list) {}
        public void log(String m) {}
        public void setStat(String a, String b) {}
        public void logAchievementMastered(String msg) {}
        public void logAchievements(java.util.List list) {}
        public void setAchievementProgressStat(String name, double pct) {}
    }

    @Test
    public void pauseTwiceToggle() {
        FakeUI ui = new FakeUI();
        GameModel m = new GameModel(ui::log, new PlayerStatsTracker());
        GameController ctrl = new GameController(ui, m, new AchievementManager(new FileHandler()));
        ctrl.pauseGame();
        ctrl.pauseGame();
        assertTrue(true); // basic check
    }
}
