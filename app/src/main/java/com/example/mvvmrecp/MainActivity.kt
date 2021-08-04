package com.example.mvvmrecp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    @Inject
//    lateinit var random:String
//@Inject
//lateinit var appl:BaseApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager.beginTransaction().replace(R.id.main_container,RecFragment())
//            .commit()
        //*****
//        val mapper=RecipeNetworkMapper()
//        val recipe=Recipe()
//        val networkentity:RecipeNetworkEntity=mapper.mapToEntity(recipe)
//        val r:Recipe=mapper.mapFromEntity(networkentity)
        //******
//        val service=Retrofit.Builder()
//            .baseUrl("https://food2fork.ca/api/recipe/")
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//            .build()
//            .create(ReciperetrofitService::class.java)
//        CoroutineScope(IO).launch {
//            val recipe=service.get(
//                token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
//                id = 583
//            )
//            Log.d("tag","found it:${recipe.title}")
//        }

//        Log.d("look",random)
//        Log.d("look again", appl.toString())
    }
}