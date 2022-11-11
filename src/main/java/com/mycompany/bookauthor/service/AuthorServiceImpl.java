package com.mycompany.bookauthor.service;

import com.mycompany.bookauthor.dto.AuthorDTO;
import com.mycompany.bookauthor.entity.AuthorEntity;
import com.mycompany.bookauthor.exception.BusinessException;
import com.mycompany.bookauthor.exception.ErrorDTO;
import com.mycompany.bookauthor.repository.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Long register(AuthorDTO authorDTO) {

        Optional<AuthorEntity> optAuth = authorRepository.findByEmail(authorDTO.getEmail());
        if(optAuth.isPresent()){
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setCode("DUP_USER");
            errorDTO.setMessage("Author with this email already exist");

            List<ErrorDTO> errorDTOList = new ArrayList<>();
            errorDTOList.add(errorDTO);

            throw new BusinessException(errorDTOList);
        }
        AuthorEntity authorEntity = new AuthorEntity();
        BeanUtils.copyProperties(authorDTO, authorEntity);
        authorEntity = authorRepository.save(authorEntity);
        return authorEntity.getId();
    }

    @Override
    public AuthorDTO getAuthorDetail(Long authorId) {
        AuthorEntity ae = authorRepository.findById(authorId).get();
        AuthorDTO authorDTO = new AuthorDTO();
        BeanUtils.copyProperties(ae, authorDTO);
        authorDTO.setPassword(null);
        return authorDTO;
    }

}
