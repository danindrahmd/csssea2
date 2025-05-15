package game.achievements;

import org.junit.Test;
import static org.junit.Assert.*;

public class StatTrackerTest {

    @Test
    public void noShotMeansZero() {
        PlayerStatsTracker stat = new PlayerStatsTracker();
        assertEquals(0.0, stat.getAccuracy(), 0.001);
    }

    @Test
    public void calcAccCorrect() {
        PlayerStatsTracker stat = new PlayerStatsTracker();
        for (int i = 0; i < 3; i++) stat.recordShotFired();
        stat.recordShotHit();
        assertEquals(1.0/3, stat.getAccuracy(), 0.001);
    }
}
