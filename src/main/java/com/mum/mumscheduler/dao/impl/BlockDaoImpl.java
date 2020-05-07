package com.mum.mumscheduler.dao.impl;

import com.mum.mumscheduler.dao.IBlockDao;
import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.respository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class BlockDaoImpl implements IBlockDao {

    @Autowired
    private BlockRepository repository;

    @Override
    public Optional<Block> getBlockById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Block> getAllBlocks() {
        return repository.findAll();
    }

    @Override
    public Block add(Block block) {
        return repository.save(block);
    }

    @Override
    public Block update(Block block, String id) {
        if(id!=null)
            return repository.save(block);
        else return null;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
