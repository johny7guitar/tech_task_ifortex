package com.ifortex.bookservice.utils;

import com.ifortex.bookservice.dto.GenreStatistics;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@PropertySource("classpath:values.properties")
public class StatisticMapper implements RowMapper<GenreStatistics>{

    @Value("${service.book.query.statistic.genre}")
    private String genreColumn;

    @Value("${service.book.query.statistic.count}")
    private String countColumn;

    @Override
    public GenreStatistics mapRow(ResultSet rs, int rowNum) throws SQLException{
        return GenreStatistics.builder()
                .genre(rs.getString(genreColumn))
                .count(rs.getLong(countColumn))
                .build();
    }

}
