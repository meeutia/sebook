package com.example.sebook.ui.theme.screens.riwayat


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sebook.R


@Composable
fun RiwayatPengajuan(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 48.dp)
            .verticalScroll(scrollState)
    ) {
        // Judul Halaman
        Text(
            text = "Riwayat Pengajuan",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Daftar Pengajuan
        Column(modifier = Modifier.fillMaxSize()) {
            PengajuanCard(
                roomName = "Ruang Tengah",
                date = "Senin, 4 Oktober 2025",
                time = "13.00 - 16.00",
                status = "Di setujui",
                imageResourceId = R.drawable.tengah_2,
                onEditClick = { /* Aksi edit */ },
                onDeleteClick = { /* Aksi hapus */ },
                onReviewClick = { navController.navigate("review") }
            )
            PengajuanCard(
                roomName = "Ruang Tengah",
                date = "Senin, 4 Oktober 2025",
                time = "13.00 - 16.00",
                status = "Di Tolak",
                imageResourceId = R.drawable.tengah_2,
                onEditClick = { /* Aksi edit */ },
                onDeleteClick = { /* Aksi hapus */ },
                onReviewClick = { navController.navigate("review") }
            )
            // Tambahkan lebih banyak card sesuai dengan data pengajuan
        }
    }
}

@Composable
fun PengajuanCard(
    roomName: String,
    date: String,
    time: String,
    status: String,
    imageResourceId: Int,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onReviewClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Gambar ruangan
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = "Room Image",
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Info pengajuan dan tombol
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Baris pertama: Nama ruangan + Status badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // Nama ruangan
                    Text(
                        text = roomName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    // Status badge
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (status == "Di setujui") Color(0xFF4CAF50) else Color(0xFFFF5252)
                    ) {
                        Text(
                            text = status,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Tanggal
                Text(
                    text = date,
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Waktu
                Text(
                    text = time,
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledIconButton(
                        onClick = onEditClick,
                        modifier = Modifier.size(36.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = Color(0xFFFF8C00),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = "Edit",
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(Modifier.width(6.dp))

                    FilledIconButton(
                        onClick = onDeleteClick,
                        modifier = Modifier.size(36.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = Color(0xFFFF8C00),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_delete),
                            contentDescription = "Delete",
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(Modifier.width(6.dp))

                    FilledIconButton(
                        onClick = onReviewClick,
                        modifier = Modifier.size(36.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = Color(0xFFFF8C00),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_review),
                            contentDescription = "Review",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRiwayatPengajuan() {
    RiwayatPengajuan(navController = rememberNavController())
}