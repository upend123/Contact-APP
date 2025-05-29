package com.example.contactapp.data.presantation.screens.splash

import android.graphics.fonts.Font
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo


@Composable
fun SplashScreenUI(viewModel: SplashScreenViewModel = SplashScreenViewModel(),
                   onClick: () -> Unit){
var isFinishedLoading = viewModel.isLoadingFinished

    LaunchedEffect(isFinishedLoading) {
       if (isFinishedLoading)
           onClick()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)
     , verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
     {
         Icon(imageVector = Icons.Default.Person, contentDescription = "logo"
         , modifier = Modifier.size(140.dp).clip(CircleShape).background(Color.LightGray).padding(24.dp)
         , tint = MaterialTheme.colorScheme.primary )
         Spacer(modifier = Modifier.height(10.dp))
         Text("Contact App", fontWeight = FontWeight.SemiBold, fontSize = 26.sp)
         Spacer(modifier = Modifier.height(20.dp))
         CircularProgressIndicator()

     }





}
