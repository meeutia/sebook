package com.example.sebook.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sebook.R
import com.example.sebook.ui.theme.components.BottomNavBar
import com.example.sebook.ui.theme.components.CustomButton


@Composable
fun Home() {
    // Wrapper untuk preview; aplikasi akan menampilkan HomeContent melalui NavHost di AppRoot
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = rememberNavController())
        },
        content = { innerPadding ->
            HomeContent(innerPadding)
        }
    )
}


// Isi konten scrollable dari halaman Home
@Composable
fun HomeContent(innerPadding: PaddingValues) {
    var currentSlide by remember { mutableStateOf(0) }
    val listState = rememberLazyListState()

    // Update currentSlide ketika LazyRow digulir
    LaunchedEffect(listState.firstVisibleItemIndex) {
        currentSlide = listState.firstVisibleItemIndex
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(innerPadding)  // Padding dari Scaffold
            .background(Color.White)  // Menambahkan background putih pada Column

    ) {
        Spacer(modifier = Modifier.height(18.dp))

        // Top Bar dengan Logo dan Profile
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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

            // Profile Section
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = "Pengguna",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = Color.Black,
                        textAlign = TextAlign.End
                    )

                    Text(
                        text = "Lihat Profil",
                        color = Color(0xFF6B8E7F),
                        fontSize = 10.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.clickable { /* Navigate to profile */ }
                    )
                }

                // Profile Image
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Curved Background Section with "Welcome" text (placed in front)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // Curved Image in front (Rectangle_2 background)

            // Card in front of the background (this will appear in front of the rectangle image)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(300.dp)
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Banner Image (you can replace this with other image or leave as is)
                    Image(
                        painter = painterResource(id = R.drawable.profile_placeholder),
                        contentDescription = "Welcome Banner",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Image(
                        painter = painterResource(id = R.drawable.rectangle_2), // Curved background
                        contentDescription = "Curved Background",
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 150.dp)
                            .align(Alignment.TopCenter)
                    )

                    // Overlay with Text and Button
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    )

                    // Welcome Text
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(14.dp)
                            .offset(y = 35.dp)
                    ) {
                        Text(
                            text = "Welcome,",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

        // Nama Pengguna and Booking Button Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // User's Name (Nama Pengguna)
            Text(
                text = "Pengguna",  // Replace with dynamic username
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 12.dp)
            )

            // Move the button down using the custom ButtonComponent
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButton(
                    text = "Booking",
                    onClick = { /* Navigate to booking */ },
                    modifier = Modifier
                        .padding(top = 12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Informasi Ruangan Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Informasi Ruangan",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Selengkapnya",
                fontSize = 13.sp,
                color = Color(0xFF6B8E7F),
                modifier = Modifier.clickable { /* Navigate to all rooms */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Room Cards Carousel
        LazyRow(
            state = listState,  // Terapkan LazyListState untuk mendeteksi scroll
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(3) { index ->
                RoomCard(
                    roomName = when (index) {
                        0 -> "Ruang Rapat Besar"
                        1 -> "Ruang Seminar"
                        2 -> "Ruang Kelas"
                        else -> "Ruang Lain"
                    },
                    onClick = { currentSlide = index },
                    imageResourceId = when (index) {
                        0 -> R.drawable.room_1
                        1 -> R.drawable.room_2
                        2 -> R.drawable.room_3
                        else -> R.drawable.room_default
                    }
                )
            }
        }

        // Pagination Dots
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(if (currentSlide == index) 10.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (currentSlide == index) Color(0xFFFF8C00)
                            else Color(0xFFD9D9D9)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun RoomCard(
    roomName: String,
    onClick: () -> Unit,
    imageResourceId: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .width(320.dp)
                .height(240.dp)
                .clickable { onClick() },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = painterResource(id = imageResourceId),
                        contentScale = ContentScale.Crop,
                        contentDescription = roomName,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Text(
                    text = roomName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}
