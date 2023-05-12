package ru.onlyfriends.api.model.entity

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import ru.onlyfriends.api.model.entity.likes.Likable

@Entity
class Post(
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    val author: User,
    @Column(length = 255)
    var text: String
    //TODO var like
) : AbstractEntity(), Likable {
    override val type get() = Likable.ClassTypes.POST  //should be with get or NullPointerException
                                                       // will be caught


}