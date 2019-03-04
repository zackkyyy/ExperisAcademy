import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class PetRockTest {
    private PetRock rocky;

    @BeforeEach
    void myTestSetUp() {
        rocky = new PetRock("Rocky");
    }

    @Test
    void getName() {
        assertEquals("Rocky", rocky.getName());
    }

    @Test
    void testUnhappyToStart() {
        assertFalse(rocky.isHappy());
    }

    @Test
    void testHappyAfterPlay() {
        rocky.playWithRock();
        assertTrue(rocky.isHappy());
    }

    @Test
    void nameFail_1() {
        assertThrows(IllegalStateException.class, () -> rocky.getHappyMessage());
    }

    @Disabled ("Exception throwing not yet defined")
    @Test
    void nameFail_2() {
        assertThrows(IllegalStateException.class, () -> rocky.getHappyMessage());
    }

    @Test
    void name() {
        rocky.playWithRock();
        String msg = rocky.getHappyMessage();

        assertEquals("I'm happy!", msg);
    }

    @Test
    void testFaveNum() {
        assertEquals(42, rocky.getFavNumber());
    }

    @Test
    void emptyNameFail() {
        assertThrows(IllegalArgumentException.class, () -> new PetRock(""));
    }

    @Test
    void waitForHappyTimeout() {
        assertThrows(AssertionFailedError.class, () -> assertTimeoutPreemptively(Duration.ofMillis(100), () -> rocky.waitTillHappy()));
    }
}