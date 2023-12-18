package com.example.demo.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "test_loom")
@SequenceGenerator(
    name = "test_loom_id_seq_gen",
    sequenceName = "test_loom_id_seq",
    allocationSize = 1
)
class LoomEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_loom_id_seq_gen")
    var id: Long = 0L,

    var uuid: UUID = UUID.randomUUID()
)
