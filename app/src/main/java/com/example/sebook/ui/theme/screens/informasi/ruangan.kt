package com.example.sebook.ui.theme.screens.informasi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.rememberNavController
import com.example.sebook.R
import com.example.sebook.ui.theme.components.BottomNavBar
import com.example.sebook.ui.theme.components.CustomButton
import com.example.sebook.ui.theme.components.RoomCard
import com.example.sebook.ui.theme.screens.HomeContent



@Composable
fun RuanganDiSakato(innerPadding: PaddingValues) {

    val navController = rememberNavController()
Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Kolom utama untuk konten
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 100.dp) // Memberikan ruang bawah untuk gambar gelombang
                        .padding(16.dp) // Memberikan padding ke seluruh kolom
                        .padding(innerPadding)  // Menggunakan innerPadding dari Scaffold
                ) {
                    Spacer(modifier = Modifier.height(13.dp))

                    // Logo SEBOOK
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        // Logo SEBOOK
                        Image(
                            painter = painterResource(id = R.drawable.logo_sebook),
                            contentDescription = "SEBOOK Logo",
                            modifier = Modifier
                                .width(100.dp)
                                .height(35.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))

                    // Judul Halaman
                    Text(
                        text = "Ruangan di Sakato",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),

                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Daftar Ruangan
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp) // Memberikan jarak antar card
                    ) {
                        RoomCard(
                            roomName = "Ruang Tengah",
                            onClick = { /* Aksi untuk Ruang Rapat Besar */ },
                            imageResourceId = R.drawable.tengah_2
                        )
                        RoomCard(
                            roomName = "Ruang Outdor Belakang",
                            onClick = { /* Aksi untuk Ruang Rapat Private */ },
                            imageResourceId = R.drawable.belakang_1
                        )
                        RoomCard(
                            roomName = "Ruang Outdoor",
                            onClick = { /* Aksi untuk Ruang Outdoor */ },
                            imageResourceId = R.drawable.amplifier_1
                        )
                    }
                    // Tombol Panduan
                    Spacer(modifier = Modifier.height(24.dp))
                    // Row untuk meletakkan tombol di pojok kanan
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), // Mengisi lebar layar
                        horizontalArrangement = Arrangement.End // Menempatkan tombol di kanan
                    ) {
                        CustomButton(
                            text = "Panduan",
                            onClick = { /* Action for Panduan button */ }

                        )
                    }
                }


// Menambahkan gambar gelombang hijau di bawah halaman
                Image(
                    painter = painterResource(id = R.drawable.rectangle_4), // Ganti dengan ID gambar Anda
                    contentDescription = "Gelombang Hijau",
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 40.dp)
                        .align(Alignment.BottomCenter), // Menempatkan gambar di bawah tengah
                    contentScale = ContentScale.FillWidth

                )
            }
        }
)
}


@Preview(showBackground = true)
@Composable
fun PreviewRuanganDiSakato() {
    RuanganDiSakato(innerPadding = PaddingValues(0.dp))
}
