package com.example.demo.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "test_io")
@SequenceGenerator(
    name = "test_io_id_seq_gen",
    sequenceName = "test_io_id_seq",
    allocationSize = 1
)
class IoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_io_id_seq_gen")
    var id: Long = 0L,

    var uuid: UUID = UUID.randomUUID()
)
