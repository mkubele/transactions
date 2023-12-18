package com.example.demo.service

import com.example.demo.config.Exception
import com.example.demo.config.LOOM
import com.example.demo.dao.BlockingRepository
import com.example.demo.dao.IoRepository
import com.example.demo.dao.LoomRepository
import com.example.demo.dao.MDCCRepository
import com.example.demo.entity.BlockingEntity
import com.example.demo.entity.IoEntity
import com.example.demo.entity.LoomEntity
import com.example.demo.entity.MDCCEntity
import jakarta.transaction.Transactional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactor.asCoroutineDispatcher
import kotlinx.coroutines.slf4j.MDCContext
import kotlinx.coroutines.supervisorScope
import org.springframework.stereotype.Service
import reactor.core.scheduler.Scheduler
import kotlin.time.measureTime

@Service
@Transactional
class TestService(
    private val loomRepository: LoomRepository,
    private val ioRepository: IoRepository,
    private val blockingRepository: BlockingRepository,
    private val mDCCRepository: MDCCRepository,
    private val blockingScheduler: Scheduler
) {

    suspend fun loom() {
        measureTime {
            supervisorScope {
                launch(Dispatchers.LOOM) {
                    loomRepository.save(LoomEntity())
                }
                launch(Dispatchers.LOOM) {
                    saveLoomThrow()
                }
            }
        }.also { println("Loom completed, time: $it") }
    }

    suspend fun mDCC() {
        measureTime {
            supervisorScope {
                launch(MDCContext()) {
                    mDCCRepository.save(MDCCEntity())
                }
                launch(MDCContext()) {
                    saveMDCCThrow()
                }
            }
        }.also { println("MDCContext completed, time: $it") }
    }

    suspend fun io() {
        measureTime {
            supervisorScope {
                launch(Dispatchers.IO) {
                    ioRepository.save(IoEntity())
                }
                launch(Dispatchers.IO) {
                    saveIoThrow()
                }
            }
        }.also { println("IO completed, time: $it") }
    }

    suspend fun blocking() {
        measureTime {
            supervisorScope {
                launch(blockingScheduler.asCoroutineDispatcher()) {
                    loomRepository.save(LoomEntity())
                }
                launch(blockingScheduler.asCoroutineDispatcher()) {
                    saveLoomThrow()
                }
            }
        }.also { println("Blocking completed, time: $it") }
    }

    fun saveIoThrow() {
        ioRepository.save(IoEntity())
        throwException()
    }

    fun saveLoomThrow() {
        loomRepository.save(LoomEntity())
        throwException()
    }

    fun saveMDCCThrow() {
        mDCCRepository.save(MDCCEntity())
        throwException()
    }

    fun saveBlockingThrow() {
        blockingRepository.save(BlockingEntity())
        throwException()
    }

    fun throwException() {
        throw Exception()
    }

}