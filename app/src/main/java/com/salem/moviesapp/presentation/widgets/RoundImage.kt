package com.salem.moviesapp.presentation.widgets


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.salem.moviesapp.R
import com.spr.jetpack_loading.components.indicators.BallSpinFadeLoaderIndicator

@Composable
fun RoundImage(url: String) {

    var loading by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
        ,
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(
                    model = url,
                    onLoading = {
                        loading = true
                    },
                    onSuccess = {
                        loading = false
                    },
                    onError = {
                        loading = false
                    }
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            if (loading) {
                BallSpinFadeLoaderIndicator(
                    color = colorResource(id = R.color.black),
                    ballRadius = 10f,
                    radius = 50f
                )
            }
        }

    }
}