import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;


class HorseTest {

    @Test
    void constructorTheNameParameterIsNullTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 2.9, 3)
        );
    }

    @Test
    void constructorMessageVerificationNameCannotBeNullTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 2.9, 3)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ",})
    void constructorEmptyStringTest(String str) {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(str, 1.0, 1.0)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ",})
    void constructorMessageVerificationNameCannotBeBlankTest(String str) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(str, 1.0, 1.0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorTheSpeedParameterIsNotNegativeTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse("Буцефал", -1.0, 1.0)
        );
    }

    @Test
    void constructorMessageVerificationSpeedCannotBeNegativeTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Буцефал", -1.0, 3)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorTheDistanceParameterIsNotNegativeTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse("Буцефал", 1.0, -1.0)
        );
    }

    @Test
    void constructorMessageVerificationDistanceCannotBeNegativeTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Буцефал", 1.0, -3)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest() {
        String name = "Буцефал";
        Horse horse = new Horse(name, 1.0, 1.0);
        assertEquals("Буцефал", horse.getName());
    }

    @Test
    void getSpeedTest() {
        double speed = 1.0;
        Horse horse = new Horse("Буцефал", speed, 1.0);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        double distance = 1.0;
        Horse horse = new Horse("Буцефал", 1.0, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void getDistanceZeroTest() {
        double distance = 0;
        Horse horse = new Horse("Буцефал", 1.0);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void moveTest() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            new Horse("Буцефал", 1.0, 1.0).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0})
    void getRandomDoubleTest(double random) {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Буцефал", 2.0, 2.0);
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(2.0 + 2.0 * random, horse.getDistance());
        }

    }
}