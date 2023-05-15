package ru.onlyfriends.api.service

import ru.onlyfriends.api.model.dto.request.ApiRequest
import ru.onlyfriends.api.model.entity.AbstractEntity
import ru.onlyfriends.api.model.entity.ApiEntity

interface CrudService<Request : ApiRequest, Model : ApiEntity, Id : Any> {

    fun create(request: Request): Model
    fun findAll(): Iterable<Model>
    fun findById(id: Id): Model

    fun delete(id: Id)

    // post
    // patch
    // delete
}