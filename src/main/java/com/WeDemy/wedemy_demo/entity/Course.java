package com.WeDemy.wedemy_demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "course")
public class Course {
    private String title;
    private String description;


    @Schema(hidden = true)
    @Id
    private String id;

    private Double price;
    private Boolean published;
}
