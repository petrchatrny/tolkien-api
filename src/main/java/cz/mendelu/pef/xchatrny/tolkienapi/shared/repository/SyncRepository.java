package cz.mendelu.pef.xchatrny.tolkienapi.shared.repository;

import cz.mendelu.pef.xchatrny.tolkienapi.shared.model.SoftDeletableEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * abstract repository used for sync of entities
 *
 * @param <E>
 */
public interface SyncRepository<E extends SoftDeletableEntity> {
    /**
     * method for obtaining entities that are not deleted and are created after target timestamp
     *
     * @param dateTime target timestamp
     * @return list of entities created after provided timestamp
     */
    public List<E> findCreatedAfter(LocalDateTime dateTime);

    /**
     * method for obtaining entities that are not deleted and were updated after target timestamp
     *
     * @param dateTime target timestamp
     * @return list of entities updated after provided timestamp
     */
    public List<E> findUpdatedAfter(LocalDateTime dateTime);

    /**
     * method for obtaining entities that are deleted and were deleted after target timestamp
     *
     * @param dateTime target timestamp
     * @return list of deleted entities after provided timestamp
     */
    public List<E> findDeletedAfter(LocalDateTime dateTime);
}
