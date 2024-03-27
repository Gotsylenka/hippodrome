import net.bytebuddy.matcher.InheritedAnnotationMatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void listIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void listIsNullThrowExceptionWithMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Horses cannot be null.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void listIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void listIsEmptyThrowExceptionWithMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Horses cannot be empty.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i<30; i++) {
            horses.add(new Horse("Horse"+i, Horse.getRandomDouble(0.2,0.9)));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> hippodromeHorse = hippodrome.getHorses();
        assertEquals(horses, hippodromeHorse);
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : hippodrome.getHorses()) {
            Mockito.verify(horse).move();
        }
        horses.clear();
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("Horse1", 1, 100.0);
        Horse horse2 = new Horse("Horse2", 2, 150.0);
        Horse horse3 = new Horse("Horse3", 3, 210.0);

        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horse3,hippodrome.getWinner());

    }
}