package com.tunahanbolat.composeweatherappwithhilt.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.sourceInformationMarkerStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tunahanbolat.composeweatherappwithhilt.animation.shimmerLoadingAnimation
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.AppTheme
import com.tunahanbolat.composeweatherappwithhilt.ui.theme.kaushan
import kotlin.math.roundToLong

@Composable
fun ShimmerLoadingScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .border(border = BorderStroke(width = 4.dp, color = Color.Black))
            .padding(12.dp)
    ) {
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
fun ComponentRectangle() {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color.LightGray)
            .height(100.dp)
            .fillMaxWidth()
            .shimmerLoadingAnimation()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            ComponentLongRectangle()
            Spacer(modifier = Modifier.padding(2.dp))
            ComponentShortRectangle()
        }
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp)
                .weight(3f),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.padding(1.dp))
            ComponentCircle()
            ComponentSquare()
        }
    }
}


@Composable
fun ComponentLongRectangle() {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .height(30.dp)
            .width(140.dp)
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentShortRectangle() {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .height(30.dp)
            .width(70.dp)
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentCircle() {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .background(color = Color.Gray)
            .height(70.dp)
            .width(70.dp)
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentSquare() {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
            .height(50.dp)
            .width(60.dp)
            .shimmerLoadingAnimation()
    )
}

@Preview
@Composable
fun ComponentsLongRectPreview() {
    ComponentLongRectangle()
}

@Preview
@Composable
fun ComponentsShortRectPreview() {
    ComponentShortRectangle()
}

@Preview
@Composable
fun ComponentsCirclePreview() {
    ComponentCircle()
}

@Preview
@Composable
fun ComponentSquarePreview() {
    ComponentSquare()
}


@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShimmerScreenPreview() {
    AppTheme {
        ShimmerLoadingScreen()
    }
}