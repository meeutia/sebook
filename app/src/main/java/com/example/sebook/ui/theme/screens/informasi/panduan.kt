package com.example.sebook.ui.theme.screens.panduan

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sebook.R
import com.example.sebook.ui.theme.components.CustomButton
import com.github.barteksc.pdfviewer.PDFView
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanduanScreen(innerPadding: PaddingValues, navController: NavController) {

    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Panduan",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { navController.popBackStack() }) {
                        androidx.compose.material3.Icon(
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
            ) {
                // Kolom utama untuk konten
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(bottom = 100.dp)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(12.dp))

                    // Judul Halaman
                    Text(
                        text = "Panduan Peminjaman",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // PDF Viewer Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(450.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        PDFViewScreen()
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Tombol Download di pojok kanan
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        CustomButton(
                            text = "Download",
                            onClick = { downloadPDF(context) }
                        )
                    }
                }

                // Gambar gelombang hijau di bawah halaman
                Image(
                    painter = painterResource(id = R.drawable.rectangle_4),
                    contentDescription = "Gelombang Hijau",
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 40.dp)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    )
}

@Composable
fun PDFViewScreen() {
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            PDFView(ctx, null).apply {
                fromStream(ctx.resources.openRawResource(R.raw.panduan))
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(true)
                    .spacing(10)
                    .onError { _ ->
                        android.widget.Toast.makeText(
                            ctx,
                            "Error",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                    }
                    .load()
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

// Fungsi untuk download PDF ke storage
fun downloadPDF(context: Context) {
    try {
        val inputStream = context.resources.openRawResource(R.raw.panduan)
        val downloadFolder = File(context.getExternalFilesDir(null), "Downloads")
        if (!downloadFolder.exists()) {
            downloadFolder.mkdirs()
        }
        val file = File(downloadFolder, "Panduan_Peminjaman.pdf")
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()
        android.widget.Toast.makeText(
            context,
            "PDF berhasil didownload!\nLokasi: ${file.absolutePath}",
            android.widget.Toast.LENGTH_LONG
        ).show()
    } catch (e: Exception) {
        android.widget.Toast.makeText(
            context,
            "Gagal download PDF: ${e.message}",
            android.widget.Toast.LENGTH_SHORT
        ).show()
        e.printStackTrace()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPanduanScreen() {
    PanduanScreen(innerPadding = PaddingValues(0.dp), navController = rememberNavController())
}