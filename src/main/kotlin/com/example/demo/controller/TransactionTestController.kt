package com.example.demo.controller

import com.example.demo.dao.IoRepository
import com.example.demo.dao.LoomRepository
import com.example.demo.service.TestService
import kotlinx.coroutines.reactor.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.scheduler.Scheduler

@RestController
@RequestMapping("/test")
class TransactionTestController(
    private val ioRepository: IoRepository,
    private val loomRepository: LoomRepository,
    private val testService: TestService,
    private val blockingScheduler: Scheduler
) {

    @GetMapping("/reset")
    fun reset() {
        ioRepository.deleteAll()
        loomRepository.deleteAll()
    }

    @GetMapping("/start")
    suspend fun start() {
        testService.run {
            loom()
            io()
            blocking()
            mDCC()
        }
    }

    @GetMapping("/start-mdc")
    suspend fun startMdc() {
        testService.mDCC()
    }

    @GetMapping("/block")
    suspend fun block() = withContext(blockingScheduler.asCoroutineDispatcher()) {
        testService.saveBlockingThrow()
    }
}