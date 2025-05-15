package game.achievements;

import org.junit.Test;
import static org.junit.Assert.*;

public class StatTrackerTest {

    @Test
    public void testNoShotMeansZeroAcc() {
        PlayerStatsTracker tracker = new PlayerStatsTracker();
        assertEquals(0.0, tracker.getAccuracy(), 0.0001);
    }

    @Test
    public void testAccCorrectCalc() {
        PlayerStatsTracker tracker = new PlayerStatsTracker();
        for (int i = 0; i < 5; i++) {
            tracker.recordShotFired();
        }
        for (int i = 0; i < 3; i++) {
            tracker.recordShotHit();
        }
        assertEquals(0.6, tracker.getAccuracy(), 0.0001);
    }
}
