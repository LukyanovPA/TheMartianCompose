package com.pavellukyanov.themartiancompose.ui.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pavellukyanov.themartiancompose.R
import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover
import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rovers

@Composable
fun RoverItem(
    rover: Rover,
    modifier: Modifier = Modifier,
    onClickRover: () -> Unit
) {
    val roverImage = when (rover.type) {
        Rovers.PERSEVERANCE -> R.drawable.perseverance
        Rovers.CURIOSITY -> R.drawable.curiosity
        Rovers.OPPORTUNITY -> R.drawable.opportunity
        Rovers.SPIRIT -> R.drawable.spirit
        else -> R.drawable.rover_placeholder
    }
    Card(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .clickable { onClickRover() },
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(Color(0xffffab91))
    ) {
        Box {
            Image(
                painter = painterResource(id = roverImage),
                contentDescription = stringResource(id = R.string.cd_rover_image),
                contentScale = ContentScale.FillWidth
            )
            Text(
                color = Color.White,
                text = rover.roverName,
                modifier = Modifier
                    .padding(
                    end = 8.dp,
                    bottom = 8.dp
                )
                    .align(Alignment.BottomEnd),
                fontWeight = FontWeight.Light,
                fontSize = 24.sp
            )
        }
    }
}