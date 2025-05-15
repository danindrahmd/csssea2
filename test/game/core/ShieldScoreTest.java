package game.core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Make sure shield gives score.
 */
public class ShieldScoreTest {

    @Test
    public void shieldPowerUpShouldAddScore() {
        Ship ship = new Ship();
        int before = ship.getScore();
        PowerUp shield = new ShieldPowerUp(0, 0);
        shield.applyEffect(ship);
        assertTrue(ship.getScore() > before);
    }
}
