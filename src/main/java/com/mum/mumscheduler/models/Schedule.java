package com.mum.mumscheduler.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Schedule {
    @Id
    private String id;
    private String userId;
    private Date generatedDate;
    private Entry entry;
    private Block block;
    private Section section;

    public Schedule() {
    }

    public Schedule(String id, String userId, Date generatedDate, Entry entry, Block block, Section section) {
        this.id = id;
        this.userId = userId;
        this.generatedDate = generatedDate;
        this.entry = entry;
        this.block = block;
        this.section = section;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
