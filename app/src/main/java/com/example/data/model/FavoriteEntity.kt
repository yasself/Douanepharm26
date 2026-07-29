package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_products")
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val dciName: String,
    val brandExamples: String, // comma separated
    val category: String,
    val galenicFormConstraint: String,
    val tariffCode: String,
    val dutyRate: Double,
    val isSurtaxed: Boolean,
    val legalJustification: String,
    val noteReference: String,
    val description: String,
    val timestamp: Long = System.currentTimeMillis()
)
