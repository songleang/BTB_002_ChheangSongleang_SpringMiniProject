package com.example.demo1.services.impl;

import com.example.demo1.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo1.repositories.BookRepositories;
import com.example.demo1.services.BookServices;

import java.util.List;

@Service
public class BookServicesImpl implements BookServices{

    BookRepositories bookRepositories;

    @Autowired
    public BookServicesImpl(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }

    @Override
    public List<Book> getAll() {
        return bookRepositories.getAll();
    }

    public BookServicesImpl() {
        super();
    }

    @Override
    public Book findOne(int id) {
        return bookRepositories.findOne(id);
    }

    @Override
    public Boolean update(Book book) {
        return bookRepositories.update(book);
    }

    @Override
    public Boolean delete(int id) {
        return this.bookRepositories.delete(id);
    }

    @Override
    public Boolean save(Book book) {
        return this.bookRepositories.save(book);
    }
}
