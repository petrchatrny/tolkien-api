package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import cz.mendelu.pef.xchatrny.tolkienapi.common.architecture.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "td_source")
public class Source extends BaseEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String name;

    private String url;
}
