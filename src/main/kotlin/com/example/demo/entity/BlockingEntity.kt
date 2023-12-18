package com.example.demo.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "test_blocking")
@SequenceGenerator(
    name = "test_blocking_id_seq_gen",
    sequenceName = "test_blocking_id_seq",
    allocationSize = 1
)
class BlockingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_blocking_id_seq_gen")
    var id: Long = 0L,

    var uuid: UUID = UUID.randomUUID()
)
