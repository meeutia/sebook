package com.example.sebook.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.sebook.R
import com.example.sebook.ui.theme.components.CustomButton
import com.example.sebook.ui.theme.components.CustomTextField
import androidx.compose.ui.text.input.ImeAction

@Composable
fun Login(onLoginClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            // Logo SEBOOK
            Image(
                painter = painterResource(id = R.drawable.logo_sebook), // tambahkan logo kamu di drawable folder
                contentDescription = "SEBOOK Logo",
                modifier = Modifier
                    .width(280.dp)
                    .height(80.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Login Title
            Text(
                text = "LOGIN",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A2B3C),
                modifier = Modifier.align(Alignment.Start),
                fontFamily = FontFamily(Font(R.font.spacegrotesk_bold)),
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomTextField(
                value = username,
                onValueChange = { username = it },
                label = "Username or Email",
                placeholder = "nama@domain.com atau username",
                keyboardType = KeyboardType.Email,              // biar muncul @/.com suggestion
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password (pakai CustomTextField)
            CustomTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                placeholder = "minimal 8 karakter",
                isPassword = true,
                imeAction = ImeAction.Done
            )


            // Forgot Password
            TextButton(
                onClick = { /* Handle forgot password */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Punya akun?",
                    color = Color(0xFF6B8E7F),
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(44.dp))

            CustomButton(
                text = "LOGIN",
                onClick = onLoginClick,
                modifier = Modifier
                    .align(Alignment.End)
            )


            // Add some space before the background image
            // Decorative Wave at Bottom - FIT untuk mempertahankan bentuk asli

        }

        Image(
            painter = painterResource(id = R.drawable.rectangle_3),
            contentDescription = "Rectangle3 Image",
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()// Batasi tinggi maksimal jika perlu
                .offset(y = 280.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    MaterialTheme {
        Login(onLoginClick =
            { /* Handle login logic here */ })
    }
}
