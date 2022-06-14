package peaksoft.bookapp.apis;

import org.springframework.web.bind.annotation.*;
import peaksoft.bookapp.dto.request.BookSaveRequest;
import peaksoft.bookapp.dto.response.SimpleResponse;
import peaksoft.bookapp.models.Book;
import peaksoft.bookapp.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookApi {

    private final BookService bookService;

    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    // find all
    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    // find by id
    @GetMapping("/find/{bookId}")
    public Book findById(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }

    // save
    @PostMapping("/save")
    public Book save(@RequestBody BookSaveRequest bookSaveRequest) {
        return bookService.save(bookSaveRequest);
    }

    // delete
    @DeleteMapping("/delete/{bookId}")
    public SimpleResponse deleteBookById(@PathVariable Long bookId) {
        return bookService.deleteById(bookId);
    }

    // update
    @PutMapping("/update/{bookId}")
    public Book updateBookById(@PathVariable Long bookId,
                               @RequestBody BookSaveRequest bookSaveRequest) {
        return bookService.updateBookById(bookId, bookSaveRequest);
    }
}
