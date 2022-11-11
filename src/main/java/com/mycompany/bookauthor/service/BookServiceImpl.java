package com.mycompany.bookauthor.service;

import com.mycompany.bookauthor.dto.BookDTO;
import com.mycompany.bookauthor.entity.AuthorEntity;
import com.mycompany.bookauthor.entity.BookEntity;
import com.mycompany.bookauthor.repository.AuthorRepository;
import com.mycompany.bookauthor.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Long addBook(Long authorId, BookDTO bookDTO) {
        AuthorEntity authorEntity = authorRepository.findById(authorId).get();
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(bookDTO, bookEntity);
        bookEntity.setAuthor(authorEntity);

        bookEntity = bookRepository.save(bookEntity);
        return bookEntity.getId();
    }

    @Override
    public List<BookDTO> getAllBooks(Long authorId) {
        List<BookEntity> bookEntities =  bookRepository.findAllByAuthorId(authorId);
        List<BookDTO> bookDTOList = null;
        if(bookEntities != null && bookEntities.size() > 0){
            BookDTO bookDTO = null;
            bookDTOList = new ArrayList<>();
            for(BookEntity be: bookEntities){
                bookDTO = new BookDTO();
                BeanUtils.copyProperties(be, bookDTO);
                bookDTOList.add(bookDTO);
            }
        }
        return bookDTOList;
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) {

        Optional<BookEntity> optEn =  bookRepository.findById(bookId);
        BookDTO dto = null;
        if(optEn.isPresent()){
            dto = new BookDTO();
            BookEntity be = optEn.get();//data from database
            be.setTitle(bookDTO.getTitle());
            be.setGenre(bookDTO.getGenre());
            be.setPrice(bookDTO.getPrice());
            be.setDescription(bookDTO.getDescription());
            BeanUtils.copyProperties(be, dto);
            bookRepository.save(be);
        }
        return dto;
    }

    @Override
    public BookDTO updateBookPrice(Long bookId, Double newPrice) {

        Optional<BookEntity> optEn =  bookRepository.findById(bookId);
        BookDTO dto = null;
        if(optEn.isPresent()){
            dto = new BookDTO();
            BookEntity be = optEn.get();//data from database
            be.setPrice(newPrice);
            BeanUtils.copyProperties(be, dto);
            bookRepository.save(be);
        }
        return dto;
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<BookDTO> searchBooksByTitle(String title) {
        List<BookEntity> bookEntities =  bookRepository.findByTitleContains(title);
        List<BookDTO> bookDTOList = null;
        if(bookEntities != null && bookEntities.size() > 0){
            BookDTO bookDTO = null;
            bookDTOList = new ArrayList<>();
            for(BookEntity be: bookEntities){
                bookDTO = new BookDTO();
                BeanUtils.copyProperties(be, bookDTO);
                bookDTOList.add(bookDTO);
            }
        }
        return bookDTOList;
    }


}
