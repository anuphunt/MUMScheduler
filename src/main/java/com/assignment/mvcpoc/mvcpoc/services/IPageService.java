package com.assignment.mvcpoc.mvcpoc.services;


import com.assignment.mvcpoc.mvcpoc.models.Page;

import java.util.List;

public interface IPageService {

    Page getPageById(long id);
    List<Page> getAllList();
    void delete(long id);
    Page add(Page page);
    Page update(Page page,long id);
}
