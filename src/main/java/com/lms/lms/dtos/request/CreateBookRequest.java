package com.lms.lms.dtos.request;

import com.lms.lms.data.models.Author;
import lombok.Data;

@Data
public class CreateBookRequest {
    private String bookName;
    private int quantity;
    private Long authorId;
    private Long isbn;
    private String yearPublished;

}
