package com.example.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ReadingListInterface extends JpaRepository<Book,Long> {
    List<Book> findByReader(String reader);

}
