package game.core;

import game.*;
import game.achievements.AchievementManager;
import game.achievements.FileHandler;
import game.achievements.PlayerStatsTracker;
import game.exceptions.BoundaryExceededException;
import game.ui.UI;
import game.utility.Direction;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for movement bounds and paused state.
 */
public class ControllableTest {

    @Test
    public void moveThrowsOutOfBounds() {
        Controllable ship = new Ship(0, 0, 100);
        try {
            ship.move(Direction.UP); // should go out of top bound
            fail("Expected BoundaryExceededException");
        } catch (BoundaryExceededException e) {
            // OK
        }
    }

    @Test
    public void moveThrowsCorrectMessage() {
        Controllable ship = new Ship(0, 0, 100);
        try {
            ship.move(Direction.UP);
            fail("Expected BoundaryExceededException");
        } catch (BoundaryExceededException e) {
            assertEquals("Out of bounds", e.getMessage()); // match required message
        }
    }

    @Test
    public void noMoveWhenPaused() {
        FakeUi ui = new FakeUi();
        GameModel model = new GameModel(ui::log, new PlayerStatsTracker());
        GameController ctrl = new GameController(ui, model, new AchievementManager(new FileHandler()));

        ctrl.pauseGame(); // toggle to paused

        int xBefore = model.getShip().getX();
        int yBefore = model.getShip().getY();

        ctrl.handlePlayerInput("W");

        int xAfter = model.getShip().getX();
        int yAfter = model.getShip().getY();

        assertEquals(xBefore, xAfter);
        assertEquals(yBefore, yAfter);
    }

    /**
     * Simple fake UI to support controller.
     */
    private static class FakeUi implements UI {
        private boolean isPaused = false;

        @Override public void start() {}
        @Override public void pause() { isPaused = !isPaused; }
        @Override public void stop() {}
        @Override public void onStep(game.ui.Tickable tickable) {}
        @Override public void onKey(game.ui.KeyHandler key) {}
        @Override public void render(java.util.List<game.core.SpaceObject> list) {}
        @Override public void log(String message) {}
        @Override public void setStat(String label, String value) {}
        @Override public void logAchievementMastered(String message) {}
        @Override public void logAchievements(java.util.List<game.achievements.Achievement> list) {}
        @Override public void setAchievementProgressStat(String name, double percent) {}
    }
}
