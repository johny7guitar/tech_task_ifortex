package com.ifortex.bookservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenreStatistics{

    private String genre;
    private Long count;

}
