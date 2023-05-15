package ru.onlyfriends.api.model.entity.like

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime
import ru.onlyfriends.api.model.entity.ApiEntity

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Likable : ApiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    override val id: Long = 0

    @Column(nullable = false, updatable = false)
    override val createdAt: LocalDateTime = LocalDateTime.now()

    @OneToMany(mappedBy = "target")
    @JsonIgnore
    open var likes: MutableList<Like> = mutableListOf()

    @get:Transient
    val likesCount: Int
        get() = likes.size

}