import interviewtasks.TestReverseStringTask;
import interviewtasks.TimeService;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestTaskTestReverseString {
    private final TestReverseStringTask testTask = new TestReverseStringTask();
    private final Instant instant = Instant.ofEpochMilli(1650471681);
    private TimeService mock = Mockito.mock(TimeService.class);

    private static final String EXPECT_STR = "";


    @Test
    public void test() {
        when(mock.getCurrentInstant()).thenReturn(instant);
        String result = testTask.getCurrentTimeString();
        assertEquals(result, "");
    }
}