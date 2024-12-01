package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.model.Book;
import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("isMemberService")
@RequiredArgsConstructor
@PropertySource("classpath:values.properties")
public class ISMemberServiceImpl implements MemberService{

    @Value("${service.book.query.romance_lover}")
    private String findRomanceLoverSql;

    @Value("${service.book.query.no_books}")
    private String noBooksMemberSql;

    private final JdbcTemplate jdbcTemplate;
    private final ISBookServiceImpl isBookService;

    @Override
    public Member findMember(){
        Member member = jdbcTemplate.queryForObject(findRomanceLoverSql, new BeanPropertyRowMapper<>(Member.class));
        populateBooks(member);
        return member;
    }

    @Override
    public List<Member> findMembers(){
        return jdbcTemplate.query(noBooksMemberSql, new BeanPropertyRowMapper<>(Member.class));
    }

    private void populateBooks(Member member){
        List<Book> books = isBookService.getAllMemberBooks(member);
        if(!Objects.isNull(books) && !books.isEmpty()) member.setBorrowedBooks(books);
    }

}
