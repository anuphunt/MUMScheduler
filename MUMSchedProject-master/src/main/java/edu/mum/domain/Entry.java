package edu.mum.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Entry {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String entryMonth;
	private int numOfFpp;
	private int numOfMpp;
	private int numOfFppOpt;
	private int numOfMppOpt;
	private int numOfUSstudents;

	@OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Block> blocks;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
	
	public String getEntryMonth() {
		return entryMonth;
	}

	public void setEntryMonth(String entryMonth) {
		this.entryMonth = entryMonth;
	}

	public int getNumOfFpp() {
		return numOfFpp;
	}

	public void setNumOfFpp(int numOfFpp) {
		this.numOfFpp = numOfFpp;
	}

	public int getNumOfMpp() {
		return numOfMpp;
	}

	public void setNumOfMpp(int numOfMpp) {
		this.numOfMpp = numOfMpp;
	}

	public int getNumOfUSstudents() {
		return numOfUSstudents;
	}

	public void setNumOfUSstudents(int numOfUSstudents) {
		this.numOfUSstudents = numOfUSstudents;
	}

	public int getNumOfFppOpt() {
		return numOfFppOpt;
	}

	public void setNumOfFppOpt(int numOfFppOpt) {
		this.numOfFppOpt = numOfFppOpt;
	}

	public int getNumOfMppOpt() {
		return numOfMppOpt;
	}

	public void setNumOfMppOpt(int numOfMppOpt) {
		this.numOfMppOpt = numOfMppOpt;
	}
	
}
