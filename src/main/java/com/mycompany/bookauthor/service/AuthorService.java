package com.mycompany.bookauthor.service;

import com.mycompany.bookauthor.dto.AuthorDTO;

public interface AuthorService {
    Long register(AuthorDTO authorDTO);
    AuthorDTO getAuthorDetail(Long authorId);
}
