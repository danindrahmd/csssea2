package game;

import game.core.Ship;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test level up condition.
 */
public class LevelUpTest {

    @Test
    public void shouldLvlUpWhenScoreMeetThreshold() {
        GameModel model = new GameModel(s -> {}, new game.achievements.PlayerStatsTracker());
        Ship ship = model.getShip();
        ship.addScore(GameModel.SCORE_THRESHOLD);
        int before = model.getLevel();
        model.levelUp();
        assertEquals(before + 1, model.getLevel());
    }
}
