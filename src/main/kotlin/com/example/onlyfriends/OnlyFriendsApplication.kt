package com.example.onlyfriends

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
//@EnableJpaRepositories
//@EntityScan("kotlin/com/example/onlyfriends/model/table")
class OnlyFriendsApplication

fun main(args: Array<String>) {
    runApplication<OnlyFriendsApplication>(*args)
}
