package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.Language;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.model.SoftDeletableEntity;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.Source;
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
    @JoinColumn(name = "id_language", nullable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "id_source")
    private Source source;
}
