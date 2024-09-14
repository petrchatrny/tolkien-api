package cz.mendelu.pef.xchatrny.tolkienapi.feature.word;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseEntity;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.language.Language;
import cz.mendelu.pef.xchatrny.tolkienapi.feature.source.Source;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "td_word")
public class Word extends BaseEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
