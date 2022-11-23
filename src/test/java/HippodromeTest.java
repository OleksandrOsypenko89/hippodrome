import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void constructorParameterIsNullTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null)
        );
    }

    @Test
    void constructorMessageVerificationHorsesCannotBeNullTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorCheckForIsEmptyListTest() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(horses));
    }

    @Test
    void constructorMessageVerificationHorsesCannotBeEmptyTest() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Буцефал", 1.0, 1.0));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertArrayEquals(horses.toArray(), hippodrome.getHorses().toArray());

    }

    @Test
    void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse hors : horses) {
            verify(hors).move();
        }
    }

    @Test
    void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            horses.add(new Horse("Horse" + i, i, ThreadLocalRandom.current().nextInt(1, 10)));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse horse = horses.stream().max(Comparator.comparing(Horse::getDistance)).get();
        assertEquals(horse, hippodrome.getWinner());
    }
}