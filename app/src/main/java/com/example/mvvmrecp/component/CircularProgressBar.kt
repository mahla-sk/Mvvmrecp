package com.example.mvvmrecp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(isDisplayed:Boolean){
if (isDisplayed){
    Row(modifier = Modifier
        .padding(top = 50.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        CircularProgressIndicator(color = MaterialTheme.colors.primary)
    }
}
}