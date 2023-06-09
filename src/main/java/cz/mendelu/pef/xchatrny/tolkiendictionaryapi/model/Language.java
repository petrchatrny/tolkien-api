package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "languages")
public class Language extends SoftDeletableEntity {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_language")
    private UUID id;

    @NotNull
    private String name;

    private Byte[] icon;

    @NotNull
    private LocalDateTime updatedAt;
}