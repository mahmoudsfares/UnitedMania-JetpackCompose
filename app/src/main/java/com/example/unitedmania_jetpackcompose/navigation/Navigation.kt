package com.example.unitedmania_jetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.unitedmania_jetpackcompose.data.Article
import com.example.unitedmania_jetpackcompose.presentation.details.DetailsScreen
import com.example.unitedmania_jetpackcompose.presentation.news.NewsScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// TODO 4: navigation - define mere route names, make this private to force the clients to pass the arguments
private object RouteNames {
    const val NEWS = "news"
    const val DETAILS = "details"
}

sealed class Navigation {
    // TODO 5: navigation - define route objects, add the method getRoute to customize passed arguments
    data object NewsScreen: Navigation() {
        fun getRoute(): String {
            return RouteNames.NEWS
        }
    }
    data object DetailsScreen: Navigation() {
        fun getRouteWithNonNullableArguments(article: Article): String {
            // TODO 22: serialization: convert Article to string
            val serializedData = Json.encodeToString(article)
            // TODO 23: serialization: ensure the JSON string is URL-encoded to avoid issues with unsupported characters in the navigation route.
            val encodedData = java.net.URLEncoder.encode(serializedData, "UTF-8")
            return RouteNames.DETAILS + "/$encodedData"
        }
    }
}

// TODO 6: navigation - create a NavGraph composable.. this defines each route in the app and the required arguments for each destination
@Composable
fun NavGraph() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Navigation.NewsScreen.getRoute()) {
        // news screen: initial, no arguments
        composable(route = RouteNames.NEWS) {
            NewsScreen(navHostController = navHostController)
        }
        // details screen: has arguments
        composable(
            route = RouteNames.DETAILS + "/{article}",
            arguments = listOf(
                navArgument(name = "article") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            // TODO 24: serialization - convert the encoded string back to the custom Article class before providing it to the destination screen
            backStackEntry ->
            val dataJson = backStackEntry.arguments?.getString("article").let {
                java.net.URLDecoder.decode(it, "UTF-8")
            }
            val article = dataJson?.let { Json.decodeFromString<Article>(it) }
            if (article != null) {
                DetailsScreen(navHostController = navHostController, article = article)
            }
        }
    }
}