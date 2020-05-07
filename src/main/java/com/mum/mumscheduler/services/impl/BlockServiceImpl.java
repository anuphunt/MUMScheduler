package com.mum.mumscheduler.services.impl;


import com.mum.mumscheduler.dao.IBlockDao;
import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.services.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockServiceImpl implements IBlockService {

    @Autowired
    private IBlockDao dao;


    @Override
    public Optional<Block> getBlockById(String id) {
        return dao.getBlockById(id);
    }

    @Override
    public List<Block> getAllBlock() {
        return dao.getAllBlocks();
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    public Block add(Block block) {
        return dao.add(block);
    }

    @Override
    public Block update(Block block, String id) {
        return dao.update(block, id);
    }
}
