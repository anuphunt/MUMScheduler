package com.assignment.mvcpoc.mvcpoc.dao.impl;

import com.assignment.mvcpoc.mvcpoc.dao.IPageDao;
import com.assignment.mvcpoc.mvcpoc.models.Page;
import com.assignment.mvcpoc.mvcpoc.respository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PageDaoImpl implements IPageDao {

    @Autowired
    private PageRepository repository;

    @Override
    public Page getPageById(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Page> getAllList() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {

        repository.delete(id);
    }

    @Override
    public Page add(Page page) {
        return repository.save(page);
    }

    @Override
    public Page update(Page page, long id) {
        Page page1 = repository.findOne(id);
        if(page1.getId()==id){
            return repository.save(page);
        }
        return null;
    }
}
