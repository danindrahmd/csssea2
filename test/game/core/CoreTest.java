package game.core;

import game.GameModel;
import game.achievements.PlayerStatsTracker;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoreTest {

    @Test
    public void levelGoesUpWhenScoreMet() {
        GameModel model = new GameModel(s -> {}, new PlayerStatsTracker());
        Ship ship = model.getShip();

        // Score enough to level up
        ship.addScore(100);
        model.levelUp();

        // Should now be level 2
        assertEquals(2, model.getLevel());
    }

    /**
     * Checks that level does not change if score is too low.
     */
    @Test
    public void noLevelUpIfScoreLow() {
        GameModel model = new GameModel(s -> {}, new PlayerStatsTracker());
        model.levelUp();

        // Still at level 1
        assertEquals(1, model.getLevel());
    }

    @Test
    public void testToStringHasSpaceAfterComma() {
        ObjectWithPosition obj = new Asteroid(3, 7);
        String result = obj.toString();
        assertTrue("Expected format with space after comma", result.contains(", "));
    }

}
