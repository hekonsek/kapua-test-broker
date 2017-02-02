package kapua.test.broker;

import org.junit.Test;

public class TestBrokerTest {

    @Test
    public void shouldSendTelemetryData() {
        new TestBroker().start();

        new MockDevice().start().sendData("foo", "bar");
    }

}
