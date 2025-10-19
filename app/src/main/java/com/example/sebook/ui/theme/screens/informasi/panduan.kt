package com.example.sebook.ui.theme.screens.panduan


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.compose.rememberNavController
import com.example.sebook.R
import com.example.sebook.ui.theme.components.BottomNavBar
import com.example.sebook.ui.theme.components.CustomButton
import com.github.barteksc.pdfviewer.PDFView  // IMPORT INI PENTING!
import java.io.File
import java.io.FileOutputStream


@Composable
fun PanduanScreen(innerPadding: PaddingValues) {

    val navController = rememberNavController()
    val context = LocalContext.current
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                // Kolom utama untuk konten
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 100.dp) // Memberikan ruang bawah untuk gambar gelombang
                        .padding(16.dp) // Memberikan padding ke seluruh kolom
                        .padding(innerPadding)  // Menggunakan innerPadding dari Scaffold
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(13.dp))

                    // Logo SEBOOK
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Start
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
                    }
                    Spacer(modifier = Modifier.height(18.dp))

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

                    // Tombol Panduan
                    Spacer(modifier = Modifier.height(24.dp))
                    // Row untuk meletakkan tombol di pojok kanan
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), // Mengisi lebar layar
                        horizontalArrangement = Arrangement.End // Menempatkan tombol di kanan
                    ) {
                        CustomButton(
                            text = "Download",
                            onClick = { downloadPDF(context) }

                        )
                    }
                }


// Menambahkan gambar gelombang hijau di bawah halaman
                Image(
                    painter = painterResource(id = R.drawable.rectangle_4), // Ganti dengan ID gambar Anda
                    contentDescription = "Gelombang Hijau",
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 40.dp)
                        .align(Alignment.BottomCenter), // Menempatkan gambar di bawah tengah
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
                // Langsung load dari raw resource
                fromStream(ctx.resources.openRawResource(R.raw.panduan))
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(true)
                    .spacing(10)
                    .onError { throwable ->
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
        // Ambil PDF dari raw resource
        val inputStream = context.resources.openRawResource(R.raw.panduan)

        // Tentukan lokasi download
        val downloadFolder = File(context.getExternalFilesDir(null), "Downloads")
        if (!downloadFolder.exists()) {
            downloadFolder.mkdirs()
        }

        val file = File(downloadFolder, "Panduan_Peminjaman.pdf")
        val outputStream = FileOutputStream(file)

        // Copy file
        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()

        // Notifikasi berhasil
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

//// Fungsi untuk buka PDF dengan aplikasi eksternal
//fun openPDF(context: Context) {
//    try {
//        // Ambil PDF dari raw resource
//        val inputStream = context.resources.openRawResource(R.raw.putih)
//
//        // Simpan ke cache
//        val file = File(context.cacheDir, "Panduan_Peminjaman.pdf")
//        val outputStream = FileOutputStream(file)
//
//        inputStream.copyTo(outputStream)
//        inputStream.close()
//        outputStream.close()
//
//        // Buat URI dengan FileProvider
//        val uri = FileProvider.getUriForFile(
//            context,
//            "${context.packageName}.provider",
//            file
//        )
//
//        // Intent untuk buka PDF
//        val intent = Intent(Intent.ACTION_VIEW).apply {
//            setDataAndType(uri, "application/pdf")
//            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK
//        }
//
//        // Cek apakah ada aplikasi yang bisa buka PDF
//        if (intent.resolveActivity(context.packageManager) != null) {
//            context.startActivity(intent)
//        } else {
//            android.widget.Toast.makeText(
//                context,
//                "Tidak ada aplikasi untuk membuka PDF",
//                android.widget.Toast.LENGTH_SHORT
//            ).show()
//        }
//    } catch (e: Exception) {
//        android.widget.Toast.makeText(
//            context,
//            "Gagal membuka PDF: ${e.message}",
//            android.widget.Toast.LENGTH_SHORT
//        ).show()
//        e.printStackTrace()
//    }
//}


@Preview(showBackground = true)
@Composable
fun PreviewPanduanScreen() {
    PanduanScreen(innerPadding = PaddingValues(0.dp))
}