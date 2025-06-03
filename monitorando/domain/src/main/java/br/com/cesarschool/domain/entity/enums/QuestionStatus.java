package br.com.cesarschool.domain.entity.enums;

public enum QuestionStatus {
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    ANSWERED("ANSWERED");

    private final String displayName;

    QuestionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
    
}

