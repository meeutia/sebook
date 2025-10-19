package com.example.sebook.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.sebook.R
import com.example.sebook.ui.theme.screens.RoomCard

@Composable
fun RoomCard(
    roomName: String,
    onClick: () -> Unit,
    imageResourceId: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
//            .padding(horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .width(330.dp)
                .height(160.dp)
                .clickable { onClick() },
            shape = RoundedCornerShape(11.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF96300))
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .clip(RoundedCornerShape(10.dp))
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
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.spacegrotesk_regular)),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoomCard() {
    // Mengganti preview dengan parameter yang valid
    RoomCard(
        roomName = "Ruang Rapat Besar",
        onClick = { /* Handle click action */ },
        imageResourceId = R.drawable.room_1  // Ganti dengan gambar yang ada di folder drawable Anda
    )
}
