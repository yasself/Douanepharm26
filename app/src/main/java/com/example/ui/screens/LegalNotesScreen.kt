package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BlueAccent
import com.example.ui.theme.BorderLight
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

data class ChapterNote(
    val title: String,
    val subtitle: String,
    val fullText: String
)

@Composable
fun LegalNotesScreen() {
    var searchQuery by remember { mutableStateOf("") }

    val notesList = remember {
        listOf(
            ChapterNote(
                title = "Note Complémentaire 5b - DCI Exonérées / Taux 2,5%",
                subtitle = "Règle clé pour la majorité des molécules DCI (Apixaban, Latanoprost, Dapagliflozine, etc.)",
                fullText = """
                    5/ Ne rentrent aux n°s 3003.90.94.00, 3004.50.00.81 et 3004.90.00.70 que les médicaments contenant :
                    
                    a) Des vitamines spécifiées (Calcium, Cholecalciferol, Isotretinoine, Vitamine K1)
                    
                    b) Les DCI suivantes :
                    1- Présentés sous forme injectable : Aciclovir, Amiodarone, Busulfan, Cabazitaxel, Esomeprazole, Ibuprofène, Irinotecan, Omeprazole, Paracetamol, Salbutamol Sulfate, etc.
                    2- Présentés sous forme de collyres : Diclofenac Sodique, Cysteamine, Indometacine, Ketotifene, etc.
                    3- À base des DCI suivantes, SOUS TOUTES FORMES GALÉNIQUES : Abacavir, Acalabrutinib, Acide acetylsalicylique, Acide bempedoique, Acide Folinique, Acide Zoledronique, Adapalene, Aflibercept, Alfuzosine, Alogliptine, Amlodipine, Amorolfine, APIXABAN, Aprepitant, Asciminib, Atorvastatine, Azilsartan, Baricitinib, Bedaquiline, Bimatoprost, Bisoprolol, Bortezomib, Bosentan, Capecitabine, Carbamazepine, Carboplatine, Ciclosporine, Cilazapril, Clopidogrel, Dabigatran, Dapagliflozine, Darunavir, Dasatinib, Decitabine, Deferasirox, Dolutegravir, Dulaglutide, Empagliflozine, Enzalutamide, Erlotinib, Ezetimib, Febuxostat, Finerenone, Fingolimod, Fulvestrant, Gabapentine, Ganciclovir, Gefitinib, Gemcitabine, Gilteritinib, Ibrutinib, Imatinib, Ivabradine, Lamivudine, Lenalidomide, Levetiracetam, Linagliptine, Liraglutide, Lopinavir/Ritonavir, Mesalazine, Metformine, Methotrexate, Nilotinib, Nirmatrelvir/Ritonavir, Olaparib, Oseltamivir, Osimertinib, Paclitaxel, Pazopanib, Pemetrexed, Perindopril, Ruxolitinib, Saxagliptine, Sitagliptine, Sofosbuvir, Sunitinib, Tacrolimus, Telmisartan, Temozolomide, Ticagrelor, Tretinoine, Valproate, Valsartan, Verapamil, Vildagliptine, Voriconazole, Zanubrutinib, etc.
                """.trimIndent()
            ),
            ChapterNote(
                title = "Note Complémentaire 2b - Antibiotiques",
                subtitle = "Classification des antibiotiques injectables, collyres ou sous toute forme galénique",
                fullText = """
                    2/ b- Ne rentrent aux n°s 3003.20.90.10 et 3004.20.00.50 que les médicaments contenant les autres antibiotiques :
                    
                    1)- Présentés sous forme injectable ou collyres :
                    Acide Fusidique, Amphotericin B liposomal, Azithromycine, Bacitracine // Colistine // Hydrocortisone, Cefuroxime, Ciprofloxacine, Clofazimine, Daptomycine, Gentamicine, Levofloxacine, Moxifloxacine, Norfloxacine, Ofloxacine.
                    
                    2)- À base des DCI suivantes, SOUS TOUTE FORME GALÉNIQUE :
                    Anidulafungine, Amikacine, Amphotericine B, Aztreonam, Bleomycine, Caspofungine, Cefdinir, Cefditoren Pivoxil, Cefepime, Cefotaxime, Cefpodoxime Proxetil, Ceftaroline fosamil, Ceftazidime/Avibactam, Ceftobiprole Medocaril, Linezolide, Meropeneme, Teicoplanine, Tigecycline, Vancomycine.
                """.trimIndent()
            ),
            ChapterNote(
                title = "Note Complémentaire 3 - Hormones & Insulines",
                subtitle = "Règles douanières pour les insulines et corticoïdes",
                fullText = """
                    3/ Ne rentrent aux n°s 3003.31.00.10, 3004.31.00.30, 3004.32.00.60, 3004.39.00.70 :
                    
                    a- Contenant de l'insuline : Insuline Aspart, Insuline Degludec, Insuline Detemir, Insuline Glargine, Insuline Glulisine, Insuline Lispro (Taux 2,5%).
                    
                    b- Contenant des hormones corticostéroïdes :
                    1°) Présentés sous forme injectable, collyres ou implant : Betamethasone, Dexamethasone, Methylprednisolone, Prednisolone.
                    2°) À base des DCI suivantes, sous toute forme galénique : Beclomethasone, Budesonide, Fluticasone, Clobetasol, Hydrocortisone.
                """.trimIndent()
            ),
            ChapterNote(
                title = "Note Complémentaire 6 - Antidiabétiques & Gastro",
                subtitle = "Glinides, Gliptines, Incretinomimétiques GLP-1, SGLT2",
                fullText = """
                    6/ Ne rentrent au n° 3003.90.95.00 que les médicaments :
                    1)- Antidiabétiques suivants :
                    a- Biguanides (Metformine)
                    b- Sulfonylurées (Glibenclamide, Gliclazide, Glimepiride)
                    c- Glinides (Repaglinide)
                    d- Gliptines / DPP-4 (Sitagliptine, Vildagliptine, Saxagliptine, Linagliptine)
                    e- GLP-1 (Liraglutide, Dulaglutide)
                    f- Gliflozines / SGLT2 (Canagliflozine, Dapagliflozine, Empagliflozine)
                    g- Inhibiteurs des alpha-glucosidases (Acarbose)
                    2)- Contenant : Esomeprazole, Lansoprazole, Tamsulosine Chlorhydrate.
                """.trimIndent()
            ),
            ChapterNote(
                title = "Note 1 du Chapitre 30 - Exclusions Officieuses",
                subtitle = "Produits exclus du Chapitre 30 (Aliments, timbres nicotine, réactifs)",
                fullText = """
                    1. Le présent Chapitre ne comprend pas :
                    a) Les aliments diététiques, compléments alimentaires, boissons toniques (Section IV).
                    b) Les comprimés/gommes à mâcher contenant de la nicotine pour le sevrage tabagique (24.04).
                    c) Les plâtres pour l'art dentaire (25.20).
                    d) Les eaux distillées aromatiques et huiles essentielles (33.01).
                    e) Les préparations de parfumerie/cosmétiques même avec propriétés thérapeutiques (33.03 à 33.07).
                    f) Les savons médicamenteux (34.01).
                    ij) Les réactifs de diagnostic (38.22).
                """.trimIndent()
            )
        )
    }

    val filteredNotes = notesList.filter {
        it.title.contains(searchQuery, ignoreCase = true) ||
                it.subtitle.contains(searchQuery, ignoreCase = true) ||
                it.fullText.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 0.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(NavyPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "TARIF DOUANIER MAROCAIN 2026",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = BlueAccent,
                            letterSpacing = 1.2.sp
                        )
                        Text(
                            text = "Notes Légales Chapitre 30",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = TextPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Filtrer les notes douanières (ex: 5b, 2b)...", fontSize = 14.sp, color = Color(0xFF94A3B8)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextPrimary) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = NavyPrimary,
                        unfocusedBorderColor = BorderLight,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderLight, RoundedCornerShape(16.dp))
                )
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            filteredNotes.forEach { note ->
                ExpandableNoteCard(note)
                Spacer(modifier = Modifier.height(12.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ExpandableNoteCard(note: ChapterNote) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Gavel,
                        contentDescription = null,
                        tint = BlueAccent,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = note.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextPrimary
                        )
                        Text(
                            text = note.subtitle,
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }
                }

                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandMore else Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = TextSecondary
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column {
                    Divider(modifier = Modifier.padding(vertical = 12.dp), color = BorderLight)
                    Surface(
                        color = Color(0xFFF8FAFC),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = note.fullText,
                            fontSize = 13.sp,
                            color = TextPrimary,
                            lineHeight = 18.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}
