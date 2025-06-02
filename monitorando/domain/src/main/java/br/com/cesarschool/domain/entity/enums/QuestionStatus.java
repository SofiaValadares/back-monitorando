package br.com.cesarschool.domain.entity.enums;

public enum QuestionStatus {
    PENDING("Pending"),
    IN_PROGRESS("In progress"),
    ANSWERED("Answered");

    private final String displayName;

    QuestionStatus(String displayName) {
        this.displayName = displayName;
    }
    
}

