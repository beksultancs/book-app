package peaksoft.bookapp.services;

import org.springframework.stereotype.Service;
import peaksoft.bookapp.dto.request.BookSaveRequest;
import peaksoft.bookapp.dto.response.SimpleResponse;
import peaksoft.bookapp.exceptions.BookNotFoundException;
import peaksoft.bookapp.models.Book;
import peaksoft.bookapp.respositories.BookRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book findById(Long bookId) {

//        boolean exists = bookRepo.existsById(bookId);
//
//        if (!exists) {
//            throw new BookNotFoundException(
//              "Book with id = " + bookId + " not found!"
//            );
//        }
//
//        return bookRepo.getById(bookId);

        return getBookById(bookId);
    }

    private Book getBookById(Long bookId) {
        return bookRepo.findById(bookId).orElseThrow(
                () -> new BookNotFoundException(
                        "Book with id = " + bookId + " not found!"
                )
        );
    }

    public Book save(BookSaveRequest bookSaveRequest) {

        Book book = new Book(
                bookSaveRequest.getName(),
                bookSaveRequest.getAuthor(),
                bookSaveRequest.getPrice()
        );

        return bookRepo.save(book);
    }

    public SimpleResponse deleteById(Long bookId) {

        boolean exists = bookRepo.existsById(bookId);

        if (!exists) {
            throw new BookNotFoundException(
                    "Book with id = " + bookId + " not found!"
            );
        }

        bookRepo.deleteById(bookId);

        return new SimpleResponse(
                "DELETED",
                "Successfully delete book!"
        );
    }

    @Transactional
    public Book updateBookById(Long bookId, BookSaveRequest bookSaveRequest) {

        Book book = getBookById(bookId);

        // name
        String currentName = book.getName();
        String newName = bookSaveRequest.getName();

        if (newName != null && !currentName.equals(newName)) {
            book.setName(newName);
        }

        // author
        String currentAuthorName = book.getAuthor();
        String newAuthorName = bookSaveRequest.getAuthor();

        if (newAuthorName != null && !newAuthorName.equals(currentAuthorName)) {
            book.setAuthor(newAuthorName);
        }

        // price
        book.setPrice(bookSaveRequest.getPrice());

        return book;
    }
}
