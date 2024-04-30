package com.tunahanbolat.composeweatherappwithhilt.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tunahanbolat.composeweatherappwithhilt.animation.shimmerLoadingAnimation
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.AppTheme

@Composable
fun ShimmerLoadingScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .border(border = BorderStroke(width = 4.dp, color = Color.Black))
            .padding(12.dp)
    ){
        Column(
            modifier = Modifier.align(alignment = Alignment.TopCenter)
        ) {
            ComponentRectangle()
            Spacer(modifier = Modifier.padding(8.dp))
            ComponentRectangle()
            Spacer(modifier = Modifier.padding(8.dp))
            ComponentRectangle()
            Spacer(modifier = Modifier.padding(8.dp))
            ComponentRectangle()
            Spacer(modifier = Modifier.padding(8.dp))
            ComponentRectangle()
            Spacer(modifier = Modifier.padding(8.dp))
            ComponentRectangle()
            Spacer(modifier = Modifier.padding(8.dp))
            ComponentRectangle()
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }

}

@Composable
fun ComponentRectangle(){
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color.LightGray)
            .height(100.dp)
            .fillMaxWidth()
            .shimmerLoadingAnimation()
    )
}

@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShimmerScreenPreview() {
    AppTheme {
        ShimmerLoadingScreen()
    }
}