package edu.mum.domain;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Schedule {


 	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
 	  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
 	  @JoinColumn (name="entry_id")
	  private Entry entry;
	  private Date generatedDate;
	  private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		id = id;
	}
	public Entry getEntry() {
		return entry;
	}
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	public Date getGeneratedDate() {
		return generatedDate;
	}
	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	  
	  
}
