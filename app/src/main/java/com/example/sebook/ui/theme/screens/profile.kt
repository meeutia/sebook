package com.example.sebook.ui.theme.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sebook.R
import com.example.sebook.ui.theme.components.CustomButton
import com.example.sebook.ui.theme.components.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: ProfileViewModel = viewModel(factory = ProfileViewModelFactory(context))
    val uiState by viewModel.uiState.collectAsState()
    var showSuccessDialog by remember { mutableStateOf(false) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.updateImage(uri?.toString())
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profile",
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
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Profile Picture Section
                Box(
                    modifier = Modifier.size(120.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    if (!uiState.imageUri.isNullOrBlank()) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(uiState.imageUri)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Profile Picture",
                            placeholder = ColorPainter(Color(0xFFE8E8E8)),
                            error = ColorPainter(Color(0xFFE8E8E8)),
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .border(3.dp, Color(0xFFF96300), CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        // Default avatar
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE8E8E8))
                                .border(3.dp, Color(0xFFF96300), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Default Avatar",
                                modifier = Modifier.size(60.dp),
                                tint = Color.Gray
                            )
                        }
                    }

                    // Edit Icon
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF96300))
                            .clickable { imagePickerLauncher.launch("image/*") },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit Photo",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tap to change photo",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Form Section
                Text(
                    text = "Informasi Akun",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A2B3C),
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = uiState.fullName,
                    onValueChange = viewModel::updateFullName,
                    label = "Nama Lengkap",
                    placeholder = "Nama Lengkap",
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next,
                    isError = uiState.nameInvalid,
                    supportingText = if (uiState.nameInvalid) "Tulis nama lengkap (tanpa angka)" else null
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = uiState.username,
                    onValueChange = viewModel::updateUsername,
                    label = "Username",
                    placeholder = "nama_pengguna",
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.None,
                    imeAction = ImeAction.Next,
                    isError = uiState.usernameInvalid,
                    supportingText = if (uiState.usernameInvalid) "Username wajib diisi" else null
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = uiState.email,
                    onValueChange = viewModel::updateEmail,
                    label = "Email",
                    placeholder = "nama@domain.com",
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                    isError = uiState.emailInvalid,
                    supportingText = if (uiState.emailInvalid) "Format email tidak valid" else null
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Change Password Section -> navigate to dedicated screen
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("change_password") }
                        .border(1.dp, Color(0xFFDBE5DB), RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Password",
                            tint = Color(0xFFF96300)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Ganti Password",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        contentDescription = "Arrow",
                        tint = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Save Button
                CustomButton(
                    text = "SIMPAN PERUBAHAN",
                    onClick = {
                        if (uiState.canSave) {
                            viewModel.saveProfile()
                            showSuccessDialog = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Logout Button
                OutlinedButton(
                    onClick = { /* Handle logout */ },
                    modifier = Modifier
                        .width(150.dp)
                        .height(36.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFF44336)
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF44336))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Logout",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "LOGOUT",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    )


    // Success Dialog
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Success",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(48.dp)
                )
            },
            title = {
                Text(
                    text = "Berhasil!",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text("Perubahan profil berhasil disimpan")
            },
            confirmButton = {
                Button(
                    onClick = { showSuccessDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF96300)
                    )
                ) {
                    Text("OK")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}