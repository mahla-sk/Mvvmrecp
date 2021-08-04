package com.example.mvvmrecp.component

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
//import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mvvmrecp.FoodCategory
import com.example.mvvmrecp.getAllfoodCategories
import kotlinx.coroutines.launch

var isactive=false
val active= mutableStateOf(false)
fun makeit() {
    active.value= !active.value
    Log.d("mylog",active.toString())

}

@Composable
fun searchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    scrollPosition: Int,
    selectedcategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onChangecategoryScroll: (Int) -> Unit,
     toggleTheme:()->Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                //val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    value = query,
                    onValueChange = {
                        onQueryChanged(it)
                    },

                    label = { Text(text = "search") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search,
                    ),
                    leadingIcon = { Icon(Icons.Filled.Search, "search") },
                    keyboardActions = KeyboardActions(onSearch = {

                        onExecuteSearch()
                        // keyboardController?.hideSoftwareKeyboard()
                    }),
                    textStyle =MaterialTheme.typography.button,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface)
                )
                Row(modifier = Modifier.align(Alignment.CenterVertically)) {
                    IconButton(
                        onClick = { toggleTheme() },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(Icons.Filled.MoreVert,"good")
                    }
                }

//                ConstraintLayout(modifier = Modifier.align(Alignment.CenterVertically)) {
//                    val (menu) = createRefs()
//                    IconButton(onClick = {toggletheme()},
//                        modifier = Modifier.constrainAs(menu){
//                                    end.linkTo(parent.end)
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                    }
//                        ) {
//                        Icon(Icons.Filled.MoreVert,contentDescription = "toggle")
//                    }
//                }
            }
            // Spacer(modifier = Modifier.padding(15.dp))
            val scrollState = rememberScrollState()
            val coroutinescope = rememberCoroutineScope()
            Row(
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .horizontalScroll(scrollState),
            ) {
                coroutinescope.launch {
                    scrollState.scrollTo(scrollPosition)
                }
                for (category in getAllfoodCategories()) {
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = selectedcategory == category,
                        onSelectedCategoryChanged = {
                            onChangecategoryScroll(scrollState.value)
                            onSelectedCategoryChanged(it)

                        },
                        onExecuteSearch = { onExecuteSearch() },
                    )
                }
            }
        }
    }
}

