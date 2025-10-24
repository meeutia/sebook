package com.example.sebook.ui.theme.screens.booking

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sebook.ui.theme.components.CustomButton
import com.example.sebook.ui.theme.components.CustomTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.input.ImeAction
import com.example.sebook.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar


@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = Color(0xFF1A2B3C),
        fontFamily = FontFamily(Font(R.font.spacegrotesk_bold)),
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BarangDropdown(
    label: String = "Barang yang di pakai",
    options: List<String>,
    selected: String?,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        // anchor pakai CustomTextField (dibuat tidak editable dgn onValueChange = {})
        CustomTextField(
            value = selected ?: "",
            onValueChange = {},                 // non-editable
            label = label,
            placeholder = "Choose",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .clickable { expanded = true },
            isError = false
        )
        Box(
            modifier = Modifier
                .fillMaxWidth() // Pastikan Box memenuhi lebar kontainer
                .offset(y = 24.dp)
                .padding(end = 12.dp), // Menambahkan sedikit ruang di kanan
            contentAlignment = Alignment.CenterEnd // Posisi ikon di kanan
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = null,
                tint = Color(0xFF1A2B3C)
            )
        }
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = { onSelected(item); expanded = false }
                )
            }
        }
    }
}

@Composable
private fun TimeField(
    label: String,
    value: String,
    onPick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    fun showPicker(context: Context) {
        val now = Calendar.getInstance()
        TimePickerDialog(
            context,
            { _, h, m ->
                val hh = h.toString().padStart(2, '0')
                val mm = m.toString().padStart(2, '0')
                onPick("$hh:$mm")
            },
            now.get(Calendar.HOUR_OF_DAY),
            now.get(Calendar.MINUTE),
            true
        ).show()
    }
    CustomTextField(
        value = value,
        onValueChange = {},                 // non-editable
        label = label,
        placeholder = label,
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next,
        modifier = modifier.clickable { showPicker(ctx) },
        isError = false
    )
}

@Composable
private fun UploadField(
    label: String = "Surat Peminjaman",
    fileName: String?,
    onUploadClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF1A2B3C),
            fontFamily = FontFamily(Font(R.font.spacegrotesk_bold)),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFFF5F5F5),
            tonalElevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable { onUploadClick() }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Outlined.CloudUpload,
                    contentDescription = null,
                    tint = Color(0xFF1A2B3C)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = fileName ?: "Upload",
                    color = Color(0xFF6B8E7F),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun BookingFormScreen(
    onSubmit: () -> Unit = {},
    onUploadSurat: () -> Unit = {}
) {
    var namaPenanggung by rememberSaveable { mutableStateOf("") }
    var asalKomunitas by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var kegiatan by rememberSaveable { mutableStateOf("") }
    var barang by rememberSaveable { mutableStateOf<String?>(null) }
    var dari by rememberSaveable { mutableStateOf("") }
    var sampai by rememberSaveable { mutableStateOf("") }
    var suratFile by rememberSaveable { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start
        ) {


            Spacer(Modifier.height(48.dp))
            SectionTitle("Isi Pengajuan Peminjaman")

            // Nama Penanggung Jawab
            Text(
                text = "Nama Penanggung Jawab",
                fontSize = 14.sp,
                color = Color(0xFF1A2B3C),
                fontFamily = FontFamily(Font(R.font.spacegrotesk_bold))
            )
            CustomTextField(
                value = namaPenanggung,
                onValueChange = { namaPenanggung = it },
                label = "Enter  name",
                placeholder = "Enter  name",
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            )

            Spacer(Modifier.height(16.dp))

            // Asal Komunitas/Organisasi
            Text(
                text = "Asal Komunitas/Organisasi",
                fontSize = 14.sp,
                color = Color(0xFF1A2B3C),
                fontFamily = FontFamily(Font(R.font.spacegrotesk_bold))
            )
            CustomTextField(
                value = asalKomunitas,
                onValueChange = { asalKomunitas = it },
                label = "Enter",
                placeholder = "Enter",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )

            Spacer(Modifier.height(16.dp))

            // Phone Number
            Text(
                text = "Phone Number",
                fontSize = 14.sp,
                color = Color(0xFF1A2B3C),
                fontFamily = FontFamily(Font(R.font.spacegrotesk_bold))
            )
            CustomTextField(
                value = phone,
                onValueChange = { phone = it.filter { c -> c.isDigit() || c == '+' || c == ' ' } },
                label = "Enter  phone number",
                placeholder = "Enter  phone number",
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )

            Spacer(Modifier.height(16.dp))

            // Kegiatan
            Text(
                text = "Kegiatan",
                fontSize = 14.sp,
                color = Color(0xFF1A2B3C),
                fontFamily = FontFamily(Font(R.font.spacegrotesk_bold))
            )
            CustomTextField(
                value = kegiatan,
                onValueChange = { kegiatan = it },
                label = "Enter",
                placeholder = "Enter",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )

            Spacer(Modifier.height(16.dp))

            // Barang yang dipakai (Dropdown)
            BarangDropdown(
                options = listOf("Sound System", "Proyektor", "Tenda", "Kursi", "Meja"),
                selected = barang,
                onSelected = { barang = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // Jam Pemakaian
            Text(
                text = "Jam Pemakaian",
                fontSize = 14.sp,
                color = Color(0xFF1A2B3C),
                fontFamily = FontFamily(Font(R.font.spacegrotesk_bold))
            )
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                TimeField(
                    label = "Dari",
                    value = dari,
                    onPick = { dari = it },
                    modifier = Modifier.weight(1f)
                )
                TimeField(
                    label = "Sampai",
                    value = sampai,
                    onPick = { sampai = it },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(16.dp))

            // Surat Peminjaman (Upload)
            UploadField(
                label = "Surat Peminjaman",
                fileName = suratFile,
                onUploadClick = {
                    // TODO: buka file picker-mu, untuk demo isi nama file dummy
                    suratFile = "surat_peminjaman.pdf"
                    onUploadSurat()
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            // Tombol Ajukan
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CustomButton(
                    text = "Ajukan",
                    onClick = onSubmit
                )
            }

            Spacer(Modifier.height(24.dp))
        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookingFormPreview() {
    MaterialTheme {
        BookingFormScreen()
    }
}