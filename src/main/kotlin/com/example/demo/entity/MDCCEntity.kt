package com.example.demo.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "test_mdcc")
@SequenceGenerator(
    name = "test_mdcc_id_seq_gen",
    sequenceName = "test_mdcc_id_seq",
    allocationSize = 1
)
class MDCCEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_mdcc_id_seq_gen")
    var id: Long = 0L,

    var uuid: UUID = UUID.randomUUID()
)
