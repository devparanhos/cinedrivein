package com.example.cinedrivein.presentation.components.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.cinedrivein.R

@Composable
fun MovieCard(
    image: String
){
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        modifier = Modifier.height(237.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()){
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(1000)
                    .transformations(RoundedCornersTransformation())
                    .build(),
                placeholder = painterResource(id = R.drawable.no_poster),
                error = painterResource(id = R.drawable.no_poster)
            )

            Image(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}