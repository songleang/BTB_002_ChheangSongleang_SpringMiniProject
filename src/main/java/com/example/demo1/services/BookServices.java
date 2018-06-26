package com.example.demo1.services;

import com.example.demo1.models.Book;

import java.util.List;

public interface BookServices {
    List<Book> getAll();
    Book findOne(int id);
    Boolean update(Book book);
    Boolean delete(int id);
    Boolean save(Book book);
}

