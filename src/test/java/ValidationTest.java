import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import util.Validation;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidationTest {
    @Test
    public void invalidNameTest() {
        boolean result = Validation.validPlayerName(" Мессииммммммммммммммммм");
        Assertions.assertThat(result).isFalse();
    }
}
