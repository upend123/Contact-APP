package com.example.contactapp.data.presantation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactapp.data.database.ContactViewModel
import com.example.contactapp.data.presantation.screens.AddEditScreenUI
import com.example.contactapp.data.presantation.screens.MainScreenUI
import com.example.contactapp.data.presantation.screens.splash.SplashScreenUI
import kotlinx.coroutines.launch


@Composable
fun AppNavigation(viewModel: ContactViewModel = hiltViewModel()) {

    var naviController = rememberNavController()
    var coroutineScope = rememberCoroutineScope()

    NavHost(naviController, startDestination = Routes.SplashScreen){

        composable<Routes.SplashScreen> {

            SplashScreenUI(){
            naviController.popBackStack()
                naviController.navigate(Routes.MainScreen)
            }
        }

        composable<Routes.MainScreen> {
           MainScreenUI(viewModel){
               naviController.navigate(Routes.AddEditScreen)
           }
        }
        composable<Routes.AddEditScreen> {
          AddEditScreenUI(viewModel, onClick = {
             coroutineScope.launch {
                 viewModel.upsertContact()
             }

              naviController.navigateUp()
          } ,
         onBackIconClick = {
             naviController.navigateUp()
          })


        }
    }

}