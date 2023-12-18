package com.example.demo.dao

import com.example.demo.entity.MDCCEntity
import org.springframework.data.repository.CrudRepository

interface MDCCRepository : CrudRepository<MDCCEntity, Long>
