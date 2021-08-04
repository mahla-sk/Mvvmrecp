package com.example.mvvmrecp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvmrecp.component.*
import com.example.mvvmrecp.domain.model.Recipe
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeListPosition: (Int) -> Unit,
    page: Int,
    onNextpage: (RecipeListEvents) -> Unit,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        if (loading && recipes.isEmpty()) {
            ShimmerRecipeCardItem(
                imageHeight = 250.dp,
                padding = 8.dp
            )

        } else {
            LazyColumn {
                itemsIndexed(items = recipes) { index, recipe ->
                    onChangeListPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE)) {
                        onNextpage(RecipeListEvents.nextPageEvent)
                    }
                    RecipeCard(recipe = recipe,
                        onClick = {
                            if (recipe.id != null) {
                                val bundle = Bundle()
                                bundle.putInt("recipeId", recipe.id)
                                navController.navigate(
                                    R.id.action_recListFragment_to_rec1Fragment2,
                                    bundle
                                )

                            } else {
                                snackbarController.getScope().launch {
                                    snackbarController.showSnackbar(
                                        scaffoldState = scaffoldState,
                                        message = "error",
                                        actionLabel = "ok"
                                    )
                                }

                            }
                        }

                    ,navController = navController)
                }

            }
        }
        CircularProgressBar(isDisplayed = loading)
        DefaultSnackbar(snackbarHostState = scaffoldState.snackbarHostState,
            onDismiss = {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            }, modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}