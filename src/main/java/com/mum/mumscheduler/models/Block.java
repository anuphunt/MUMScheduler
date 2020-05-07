package com.mum.mumscheduler.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "block")
public class Block {

    @Id
    private String blockID;
    private String entryName;
    private int FPPNum;
    private int MPPNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    public Block() {

    }

    public Block(String blockID, String entryName, int FPPNum, int MPPNum, String startDate, String endDate) {
        this.blockID = blockID;
        this.entryName = entryName;
        this.FPPNum = FPPNum;
        this.MPPNum = MPPNum;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public int getFPPNum() {
        return FPPNum;
    }

    public void setFPPNum(int FPPNum) {
        this.FPPNum = FPPNum;
    }

    public int getMPPNum() {
        return MPPNum;
    }

    public void setMPPNum(int MPPNum) {
        this.MPPNum = MPPNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
