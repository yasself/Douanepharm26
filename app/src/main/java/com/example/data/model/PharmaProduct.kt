package com.example.data.model

data class PharmaProduct(
    val id: String,
    val dciName: String,
    val brandExamples: List<String>,
    val category: String,
    val galenicFormConstraint: String,
    val tariffCode: String,
    val dutyRate: Double,
    val isSurtaxed: Boolean,
    val legalJustification: String,
    val noteReference: String,
    val description: String,
    val synonyms: List<String> = emptyList()
)
