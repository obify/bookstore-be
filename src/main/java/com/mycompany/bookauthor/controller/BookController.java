package com.mycompany.bookauthor.controller;

import com.mycompany.bookauthor.dto.BookDTO;
import com.mycompany.bookauthor.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/authors/{authorId}/books")
    public ResponseEntity<Long> addBook(@PathVariable Long authorId, @RequestBody BookDTO bookDTO){
        Long bookId = bookService.addBook(authorId, bookDTO);
        ResponseEntity re = new ResponseEntity(authorId, HttpStatus.CREATED);
        return re;
    }

    @GetMapping("/authors/{authorId}/books")
    public ResponseEntity<List<BookDTO>> getAllBooks(@PathVariable Long authorId){
        List<BookDTO> bookDTOList = bookService.getAllBooks(authorId);
        ResponseEntity re = new ResponseEntity(bookDTOList, HttpStatus.OK);
        return re;
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO){
        bookDTO = bookService.updateBook(bookId, bookDTO);
        ResponseEntity<BookDTO> responseEntity = new ResponseEntity<>(bookDTO, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/books/update-price/{bookId}")
    public ResponseEntity<BookDTO> updateBookPrice(@RequestBody BookDTO bookDTO, @PathVariable Long bookId){
        bookDTO = bookService.updateBookPrice(bookId, bookDTO.getPrice());
        ResponseEntity<BookDTO> responseEntity = new ResponseEntity<>(bookDTO, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<BookDTO>> searchBooksByTitle(@RequestParam String searchText){
        List<BookDTO> bookDTOList = bookService.searchBooksByTitle(searchText);
        ResponseEntity re = new ResponseEntity(bookDTOList, HttpStatus.OK);
        return re;
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}
