package {{directory_path_code}}.samples.kafka.base;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * KafkaIntegrationTest is an abstraction to facilitate the creation of integration tests for kafka producers together with EmbeddedKafkaBroker
 * Allows creation of custom consumers for kafka topics
 * Allows reading of records present in kafka topics
 * Allows counting of records present in kafka topics
 */
@Component
public class KafkaIntegrationTest {
    
    @Autowired(required = false)
    private EmbeddedKafkaBroker embeddedKafka;

    /**
     * Create a Consumer for a Topic. Offers extension for Personalized Deserializers for Event Key and Payload.
     *
     * @param topic             topic to be read
     * @param key               class to which the key will be deserialized
     * @param value             class to which the payload will be deserialized
     * @param keyDeserializer   key deserializer
     * @param valueDeserializer payload deserializer
     * @return Return a Consumer
     */
    private <K, V> Consumer<K, V> createConsumer(
            String topic, Class<K> key, Class<V> value, Deserializer keyDeserializer, Deserializer valueDeserializer) {

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(topic, "true", this.embeddedKafka);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        DefaultKafkaConsumerFactory<K, V> consumerFactory = new DefaultKafkaConsumerFactory(
                consumerProps, keyDeserializer, valueDeserializer
        );
        return consumerFactory.createConsumer();
    }

    /**
     * Create a Consumer for a Topic. Uses String and Json Deserializers for Event Key and Payload.
     *
     * @param topic topic to be read
     * @param value class to which the payload will be deserialized
     * @return Return a Consumer
     */
    private <V> Consumer<String, V> createConsumer(String topic, Class<V> value) {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(topic, "true", this.embeddedKafka);
        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        DefaultKafkaConsumerFactory<String, V> consumerFactory = new DefaultKafkaConsumerFactory(
                consumerProps, new StringDeserializer(), new JsonDeserializer<>()
        );
        return consumerFactory.createConsumer();
    }

    /**
     * Create a generic consumer for a Topic
     *
     * @param topic topic to be read
     * @return Return a Consumer
     */
    private Consumer<Object, Object> createConsumer(String topic) {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(topic, "true", this.embeddedKafka);
        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        DefaultKafkaConsumerFactory<Object, Object> consumerFactory = new DefaultKafkaConsumerFactory(
                consumerProps, new StringDeserializer(), new JsonDeserializer<>()
        );
        return consumerFactory.createConsumer();
    }

    /**
     * Get all records for a given topic.
     * This method offers the possibility of extension to custom Deserializers for Event Key and Value.
     *
     * @param topic             topic to be read
     * @param key               class to which the key will be deserialized
     * @param value             class to which the payload will be deserialized
     * @param keyDeserializer   key deserializer
     * @param valueDeserializer payload deserializer
     * @return returns a list with all records present for that topic
     */
    public <K, V> List<ConsumerRecord<K, V>> getRecords(
            String topic, Class<K> key, Class<V> value, Deserializer keyDeserializer, Deserializer valueDeserializer) {


        try (Consumer<K, V> consumer = createConsumer(topic, key, value, keyDeserializer, valueDeserializer)) {

            this.embeddedKafka.consumeFromEmbeddedTopics(consumer, topic);

            Iterable<ConsumerRecord<K, V>> recordsIterable = KafkaTestUtils.getRecords(consumer, 5000);

            return StreamSupport.stream(recordsIterable.spliterator(), false)
                    .collect(Collectors.toList());

        }

    }


    /**
     * Get all records for a given topic in a period of time.
     * This method offers the possibility of extension to custom Deserializers for Event Key and Value.
     *
     * @param topic             topic to be read
     * @param key               class to which the key will be deserialized
     * @param value             class to which the payload will be deserialized
     * @param keyDeserializer   key deserializer
     * @param valueDeserializer payload deserializer
     * @param timeOut           determines the time to wait for reading the records in the topic
     * @return a list of all records present for that topic
     */
    public <K, V> List<ConsumerRecord<K, V>> getRecords(
            String topic, Class<K> key, Class<V> value, Deserializer keyDeserializer, Deserializer valueDeserializer, Duration timeOut) {

        try (Consumer<K, V> consumer = createConsumer(topic, key, value, keyDeserializer, valueDeserializer)) {
            this.embeddedKafka.consumeFromEmbeddedTopics(consumer, topic);

            long timeOutEmMilliseconds = TimeUnit.MILLISECONDS.convert(timeOut);
            Iterable<ConsumerRecord<K, V>> recordsIterable = KafkaTestUtils.getRecords(consumer, timeOutEmMilliseconds);

            return StreamSupport.stream(recordsIterable.spliterator(), false)
                    .collect(Collectors.toList());

        }

    }


    /**
     * Get all records for a given topic. This implementation uses String and Json deserializers by default for topic records.
     *
     * @param topic topic to be read
     * @param value class to which the payload will be deserialized
     * @return returns a list of all records present for that topic
     */
    public <V> List<ConsumerRecord<String, V>> getRecords(String topic, Class<V> value) {

        try (Consumer<String, V> consumer = createConsumer(topic, value)) {

            this.embeddedKafka.consumeFromEmbeddedTopics(consumer, topic);

            Iterable<ConsumerRecord<String, V>> recordsIterable = KafkaTestUtils.getRecords(consumer, 5000);

            return StreamSupport.stream(recordsIterable.spliterator(), false)
                    .collect(Collectors.toList());
        }

    }

    /**
     * Get all records of a given topic in a period of time.
     * This implementation uses String and Json deserializers by default for topic records.
     *
     * @param topic   topic to be read
     * @param value   class to which the payload will be deserialized
     * @param timeOut determines the time to wait for reading the records in the topic
     * @return returns a list of all records present for that topic
     */
    public <V> List<ConsumerRecord<String, V>> getRecords(String topic, Class<V> value, Duration timeOut) {

        try (Consumer<String, V> consumer = createConsumer(topic, value)) {

            this.embeddedKafka.consumeFromEmbeddedTopics(consumer, topic);

            long timeOutEmMilliseconds = TimeUnit.MILLISECONDS.convert(timeOut);

            Iterable<ConsumerRecord<String, V>> recordsIterable = KafkaTestUtils.getRecords(consumer, timeOutEmMilliseconds);
            return StreamSupport.stream(recordsIterable.spliterator(), false)
                    .collect(Collectors.toList());
        }

    }

    /**
     * Returns the total number of records present in the topic
     *
     * @param topic topic to be read
     * @return returns an integer with the number of records in the topic
     */
    public int countRecordsInTopic(String topic) {
        try (Consumer<Object, Object> consumer = createConsumer(topic)) {

            this.embeddedKafka.consumeFromEmbeddedTopics(consumer, topic);

            ConsumerRecords<Object, Object> records = KafkaTestUtils.getRecords(consumer, 5000);

            return records.count();
        }


    }

    /**
     * Returns the count of records present in the topic in the time period.
     *
     * @param topic   topic to be read
     * @param timeOut determines the time to wait for counting the records in the topic
     * @return returns an integer with the number of records in the topic
     */
    public int countRecordsInTopic(String topic, Duration timeOut) {
        try (Consumer<Object, Object> consumer = createConsumer(topic)){

            this.embeddedKafka.consumeFromEmbeddedTopics(consumer, topic);

            long timeOutEmMilliseconds = TimeUnit.MILLISECONDS.convert(timeOut);

            ConsumerRecords<Object, Object> records = KafkaTestUtils.getRecords(consumer, timeOutEmMilliseconds);
            return records.count();
        }

    }


}