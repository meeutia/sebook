package com.example.sebook.ui.theme.screens.informasi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.sebook.R
import com.example.sebook.ui.theme.components.CustomButton
import com.example.sebook.ui.theme.components.RoomCard
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RuanganDiSakato(innerPadding: PaddingValues, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Konten utama dapat di-scroll
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(bottom = 100.dp) // ruang untuk gelombang di bawah
                .padding(16.dp) // padding konten
        ) {
            Spacer(modifier = Modifier.height(13.dp))

            // Logo SEBOOK
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RoomCard(
                    roomName = "Ruang Ampliteather",
                    onClick = { navController.navigate("detail_ruangan/ampliteather") },
                    imageResourceId = R.drawable.amplifier_1
                )
                RoomCard(
                    roomName = "Ruang Tengah",
                    onClick = { navController.navigate("detail_ruangan/tengah") },
                    imageResourceId = R.drawable.tengah_2
                )
                RoomCard(
                    roomName = "Ruang Outdor",
                    onClick = { navController.navigate("detail_ruangan/outdor") },
                    imageResourceId = R.drawable.belakang_1
                )
            }

            // Tombol Panduan di pojok kanan
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CustomButton(
                    text = "Panduan",
                    onClick = { navController.navigate("panduan") }
                )
            }
        }

        // Gambar gelombang hijau di bawah (overlay)
        Image(
            painter = painterResource(id = R.drawable.rectangle_4),
            contentDescription = "Gelombang Hijau",
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 40.dp)
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRuanganDiSakato() {
    RuanganDiSakato(innerPadding = PaddingValues(0.dp), navController = rememberNavController())
}
