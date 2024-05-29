package com.crud.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
