package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.MainTab
import com.example.ui.StoryViewModel
import com.example.ui.components.NarratorCard
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.NeonPurple

@Composable
fun NarratorsScreen(
    viewModel: StoryViewModel,
    modifier: Modifier = Modifier
) {
    val narrators = viewModel.narrators
    val selectedNarratorId by viewModel.selectedNarratorId.collectAsState()
    val isPlaying by viewModel.ttsEngine.isPlaying.collectAsState()
    var activePlayingNarratorId by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 70.dp) // Below header overlay
            .testTag("narrators_screen")
    ) {
        // Banner
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "Artists",
                        tint = AmberGold,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Voice Artists & Narrators",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                if (selectedNarratorId != null) {
                    Button(
                        onClick = { viewModel.setNarratorFilter(null) },
                        colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Icon(Icons.Default.FilterList, contentDescription = "Clear", tint = Color.White, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Show All", fontSize = 11.sp)
                    }
                }
            }

            Text(
                text = "Listen to preview voice samples and filter story reels by your favorite narrator.",
                fontSize = 12.sp,
                color = ElectricViolet,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(narrators) { narrator ->
                val isCurrentSamplePlaying = (activePlayingNarratorId == narrator.id && isPlaying)

                NarratorCard(
                    narrator = narrator,
                    isPlayingSample = isCurrentSamplePlaying,
                    onToggleSample = {
                        if (isCurrentSamplePlaying) {
                            viewModel.ttsEngine.stop()
                            activePlayingNarratorId = null
                        } else {
                            activePlayingNarratorId = narrator.id
                            viewModel.ttsEngine.startNarration(
                                storyId = "sample_${narrator.id}",
                                paragraphs = listOf(narrator.sampleQuote),
                                langCode = viewModel.selectedLanguage.value,
                                pitch = narrator.defaultPitch,
                                speed = narrator.defaultSpeed
                            )
                        }
                    },
                    onViewStories = {
                        viewModel.setNarratorFilter(narrator.id)
                        viewModel.setTab(MainTab.REELS)
                    }
                )
            }
        }
    }
}
