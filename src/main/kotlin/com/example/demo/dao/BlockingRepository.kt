package com.example.demo.dao

import com.example.demo.entity.BlockingEntity
import org.springframework.data.repository.CrudRepository

interface BlockingRepository : CrudRepository<BlockingEntity, Long>
