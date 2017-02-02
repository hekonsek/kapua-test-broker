package kapua.test.broker;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MockDevice {

    private MqttClient sampleClient;

    public MockDevice start() {
        String broker = "tcp://localhost:1883";
        String clientId = "kapua-test-client";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);

            return this;
        } catch (MqttException me) {
            throw new RuntimeException(me);
        }
    }

    public void stop() {
        try {
            sampleClient.disconnect();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendData(String key, String value) {
        try {
            String payload = key + value;
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(2);
            sampleClient.publish("dataservice.store", message);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

}