package shared;

import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    void shouldConvertIntToStatus() {
        Status status = Status.intToStatus(1);
        assert(status == Status.WAITING);
    }

    @Test
    void shouldConvertIntToStatus2() {
        Status status = Status.intToStatus(2);
        assert(status == Status.IN_PROGRESS);
    }

    @Test
    void shouldConvertIntToStatus3() {
        Status status = Status.intToStatus(3);
        assert(status == Status.DONE);
    }

    @Test
    void shouldConvertIntToStatus4() {
        Status status = Status.intToStatus(4);
        assert(status == Status.CANCELLED);
    }

    @Test
    void shouldConvertIntToStatus5() {
        Status status = Status.intToStatus(5);
        assert(status == null);
    }
}
