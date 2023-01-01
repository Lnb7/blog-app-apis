package com.blogappapis.mapper;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostResponse {

    private List<PostMapper> content;
    private int pageNumber;

    private int pageSize;

    private long totalElement;

    private int totalPage;

    private boolean lastPage;
}
