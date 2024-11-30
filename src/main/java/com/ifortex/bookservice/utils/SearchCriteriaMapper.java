package com.ifortex.bookservice.utils;

import com.ifortex.bookservice.dto.SearchCriteria;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@PropertySource("classpath:values.properties")
public class SearchCriteriaMapper{

    @Value("${service.book.query.author}")
    private String author;

    @Value("${service.book.query.description}")
    private String description;

    @Value("${service.book.query.genre}")
    private String genre;

    @Value("${service.book.query.title}")
    private String title;

    @Value("${service.book.query.year}")
    private String year;

    public Map<String, Object> toMap(SearchCriteria criteria){

        if(Objects.isNull(criteria)) return Collections.emptyMap();

        Map<String, Object> result = new HashMap<>();
        map(result, author, criteria.getAuthor());
        map(result, description, criteria.getDescription());
        map(result, title, criteria.getTitle());
        map(result, genre, criteria.getGenre());
        map(result, year, criteria.getYear());
        return result;

    }

    private void map(Map<String, Object> dst, String key, String val){
        if(val != null && !val.isBlank()){
            dst.put(key, val);
        }
    }

    private void map(Map<String, Object> dst, String key, Integer val){
        if(val != null){
            dst.put(key, val);
        }
    }

}
