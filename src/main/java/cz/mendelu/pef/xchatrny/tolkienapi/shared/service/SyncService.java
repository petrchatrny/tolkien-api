package cz.mendelu.pef.xchatrny.tolkienapi.shared.service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @param <E>
 */
public interface SyncService<E> {
    /**
     * @param dateTime
     * @return
     */
    List<E> getCreatedAfter(LocalDateTime dateTime);

    /**
     * @param dateTime
     * @return
     */
    List<E> getUpdatedAfter(LocalDateTime dateTime);

    /**
     * @param dateTime
     * @return
     */
    List<E> getDeletedAfter(LocalDateTime dateTime);
}
