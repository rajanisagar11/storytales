package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.MainTab
import com.example.ui.StoryViewModel
import com.example.ui.components.BottomNavBar
import com.example.ui.components.TopHeaderBar
import com.example.ui.screens.BookmarksScreen
import com.example.ui.screens.NarratorsScreen
import com.example.ui.screens.ReelsFeedScreen
import com.example.ui.screens.StoryCreatorScreen
import com.example.ui.theme.ReelTalesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReelTalesTheme {
                MainAppScreen()
            }
        }
    }
}

@Composable
fun MainAppScreen(
    viewModel: StoryViewModel = viewModel()
) {
    val currentTab by viewModel.currentTab.collectAsState()
    val selectedLanguage by viewModel.selectedLanguage.collectAsState()
    val selectedGenre by viewModel.selectedGenre.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                currentTab = currentTab,
                onTabSelected = { tab -> viewModel.setTab(tab) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Main Active Tab Content
            when (currentTab) {
                MainTab.REELS -> ReelsFeedScreen(viewModel = viewModel)
                MainTab.NARRATORS -> NarratorsScreen(viewModel = viewModel)
                MainTab.BOOKMARKS -> BookmarksScreen(viewModel = viewModel)
                MainTab.CREATOR -> StoryCreatorScreen(viewModel = viewModel)
            }

            // Floating Top Header Bar (Language, Search, Genre Filters)
            TopHeaderBar(
                selectedLanguage = selectedLanguage,
                selectedGenre = selectedGenre,
                searchQuery = searchQuery,
                onLanguageSelected = { lang -> viewModel.setLanguage(lang) },
                onGenreSelected = { genre -> viewModel.setGenreFilter(genre) },
                onSearchQueryChanged = { query -> viewModel.setSearchQuery(query) },
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}
