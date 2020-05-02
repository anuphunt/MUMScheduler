package com.assignment.mvcpoc.mvcpoc.services.impl;

import com.assignment.mvcpoc.mvcpoc.dao.IPageDao;
import com.assignment.mvcpoc.mvcpoc.models.Page;
import com.assignment.mvcpoc.mvcpoc.services.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements IPageService{


    @Autowired
    private IPageDao dao;

    @Override
    public Page getPageById(long id) {
        return dao.getPageById(id);
    }

    @Override
    public List<Page> getAllList() {
        return dao.getAllList();
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public Page add(Page page) {
        return dao.add(page);
    }

    @Override
    public Page update(Page page, long id) {
        return dao.update(page, id);
    }
}
