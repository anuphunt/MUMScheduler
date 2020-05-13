package edu.mum.service;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.mum.domain.Entry;
import edu.mum.domain.Section;
import edu.mum.repository.EntryRepository;

@Service
public class EntryService {

	@Autowired
	private EntryRepository entryRepository;

	public void saveEntry(Entry entry) {
		/*List<Block> blocks = new ArrayList<Block>();
		String entryMonth = entry.getEntryMonth();
		if(entryMonth.equalsIgnoreCase("January")) {
			//int monthInt = Calendar.JANUARY;
			for(int i=2;i<8;i++) {
				String month = getMonth(i);
				Block block = new Block();
				block.setBlockMonth(month);
				blocks.add(block);
			}
		}
		entry.setBlocks(blocks);*/
		entryRepository.save(entry);
	}
	public String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}

	public Entry getEntry(Long id) {
		return entryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Illegal argument " + id));
	}

	public List<Entry> getAllEntry() {
		return (List<Entry>) entryRepository.findAll();
	}

	public Entry getEntryByMonth(String entryMonth) {
		return entryRepository.findByEntryMonth(entryMonth);
	}

	public void deleteEntry(Long id) {
		entryRepository.deleteById(id);

	}

	public void updateEntry(String entryMonth, int numOfFpp, int numOfMpp, int numOfUSstudents, Long id) {
		entryRepository.update(entryMonth, numOfFpp, numOfMpp, numOfUSstudents, id);
	}
	
	public List<Section> getAllSectionsByEntryId(long id){
		Entry entry = entryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Illegal argument " + id));
		List<Section> sections = new ArrayList<>();
		entry.getBlocks().forEach(b-> sections.addAll(b.getSections()));
		return sections;
	}
}
