package ru.onlyfriends.api.service

import ru.onlyfriends.api.model.dto.request.ApiRequest
import ru.onlyfriends.api.model.entity.AbstractEntity

interface CrudService<Request : ApiRequest, Model : AbstractEntity, Id : Any> {

    fun create(request: Request): Model
    fun findAll(): Iterable<Model>
    fun findById(id: Id): Model

    fun delete(id: Id)

    // post
    // patch
    // delete
}