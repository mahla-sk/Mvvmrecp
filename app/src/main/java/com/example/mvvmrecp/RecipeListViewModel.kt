package com.example.mvvmrecp

import android.net.http.SslCertificate.restoreState
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecp.RecipeListEvents.*
import com.example.mvvmrecp.domain.model.Recipe
import com.example.mvvmrecp.repository.RecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Named
const val PAGE_SIZE=30
const val STATE_KEY_PAGE = "recipe.state.page.key"
const val STATE_KEY_QUERY = "recipe.state.query.key"
const val STATE_KEY_LIST_POSITION = "recipe.state.query.list_position"
const val STATE_KEY_SELECTED_CATEGORY = "recipe.state.query.selected_category"
class RecipeListViewModel
@ViewModelInject
constructor(
    private val repository: RecipeRepository,
    private @Named("tok") val token: String,
    @Assisted private val savedStateHandle:SavedStateHandle,
) : ViewModel() {
    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition:Int=0
    val loading= mutableStateOf(false)
    val page= mutableStateOf(1)
    private var recipelistscrollposition = 0
    init {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { q ->
            setQuery(q)
        }
        savedStateHandle.get<FoodCategory>(STATE_KEY_SELECTED_CATEGORY)?.let { f ->
            setSelectedCategory(f)
        }
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }
        if (recipelistscrollposition != 0) {
            onTriggerEvent(restoreStateEvent)
        } else {
            onTriggerEvent(newSearchEvent)
        }
    }
    fun onTriggerEvent(event:RecipeListEvents){
        viewModelScope.launch {
            try {
                when(event){
                    is newSearchEvent->{
                        newsearch()
                    }
                    is nextPageEvent->{
                        nextPage()
                    }
                    is restoreStateEvent->{
                        restoreEvent()
                    }

                }
            }catch (e:Exception){
                Log.e("excep","${e},${e.cause}")
                e.printStackTrace()
            }
            finally {
                Log.d("finally", "launchJob: finally called.")
            }
        }
    }

    //    init {
//       // println("viewmodel:${randomString}")
//        println("viewmodel:${repository}")
//        println("viewmodel:${token}")
//    }
//   // fun getrandomstring()=randomString
//    fun getrepo()=repository
//    fun gettok()=token
    fun onQueryChanged(query: String) {
        setQuery(query)
    }

    suspend fun newsearch() {

            loading.value=true
            resetSearchstate()
            delay(1000)
            val result = repository.search(token = token, page = 1, query = query.value)
            recipes.value = result
            loading.value=false

    }
    private fun incrementPage(){
//        page.value=page.value+1
        setPage(page.value+1)
    }
    fun onChangeListPosition(position:Int){
        setListScrollPosition(position = position)
    }
    private fun appendRecipes(recipes:List<Recipe>){
        val current=ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value=current
    }
suspend fun nextPage(){

        if ((recipelistscrollposition+1)>=(page.value * PAGE_SIZE) ){
            loading.value=true
            incrementPage()
            delay(1000)
            if (page.value>1){
                val result=repository.search(
                    token = token,
                    page=page.value,
                    query = query.value
                )
                appendRecipes(result)
            }
            loading.value=false
        }

}
    fun onSelectedCategoryChanged(category: String) {
        val newcategory = getFoodCategory(category)
        setSelectedCategory(newcategory)
        onQueryChanged(category)
    }

    fun onChangecategoryScroll(position:Int){
        categoryScrollPosition =position
    }
    private fun resetSearchstate(){
        recipes.value= listOf()
        page.value=1
        onChangeListPosition(0)
        if (selectedCategory.value?.value != query.value) clearSelectedCategory()
    }

    private fun clearSelectedCategory(){
//        selectedCategory.value=null
       setSelectedCategory(null)
    }
    private fun setListScrollPosition(position:Int){
        recipelistscrollposition=position
        savedStateHandle.set(STATE_KEY_LIST_POSITION,position)
    }
    private fun setPage(page:Int){
        this.page.value=page
        savedStateHandle.set(STATE_KEY_PAGE,page)
    }
    private fun setSelectedCategory(category: FoodCategory?){
        selectedCategory.value=category
        savedStateHandle.set(STATE_KEY_SELECTED_CATEGORY,category)
    }
    private fun setQuery(query:String){
        this.query.value=query
        savedStateHandle.set(STATE_KEY_QUERY,query)
    }
    private suspend fun restoreEvent(){
        loading.value=true
        val results:MutableList<Recipe> = mutableListOf()
        for (p in 1..page.value){
            val result=repository.search(
                token = token,page = p,query = query.value
            )
            results.addAll(result)
            if (page.value==p){
                recipes.value=results
                loading.value=false
            }
        }
    }
}