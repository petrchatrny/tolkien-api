package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "word")
public class Word extends SoftDeletableEntity {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_word")
    private UUID id;

    @NotNull
    private String czechMeaning;

    @NotNull
    private String translation;

    private String tengwar;

    @NotNull
    private Boolean addedByAdmin = true;

    @NotNull
    @ManyToOne
    private Language language;

    @ManyToOne
    private Source source;
}
