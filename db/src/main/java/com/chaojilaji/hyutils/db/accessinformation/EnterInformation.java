package com.chaojilaji.hyutils.db.accessinformation;

import java.time.LocalDateTime;

public class EnterInformation {
    private String message;
    private LocalDateTime inTime;
    private Long previousHistoryId; // 流传出来到这里的历史Id

    public Long getPreviousHistoryId() {
        return previousHistoryId;
    }

    public void setPreviousHistoryId(Long previousHistoryId) {
        this.previousHistoryId = previousHistoryId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }
}
