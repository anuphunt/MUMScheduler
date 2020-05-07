package com.mum.mumscheduler.services;

import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.models.Student;

import java.util.List;
import java.util.Optional;

public interface IBlockService {

    Optional<Block> getBlockById(String id);
    List<Block> getAllBlock();
    void delete(String id);
    Block add(Block block);
    Block update(Block block, String id);
}
