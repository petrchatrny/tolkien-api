package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.Language;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.Source;
import cz.mendelu.pef.xchatrny.tolkienapi.shared.model.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_word")
    private UUID id;

    @NotNull
    private String czechMeaning;

    @NotNull
    private String translation;

    private String tengwar;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_language", nullable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "id_source")
    private Source source;
}
