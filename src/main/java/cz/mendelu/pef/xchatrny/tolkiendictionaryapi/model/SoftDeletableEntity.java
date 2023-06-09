package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class SoftDeletableEntity {

    @NotNull
    protected LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

    protected LocalDateTime deletedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
