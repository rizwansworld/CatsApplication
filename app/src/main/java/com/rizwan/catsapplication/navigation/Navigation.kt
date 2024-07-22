package com.rizwan.catsapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rizwan.catsapplication.screens.CatDetailScreen
import com.rizwan.catsapplication.screens.CatListScreen

@Composable
fun MainNavigation() {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = CAT_LIST_SCREEN) {
        composable(CAT_LIST_SCREEN) {
            CatListScreen(navHostController)
        }
        composable(CAT_DETAIL_SCREEN) {
            CatDetailScreen(navHostController)
        }
    }

}

const val CAT_LIST_SCREEN = "Cat List screen"
const val CAT_DETAIL_SCREEN = "Cat Detail screen"