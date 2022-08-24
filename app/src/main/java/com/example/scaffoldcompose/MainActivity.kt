package com.example.scaffoldcompose

import android.app.assist.AssistContent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scaffoldcompose.ui.theme.ScaffoldComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MainContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    //content yani bodysi bir composable fonksiyon
    Scaffold(
        //bodysini yani contentini belirtmek gerekiyor
        topBar = {
            TopAppBar(
                backgroundColor = Color.Magenta,
                elevation = 5.dp
            ) {
                Text(text = "Movies")
            }
        }
    ) {
        //bodysi içine verdiği composable fonksion oldugunu belirttik
        content()
    }

}

@Composable
fun MainContent(movieList: List<String> = listOf(
    "Avatar","300","Harry Potter","Life"
)) {
   Column(
       modifier= Modifier
           .padding(12.dp)
           .background(color = Color.Yellow)

   ) {
       LazyColumn(
        
       ){

           items(items=movieList){
               MovieCard(movie =it,){
                   movie->
                   Log.d("TAG","Maincontent:$movie")
               }
           }
       }

   }

}

@Composable
fun MovieCard(movie:String,onItemClick:(String)->Unit={}){

    //her bir iteme tıklama özelliği için clickable diyicez
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                       onItemClick(movie)
            },
        shape = RoundedCornerShape(corner= CornerSize(15.dp),
        ),
        elevation =6.dp

    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement =Arrangement.Start) {
            Surface(
             modifier = Modifier
                 .padding(12.dp)
                 .size(100.dp),
                shape = RectangleShape,
                elevation =4.dp
            ) {
                Icon(imageVector = Icons.Default.AccountBox,
                    contentDescription ="movie image" )

            }
            Text(text =movie)
        }


    }
}

