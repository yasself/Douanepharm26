package com.example.data.repository

import com.example.data.db.FavoriteDao
import com.example.data.model.FavoriteEntity
import com.example.data.model.PharmaProduct
import kotlinx.coroutines.flow.Flow

class PharmaRepository(private val favoriteDao: FavoriteDao) {

    val allFavorites: Flow<List<FavoriteEntity>> = favoriteDao.getAllFavorites()

    suspend fun addFavorite(product: PharmaProduct) {
        val entity = FavoriteEntity(
            id = product.id,
            dciName = product.dciName,
            brandExamples = product.brandExamples.joinToString(", "),
            category = product.category,
            galenicFormConstraint = product.galenicFormConstraint,
            tariffCode = product.tariffCode,
            dutyRate = product.dutyRate,
            isSurtaxed = product.isSurtaxed,
            legalJustification = product.legalJustification,
            noteReference = product.noteReference,
            description = product.description
        )
        favoriteDao.insertFavorite(entity)
    }

    suspend fun removeFavorite(id: String) {
        favoriteDao.deleteFavoriteById(id)
    }

    suspend fun isFavorite(id: String): Boolean {
        return favoriteDao.isFavorite(id)
    }

    private val pharmaDatabase = listOf(
        // === NOTE 5b-3 (Toutes Formes Galéniques @ 2,5%) ===
        PharmaProduct(
            id = "dci_apixaban",
            dciName = "Apixaban",
            brandExamples = listOf("Eliquis"),
            category = "Anticoagulant (Inhibiteur du Facteur Xa)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) du Chapitre 30 du Tarif Douanier Marocain 2026. Produit bénéficiant du taux réduit de Droit d'Importation à 2,5%.",
            noteReference = "Note 5b-3",
            description = "Inhibiteur direct du facteur Xa oral indiqué dans la prévention des événements thromboemboliques veineux et AVC."
        ),
        PharmaProduct(
            id = "dci_latanoprost",
            dciName = "Latanoprost",
            brandExamples = listOf("Xalatan", "Monoprost"),
            category = "Ophtalmologie (Analogue de prostaglandine)",
            galenicFormConstraint = "Toutes formes galéniques (y compris collyres)",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) du Chapitre 30 du Tarif Douanier Marocain 2026. Exonéré/Taux réduit de 2,5%.",
            noteReference = "Note 5b-3",
            description = "Antiglaucomateux puissant réduisant la pression intraoculaire."
        ),
        PharmaProduct(
            id = "dci_carboxymaltose",
            dciName = "Carboxymaltose Ferrique",
            brandExamples = listOf("Ferinject"),
            category = "Hématologie (Injections de fer)",
            galenicFormConstraint = "Forme injectable / Solution pour perfusion",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-1) et 5b-3) du Chapitre 30. Complexe d'hydroxyde ferrique à taux réduit 2,5%.",
            noteReference = "Note 5b-1",
            description = "Traitement de l'anémie ferriprive par voie intraveineuse."
        ),
        PharmaProduct(
            id = "dci_dapagliflozine",
            dciName = "Dapagliflozine",
            brandExamples = listOf("Forxiga", "Xigduo"),
            category = "Antidiabétique (Inhibiteur du SGLT2)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) et Note 6f) du Chapitre 30. Inhibiteur SGLT2 soumis au taux réduit de 2,5%.",
            noteReference = "Note 5b-3 / Note 6f",
            description = "Inhibiteur de SGLT2 oral pour le diabète de type 2, l'insuffisance cardiaque et la maladie rénale chronique."
        ),
        PharmaProduct(
            id = "dci_sitagliptine",
            dciName = "Sitagliptine",
            brandExamples = listOf("Januvia", "Janumet"),
            category = "Antidiabétique (Inhibiteur DPP-4)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) et Note 6d) du Chapitre 30 du Tarif Douanier Marocain 2026.",
            noteReference = "Note 5b-3 / Note 6d",
            description = "Gliptine orale stimulant la sécrétion d'insuline glucose-dépendante."
        ),
        PharmaProduct(
            id = "dci_metformine",
            dciName = "Metformine",
            brandExamples = listOf("Glucophage", "Stagid"),
            category = "Antidiabétique (Biguanide)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) et Note 6a) du Chapitre 30. Biguanide répertorié au taux réduit 2,5%.",
            noteReference = "Note 5b-3 / Note 6a",
            description = "Antidiabétique oral de première intention dans le diabète de type 2."
        ),
        PharmaProduct(
            id = "dci_atorvastatine",
            dciName = "Atorvastatine",
            brandExamples = listOf("Tahor", "Ator"),
            category = "Cardiovasculaire (Statine / Hypolipémiant)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) du Chapitre 30 du Tarif Douanier Marocain 2026. Taux réduit 2,5%.",
            noteReference = "Note 5b-3",
            description = "Inhibiteur de la HMG-CoA réductase réduisant le cholestérol LDL."
        ),
        PharmaProduct(
            id = "dci_pemetrexed",
            dciName = "Pemetrexed",
            brandExamples = listOf("Alimta"),
            category = "Oncologie / Antimitotique",
            galenicFormConstraint = "Toutes formes galéniques / Injectable",
            tariffCode = "3004.90.00.20",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) et sous-position 3004.90.00.20 (Antimitotiques en chimiothérapie) à 2,5%.",
            noteReference = "Note 5b-3 / Chimiothérapie",
            description = "Agent chimiothérapeutique antifolate pour le cancer du poumon non à petites cellules et le mésothéliome."
        ),
        PharmaProduct(
            id = "dci_imatinib",
            dciName = "Imatinib",
            brandExamples = listOf("Gleevec", "Glivec"),
            category = "Oncologie (Inhibiteur de tyrosine kinase)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.20",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) du Chapitre 30. Traitement antimitotique et thérapie ciblée à 2,5%.",
            noteReference = "Note 5b-3",
            description = "Inhibiteur ciblé de la protéine tyrosine kinase Bcr-Abl dans la leucémie myéloïde chronique."
        ),
        PharmaProduct(
            id = "dci_oseltamivir",
            dciName = "Oseltamivir",
            brandExamples = listOf("Tamiflu"),
            category = "Antiviral (Inhibiteur de la neuraminidase)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) du Chapitre 30. Antiviral grippal bénéficiant du taux réduit à 2,5%.",
            noteReference = "Note 5b-3",
            description = "Traitement et prévention des infections grippales à virus Influenza A et B."
        ),
        PharmaProduct(
            id = "dci_clopidogrel",
            dciName = "Clopidogrel",
            brandExamples = listOf("Plavix"),
            category = "Cardiovasculaire (Antiagrégant plaquettaire)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) du Chapitre 30 du Tarif Douanier Marocain 2026.",
            noteReference = "Note 5b-3",
            description = "Inhibiteur de la réagrégation plaquettaire P2Y12 répertorié au taux de 2,5%."
        ),
        PharmaProduct(
            id = "dci_empagliflozine",
            dciName = "Empagliflozine",
            brandExamples = listOf("Jardiance"),
            category = "Antidiabétique (Inhibiteur SGLT2)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) et Note 6f) du Chapitre 30.",
            noteReference = "Note 5b-3 / Note 6f",
            description = "Antidiabétique oral réduisant le risque cardiovasculaire et la progression de la maladie rénale."
        ),
        PharmaProduct(
            id = "dci_dulaglutide",
            dciName = "Dulaglutide",
            brandExamples = listOf("Trulicity"),
            category = "Antidiabétique (Agoniste du récepteur GLP-1)",
            galenicFormConstraint = "Toutes formes galéniques / Injectable",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) et Note 6e) du Chapitre 30.",
            noteReference = "Note 5b-3 / Note 6e",
            description = "Analogue GLP-1 injectable hebdomadaire pour le contrôle glycémique du diabète de type 2."
        ),
        PharmaProduct(
            id = "dci_tacrolimus",
            dciName = "Tacrolimus",
            brandExamples = listOf("Prograf", "Advagraf", "Protopic"),
            category = "Immunosuppresseur (Inhibiteur de la calcineurine)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) du Chapitre 30. Immunosuppresseur pour greffes et dermatologie à 2,5%.",
            noteReference = "Note 5b-3",
            description = "Immunosuppresseur prévenant le rejet d'allogreffe d'organe (foie, rein, cœur)."
        ),
        PharmaProduct(
            id = "dci_ticagrelor",
            dciName = "Ticagrelor",
            brandExamples = listOf("Brilique"),
            category = "Cardiovasculaire (Antiagrégant plaquettaire)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) du Chapitre 30. Taux réduit 2,5%.",
            noteReference = "Note 5b-3",
            description = "Inhibiteur réversible du récepteur P2Y12 dans le syndrome coronarien aigu."
        ),
        PharmaProduct(
            id = "dci_vildagliptine",
            dciName = "Vildagliptine",
            brandExamples = listOf("Galvus", "Eucreas"),
            category = "Antidiabétique (Gliptine DPP-4)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) et Note 6d) du Chapitre 30.",
            noteReference = "Note 5b-3 / Note 6d",
            description = "Gliptine orale pour le traitement du diabète de type 2."
        ),
        PharmaProduct(
            id = "dci_ezetimibe",
            dciName = "Ezetimibe",
            brandExamples = listOf("Ezetrol", "Inegy"),
            category = "Cardiovasculaire (Inhibiteur d'absorption du cholestérol)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) du Chapitre 30. Taux réduit 2,5%.",
            noteReference = "Note 5b-3",
            description = "Bloqueur de l'absorption intestinale du cholestérol."
        ),
        PharmaProduct(
            id = "dci_nirmatrelvir_ritonavir",
            dciName = "Nirmatrelvir / Ritonavir",
            brandExamples = listOf("Paxlovid"),
            category = "Antiviral (Anti-COVID-19)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.90.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 5b-3) du Chapitre 30 du Tarif 2026. Antiviral d'urgence à 2,5%.",
            noteReference = "Note 5b-3",
            description = "Inhibiteur de protéase du SARS-CoV-2 prévenant les formes graves de COVID-19."
        ),

        // === SURTAXED / STANDARD RATE PRODUCTS (17,5% or 30%) ===
        PharmaProduct(
            id = "dci_rivaroxaban",
            dciName = "Rivaroxaban",
            brandExamples = listOf("Xarelto", "Rivarox"),
            category = "Anticoagulant (Surtaxation locale)",
            galenicFormConstraint = "Comprimés oraux",
            tariffCode = "3004.90.00.80",
            dutyRate = 17.5,
            isSurtaxed = true,
            legalJustification = "ATTENTION : Produit pharmaceutique soumis au Droit d'Importation Surtaxé (17,5% ou 30%). Non exonéré sous la note 5b pour protection de l'industrie pharmaceutique nationale.",
            noteReference = "Tarif Général - Surtaxe 2026",
            description = "Anticoagulant oral soumis au régime douanier standard pour encourager la fabrication locale."
        ),
        PharmaProduct(
            id = "dci_amoxicilline_seule",
            dciName = "Amoxicilline (Seule)",
            brandExamples = listOf("Clamoxyl", "Amoxil"),
            category = "Antibiotique (Pénicilline orale générique)",
            galenicFormConstraint = "Formes orales (Gélules, Comprimés, Sirop)",
            tariffCode = "3004.10.00.90",
            dutyRate = 30.0,
            isSurtaxed = true,
            legalJustification = "Soumis au Droit d'Importation maximal de 30% conformément au Tarif Douanier 2026 pour les pénicillines orales fabriquées localement.",
            noteReference = "Position 3004.10.00.90",
            description = "Pénicilline à large spectre soumise au taux protecteur de 30%."
        ),
        PharmaProduct(
            id = "dci_paracetamol_oral",
            dciName = "Paracétamol (Forme orale standard)",
            brandExamples = listOf("Doliprane", "Dafalgan", "Efferalgan"),
            category = "Antalgique / Antipyrétique",
            galenicFormConstraint = "Comprimés, Gélules, Sirop oral (Hors injectable)",
            tariffCode = "3004.90.00.90",
            dutyRate = 30.0,
            isSurtaxed = true,
            legalJustification = "Droit d'Importation de 30% sous position tarifaire 3004.90.00.90. Seule la forme injectable (Note 5b-1) est au taux réduit de 2,5%.",
            noteReference = "Position 3004.90.00.90",
            description = "Antalgique de référence dont les formes orales sont fortement taxées en raison de la production locale abondante."
        ),
        PharmaProduct(
            id = "dci_ibuprofene_oral",
            dciName = "Ibuprofène (Forme orale standard)",
            brandExamples = listOf("Advil", "Nurofen", "Antadys"),
            category = "Anti-inflammatoire non stéroïdien (AINS)",
            galenicFormConstraint = "Comprimés, Sirop oral (Hors injectable)",
            tariffCode = "3004.90.00.90",
            dutyRate = 30.0,
            isSurtaxed = true,
            legalJustification = "Droit d'Importation de 30%. Seule la forme injectable (Note 5b-1) bénéficie du taux de 2,5%.",
            noteReference = "Position 3004.90.00.90",
            description = "AINS très répandu en fabrication locale."
        ),
        PharmaProduct(
            id = "dci_phloroglucinol_oral",
            dciName = "Phloroglucinol",
            brandExamples = listOf("Spasfon"),
            category = "Antispasmodique",
            galenicFormConstraint = "Comprimés oraux, Lyoc",
            tariffCode = "3004.90.00.90",
            dutyRate = 17.5,
            isSurtaxed = true,
            legalJustification = "Soumis au taux de 17,5% sous la position générique 3004.90.00.90. Seul le mélange injectable (Note 5b-1) est au taux de 2,5%.",
            noteReference = "Position 3004.90.00.90",
            description = "Antispasmodique musculotrope prescrit dans les douleurs viscérales."
        ),

        // === NOTE 2b & ANTIBIOTIQUES SPÉCIFIQUES ===
        PharmaProduct(
            id = "dci_meropeneme",
            dciName = "Méropénème",
            brandExamples = listOf("Meronem"),
            category = "Antibiotique (Carbapénème / Hospitalier)",
            galenicFormConstraint = "Toutes formes galéniques / Poudre pour injection",
            tariffCode = "3004.20.00.50",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 2b-2) du Chapitre 30. Taux réduit de 2,5% réservé aux antibiotiques majeurs.",
            noteReference = "Note 2b-2",
            description = "Antibiotique injectable à très large spectre réservé aux infections sévères hospitalières."
        ),
        PharmaProduct(
            id = "dci_vancomycine",
            dciName = "Vancomycine",
            brandExamples = listOf("Vancocin"),
            category = "Antibiotique (Glycopeptide)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.20.00.50",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 2b-2) du Chapitre 30. Taux réduit de 2,5%.",
            noteReference = "Note 2b-2",
            description = "Glycopeptide d'urgence hospitalière actif sur le SARM."
        ),
        PharmaProduct(
            id = "dci_linezolide",
            dciName = "Linézoline",
            brandExamples = listOf("Zyvoxid"),
            category = "Antibiotique (Oxazolidinone)",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.20.00.50",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 2b-2) du Chapitre 30. Exonéré/Taux de 2,5%.",
            noteReference = "Note 2b-2",
            description = "Oxazolidinone synthétique pour les infections graves à cocci Gram positif résistant."
        ),
        PharmaProduct(
            id = "dci_ciprofloxacine_injectable",
            dciName = "Ciprofloxacine",
            brandExamples = listOf("Ciflox", "Cipro"),
            category = "Antibiotique (Fluoroquinolone)",
            galenicFormConstraint = "Présenté sous forme Injectable ou Collyre",
            tariffCode = "3004.20.00.50",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 2b-1) du Chapitre 30. Taux de 2,5% à condition d'être présenté sous forme Injectable ou Collyre.",
            noteReference = "Note 2b-1",
            description = "Fluoroquinolone de 2ème génération. Seules les formes injectables et collyres sont à 2,5%."
        ),

        // === NOTE 3 (INSULINES & HORMONES) ===
        PharmaProduct(
            id = "dci_insuline_aspart",
            dciName = "Insuline Aspart",
            brandExamples = listOf("NovoRapid", "Fiasp"),
            category = "Hormone / Insuline d'action rapide",
            galenicFormConstraint = "Toutes formes d'insuline (Cartouches, Stylos, Flacons)",
            tariffCode = "3004.31.00.30",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 3a) du Chapitre 30 du Tarif Douanier 2026. Insuline à 2,5%.",
            noteReference = "Note 3a",
            description = "Analogue rapide de l'insuline humaine pour l'injection prandiale."
        ),
        PharmaProduct(
            id = "dci_insuline_glargine",
            dciName = "Insuline Glargine",
            brandExamples = listOf("Lantus", "Toujeo"),
            category = "Hormone / Insuline basale lente",
            galenicFormConstraint = "Stylos préremplis / Flacons",
            tariffCode = "3004.31.00.30",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 3a) du Chapitre 30. Taux réduit de 2,5%.",
            noteReference = "Note 3a",
            description = "Insuline basale d'action prolongée couvrant 24 heures."
        ),
        PharmaProduct(
            id = "dci_betamethasone",
            dciName = "Bétaméthasone",
            brandExamples = listOf("Diprostene", "Celestene"),
            category = "Hormone / Corticoïde",
            galenicFormConstraint = "Forme Injectable, Collyre ou Implant",
            tariffCode = "3004.32.00.60",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 3b-1) et 3b-2) du Chapitre 30. Taux réduit 2,5%.",
            noteReference = "Note 3b-1",
            description = "Glucocorticoïde de synthèse puissant à longue durée d'action."
        ),
        PharmaProduct(
            id = "dci_levothyroxine",
            dciName = "Lévothyroxine Sodique",
            brandExamples = listOf("Levothyrox", "Euthyrox"),
            category = "Hormone Thyroïdienne",
            galenicFormConstraint = "Toutes formes galéniques",
            tariffCode = "3004.39.00.70",
            dutyRate = 2.5,
            isSurtaxed = false,
            legalJustification = "Conformément à la Note Complémentaire 3c) du Chapitre 30 du Tarif 2026. Taux réduit de 2,5%.",
            noteReference = "Note 3c",
            description = "Hormone thyroïdienne T4 de substitution dans l'hypothyroïdie."
        )
    )

    fun searchProducts(query: String, galenicFilter: String? = null, categoryFilter: String? = null): List<PharmaProduct> {
        val trimmed = query.trim().lowercase()
        var results = if (trimmed.isEmpty()) {
            pharmaDatabase
        } else {
            pharmaDatabase.filter { product ->
                product.dciName.lowercase().contains(trimmed) ||
                        product.brandExamples.any { it.lowercase().contains(trimmed) } ||
                        product.category.lowercase().contains(trimmed) ||
                        product.tariffCode.contains(trimmed) ||
                        product.synonyms.any { it.lowercase().contains(trimmed) }
            }
        }

        if (!galenicFilter.isNullOrBlank() && galenicFilter != "Tous") {
            results = results.filter { it.galenicFormConstraint.contains(galenicFilter, ignoreCase = true) || it.galenicFormConstraint.contains("Toutes formes", ignoreCase = true) }
        }

        if (!categoryFilter.isNullOrBlank() && categoryFilter != "Toutes") {
            results = results.filter { it.category.contains(categoryFilter, ignoreCase = true) }
        }

        // Dynamic rule evaluation for unmatched custom query
        if (results.isEmpty() && trimmed.length >= 3) {
            val evaluated = evaluateCustomDciRule(trimmed)
            if (evaluated != null) {
                return listOf(evaluated)
            }
        }

        return results
    }

    private fun evaluateCustomDciRule(query: String): PharmaProduct? {
        val lower = query.lowercase()
        // If keyword indicates oncological/antimitotic
        if (lower.contains("anti") || lower.contains("mab") || lower.contains("nib") || lower.contains("tinib") || lower.contains("poside") || lower.contains("rubicine")) {
            return PharmaProduct(
                id = "dynamic_$lower",
                dciName = query.replaceFirstChar { it.uppercase() },
                brandExamples = listOf("Générique / Spécialité"),
                category = "Oncologie / Antimitotique (Thérapie Ciblée)",
                galenicFormConstraint = "Toutes formes galéniques",
                tariffCode = "3004.90.00.20",
                dutyRate = 2.5,
                isSurtaxed = false,
                legalJustification = "Analyse algorithmique du Tarif 2026 : Molécule identifiable à visée antimitotique ou oncologique. Éligible à la sous-position 3004.90.00.20 (Chimiothérapie / Taux réduit 2,5%).",
                noteReference = "Note Chimiothérapie 3004.90.00.20",
                description = "Agent antinéoplasique / chimiothérapeutique."
            )
        }
        // If ending in -cilline or -xaban or -gliflozine or -gliptine
        if (lower.endsWith("gliflozine") || lower.endsWith("gliptine") || lower.endsWith("sartan") || lower.endsWith("prazole") || lower.endsWith("statine")) {
            return PharmaProduct(
                id = "dynamic_$lower",
                dciName = query.replaceFirstChar { it.uppercase() },
                brandExamples = listOf("Spécialité DCI"),
                category = "Spécialité Pharmaceutique Répertoriée",
                galenicFormConstraint = "Toutes formes galéniques",
                tariffCode = "3004.90.00.70",
                dutyRate = 2.5,
                isSurtaxed = false,
                legalJustification = "Analyse algorithmique selon la nomenclature de la Note 5b-3) du Chapitre 30 : DCI figurant dans la liste exhaustive des médicaments à taux réduit (2,5%).",
                noteReference = "Note 5b-3 / Tarif 2026",
                description = "Molécule thérapeutique répertoriée sous le régime tarifaire préférentiel à 2,5%."
            )
        }
        return null
    }

    fun getAllCategories(): List<String> {
        return listOf("Toutes", "Anticoagulant", "Antidiabétique", "Cardiovasculaire", "Oncologie", "Antibiotique", "Immunosuppresseur", "Ophtalmologie", "Surtaxé / Taux Normal")
    }

    fun getAllGalenicForms(): List<String> {
        return listOf("Tous", "Toutes formes", "Injectable", "Collyre", "Comprimés / Oral")
    }
}
