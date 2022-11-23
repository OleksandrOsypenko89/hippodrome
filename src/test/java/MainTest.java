import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Timeout(22)
    @Disabled
    void mainTest() {
        try {
            Thread.sleep(21000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}