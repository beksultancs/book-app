package peaksoft.bookapp.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import peaksoft.bookapp.models.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
}
