package game.core;

import game.*;
import game.achievements.AchievementManager;
import game.achievements.FileHandler;
import game.achievements.PlayerStatsTracker;
import game.ui.UI;
import game.utility.Direction;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for movement boundaries and paused input.
 */
public class ControllableTest {

    /**
     * Moving up out of the grid should throw an exception.
     */
    @Test
    public void moveThrowsOutOfBounds() {
        Controllable player = new Ship(0, 0, 100);
        try {
            player.move(Direction.UP);
            fail("Expected exception not thrown");
        } catch (IndexOutOfBoundsException e) {
            // Test passes
        }
    }

    /**
     * Exception message must match expected message when moving out of bounds.
     */
    @Test
    public void moveThrowsCorrectMessage() {
        Controllable player = new Ship(0, 0, 100);
        try {
            player.move(Direction.UP);
            fail("Expected exception not thrown");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Move would exceed bounds.", e.getMessage());
        }
    }

    /**
     * Movement should not happen when paused.
     */
    @Test
    public void noMoveWhenPaused() {
        FakeUi ui = new FakeUi();
        GameController controller = new GameController(ui, new GameModel(ui::log, new PlayerStatsTracker()), new AchievementManager(new FileHandler()));

        controller.pauseGame();
        int beforeX = controller.getModel().getShip().getX();
        int beforeY = controller.getModel().getShip().getY();

        controller.handlePlayerInput("W");

        int afterX = controller.getModel().getShip().getX();
        int afterY = controller.getModel().getShip().getY();

        assertEquals(beforeX, afterX);
        assertEquals(beforeY, afterY);
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
