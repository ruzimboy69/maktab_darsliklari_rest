package com.example.maktab_darsliklari_rest.dto;

import com.example.maktab_darsliklari_rest.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClassDTO {
    private String name;


    private List<Integer> booksId;
}
