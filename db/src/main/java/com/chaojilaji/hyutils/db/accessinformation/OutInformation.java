package com.chaojilaji.hyutils.db.accessinformation;

import java.time.LocalDateTime;

public class OutInformation {
    private String message;
    private LocalDateTime outTime;
    private Long nextHistoryId; // 流转到的历史节点Id

    public Long getNextHistoryId() {
        return nextHistoryId;
    }

    public void setNextHistoryId(Long nextHistoryId) {
        this.nextHistoryId = nextHistoryId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }
}
