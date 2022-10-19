package {{directory_path_code}}.samples.kafka.consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import {{directory_path_code}}.samples.kafka.BookEvent;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EmbeddedKafka(
        topics = "${spring.kafka.consumer.topic}",
        partitions = 1,
        bootstrapServersProperty = "spring.kafka.bootstrap-servers"
)
class BookConsumerTest {
    @Autowired
    private KafkaTemplate<String, BookEvent> template;
    @Autowired
    private BookConsumer consumer;
    @Autowired
    private BookRepository repository;
    @Value("${spring.kafka.consumer.topic}")
    private String topic;


    @Test
    @DisplayName("should consume a message in the books topic")
    void t1() {
        //scenery
        BookEvent bookEvent = new BookEvent(UUID.randomUUID(), "Clean Code", "Code Clean", BigDecimal.ONE);
        template.send(topic, bookEvent);

        //action
        /**
         * O message consumption is being done async
         */

        //validation
        await().atMost(Duration.ofSeconds(3))
                .untilAsserted(() -> {
                    assertThat(repository.findAll())
                            .hasSize(1)
                            .usingRecursiveFieldByFieldElementComparator()
                            .containsExactly(bookEvent.toModel());

                });
    }

    @Test
    @DisplayName("should not consume invalid messages")
    void t2() {
        //scenery
        BookEvent bookEvent = new BookEvent(null, null, null, null);
        template.send(topic, bookEvent);

        //action
        /**
         * O message consumption is being done async
         */

        //validation
        await().atMost(Duration.ofSeconds(3))
                .untilAsserted(() -> {
                    assertThat(repository.count())
                            .isEqualTo(0);
                });
    }
}