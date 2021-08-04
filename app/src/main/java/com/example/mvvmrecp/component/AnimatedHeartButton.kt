package com.example.mvvmrecp.component

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.*
import androidx.compose.animation.core.Transition.TransitionAnimationState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toIcon
import androidx.core.graphics.get
import androidx.core.graphics.set
import com.example.mvvmrecp.R
//import com.example.mvvmrecp.component.HeartAnimationDefinition.HeartButtonState.ACTIVE
//import com.example.mvvmrecp.component.HeartAnimationDefinition.HeartButtonState.IDLE
//import com.example.mvvmrecp.component.HeartAnimationDefinition.heartSize
//import com.example.mvvmrecp.component.HeartAnimationDefinition.heartTransitionDefinition
import com.example.mvvmrecp.domain.model.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun heartButton(onToggle:()->Unit) {
    val animationState = remember { mutableStateOf(false) }
    val pulse = rememberInfiniteTransition()
    val pulseMag by pulse.animateFloat(
        initialValue = 40f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        var active: Boolean=false

        if (active == false) {

            loadPicture(drawable = R.drawable.heart_grey).value?.let { image ->

                Image(
                    bitmap = image.asImageBitmap(),
                    contentDescription = "like",
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                active = true
                                animationState.value = !animationState.value
                                Log.d("showme", "i am not red")
                            },
                        )
                        .align(Alignment.CenterVertically)
                        .height(pulseMag.dp)
                        .width(pulseMag.dp)
                )
            }


        } else {
            loadPicture(drawable = R.drawable.heart_red).value?.let { image ->

                Image(
                    bitmap = image.asImageBitmap(),
                    contentDescription = "like",
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                active = false
                                animationState.value = !animationState.value
                                Log.d("showme", "i am grey")
                            },
                        )
                        .align(Alignment.CenterVertically)
                        .height(pulseMag.dp)
                        .width(pulseMag.dp)
                )
            }
        }
    }
}




//@InternalAnimationApi
//@ExperimentalCoroutinesApi
//@Composable
//fun AnimatedHeartButton(
//    modifier: Modifier,
//    buttonState: MutableState<HeartAnimationDefinition.HeartButtonState>,
//    onToggle: () -> Unit,
//) {
//
//    val toState = if (buttonState.value == IDLE) {
//        ACTIVE
//    } else {
//        IDLE
//    }
//
//    val state = updateTransition(
//        definition = heartTransitionDefinition,
//        initState = buttonState.value,
//        toState = toState
//    )
//
//    HeartButton(
//        modifier = modifier,
//        buttonState = buttonState,
//        state = state,
//        onToggle = onToggle
//    )
//}
//
//@InternalAnimationApi
//@ExperimentalCoroutinesApi
//@Composable
//private fun HeartButton(
//    modifier: Modifier,
//    buttonState: MutableState<HeartAnimationDefinition.HeartButtonState>,
//    state:TransitionState,
//    onToggle: () -> Unit,
//) {
//    if (buttonState.value == ACTIVE) {
//        loadPicture(drawable = R.drawable.heart_red).value?.let { image ->
//            Image(
//                bitmap = image.asImageBitmap(),
//                modifier = modifier
//                    .clickable(
//                        onClick = onToggle,
//                    )
//                    .width(50.dp)
//                    .height(50.dp)
//                ,
//                contentDescription = "red heart"
//            )
//        }
//    } else {
//        loadPicture(drawable = R.drawable.heart_grey).value?.let { image ->
//            Image(
//                bitmap = image.asImageBitmap(),
//                modifier = modifier
//                    .clickable(
//                        onClick = onToggle,
//                    )
//                    .width(50.dp)
//                    .height(50.dp),
//                contentDescription = "grey heart"
//            )
//        }
//    }
//}
//object HeartAnimationDefinition{
//
//    enum class HeartButtonState {
//        IDLE, ACTIVE
//    }
//
//    var heartColor = Color.LightGray
//    private val idleIconSize = 50.dp
//    var heartSize = idleIconSize
//    private val expandedIconSize = 80.dp
//
//    val heartTransitionDefinition = HeartAnimationDefinition<HeartButtonState> {
//        state(IDLE) {
//            this.heartColor = Color.LightGray
//            this.heartSize = idleIconSize
//        }
//
//        state(ACTIVE) {
//            this.heartColor = Color.Red
//            this.heartSize = idleIconSize
//        }
//
//        transition(IDLE to ACTIVE) {
//            heartColor using tween(durationMillis = 500)
//
//            heartSize using keyframes {
//                durationMillis = 500
//                expandedIconSize at 100
//                idleIconSize at 200
//            }
//        }
//
//        transition(ACTIVE to IDLE) {
//            heartColor using tween(durationMillis = 500)
//
//             heartSize keyframes {
//                durationMillis = 500
//                expandedIconSize at 100
//                idleIconSize at 200
//            }
//        }
//    }
//}



