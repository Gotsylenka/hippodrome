import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.MalformedParameterizedTypeException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    private static final String name = "Diego";
    private static final double speed = 2.3;
    private static final double negativeSpeed = -2.3;
    private static final double distance = 5.2;
    private static final double negativeDistance = -5.2;
    @Test
    void constructorFistParamIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
    }

    @Test
    void constructorFirstParamIsNullThrowExceptionWithMessage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Name cannot be null.";
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource (strings = {""," ","\t","\n","\r","\f"})
    void constructorFirstParamIsEmptyLine(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @ParameterizedTest
    @ValueSource (strings = {""," ","\t","\n","\r","\f"})
    void constructorFirstParamIsEmptyLineThrowExceptionWithMessage(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Name cannot be blank.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void constructorSecondParamIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, negativeSpeed, distance));
    }

    @Test
    void constructorSecondParamIsNegativeThrowExceptionWithMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, negativeSpeed, distance));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Speed cannot be negative.";
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    void constructorThirdParamIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, negativeDistance));
    }

    @Test
    void constructorThirdParamIsNegativeThrowExceptionWithMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, negativeDistance));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Distance cannot be negative.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getName() {
        Horse horse = new Horse(name, speed, distance);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse(name, speed, distance);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse(name, speed, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void constructorWithTwoParams() {
        Horse horse = new Horse(name, speed);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void move() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse =new Horse(name, speed, distance);
            horse.move();
            mockedStatic.verify(()-> Horse.getRandomDouble(0.2,0.9));
        }
    }
    @ParameterizedTest
    @CsvSource({
            "0.2,0.9",
            "0.1,0.5",
            "0,3,0.7"
    })
    void moveWithGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse = new Horse(name, speed, distance);
            horse.move();
            double actualDistance = distance + speed * 0.5;
            double expectedDistance = distance + speed * Horse.getRandomDouble(0.2, 0.9);
            assertEquals(expectedDistance, actualDistance);
        }
    }
}