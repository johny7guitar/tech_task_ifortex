package com.ifortex.bookservice.controller;

import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/v1/members")
@AllArgsConstructor
public class MemberController {

  @Qualifier("isMemberService") private final MemberService isMemberService;

  @GetMapping("amateur")
  public Member findMember() {
    return isMemberService.findMember();
  }

  @GetMapping
  public List<Member> findMembers() {
    return isMemberService.findMembers();
  }
}
