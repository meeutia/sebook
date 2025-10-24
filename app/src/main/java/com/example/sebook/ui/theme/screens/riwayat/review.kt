package com.example.sebook.ui.theme.screens.riwayat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sebook.R
import androidx.compose.material.icons.filled.ArrowBack
import com.example.sebook.ui.theme.components.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(navController: NavController, onSubmit: () -> Unit = {}) {
    var rating by remember { mutableStateOf(0) }
    var reviewText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Review",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                // Question Title
                Text(
                    text = "Bagaimana Pengalamannya?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Rating
                Text("Rating", fontSize = 14.sp, color = Color.Gray)

                Row(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    for (i in 1..5) {
                        IconButton(onClick = { rating = i }) {
                            Icon(
                                painter = painterResource(id = if (i <= rating) R.drawable.ic_star_filled else R.drawable.ic_star_empty),
                                contentDescription = "Star $i",
                                modifier = Modifier.size(32.dp),
                                tint = if (i <= rating) Color(0xFFF96300) else Color(0xFFF96300)
                            )
                        }
                    }
                }

                // Review Text Area
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = reviewText,
                    onValueChange = { reviewText = it },
                    label = { Text("Tulis Ulasan Anda") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    maxLines = 5,
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        disabledContainerColor = Color(0xFFF5F5F5),
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color(0xFFDBE5DB),
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Tombol Ajukan
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    CustomButton(
                        text = "Submit",
                        onClick = onSubmit
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {
    ReviewScreen(navController = rememberNavController())
}