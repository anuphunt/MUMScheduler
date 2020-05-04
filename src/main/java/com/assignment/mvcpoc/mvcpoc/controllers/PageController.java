package com.assignment.mvcpoc.mvcpoc.controllers;

import com.assignment.mvcpoc.mvcpoc.models.Page;
import com.assignment.mvcpoc.mvcpoc.services.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private IPageService service;

    @GetMapping("/{id}")
    public Page getPageById(@PathVariable("id") long id){
        return service.getPageById(id);
    }


    @GetMapping("/")
    public List<Page> getAllPageList(){
        return service.getAllList();
    }



    @PostMapping("/")
    public Page addPage(@RequestBody Page page){
        return service.add(page);
    }


    @PutMapping("/{id}")
    public Page updatePage(@PathVariable("id") long id,@RequestBody Page page){
        return service.update(page,id);
    }


    @DeleteMapping
    public void delete(long id){
        service.delete(id);
    }
}
