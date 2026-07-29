package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.BlueAccent
import com.example.ui.theme.BorderLight
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.PharmaViewModel
import java.text.DecimalFormat

@Composable
fun SimulatorScreen(viewModel: PharmaViewModel) {
    val cifInput by viewModel.simulatorCifInput.collectAsStateWithLifecycle()
    val currency by viewModel.simulatorCurrency.collectAsStateWithLifecycle()
    val dutyRate by viewModel.simulatorDutyRate.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val exchangeRate = when (currency) {
        "EUR" -> 10.85
        "USD" -> 9.95
        else -> 1.0
    }

    val cifNumeric = cifInput.toDoubleOrNull() ?: 0.0
    val cifMad = cifNumeric * exchangeRate

    val dutyAmountMad = cifMad * (dutyRate / 100.0)
    val parafiscaleMad = cifMad * 0.0025 // 0,25%
    val tvaBaseMad = cifMad + dutyAmountMad + parafiscaleMad
    val tvaAmountMad = tvaBaseMad * 0.20 // 20%
    val totalCustomsMad = dutyAmountMad + parafiscaleMad + tvaAmountMad

    val formatter = DecimalFormat("#,##0.00")

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
                            imageVector = Icons.Default.Calculate,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "SIMULATION DE LIQUIDATION",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = BlueAccent,
                            letterSpacing = 1.2.sp
                        )
                        Text(
                            text = "Simulateur Droits & Taxes 2026",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = TextPrimary
                        )
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            // Inputs Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderLight),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "PARAMÈTRES D'IMPORTATION",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    // Currency Selector Row
                    Text(text = "Devise de la Valeur CIF :", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("MAD", "EUR", "USD").forEach { curr ->
                            FilterChip(
                                selected = currency == curr,
                                onClick = { viewModel.updateSimulatorCurrency(curr) },
                                label = { Text(text = curr, fontWeight = FontWeight.Bold) },
                                shape = RoundedCornerShape(12.dp),
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = NavyPrimary,
                                    selectedLabelColor = Color.White,
                                    containerColor = Color(0xFFF1F5F9),
                                    labelColor = TextPrimary
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // CIF Value Input
                    Text(text = "Valeur CAF / CIF (Prix + Assurance + Fret) :", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = cifInput,
                        onValueChange = { viewModel.updateSimulatorCif(it) },
                        suffix = { Text(text = currency, fontWeight = FontWeight.ExtraBold, color = BlueAccent) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = NavyPrimary,
                            unfocusedBorderColor = BorderLight,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderLight, RoundedCornerShape(16.dp))
                    )

                    if (currency != "MAD") {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Taux indicatif: 1 $currency = $exchangeRate MAD",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = BlueAccent
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Duty Rate Selector
                    Text(text = "Taux du Droit d'Importation (DI) :", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(2.5, 17.5, 30.0).forEach { rate ->
                            FilterChip(
                                selected = dutyRate == rate,
                                onClick = { viewModel.updateSimulatorDutyRate(rate) },
                                label = { Text(text = "$rate %", fontWeight = FontWeight.Bold) },
                                shape = RoundedCornerShape(12.dp),
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = if (rate == 2.5) Color(0xFF059669) else Color(0xFFDC2626),
                                    selectedLabelColor = Color.White,
                                    containerColor = Color(0xFFF1F5F9),
                                    labelColor = TextPrimary
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Total Summary Banner Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderLight, RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "TOTAL DROITS & TAXES DOUANIÈRES À PAYER",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BlueAccent,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${formatter.format(totalCustomsMad)} MAD",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = NavyPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Estimation sur la base de la valeur CIF de ${formatter.format(cifMad)} MAD",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Detailed Breakdown Table Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderLight),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "DÉTAIL DE LA LIQUIDATION DOUANIÈRE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    CalcRow("Valeur Douane (CIF en MAD)", "${formatter.format(cifMad)} MAD", isHeader = true)
                    Divider(modifier = Modifier.padding(vertical = 10.dp), color = BorderLight)

                    CalcRow("Droit d'Importation (DI ${dutyRate}%)", "${formatter.format(dutyAmountMad)} MAD")
                    Divider(modifier = Modifier.padding(vertical = 10.dp), color = BorderLight)

                    CalcRow("Taxe Parafiscale (TPI 0,25%)", "${formatter.format(parafiscaleMad)} MAD")
                    Divider(modifier = Modifier.padding(vertical = 10.dp), color = BorderLight)

                    CalcRow("Assiette de Calcul TVA 20%", "${formatter.format(tvaBaseMad)} MAD")
                    Divider(modifier = Modifier.padding(vertical = 10.dp), color = BorderLight)

                    CalcRow("TVA à l'Importation (20%)", "${formatter.format(tvaAmountMad)} MAD")
                    Divider(modifier = Modifier.padding(vertical = 10.dp), color = BorderLight)

                    CalcRow("TOTAL TAXES DOUANE", "${formatter.format(totalCustomsMad)} MAD", isTotal = true)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Copy Simulation Summary Button
            Button(
                onClick = {
                    val summaryText = """
                        --- SIMULATION LIQUIDATION DOUANE MAROC 2026 ---
                        Valeur CIF Saisie: $cifNumeric $currency (${formatter.format(cifMad)} MAD)
                        Droit d'Importation (DI $dutyRate%): ${formatter.format(dutyAmountMad)} MAD
                        Taxe Parafiscale (0,25%): ${formatter.format(parafiscaleMad)} MAD
                        TVA Importation (20%): ${formatter.format(tvaAmountMad)} MAD
                        -----------------------------------------------
                        TOTAL TAXES À PAYER: ${formatter.format(totalCustomsMad)} MAD
                        -----------------------------------------------
                    """.trimIndent()
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(ClipData.newPlainText("Simulation Douane", summaryText))
                    Toast.makeText(context, "Simulation copiée dans le presse-papiers", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary)
            ) {
                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Copier le Décompte Complet", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CalcRow(
    label: String,
    value: String,
    isHeader: Boolean = false,
    isTotal: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = if (isTotal) 13.sp else 12.sp,
            fontWeight = if (isHeader || isTotal) FontWeight.Bold else FontWeight.Normal,
            color = if (isTotal) NavyPrimary else TextSecondary
        )
        Text(
            text = value,
            fontSize = if (isTotal) 15.sp else 13.sp,
            fontWeight = if (isHeader || isTotal) FontWeight.ExtraBold else FontWeight.SemiBold,
            color = if (isTotal) BlueAccent else TextPrimary
        )
    }
}
