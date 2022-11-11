package com.mycompany.bookauthor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {

    private Long id;
    private String title;
    private String genre;
    private Double price;
    private String description;
    private AuthorDTO author;
}
