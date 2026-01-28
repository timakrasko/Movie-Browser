package com.example.moviebrowser.naigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.moviebrowser.presentation.screen.FavoritesScreen
import com.example.moviebrowser.presentation.screen.MovieDetailsScreen
import com.example.moviebrowser.presentation.screen.MovieListScreen


@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val navigationState = rememberNavigationState(
        startRoute = Route.MovieList,
        topLevelRoutes = TOP_LEVEL_DESTINATION.keys
    )

    val navigator = remember {
        Navigator(navigationState)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            MovieNavigationBar(
                selectedKey = navigationState.topLevelRoute,
                onSelectKey = {
                    navigator.navigate(it)
                }
            )
        }
    ) { innerPadding ->
        NavDisplay (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            onBack = navigator::goBack,
            entries = navigationState.toEntries(
                entryProvider {
                    entry<Route.MovieList> {
                        MovieListScreen(
                            onMovieClick = {
                                navigator.navigate(Route.MovieDetails(it.id ))
                            }
                        )
                    }

                    entry<Route.MovieDetails> {
                        MovieDetailsScreen(
                            it.id
                        )
                    }

                    entry<Route.Favorite> {
                        FavoritesScreen(
                            onMovieClick = {
                                navigator.navigate(Route.MovieDetails(it.id ))
                            }
                        )
                    }
                }
            )
        )
    }
}