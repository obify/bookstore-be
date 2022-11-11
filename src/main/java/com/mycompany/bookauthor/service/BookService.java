package com.mycompany.bookauthor.service;

import com.mycompany.bookauthor.dto.BookDTO;

import java.util.List;

public interface BookService {
    Long addBook(Long authorId, BookDTO bookDTO);
    List<BookDTO> getAllBooks(Long authorId);
    BookDTO updateBook(Long bookId, BookDTO bookDTO);
    BookDTO updateBookPrice(Long bookId, Double newPrice);
    void deleteBook(Long bookId);

    List<BookDTO> searchBooksByTitle(String title);
}
