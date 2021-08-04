package com.example.mvvmrecp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.mvvmrecp.R
import com.example.mvvmrecp.domain.model.Recipe
import com.example.mvvmrecp.domain.model.util.loadPicture

@Composable
fun RecipeView(recipe:Recipe,){
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {
        recipe.featuredImage?.let {
            url-> val image= loadPicture(url = url, defaultImage = R.drawable.empty_plate ).value
            image?.let { img->
                Image(bitmap = img.asImageBitmap(), contentDescription ="good",
                modifier = Modifier
                    .height(260.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop)

            }
            Column(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {
             recipe.title?.let {title->
                 Row(modifier= Modifier
                     .padding(bottom = 4.dp)
                     .fillMaxWidth()) {
                     Text(text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                     style = MaterialTheme.typography.h3
                     )
                     val rank=recipe.rating.toString()
                     Text(text = rank,
                         modifier = Modifier
                             .fillMaxWidth()
                             .wrapContentWidth(Alignment.End)
                             .align(Alignment.CenterVertically),
                         style = MaterialTheme.typography.h5
                     )
                 }
             }
                recipe.publisher?.let { publisher->
                    val updated=recipe.dataUpdated
                    Text(text = if (updated != null)
                        "Updated $updated by $publisher"
                    else "by $publisher",
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    style = MaterialTheme.typography.caption)
                }
                recipe.description?.let { desc->
                    Text(
                        text = desc,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                        ,
                        style = MaterialTheme.typography.body1
                    )
                }
                for (ingredient in recipe.ingredients){
                    Text(
                        text = ingredient,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                        ,
                        style = MaterialTheme.typography.body1
                    )
                }
                recipe.cookingInstructions?.let { instructions ->
                    Text(
                        text = instructions,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                        ,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}