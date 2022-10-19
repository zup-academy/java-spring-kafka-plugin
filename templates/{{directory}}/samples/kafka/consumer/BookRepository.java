package {{directory_path_code}}.samples.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tip: Here we can use Spring Data JPA to make our lives easier.
 */
@Repository
@Validated
public class BookRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepository.class);
    /**
     * Just a simple In-Memory Database
     */
    private final Map<UUID, Book> DATABASE = new ConcurrentHashMap<>();

    public Book save(@Valid Book book) {
        LOGGER.info(
                "Persisting a new book into database..."
        );
        return DATABASE.put(book.getId(), book);
    }

    public void deleteAll() {
        LOGGER.info(
                "Deleting all books from database..."
        );
        DATABASE.clear();
    }

    public List<Book> findAll() {
        return DATABASE.values()
                .stream()
                .toList();
    }
    public int count() {
        return DATABASE.size();
    }
}
