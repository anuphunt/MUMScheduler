package com.mum.mumscheduler.services.impl;

import com.mum.mumscheduler.dao.IBlockDao;
import com.mum.mumscheduler.dao.IEntryDao;
import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.models.Entry;
import com.mum.mumscheduler.services.IEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements IEntryService {

    @Autowired
    private IEntryDao dao;

    @Override
    public Optional<Entry> getEntryByIntake(String intake) {
        return dao.getEntryByIntake(intake);
    }

    @Override
    public List<Entry> getAllEntry() {
        return dao.getAllEntry();
    }

    @Override
    public void delete(String intake) {
        dao.delete(intake);

    }

    @Override
    public Entry add(Entry intake) {
        return dao.add(intake);
    }

    @Override
    public Entry update(Entry entry, String intake) {
        return dao.update(entry,intake);
    }


}
