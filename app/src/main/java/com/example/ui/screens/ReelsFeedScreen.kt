package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.StoryEntity
import com.example.ui.StoryViewModel
import com.example.ui.components.QuoteShareDialog
import com.example.ui.components.StoryReelItem
import com.example.ui.theme.AmberGold
import com.example.ui.theme.NeonPurple

@Composable
fun ReelsFeedScreen(
    viewModel: StoryViewModel,
    modifier: Modifier = Modifier
) {
    val stories by viewModel.stories.collectAsState()
    val activeIndex by viewModel.activeStoryIndex.collectAsState()
    val selectedLanguage by viewModel.selectedLanguage.collectAsState()
    val bookmarkedIds by viewModel.bookmarkedIds.collectAsState()
    val isPlaying by viewModel.ttsEngine.isPlaying.collectAsState()
    val currentParagraphIndex by viewModel.ttsEngine.currentParagraphIndex.collectAsState()
    val isReadingMode by viewModel.isReadingMode.collectAsState()

    var quoteToShare by remember { mutableStateOf<Pair<StoryEntity, String>?>(null) }

    if (stories.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "Empty",
                    tint = AmberGold,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "No story reels match your filter",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Try clearing the search query or switching language / genre filters above.",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        viewModel.setGenreFilter("All")
                        viewModel.setNarratorFilter(null)
                        viewModel.setSearchQuery("")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Reset Filters")
                }
            }
        }
    } else {
        val pagerState = rememberPagerState(
            initialPage = activeIndex.coerceIn(0, stories.size - 1)
        ) { stories.size }

        // Sync ViewModel active index when user swipes
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                if (page in stories.indices) {
                    viewModel.setActiveStoryIndex(page)
                    viewModel.playStoryNarration(stories[page])
                }
            }
        }

        Box(modifier = modifier.fillMaxSize().testTag("reels_feed_view")) {
            VerticalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val story = stories[page]
                val isBookmarked = bookmarkedIds.contains(story.id)

                StoryReelItem(
                    story = story,
                    selectedLanguage = selectedLanguage,
                    isPlaying = (page == pagerState.currentPage && isPlaying),
                    currentParagraphIndex = if (page == pagerState.currentPage) currentParagraphIndex else 0,
                    isBookmarked = isBookmarked,
                    isReadingMode = isReadingMode,
                    onPlayPauseToggle = {
                        if (page == pagerState.currentPage) {
                            viewModel.ttsEngine.togglePlayPause()
                        } else {
                            viewModel.setActiveStoryIndex(page)
                            viewModel.playStoryNarration(story)
                        }
                    },
                    onSeekParagraph = { index ->
                        if (page == pagerState.currentPage) {
                            viewModel.ttsEngine.seekToParagraph(index)
                        }
                    },
                    onBookmarkToggle = { viewModel.toggleBookmark(story.id) },
                    onReadingModeToggle = { viewModel.toggleReadingMode() },
                    onShareQuote = { quote -> quoteToShare = Pair(story, quote) }
                )
            }

            // Quote Dialog
            quoteToShare?.let { (story, quote) ->
                QuoteShareDialog(
                    storyTitle = story.title,
                    quoteText = quote,
                    narratorName = story.narratorName,
                    onDismiss = { quoteToShare = null }
                )
            }
        }
    }
}
