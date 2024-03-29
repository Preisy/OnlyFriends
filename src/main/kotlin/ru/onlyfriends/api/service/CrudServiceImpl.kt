package ru.onlyfriends.api.service

import org.springframework.data.repository.CrudRepository
import ru.onlyfriends.api.model.dto.exception.ResourceNotFoundException
import ru.onlyfriends.api.model.dto.request.ApiRequest
import ru.onlyfriends.api.model.entity.AbstractEntity
import ru.onlyfriends.api.model.entity.ApiEntity
import ru.onlyfriends.api.service.exception.NotCorrespondingToModel

abstract class CrudServiceImpl<
        Request : ApiRequest,
        Model : ApiEntity,
        Id : Any,
        Repository : CrudRepository<Model, Id>>(
    protected val modelSimpleName: String? = null,
) : CrudService<Request, Model, Id> {

    protected abstract val repository: Repository

    override fun create(request: Request): Model {
        val model = request.asModel() as? Model ?: throw NotCorrespondingToModel()
        return repository.save(model)
    }

    override fun findAll(): Iterable<Model> = repository.findAll()
    override fun findById(id: Id): Model = repository.findById(id).orElseThrow {
        notFoundException()
    }

    override fun delete(id: Id) {
        if (!repository.existsById(id)) throw notFoundException()
        repository.deleteById(id)
    }

    private fun notFoundException(): ResourceNotFoundException {
        if (modelSimpleName == null) return ResourceNotFoundException()
        else return ResourceNotFoundException(modelSimpleName)
    }
}