package cz.procyon.tolkiendict.api.feature.word;

import cz.procyon.tolkiendict.api.common.architecture.BaseEntity;
import cz.procyon.tolkiendict.api.feature.language.Language;
import cz.procyon.tolkiendict.api.feature.source.Source;
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
