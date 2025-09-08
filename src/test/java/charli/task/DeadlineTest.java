package charli.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testDeadlineCreation() {
        Deadline deadline = new Deadline("Release album", "25/12/2024 1800");
        assertEquals("Release album", deadline.getDescription());
        assertFalse(deadline.isDone());
        assertTrue(deadline.toString().contains("Release album"));
        assertTrue(deadline.toString().contains("Dec 25 2024, 6:00pm"));
    }

    @Test
    public void testDeadlineWithLocalDateTime() {
        LocalDateTime by = LocalDateTime.of(2024, 12, 25, 18, 0);
        Deadline deadline = new Deadline("Release album", by);
        assertEquals(by, deadline.getBy());
    }

    @Test
    public void testDeadlineMarkAsDone() {
        Deadline deadline = new Deadline("Release album", "25/12/2024 1800");
        deadline.markAsDone();
        assertTrue(deadline.isDone());
        assertTrue(deadline.toString().contains("[✓]"));
    }

    @Test
    public void testInvalidDateThrowsException() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("Release album", "invalid-date");
        });
    }


}
