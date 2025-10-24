package com.example.sebook.ui.theme.screens.informasi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.sebook.ui.theme.components.CustomButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRuangan(
    navController: NavController,
    innerPadding: PaddingValues,
    roomName: String,
    imageResources: List<Int>,
    description: String,
    capacity: Int,
    facilities: List<String>
) {
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
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                windowInsets = WindowInsets(top = 24.dp)
            )
        },
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
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 26.dp, horizontal = 36.dp)
                ) {
                    val pagerState = rememberPagerState(
                        initialPage = 0,
                        pageCount = { imageResources.size }
                    )
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                    ) { page ->
                        Image(
                            painter = painterResource(id = imageResources[page]),
                            contentDescription = "Ruangan Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(imageResources.size) { index ->
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
                    Spacer(modifier = Modifier.height(20.dp))

                    // Tombol aksi
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomButton(
                            text = "Ajukan Booking",
                            onClick = { navController.navigate("jadwal/1") },
                            modifier = Modifier
                                .padding(3.dp)
                                .fillMaxWidth(0.4f),
                            fontSize = 11.sp
                        )
                        CustomButton(
                            text = "Jadwal Tersedia",
                            onClick = { navController.navigate("jadwal/0") },
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxWidth(0.9f),
                            fontSize = 11.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Nama Ruangan
                    Text(
                        text = roomName,
                        fontFamily = FontFamily(Font(R.font.poppins_extrabold)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Kapasitas
                    Text(
                        text = "Kapasitas: $capacity orang",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.spacegrotesk_regular)),
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Fasilitas
                    Text(
                        text = "Fasilitas:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.poppins_extrabold))
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        facilities.forEach { item ->
                            Text(
                                text = "• $item",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.spacegrotesk_regular))
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Deskripsi Ruangan
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.spacegrotesk_regular)),
                        lineHeight = 20.sp
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailRuangan() {
    val roomName = "Ruangan Tengah"
    val imageResources = listOf(R.drawable.tengah_1, R.drawable.tengah_2, R.drawable.tengah_3)
    val description = "Ruangan Tengah di Sakato Community Hub merupakan ruang serbaguna yang dirancang untuk mendukung kegiatan kolaboratif berskala menengah hingga besar. Dilengkapi dengan fasilitas modern dan suasana yang profesional, ruang ini ideal untuk kegiatan seperti rapat tim, workshop, presentasi, hingga diskusi komunitas."
    DetailRuangan(
        navController = rememberNavController(),
        innerPadding = PaddingValues(0.dp),
        roomName = roomName,
        imageResources = imageResources,
        description = description,
        capacity = 30,
        facilities = listOf("Proyektor + layar", "Kursi & meja", "Stop kontak")
    )
}