package {{directory_path_code}}.samples.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import {{directory_path_code}}.samples.kafka.BookEvent;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Component
public class BookProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookProducer.class);
    private final KafkaTemplate<String, BookEvent> template;
    private final String topico;

    public BookProducer(
            KafkaTemplate<String, BookEvent> template,
            @Value("${spring.kafka.producer.topic}") String topico
    ) {
        this.template = template;
        this.topico = topico;
    }


    public void send(@Valid @NotNull BookEvent book) {
        template.send(topico, book);
        LOGGER.info("book submmit sucess!{}", book);
    }


}
