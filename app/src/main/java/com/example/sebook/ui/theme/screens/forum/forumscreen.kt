package com.example.sebook.ui.theme.screens.forum


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sebook.R
import com.example.sebook.ui.theme.components.BottomNavBar

data class Review(
    val userName: String,
    val userImage: Int,
    val timeAgo: String,
    val rating: Int,
    val reviewText: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumScreen(navController: NavController) {
    val reviews = listOf(
        Review(
            userName = "Ethan Walker",
            userImage = R.drawable.user1,
            timeAgo = "2 weeks ago",
            rating = 5,
            reviewText = "The climb was challenging but incredibly rewarding. The views from the summit were breathtaking, and the trail was well-maintained. Highly recommend for experienced hikers."
        ),
        Review(
            userName = "Sophia Bennett",
            userImage = R.drawable.user2,
            timeAgo = "1 month ago",
            rating = 4,
            reviewText = "Enjoyed the hike overall, but some sections were quite steep. The scenery was beautiful, and the weather was perfect. Would suggest bringing plenty of water."
        ),
        Review(
            userName = "Liam Carter",
            userImage = R.drawable.user3,
            timeAgo = "2 months ago",
            rating = 3,
            reviewText = "The trail was a bit crowded, and some parts were poorly marked. The views were decent, but not as spectacular as I expected. Could be better."
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Forum",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                windowInsets = WindowInsets(top = 24.dp)
            )
        },
        bottomBar = {
            BottomNavBar(navController = rememberNavController())
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
            ) {
                // Recent Reviews Title
                Text(
                    text = "Recent Reviews",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                // Reviews List
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(reviews) { review ->
                        ReviewItem(review = review)
                        Divider(
                            color = Color(0xFFE0E0E0),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun ReviewItem(review: Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // User Info Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // User Avatar
            Image(
                painter = painterResource(id = review.userImage),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // User Name and Time
            Column {
                Text(
                    text = review.userName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = review.timeAgo,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Star Rating
        Row {
            for (i in 1..5) {
                Icon(
                    painter = painterResource(
                        id = if (i <= review.rating) R.drawable.ic_star_filled else R.drawable.ic_star_empty
                    ),
                    contentDescription = "Star $i",
                    modifier = Modifier.size(20.dp),
                    tint = if (i <= review.rating) Color(0xFFF96300) else Color.LightGray
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Review Text
        Text(
            text = review.reviewText,
            fontSize = 14.sp,
            color = Color.DarkGray,
            lineHeight = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ForumScreenPreview() {
    ForumScreen(navController = rememberNavController())
}