package ru.makhonya.javalearn.bureaucracy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Document {

    private final UUID documentId;
    private String title;
    private Boolean signature;
    private LocalDateTime creationDate;

    public Document(UUID id, String title, LocalDateTime creationDate) {
        this.documentId = id;
        this.title = title;
        this.signature = false;
        this.creationDate = creationDate;
    }

    public UUID getDocumentId() {
        return documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSignature() {
        this.signature = true;
    }

    public boolean isSigned() {
        return signature != null;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + documentId +
                ", title='" + title + '\'' +
                ", signature='" + signature + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return documentId == document.documentId &&
                Objects.equals(title, document.title) &&
                Objects.equals(signature, document.signature) &&
                Objects.equals(creationDate, document.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, title, signature, creationDate);
    }
}
