package com.ifortex.bookservice.utils;

import com.ifortex.bookservice.dto.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:values.properties")
public class BookSearchQueryUtil{

    private final SearchCriteriaMapper searchCriteriaMapper;

    @Value("${service.book.query.date_format}")
    private String dateFormat;

    @Value("${service.book.query.year}")
    private String yearColumn;

    @Value("${service.book.query.genre}")
    private String genreColumn;

    public String getQuery(SearchCriteria criteria){

        Map<String, Object> searchParams = searchCriteriaMapper.toMap(criteria);

        StringBuilder sql = new StringBuilder("select * from ").append("books ");
        if(!searchParams.isEmpty()) sql.append(" where ");

        sql.append(searchParams.entrySet().stream()
                .map(entry -> {
                    if(entry.getKey().equals(yearColumn)){
                        return String.format("to_char(%s, '%s') like '%%%s%%'", entry.getKey(), dateFormat, entry.getValue());
                    }else if(entry.getKey().equals(genreColumn)){
                        return String.format("array_to_string(%s, ' ') ilike '%%%s%%'", genreColumn, entry.getValue());
                    }else{
                        return String.format("%s ilike '%%%s%%'", entry.getKey(), entry.getValue());
                    }
                })
                .collect(Collectors.joining(" or ")));

        sql.append(" order by ").append(yearColumn).append(" desc");

        return sql.toString();

    }

}
