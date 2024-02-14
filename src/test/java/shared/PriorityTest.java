package shared;

import org.junit.jupiter.api.Test;

public class PriorityTest {
    @Test
    void shouldConvertIntToPriority() {
        Priority priority = Priority.intToPriority(1);
        assert(priority == Priority.NORMAL);
    }

    @Test
    void shouldConvertIntToPriority2() {
        Priority priority = Priority.intToPriority(2);
        assert(priority == Priority.BEST_EFFORT);
    }

    @Test
    void shouldConvertIntToPriority3() {
        Priority priority = Priority.intToPriority(3);
        assert(priority == Priority.CRITICAL);
    }

    @Test
    void shouldConvertIntToPriority4() {
        Priority priority = Priority.intToPriority(4);
        assert(priority == null);
    }
}
