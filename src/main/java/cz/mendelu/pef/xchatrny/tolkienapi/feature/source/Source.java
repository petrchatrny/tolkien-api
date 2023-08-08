package cz.mendelu.pef.xchatrny.tolkienapi.feature.source;

import cz.mendelu.pef.xchatrny.tolkienapi.shared.model.SoftDeletableEntity;
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
@Table(name = "source")
public final class Source extends SoftDeletableEntity {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_source")
    private UUID id;

    @NotNull
    private String name;

    private String url;
}
