package {{directory_path_code}}.samples.kafka.producer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import {{directory_path_code}}.samples.kafka.BookEvent;
import {{directory_path_code}}.samples.kafka.base.KafkaIntegrationTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
/**
 * EmbeddedKafkaBroker does not provide a mechanism to programmatically clean up the records of a topic,
 * so it becomes necessary to recreate the ApplicationContext after executing each test method.
 * @see  https://github.com/spring-projects/spring-kafka/issues/1114#issuecomment-499482212
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EmbeddedKafka(
        topics = "${spring.kafka.producer.topic}",
        partitions = 1,
        bootstrapServersProperty = "spring.kafka.bootstrap-servers"
)
class BookProducerTest {
    @Autowired
    private BookProducer producer;

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    @Autowired
    private KafkaIntegrationTest kafkaIntegrationTest;


    @Test
    @DisplayName("should contain a message in the books topic")
    void t1() throws Exception {
        //scenery
        BookEvent bookEvent = new BookEvent(UUID.randomUUID(),"Domain Drive Design", "DDD da massa", BigDecimal.TEN);

        //action
        producer.send(bookEvent);

        //validation
        List<ConsumerRecord<String, BookEvent>> records = kafkaIntegrationTest.getRecords(
                topic,
                BookEvent.class,
                Duration.ofSeconds(3)
        );

        assertThat(records)
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparator()
                .extracting(ConsumerRecord::value)
                .containsExactlyInAnyOrder(bookEvent);


    }

    @Test
    @DisplayName("must not contain a message in the books topic")
    void t2() {
        //scnary, action and validation
        int count = kafkaIntegrationTest.countRecordsInTopic(topic);

        assertThat(count)
                .isEqualTo(0);
    }
    
    @Test
    @DisplayName("should not send a null message")
    void t3() {
        //action   and validation
        ConstraintViolationException violations = assertThrows(
                ConstraintViolationException.class,
                () -> producer.send(null)
        );

        assertThat(violations)
                .hasMessage("send.book: must not be null");


    }

    @Test
    @DisplayName("should not send invalid message")
    void t4() {
        //scenary
        BookEvent bookEvent = new BookEvent(null, null, null, null);

        //action and validation
        ConstraintViolationException violations = assertThrows(
                ConstraintViolationException.class,
                () -> producer.send(bookEvent)
        );

        assertThat(violations)                               
                .hasMessageContainingAll(
                        "send.book.price: must not be null",
                        "send.book.id: must not be null",
                        "send.book.description: must not be blank",
                        "send.book.title: must not be blank"
                );
    }    
                                                                     
                                                                                     
}
