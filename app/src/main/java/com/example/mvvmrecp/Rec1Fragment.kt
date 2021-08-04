package com.example.mvvmrecp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.example.mvvmrecp.RecipeEvents.GetRecipeEvent
import com.example.mvvmrecp.component.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class Rec1Fragment : Fragment() {
//private var recipeId:MutableState<Int> = mutableStateOf(-1)
private val viewModel : RecipeViewModel by viewModels()
//val args: Rec1FragmentArgs by navArgs()

  //  private var recipeId: MutableState<Int> = mutableStateOf(-1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

          arguments?.getInt("recipeId")?.let { recipeId->
              viewModel.onTriggerEvent(GetRecipeEvent(recipeId))
          }


//        CoroutineScope(Main).launch {
//            delay(2000)
//            recipeId.value = args.recipeId
//        }
    }
    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       return ComposeView(requireContext()).apply {
           setContent {
               val loading=viewModel.loading.value
               val recipe=viewModel.recipe.value
               val scaffoldState= rememberScaffoldState()
               Scaffold(
                   scaffoldState = scaffoldState,
                   snackbarHost = {
                       scaffoldState.snackbarHostState
                   }
               ) {
                   Box (
                       modifier = Modifier.fillMaxSize()
                   ){
                       if (loading && recipe == null){
                           LoadingShimmer(imageHeight = 260.dp)
                       }
                       else recipe?.let {
                               RecipeView(
                                   recipe = it,
                               )
                       }
                       CircularProgressBar(isDisplayed = loading)
                       DefaultSnackbar(
                           snackbarHostState = scaffoldState.snackbarHostState,
                           onDismiss = {
                               scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                           },
                           modifier = Modifier.align(Alignment.BottomCenter)
                       )
                   }
               }

           }
       }
    }


    }
