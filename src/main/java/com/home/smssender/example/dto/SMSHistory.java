package com.home.smssender.example.dto;

import com.home.smssender.example.domain.SMS;

import java.util.List;

public class SMSHistory {
    private List<SMS> processed;
    private int countProcessed;
    private List<SMS> unprocessed;
    private int countUnprocessed;

    public SMSHistory(List<SMS> processed, List<SMS> unprocessed) {
        this.processed = processed;
        this.countProcessed = processed.size();
        this.unprocessed = unprocessed;
        this.countUnprocessed = unprocessed.size();
    }

    public List<SMS> getProcessed() {
        return processed;
    }

    public void setProcessed(List<SMS> processed) {
        this.processed = processed;
        this.countProcessed = processed.size();
    }

    public int getCountProcessed() {
        return countProcessed;
    }

    public List<SMS> getUnprocessed() {
        return unprocessed;
    }

    public void setUnprocessed(List<SMS> unprocessed) {
        this.unprocessed = unprocessed;
        this.countUnprocessed = unprocessed.size();
    }

    public int getCountUnprocessed() {
        return countUnprocessed;
    }
}
