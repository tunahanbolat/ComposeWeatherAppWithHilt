package com.tunahanbolat.composeweatherappwithhilt.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tunahanbolat.composeweatherappwithhilt.R


@Composable
fun deneme(){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            Color.Cyan
        )

    ) {
        Box(modifier = Modifier.fillMaxSize()){
//            Image(
//                painter = painterResource(id = R.drawable.rainy),
//                contentDescription = null,
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.FillBounds
//                )
            Row() {
                Column() {
                    Text(
                        text = "ÇORUM",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, top = 24.dp)
                    )
                    Text(
                        text = "Parçalı Bulutlu",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))

                Text(
                    text = "12",
                    modifier = Modifier
                        .padding(top = 30.dp),
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.DarkGray
                )
            }
        }



    }
}

@Preview
@Composable
fun ekran(){
    deneme()
}