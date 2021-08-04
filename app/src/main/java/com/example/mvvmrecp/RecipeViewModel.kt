package com.example.mvvmrecp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecp.RecipeEvents.GetRecipeEvent
import com.example.mvvmrecp.domain.model.Recipe
import com.example.mvvmrecp.repository.RecipeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
const val STATE_KEY_RECIPE = "recipe.state.recipe.key"
@ExperimentalCoroutinesApi
class RecipeViewModel
@ViewModelInject
constructor(
    private val recipeRepository:RecipeRepository,
    @Named("tok") private val token:String,
    @Assisted private val state:SavedStateHandle,
):ViewModel(){
    val recipe:MutableState<Recipe?> = mutableStateOf(null)
    val loading= mutableStateOf(false)
    init {
        state.get<Int>(STATE_KEY_RECIPE)?.let {
            recipeId-> onTriggerEvent(GetRecipeEvent(recipeId))
        }
    }
    fun onTriggerEvent(event:RecipeEvents){
        viewModelScope.launch {
            when(event){
                is GetRecipeEvent->{
                    if (recipe.value==null){
                        getRecipe(event.id)
                    }
                }
            }
        }
    }
    private suspend fun getRecipe(id:Int){
         loading.value=true
        delay(1000)
        val recipe=recipeRepository.get(token = token,id = id)
        this.recipe.value=recipe
        state.set(STATE_KEY_RECIPE,recipe.id)
        loading.value=false
    }
}