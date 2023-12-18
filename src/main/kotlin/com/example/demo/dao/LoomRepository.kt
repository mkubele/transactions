package com.example.demo.dao

import com.example.demo.entity.LoomEntity
import org.springframework.data.repository.CrudRepository

interface LoomRepository : CrudRepository<LoomEntity, Long>
