package com.example.demo1.controller;

import com.example.demo1.models.Book;
import com.example.demo1.services.BookServices;
import com.example.demo1.services.UploadService;
import com.example.demo1.services.impl.BookServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class BookController {
    private  BookServices bookServices;

    @Autowired
    public UploadService uploadService;

    @Autowired
    public BookController (BookServices bookServices){
        this.bookServices=bookServices;
    }
    @GetMapping({"/","index"})
    public String index(ModelMap model) {
        List<Book> bookList=this.bookServices.getAll();
        model.addAttribute("books", bookList);
        return "book/index";
    }

    @GetMapping("view/{id}")
    public String view(@PathVariable("id") Integer id, Model model){
        Book book=this.bookServices.findOne(id);
        model.addAttribute("book", book);
        return "book/view";
    }
    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Integer id, Model model){
        Book book=this.bookServices.findOne(id);

        model.addAttribute("isNew", false);
        model.addAttribute("book", book);
        return "book/create-book";
    }
    @PostMapping("/update/submit")
    public String updateSubmit(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, MultipartFile file, Model model){

        String filename= this.uploadService.singleFileUpload(file, null);

        if (!file.isEmpty()){
            book.setThumbnail("/images-btb/"+filename);
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("isNew", true);
            return "book/create-book";
        }
        this.bookServices.update(book);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        this.bookServices.delete(id);
        return "redirect:/index";
    }

    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("isNew", true);
        model.addAttribute("book", new Book());

        return "book/create-book";
    }

    @PostMapping("/create/submit")
    public String create(@Valid Book book, BindingResult bindingResult, MultipartFile file, Model model){

        String filename= this.uploadService.singleFileUpload(file, null);

        book.setThumbnail("/images-btb/"+filename);

        if(bindingResult.hasErrors()){
            model.addAttribute("isNew", true);
            return "book/create-book";
        }else{
            bookServices.save(book);
            return "redirect:/index";
        }

    }


//    @GetMapping("/test-multi-upload")
}
