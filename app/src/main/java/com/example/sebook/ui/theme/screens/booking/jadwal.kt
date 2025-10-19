package com.example.sebook.ui.theme.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import java.util.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import androidx.compose.runtime.*

import com.example.sebook.R

import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.DatePickerDefaults.dateFormatter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.sebook.ui.theme.components.CustomButton

data class BookingInfo(
    val day: Int,
    val bookedBy: String,
    val time: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController) {
    var currentCalendar by remember { mutableStateOf(Calendar.getInstance()) }
    var selectedDate by remember { mutableStateOf<Int?>(null) }

    // Contoh data booking dengan info lengkap
    val bookingInfoList = remember {
        mutableStateOf(
            listOf(
                BookingInfo(4, "PT Maju Jaya", "09:00 - 12:00"),
                BookingInfo(16, "Komunitas IT Sakato", "13:00 - 17:00"),
                BookingInfo(20, "Dinas Pendidikan", "08:00 - 10:00")
            )
        )
    }

    val bookedDates = remember { mutableStateOf(setOf(4, 16, 20)) } // Contoh tanggal yang sudah dibooking

    val monthFormatter = SimpleDateFormat("MMMM yyyy", Locale("id", "ID"))

    Scaffold(
        containerColor = Color.White,
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
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(26.dp)
                    .background(Color.White)

            ) {
                // Month Selector
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF0F5F0)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            currentCalendar = (currentCalendar.clone() as Calendar).apply {
                                add(Calendar.MONTH, -1)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Previous Month",
                                tint = Color(0xFF6B8E7F)
                            )
                        }

                        Text(
                            text = monthFormatter.format(currentCalendar.time),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        IconButton(onClick = {
                            currentCalendar = (currentCalendar.clone() as Calendar).apply {
                                add(Calendar.MONTH, 1)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Next Month",
                                tint = Color(0xFF6B8E7F)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Day Names (Sen, Sel, Rab, etc.)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf("Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab").forEach { day ->
                        Text(
                            text = day,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Calendar Grid
                val daysInMonth = getDaysInMonthWithCalendar(currentCalendar)

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    contentPadding = PaddingValues(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(daysInMonth.size) { index ->
                        val day = daysInMonth[index]
                        if (day > 0) {
                            DayCell(
                                day = day,
                                isBooked = bookedDates.value.contains(day),
                                isSelected = selectedDate == day,
                                onClick = {
                                    if (!bookedDates.value.contains(day)) {
                                        selectedDate = day
                                    }
                                }
                            )
                        } else {
                            // Empty cell for alignment
                            Box(modifier = Modifier.size(48.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Legend
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LegendItem(color = Color(0xFF6B8E7F), text = "Telah Dibooking")
                    LegendItem(color = Color(0xFFF0F5F0), text = "Tersedia")
                    LegendItem(color = Color(0xFFFF8C00), text = "Dipilih")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Daftar Booking yang sudah ada
                Text(
                    text = "Jadwal yang Telah Dibooking:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Card untuk setiap booking
                bookingInfoList.value.forEach { bookingInfo ->
                    BookingInfoCard(bookingInfo = bookingInfo)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(12.dp))

                CustomButton(
                    text = "Ajukan Booking",
                    onClick = {

                    },
                    modifier = Modifier
                        .align(Alignment.End), // Align the button to the bottom right ,
                    fontSize = 13.sp
                )
            }
        }
    )
}

@Composable
fun BookingInfoCard(bookingInfo: BookingInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Date Circle
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .background(Color(0xFF6B8E7F), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${bookingInfo.day}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Booking Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = bookingInfo.bookedBy,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Jam: ${bookingInfo.time}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun DayCell(day: Int, isBooked: Boolean, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = when {
        isBooked -> Color(0xFF6B8E7F)
        isSelected -> Color(0xFFFF8C00)
        else -> Color(0xFFF0F5F0)
    }
    val textColor = if (isBooked || isSelected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .size(48.dp)
            .padding(4.dp)
            .background(backgroundColor, CircleShape)
            .clickable(enabled = !isBooked, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$day",
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun LegendItem(color: Color, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color, CircleShape)
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

fun getDaysInMonthWithCalendar(calendar: Calendar): List<Int> {
    val cal = calendar.clone() as Calendar
    cal.set(Calendar.DAY_OF_MONTH, 1)

    val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1 // 0 = Sunday

    val days = mutableListOf<Int>()

    // Add empty cells for alignment
    repeat(firstDayOfWeek) {
        days.add(0)
    }

    // Add actual days
    for (day in 1..daysInMonth) {
        days.add(day)
    }

    return days
}

@Preview(showBackground = true)
@Composable
fun PreviewBookingScreen() {
    BookingScreen(navController = rememberNavController())
}