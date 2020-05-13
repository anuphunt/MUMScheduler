package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Block;
import edu.mum.domain.Entry;
import edu.mum.repository.BlockRepository;



@Service
public class BlockService{
	
	@Autowired
	private BlockRepository blockRepository;
	@Autowired
	private EntryService entryService;
	

	public void saveBlock(Block block, Long entry_id) {
		Entry currentEntry = entryService.getEntry(new Long(entry_id));
		block.setEntry(currentEntry);
		currentEntry.getBlocks().add(block);
		entryService.saveEntry(currentEntry);
	}

	
	public Block getBlock(String blockMonth) {
		return blockRepository.findBlockByBlockMonth(blockMonth);
	}

	
	public List<Block> getAllBlock() {
		return (List<Block>) blockRepository.findAll();
	}

	public Block getBlockById(Long id) {
		return blockRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Illegal argument " + id));
	}

	
	public void deleteBlock(Long id) {
		blockRepository.deleteById(id);
	}

	
	public void updateBlock(Block block) {
		blockRepository.save(block);
		
	}
	
	public List<Block> getAllBlocksByEntryId(Long id){
		return blockRepository.findByEntryId(id);
		
	}
	
}
