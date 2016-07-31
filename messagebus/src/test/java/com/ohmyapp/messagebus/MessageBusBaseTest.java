package com.ohmyapp.messagebus;

import kafka.server.KafkaConfig;
import kafka.server.KafkaServerStartable;
import org.apache.curator.test.TestingServer;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.fail;

/**
 * Created by Emerald on 7/31/2016.
 */
public class MessageBusBaseTest {
    private static final Logger LOGGER = Logger.getLogger(MessageBusBaseTest.class);
    protected static TestingServer zkServer;
    protected static KafkaServerStartable kafkaServer;

    @BeforeClass
    public static void startServer() {
        // start local zookeeper
        try {
            zkServer = new TestingServer(2181, true);
            LOGGER.debug("localhost zookeeper started");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            fail("zookeeper client failed to start");
        }

        // start local kafka server
        KafkaConfig config = getKafkaConfig(getZkConnectString());
        kafkaServer = new KafkaServerStartable(config);
        try {
            kafkaServer.startup();
            LOGGER.debug("localhost kafka started");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            fail("localhost kafka failed to start");
        }
    }

    private static KafkaConfig getKafkaConfig(final String zkConnectString) {
        Properties props = new Properties();
        props.put("zookeeper.connect", zkConnectString);
        props.put("host.name", "127.0.0.1");
        props.put("broker.id", "0");
        props.put("port", "9092");
        props.put("log.dir", "/tmp/kafka-123456");
        props.put("replica.socket.timeout.ms", "1500");
        props.put("controlled.shutdown.enable", "true");
        props.put("auto.create.topics.enable", "true");
        props.put("delete.topic.enable", "true");
        return new KafkaConfig(props);
    }

    protected static String getKafkaBrokerString() {
        return String.format("127.0.0.1:%d", kafkaServer.serverConfig().port());
    }

    protected static String getZkConnectString() {
        return zkServer.getConnectString();
    }

    protected static int getKafkaPort() {
        return kafkaServer.serverConfig().port();
    }

    @AfterClass
    public static void stop() throws IOException {
        kafkaServer.shutdown();
        kafkaServer = null;
        LOGGER.debug("localhost kafka stopped");
        zkServer.stop();
        zkServer = null;
        LOGGER.debug("localhost zookeeper stopped");
    }
}
