package com.mum.mumscheduler.dao.impl;

import com.mum.mumscheduler.dao.IBlockDao;
import com.mum.mumscheduler.dao.IEntryDao;
import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.models.Entry;
import com.mum.mumscheduler.respository.BlockRepository;
import com.mum.mumscheduler.respository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class EntryDaoImpl implements IEntryDao {

    @Autowired
    private EntryRepository repository;


    @Override
    public Optional<Entry> getEntryByIntake(String intake) {
        return repository.findById(intake);
    }
    @Override
    public List<Entry> getAllEntry() {
        return repository.findAll();
    }

    @Override
    public Entry add(Entry entry) {
        return repository.save(entry);
    }

    @Override
    public Entry update(Entry entry, String intake) {
        if(intake!=null)
            return repository.save(entry);
        else return null;
    }

    @Override
    public void delete(String intake) {
        repository.deleteById(intake);
    }
}
