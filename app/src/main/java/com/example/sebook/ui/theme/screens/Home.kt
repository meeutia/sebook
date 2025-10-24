package com.example.sebook.ui.theme.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue


@Composable
fun Home() {
    // Wrapper untuk preview; aplikasi akan menampilkan HomeContent melalui NavHost di MainActivity
    val previewNav = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = previewNav)
        },
        content = { innerPadding ->
            HomeContent(innerPadding, previewNav)
        }
    )
}


@Composable
fun HomeContent(innerPadding: PaddingValues, navController: NavHostController) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 3 }
    )

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
                    // Notification Button with Badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box {
                            IconButton(
                                onClick = { navController.navigate("notifications") },
                                modifier = Modifier.size(45.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_notification),
                                    contentDescription = "Notifications",
                                    modifier = Modifier.size(28.dp),
                                    tint = Color.Black
                                )
                            }

                            // Badge notifikasi (contoh state sementara)
                            val notificationCount by remember { mutableStateOf(5) }
                            if (notificationCount > 0) {
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .align(Alignment.TopEnd)
                                        .offset(x = (-4).dp, y = 4.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFFF3B30)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (notificationCount > 9) "9+" else notificationCount.toString(),
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                // Profile Image
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color(0xFFF96300), CircleShape)
                        .clickable { navController.navigate("profile") },
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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(300.dp)
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_placeholder),
                        contentDescription = "Welcome Banner",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Image(
                        painter = painterResource(id = R.drawable.rectangle_2),
                        contentDescription = "Curved Background",
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 150.dp)
                            .align(Alignment.TopCenter)
                    )

                    Box(modifier = Modifier.fillMaxSize())

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
            Text(
                text = "Pengguna",
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 12.dp)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButton(
                    text = "Booking",
                    onClick = { navController.navigate("booking") },
                    modifier = Modifier.padding(top = 12.dp)
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
                modifier = Modifier.clickable { navController.navigate("information") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            val scale = lerp(
                start = 0.85f,
                stop = 1f,
                fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
            )

            val (roomName, roomKey, imageRes) = when (page) {
                0 -> Triple("Ruang Ampliteather", "ampliteather", R.drawable.amplifier_1)
                1 -> Triple("Ruang Tengah", "tengah", R.drawable.tengah_2)
                2 -> Triple("Ruang Outdor", "outdor", R.drawable.belakang_1)
                else -> Triple("Ruang Ampliteather", "ampliteather", R.drawable.amplifier_1)
            }

            RoomCard(
                roomName = roomName,
                onClick = { navController.navigate("detail_ruangan/$roomKey") },
                imageResourceId = imageRes,
                scale = scale
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
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

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun RoomCard(
    roomName: String,
    onClick: () -> Unit,
    imageResourceId: Int,
    scale: Float = 1f
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
                .fillMaxWidth()
                .height(240.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
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
