package com.ifortex.bookservice.utils;

import com.ifortex.bookservice.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class BookPropertyRowMapper implements RowMapper<Book>{

    private final BeanPropertyRowMapper<Book> defaultMapper;

    public BookPropertyRowMapper(){
        this.defaultMapper = new BeanPropertyRowMapper<>(Book.class);
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException{

        Book book = defaultMapper.mapRow(rs, rowNum);

        String genres = rs.getString("genre");
        if(!Objects.isNull(genres) && !genres.isBlank()){
            genres = genres.substring(1, genres.length() - 1);
            book.setGenres(Set.of(Stream.of(genres.split(",")).map(String::trim).toArray(String[]::new)));
        }

        return book;

    }

}
