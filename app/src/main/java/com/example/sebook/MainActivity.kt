package com.example.sebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sebook.ui.theme.SebookTheme
import com.example.sebook.ui.theme.components.BottomNavBar
import com.example.sebook.ui.theme.screens.*
import com.example.sebook.ui.theme.screens.booking.BookingFormScreen
import com.example.sebook.ui.theme.screens.booking.BookingScreen
import com.example.sebook.ui.theme.screens.forum.ForumScreen
import com.example.sebook.ui.theme.screens.informasi.DetailRuangan
import com.example.sebook.ui.theme.screens.informasi.RuanganDiSakato
import com.example.sebook.ui.theme.screens.notification.NotificationScreen
import com.example.sebook.ui.theme.screens.riwayat.RiwayatPengajuan
import com.example.sebook.ui.theme.screens.panduan.PanduanScreen
import com.example.sebook.ui.theme.screens.ChangePasswordScreen
import com.example.sebook.ui.theme.screens.riwayat.ReviewScreen

// MainActivity: Entry point for the Booking Room App
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set up the Compose UI with the app theme and root navigation
        setContent {
            SebookTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Tampilkan BottomNavBar hanya pada route tab utama
                val showBottomBar = when (currentRoute) {
                    "home", "history", "information", "forum" -> true
                    else -> false
                }

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) BottomNavBar(navController = navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "welcome",
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable("welcome") {
                            WelcomeScreen(
                                onLoginClick = { navController.navigate("login") },
                                onRegisterClick = { navController.navigate("regist") }
                            )
                        }
                        composable("regist") { Regist(onRegisterClick = { navController.navigate("login") { launchSingleTop = true } }) }
                        composable("login") {
                            Login(onLoginClick = {
                                navController.navigate("home") {
                                    popUpTo("welcome") { inclusive = true }
                                    launchSingleTop = true
                                }
                            })
                        }
                        composable("home") { Box(Modifier.fillMaxSize()) { HomeContent(innerPadding, navController) } }
                        composable("history") { Box(Modifier.fillMaxSize()) { RiwayatPengajuan(navController) } }
                        composable("information") { Box(Modifier.fillMaxSize()) { RuanganDiSakato(innerPadding, navController) } }
                        composable("forum") { Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { ForumScreen(navController) } }

                        // Additional direct screens
                        composable("notifications") { NotificationScreen(navController) }
                        composable("profile") { ProfileScreen(navController) }
                        composable("change_password") { ChangePasswordScreen(navController) }
                        composable("booking") { BookingScreen(navController) }
                        composable("jadwal/{selectable}") { backStackEntry ->
                            val selectable = backStackEntry.arguments?.getString("selectable") == "1"
                            BookingScreen(navController = navController, selectable = selectable)
                        }
                        composable("panduan") { PanduanScreen(innerPadding, navController) }
                        composable("detail_ruangan/{roomKey}") { backStackEntry ->
                            val key = backStackEntry.arguments?.getString("roomKey").orEmpty()
                            val roomName: String
                            val images: List<Int>
                            val description: String
                            val capacity: Int
                            val facilities: List<String>

                            when (key) {
                                "ampliteather" -> {
                                    roomName = "Ruang Ampliteather"
                                    images = listOf(
                                        R.drawable.amplifier_1,
                                        R.drawable.amplifier_2,
                                        R.drawable.amplifier_3
                                    )
                                    description = "Ruang Ampliteather cocok untuk presentasi terbuka dan kegiatan komunitas dengan tata ruang tribun."
                                    capacity = 60
                                    facilities = listOf("Tribun bertingkat", "Sound system", "Panggung kecil")
                                }
                                "tengah" -> {
                                    roomName = "Ruang Tengah"
                                    images = listOf(
                                        R.drawable.tengah_1,
                                        R.drawable.tengah_2,
                                        R.drawable.tengah_3
                                    )
                                    description = "Ruang serbaguna yang ideal untuk rapat, workshop, dan presentasi."
                                    capacity = 30
                                    facilities = listOf("Proyektor + layar", "Kursi & meja", "Stop kontak")
                                }
                                "outdor" -> {
                                    roomName = "Ruang Outdor"
                                    images = listOf(
                                        R.drawable.belakang_1,
                                        R.drawable.belakang_2,
                                        R.drawable.belakang_3
                                    )
                                    description = "Area luar ruangan cocok untuk diskusi santai atau acara komunitas."
                                    capacity = 40
                                    facilities = listOf("Area terbuka", "Pencahayaan outdoor", "Akses listrik terbatas")
                                }
                                else -> {
                                    roomName = "Ruang Ampliteather"
                                    images = listOf(
                                        R.drawable.amplifier_1,
                                        R.drawable.amplifier_2,
                                        R.drawable.amplifier_3
                                    )
                                    description = "Ruang Ampliteather cocok untuk presentasi terbuka dan kegiatan komunitas dengan tata ruang tribun."
                                    capacity = 60
                                    facilities = listOf("Tribun bertingkat", "Sound system", "Panggung kecil")
                                }
                            }

                            DetailRuangan(
                                navController = navController,
                                innerPadding = innerPadding,
                                roomName = roomName,
                                imageResources = images,
                                description = description,
                                capacity = capacity,
                                facilities = facilities
                            )
                        }
                        composable("form") { BookingFormScreen() }
                        composable("review") { ReviewScreen(navController) }
                    }
                }
            }
        }
    }
}
