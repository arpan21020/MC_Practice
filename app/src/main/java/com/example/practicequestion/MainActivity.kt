package com.example.practicequestion

import CompaniesViewModel
import MyData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicequestion.ui.theme.PracticeQuestionTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var userViewModel: CompaniesViewModel= CompaniesViewModel(this)

        setContent {
            PracticeQuestionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomePage(userViewModel)
                }
            }
        }
    }
}


@Composable
fun HomePage(userViewModel: CompaniesViewModel){
    val navController= rememberNavController()
    NavHost(navController = navController , startDestination ="main" ){
        composable("main"){
            Home(userViewModel,navController)
        }

    }
}

@Composable
fun Home(userViewModel: CompaniesViewModel,navController: NavHostController?){
    Column() {
        Button(onClick = { /*TODO*/ }) {
            Text(text="View Companies")
        }
        Button(onClick = {
            userViewModel.addCompany(
                MyData("Microsoft","SDE","01-03-2023")
            )
            println("Company added")
        }) {
            Text(text="Add Company")
        }

    }

}

@Composable
fun ViewCompanies(){
    Column() {
        CompanyCard("Microsoft","SDE","12-10-2024",{})
        CompanyCard("Microsoft","SDE","12-10-2024",{})
        CompanyCard("Microsoft","SDE","12-10-2024",{})
        CompanyCard("Microsoft","SDE","12-10-2024",{})

    }
}

@Composable
fun CompanyCard(name:String,job:String, deadline:String,clickAction:()->Unit){
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable(onClick = { clickAction.invoke() })
        ,
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,


            ) {
            Text(text = name)
            Text("Role: $job")
            Text(text = deadline)

        }
        
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PracticeQuestionTheme {
        ViewCompanies()
    }
}
