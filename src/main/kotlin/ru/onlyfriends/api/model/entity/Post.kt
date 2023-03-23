package ru.onlyfriends.api.model.entity

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class Post(
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator::class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne( fetch = FetchType.LAZY)
    val author: User,
    @Column(length = 255)
    var text: String
) : AbstractEntity()