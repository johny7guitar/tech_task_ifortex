package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("isMemberService")
@RequiredArgsConstructor
@PropertySource("classpath:values.properties")
public class ISMemberServiceImpl implements MemberService{

    @Value("${service.book.query.romance_lover}")
    private String findRomanceLoverSql;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Member findMember(){
        return jdbcTemplate.queryForObject(findRomanceLoverSql, new BeanPropertyRowMapper<>(Member.class));
    }

    @Override
    public List<Member> findMembers(){
        return List.of();
    }
}
