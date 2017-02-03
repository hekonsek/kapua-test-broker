package kapua.test.broker;

import org.apache.activemq.broker.BrokerService;

public class TestBroker {

    private final BrokerService broker = new BrokerService();

    public void start() {
        try {
            broker.setPersistent(false);
            broker.addConnector("mqtt://0.0.0.0:1883");
            // THIS IS WHERE WE CONNECT KAPUA WITH BROKER
            //  broker.setPlugins(new BrokerPlugin[new KapuaBrokerPlugin()]);
            broker.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            broker.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}