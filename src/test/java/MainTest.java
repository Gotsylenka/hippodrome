import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private String[] args;
    @Timeout(22)
    @Test
    @Disabled
    void mainIsMoreThen22Seconds() throws Exception {
        Main.main(args);
    }
}