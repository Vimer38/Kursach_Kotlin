package com.example.kursah_kotlin.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun AuthScreen() {
    var loginText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("Войти") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally, ) {
            Text("NutriChef", style = TextStyle(fontSize = 40.sp))
            Spacer(modifier = Modifier.height(50.dp))
            LoginRegisterToggle(
                selectedOption = selectedOption,
                onOptionSelected = { selectedOption = it }
            )
            Spacer(modifier = Modifier.height(30.dp))
            CustomTextField(
                value = loginText,
                onValueChange = { loginText = it },
                placeholder = "Логин"
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                value = passwordText,
                onValueChange = {passwordText = it},
                placeholder = "Пароль"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .size(100.dp,70.dp)
                .padding(horizontal = 16.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),shape = RoundedCornerShape(20.dp), onClick = {}) {
                Text("Войти", modifier = Modifier.padding(15.dp), style =  TextStyle(fontSize = 17.sp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text("Нажимая на кнопку 'Войти', вы принимаете условия")
            Text("Политики Конфиденциальности", style = TextStyle(fontWeight = FontWeight.Bold))
        }
        Text("Продолжить без регистрации", modifier = Modifier.padding(16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Color.Gray,
                fontSize = 16.sp
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.LightGray.copy(alpha = 0.3f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp),
    )

}

@Composable
fun LoginRegisterToggle(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val options = listOf("Войти", "Зарегистрироваться")
    val containerShape = RoundedCornerShape(28.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp)
            .clip(containerShape)
            .background(Color(0xFFF1F1F1))
            .border(width = 1.dp, color = Color(0xFFE0E0E0), shape = containerShape)
            .padding(4.dp)
    ) {
        options.forEach { option ->
            val isSelected = option == selectedOption
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(containerShape)
                    .background(if (isSelected) Color.White else Color.Transparent)
                    .clickable { onOptionSelected(option) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) Color.Black else Color.Gray
                    )
                )
            }
        }
    }
}