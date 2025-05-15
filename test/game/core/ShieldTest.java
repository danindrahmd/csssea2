package game.core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Make sure shield gives score.
 */
public class ShieldTest {

    @Test
    public void shieldPowerUpShouldAddScore() {
        Ship ship = new Ship();
        int before = ship.getScore();
        PowerUp shield = new ShieldPowerUp(0, 0);
        shield.applyEffect(ship);
        assertTrue(ship.getScore() > before);
    }

    @Test
    public void testShieldPowerUpAddsScore() {
        Ship ship = new Ship();
        int before = ship.getScore();
        ShieldPowerUp p = new ShieldPowerUp(0, 0);
        p.applyEffect(ship);
        int after = ship.getScore();
        assertTrue("Score should increase after collecting ShieldPowerUp", after > before);
    }

}
