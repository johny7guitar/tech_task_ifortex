package com.ifortex.bookservice.controller;

import com.ifortex.bookservice.dto.SearchCriteria;
import com.ifortex.bookservice.model.Book;
import com.ifortex.bookservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("api/v1/books")
@AllArgsConstructor
public class BookController {

  @Qualifier("ISBookService") private final BookService isBookService;

  @GetMapping("/statistic")
  public Map<String, Long> getStatistic() {
    return isBookService.getBooks();
  }

  @GetMapping("search")
  public List<Book> findBooks(@RequestBody @Nullable SearchCriteria searchCriteria) {
    return isBookService.getAllByCriteria(searchCriteria);
  }
}
