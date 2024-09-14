package cz.procyon.tolkiendict.api.common.architecture;

import org.mapstruct.MappingTarget;

public interface BaseMapper<ENTITY, CREATE, UPDATE, RESPONSE> {

    ENTITY fromCreate(CREATE dto);

    void fromUpdate(UPDATE dto, @MappingTarget ENTITY entity);

    RESPONSE toResponse(ENTITY entity);
}
