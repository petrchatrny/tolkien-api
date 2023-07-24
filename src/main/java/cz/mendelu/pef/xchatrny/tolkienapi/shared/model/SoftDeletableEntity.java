package cz.mendelu.pef.xchatrny.tolkienapi.shared.model;

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
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
