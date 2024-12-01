package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.dto.SearchCriteria;
import com.ifortex.bookservice.model.Book;
import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.service.BookService;
import com.ifortex.bookservice.utils.BookPropertyRowMapper;
import com.ifortex.bookservice.utils.BookSearchQueryUtil;
import com.ifortex.bookservice.utils.StatisticMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("isBookService")
@RequiredArgsConstructor
@PropertySource("classpath:values.properties")
public class ISBookServiceImpl implements BookService{

    private final JdbcTemplate jdbcTemplate;
    private final StatisticMapper statisticMapper;
    private final BookPropertyRowMapper bookPropertyRowMapper;
    private final BookSearchQueryUtil bookSearchQueryUtil;

    @Value("${service.book.query.statistic}")
    private String statisticsQuery;

    @Value("${service.book.query.books.sql}")
    private String memberBooksQuery;

    @Override
    public Map<String, Long> getBooks(){
        Map<String, Long> result = new LinkedHashMap<>();
        jdbcTemplate.query(statisticsQuery, statisticMapper)
                .stream()
                .forEachOrdered(stat -> result.put(stat.getGenre(), stat.getCount()));
        return result;
    }

    @Override
    public List<Book> getAllByCriteria(SearchCriteria searchCriteria){
        return jdbcTemplate.query(bookSearchQueryUtil.getQuery(searchCriteria), bookPropertyRowMapper);
    }

    public List<Book> getAllMemberBooks(Member member){
        return jdbcTemplate.query(String.format(memberBooksQuery, member.getId()), new BookPropertyRowMapper());
    }

}
