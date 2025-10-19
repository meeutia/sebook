package com.example.sebook.ui.theme.screens.informasi

import android.R.attr.description
import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sebook.R
import com.example.sebook.ui.theme.components.BottomNavBar
import com.example.sebook.ui.theme.components.CustomButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRuangan(
    navController: NavController,
    innerPadding: PaddingValues,
    roomName: String,
    imageResources: List<Int>,
    description: String) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Detail Ruangan",
                        fontFamily = FontFamily(Font(R.font.poppins_extrabold)),
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                // TAMBAHKAN INI untuk turunkan TopAppBar
                windowInsets = WindowInsets(top = 24.dp)
            )
        },
//        bottomBar = {
//            BottomNavBar(navController = navController)
//        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 26.dp, horizontal = 36.dp)
                ) {
                    // Image Slider dengan HorizontalPager
                    val pagerState = rememberPagerState(
                        initialPage = 0,
                        pageCount = { 3 } // 3 gambar per ruangan
                    )
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp))

                            { page ->
                        Image(
                            painter = painterResource(id = imageResources[page]), // Menggunakan image dari data
                            contentDescription = "Ruangan Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(10.dp))
                            ,
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Pagination Dots
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(3) { index ->
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .size(if (pagerState.currentPage == index) 10.dp else 8.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (pagerState.currentPage == index) Color(0xFFFF8C00)
                                        else Color(0xFFD9D9D9)
                                    )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(26.dp))
// Tombol-tombol
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomButton(
                            text = "Ajukan Booking",
                            onClick = { /* Navigate to booking */ },
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(0.4f),
                            fontSize = 11.sp // Mengubah fontSize hanya pada tombol ini
                        )
                        CustomButton(
                            text = "Jadwal Tersedia",
                            onClick = { /* Navigate to schedule */ },
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(0.7f),
                            fontSize = 11.sp // Mengubah fontSize hanya pada tombol ini
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))

                    // Nama Ruangan
                    Text(
                        text = roomName, // Nama ruangan dari parameter                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_extrabold)),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()  // Membuat lebar mengambil seluruh kolom
                            .padding(start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Deskripsi Ruangan
                    Text(
                        text = description, // Deskripsi dari parameter
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.spacegrotesk_regular)),
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailRuangan() {
    val roomName = "Ruangan Tengah"
    val imageResources = listOf(R.drawable.tengah_1, R.drawable.tengah_2, R.drawable.tengah_3) // Ganti dengan gambar yang sesuai
    val description = "Ruangan Tengah di Sakato Community Hub merupakan ruang serbaguna yang dirancang untuk mendukung kegiatan kolaboratif berskala menengah hingga besar. Dilengkapi dengan fasilitas modern dan suasana yang profesional, ruang ini ideal untuk kegiatan seperti rapat tim, workshop, presentasi, hingga diskusi komunitas."

    DetailRuangan(navController = rememberNavController(), innerPadding = PaddingValues(0.dp),roomName = roomName, imageResources = imageResources, description = description)
}