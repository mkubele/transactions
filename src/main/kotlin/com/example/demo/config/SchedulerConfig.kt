package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import java.util.concurrent.Executors

@Configuration
class SchedulerConfig {

    @Bean
    fun blockingScheduler(): Scheduler = Schedulers.fromExecutor(Executors.newFixedThreadPool(20))
}