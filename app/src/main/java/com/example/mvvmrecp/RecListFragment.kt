package com.example.mvvmrecp

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AndroidViewConfiguration
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.example.mvvmrecp.RecipeListEvents.newSearchEvent
import com.example.mvvmrecp.RecipeListEvents.nextPageEvent
import com.example.mvvmrecp.component.*
import com.example.mvvmrecp.di.RepositoryModule
import com.example.mvvmrecp.domain.model.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

//import androidx.ui.foundation.Text
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint

class RecListFragment : Fragment() {
    //@Inject
//     lateinit var application: BaseApplication

    //@Inject
    private val snackbarController = SnackbarController(lifecycleScope)
    private val viewmodel: RecipeListViewModel by viewModels()


    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //println("fragment${viewmodel.getrandomstring()}")
//        println("fragment${viewmodel.getrepo()}")
//        println("fragment${viewmodel.gettok()}")
//
//    }
    // @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflater.inflate(R.layout.recfrag_list,container,false)
//        return ComposeView(requireContext()).apply {
//            setContent {
//                Text(text = "pleasssssse")
//            }
//        }
        //******this was the important part
//        val view=inflater.inflate(R.layout.recfrag_list,container,false)
//        view.findViewById<ComposeView>(R.id.compose_view).setContent {
//            Column(modifier = Modifier
//                .border(border = BorderStroke(1.dp, Color.Cyan))
//                .padding(16.dp)) {
//                 Text(text ="this is a compose view" )
//                Spacer(modifier = Modifier.padding(10.dp))
//                CircularProgressIndicator()
//                Spacer(modifier = Modifier.padding(10.dp))
//                Text(text = "good")
//                Spacer(modifier = Modifier.padding(10.dp))
//                val customView=HorizontalDottedProgress(LocalContext.current)
//                AndroidView(factory = {customView})
//
//            }
//        }
//
//     *****   return view
        //*************
//        return ComposeView(requireContext()).apply {
//            setContent {
//                val recipes=viewmodel.recipes.value
//                for(recipe in recipes){
//                    Log.d(TAG,"here${recipe.title}")
//                }
//
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text(
//                        text = "recipe list"
//                    )
//                    Spacer(modifier = Modifier.padding(10.dp))
//                    Button(onClick = { findNavController().navigate(R.id.action_recFragment2_to_rec1Fragment2) }) {
//                        Text(text = "to recipe fragment")
//                    }
//                }
//            }
//        }
        return ComposeView(requireContext()).apply {
            setContent {
                Apptheme(
                    darktheme = active.value
                    //application.isdark.value
                ) {
                    val recipes = viewmodel.recipes.value
                    val query = viewmodel.query.value
                    val selectedcategory = viewmodel.selectedCategory.value
                    val loading = viewmodel.loading.value
                    val page=viewmodel.page.value
                    val scaffoldState= rememberScaffoldState()
                    Scaffold(topBar = {
                        searchAppBar(
                            query = query,
                            onQueryChanged = viewmodel::onQueryChanged,
                            onExecuteSearch = {
                                if (viewmodel.selectedCategory.value?.value=="Milk"){
                                    lifecycleScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = "invalid category",
                                            actionLabel = "hide",
                                        )
                                    }
                                }else viewmodel.onTriggerEvent(newSearchEvent)
                            },
                            scrollPosition = viewmodel.categoryScrollPosition,
                            selectedcategory = selectedcategory,
                            onSelectedCategoryChanged = viewmodel::onSelectedCategoryChanged,
                            onChangecategoryScroll = viewmodel::onChangecategoryScroll,
                            toggleTheme = {
                                makeit()
//                                Log.d("here is",makeit().toString())
                                //    application.toggleLightTheme()
                            }
                        )
                    },
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        },
//                    bottomBar = { myBottomBar()},
//                    drawerContent = { myDrawer()}
                    ) {
                        RecipeList(
                            loading = loading,
                            recipes = recipes,
                            onChangeListPosition = { viewmodel::onChangeListPosition },
                            page = page,
                            onNextpage = {viewmodel.onTriggerEvent(nextPageEvent)},
                            navController = findNavController(),
                            scaffoldState = scaffoldState,
                            snackbarController = snackbarController,
                        )


                    }

//                    val state = remember { mutableStateOf(false) }
//heartButton(onToggle = {state.value = if(state.value == false) true else false},)

                }
            }

        }
    }


}

//@Composable
//fun myBottomBar(){
//    BottomNavigation(elevation = 12.dp) {
//        BottomNavigationItem(
//            selected = false,
//            onClick = { },
//            icon = {Icon(Icons.Default.Mail,"j")})
//        BottomNavigationItem(
//            selected = false,
//            onClick = { },
//            icon = {Icon(Icons.Default.Motorcycle,"j")})
//    }
//}
//
//@Composable
//fun myDrawer(){
//    Column {
//        Text(text ="first" )
//        Text(text ="second" )
//        Text(text ="third" )
//        Text(text ="fourth" )
//    }
//}