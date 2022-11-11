package com.mycompany.bookauthor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
@Getter
@Setter
@NoArgsConstructor
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String password;
    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private List<BookEntity> books;
}
