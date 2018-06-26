package com.example.demo1.repositories;

import com.github.javafaker.Faker;
import com.example.demo1.models.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepositories {
//-------get Data from DATABASE---------
    @Select("select * from tb_book")
    List<Book> getAll();

    @Select("select * from tb_book where id=#{id}")
    Book findOne(Integer id);

    @Update("update tb_book set title=#{title}, author=#{author}, publisher=#{publisher}, thumbnail=#{thumbnail} where id=#{id}")
    boolean update(Book book);

    @Delete("delete from tb_book where id=#{id}")
    boolean delete(Integer id);

    @Insert("insert into tb_book(title, author, publisher, thumbnail) values(#{title}, #{author}, #{publisher}, #{thumbnail})")
    boolean save(Book book);
//---------------------------------------
//    Faker faker = new Faker();
//    List<Book> booklist = new ArrayList();
//
//    {
//        for (int i = 1; i < 11; i++) {
//            Book book = new Book();
//            book.setId(i);
//            book.setTitle(faker.book().title());
//            book.setAuthor(faker.book().author());
//            book.setPublisher(faker.book().publisher());
//            booklist.add(book);
//        }
//    }
//
//    public List<Book> getAll() {
//        return booklist;
//    }
//
//    public Book findOne(int id){
//        for (int i=0; i<booklist.size(); i++){
//            if(booklist.get(i).getId()==id){
//                return booklist.get(i);
//            }
//        }
//        return null;
//    }
//    public boolean update(Book book){
//        for(int i = 0; i<booklist.size(); i++){
//            if (booklist.get(i).getId() == book.getId()) {
//                booklist.set(i, book);
//                return true;
//            }
//        }
//        return false;
//    }
//    public boolean delete(Integer id){
//        for (int i=0; i<booklist.size();i++){
//            if (booklist.get(i).getId()==id){
//                booklist.remove(i);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean save(Book book){
//        boolean isSave = false;
//        if (booklist.add(book)) {
//            isSave = true;
//        }
//        return isSave;
//    }


}
