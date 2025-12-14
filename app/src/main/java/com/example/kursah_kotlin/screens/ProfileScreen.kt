package com.example.kursah_kotlin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kursah_kotlin.R
import com.example.kursah_kotlin.data.local.UserPreferences
import com.example.kursah_kotlin.ui.theme.PlayfairDisplayFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onNavigationClick: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    var firstName by remember { mutableStateOf<String?>(null) }
    var lastName by remember { mutableStateOf<String?>(null) }
    var age by remember { mutableStateOf<String?>(null) }
    var email by remember { mutableStateOf<String?>(null) }
    var goal by remember { mutableStateOf<String?>(null) }
    var photoPath by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        firstName = userPreferences.getFirstName()
        lastName = userPreferences.getLastName()
        age = userPreferences.getAge()
        email = userPreferences.getEmail()
        goal = userPreferences.getGoal()
        photoPath = userPreferences.getPhotoPath()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(238, 238, 238), RoundedCornerShape(20.dp))
                            .clickable { onBackClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Назад",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Профиль",
                            style = TextStyle(
                                fontFamily = PlayfairDisplayFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedTab = "profile",
                onTabSelected = onNavigationClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Фотография профиля
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(
                        Color(238, 238, 238),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (photoPath != null) {
                    // TODO: Загрузить изображение из photoPath
                    Image(
                        painter = painterResource(id = R.drawable.nutrichef_logo),
                        contentDescription = "Фото профиля",
                        modifier = Modifier.size(120.dp)
                    )
                } else {
                    Text(
                        text = "${firstName?.firstOrNull() ?: ""}${lastName?.firstOrNull() ?: ""}",
                        style = TextStyle(
                            fontFamily = PlayfairDisplayFontFamily,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Имя и фамилия
            Text(
                text = "${firstName ?: ""} ${lastName ?: ""}".trim(),
                style = TextStyle(
                    fontFamily = PlayfairDisplayFontFamily,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Информация о пользователе
            ProfileInfoItem(
                label = "Возраст",
                value = age ?: "Не указан"
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileInfoItem(
                label = "Почта",
                value = email ?: "Не указана"
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileInfoItem(
                label = "Цель использования",
                value = goal ?: "Не указана"
            )

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun ProfileInfoItem(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(238, 238, 238),
                RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = PlayfairDisplayFontFamily,
                fontSize = 14.sp,
                color = Color.Gray
            )
        )
        Text(
            text = value,
            style = TextStyle(
                fontFamily = PlayfairDisplayFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        )
    }
}

