package edu.mum.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Block {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String blockMonth;
	private Date blockStartDate;
	private Date blockEndDate;
	private int numOfStudents;
	private int blockOrder;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id")
	private Entry entry;
	
	@OneToMany(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Section> sections;
	
	public Block(){
		sections = new ArrayList<Section>();
	}
	
	

	public long getId() {
		return id;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBlockMonth() {
		return blockMonth;
	}

	public void setBlockMonth(String blockMonth) {
		this.blockMonth = blockMonth;
	}

	public Date getBlockStartDate() {
		return blockStartDate;
	}

	public void setBlockStartDate(Date blockStartDate) {
		this.blockStartDate = blockStartDate;
	}

	public Date getBlockEndDate() {
		return blockEndDate;
	}

	public void setBlockEndDate(Date blockEndDate) {
		this.blockEndDate = blockEndDate;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public int getNumOfStudents() {
		return numOfStudents;
	}

	public void setNumOfStudents(int numOfStudents) {
		this.numOfStudents = numOfStudents;
	}

	public int getBlockOrder() {
		return blockOrder;
	}

	public void setBlockOrder(int blockOrder) {
		this.blockOrder = blockOrder;
	}
	
}
