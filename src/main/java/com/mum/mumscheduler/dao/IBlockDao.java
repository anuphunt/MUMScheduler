package com.mum.mumscheduler.dao;

import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.models.Student;

import java.util.List;
import java.util.Optional;

public interface IBlockDao {
    Optional<Block> getBlockById(String id);
    List<Block> getAllBlocks();
    Block add(Block block);
    Block update(Block block, String id);
    void delete(String id);
}
