package com.example.demo.dao

import com.example.demo.entity.IoEntity
import org.springframework.data.repository.CrudRepository

interface IoRepository: CrudRepository<IoEntity, Long>
