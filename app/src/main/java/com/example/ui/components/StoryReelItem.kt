package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.StoryEntity
import com.example.ui.theme.AmberGold
import com.example.ui.theme.CoralRose
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.NeonPurple

@Composable
fun StoryReelItem(
    story: StoryEntity,
    selectedLanguage: String,
    isPlaying: Boolean,
    currentParagraphIndex: Int,
    isBookmarked: Boolean,
    isReadingMode: Boolean,
    onPlayPauseToggle: () -> Unit,
    onSeekParagraph: (Int) -> Unit,
    onBookmarkToggle: () -> Unit,
    onReadingModeToggle: () -> Unit,
    onShareQuote: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isLiked by remember { mutableStateOf(false) }
    var likesCount by remember { mutableStateOf(story.likesCount) }
    var currentSpeed by remember { mutableStateOf(1.0f) }

    // Content based on selected language
    val storyText = when (selectedLanguage) {
        "hi" -> story.contentHi
        "es" -> story.contentEs
        "fr" -> story.contentFr
        "de" -> story.contentDe
        "ja" -> story.contentJa
        else -> story.contentEn
    }

    val paragraphs = remember(storyText) {
        storyText.split("\n\n").map { it.trim() }.filter { it.isNotEmpty() }
    }

    // Cover image resource lookup
    val coverResId = when (story.coverImageRes) {
        "img_story_sci_fi" -> R.drawable.img_story_sci_fi
        "img_story_fantasy" -> R.drawable.img_story_fantasy
        else -> R.drawable.img_app_icon
    }

    // Infinite breathing pulse for audio playing ripple
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .testTag("story_reel_item_${story.id}")
    ) {
        // 1. Fullscreen Background Artwork Image
        Image(
            painter = painterResource(id = coverResId),
            contentDescription = story.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient Darkening Overlay for Text Legibility
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.4f),
                            Color.Black.copy(alpha = 0.85f),
                            Color.Black.copy(alpha = 0.95f)
                        )
                    )
                )
        )

        // Main Story Container
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            // Left & Middle Content Column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Header Info
                Column {
                    Spacer(modifier = Modifier.height(60.dp)) // Space for header overlay

                    // Category Genre & Read Time Pill
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = NeonPurple.copy(alpha = 0.8f)
                        ) {
                            Text(
                                text = story.genre,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${story.readTimeMinutes} min listen",
                            fontSize = 11.sp,
                            color = AmberGold,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Title
                    Text(
                        text = story.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 30.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Narrator Info
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Headphones,
                            contentDescription = "Narrator",
                            tint = ElectricViolet,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Voice: ${story.narratorName}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ElectricViolet
                        )
                    }
                }

                // Center Story Text Area
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 12.dp)
                ) {
                    if (isReadingMode) {
                        // Full Reader Mode
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Author: ${story.author}",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                fontStyle = FontStyle.Italic
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            paragraphs.forEachIndexed { idx, para ->
                                val isCurrent = (idx == currentParagraphIndex)
                                Text(
                                    text = para,
                                    fontSize = 15.sp,
                                    lineHeight = 22.sp,
                                    color = if (isCurrent) AmberGold else Color.White,
                                    fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { onSeekParagraph(idx) }
                                        .padding(vertical = 6.dp)
                                )
                            }
                        }
                    } else {
                        // Listening Reel Mode (Highlighted Current Paragraph)
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            // Waveform indicator when playing
                            if (isPlaying) {
                                AnimatedWaveform()
                                Spacer(modifier = Modifier.height(12.dp))
                            }

                            val activeText = if (currentParagraphIndex in paragraphs.indices) {
                                paragraphs[currentParagraphIndex]
                            } else {
                                paragraphs.firstOrNull() ?: ""
                            }

                            Text(
                                text = activeText,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = AmberGold,
                                lineHeight = 26.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.Black.copy(alpha = 0.65f))
                                    .border(1.dp, NeonPurple.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                                    .padding(18.dp)
                            )
                        }
                    }
                }

                // Bottom Audio Controls Bar
                Column {
                    // Paragraph Scrubber Slider
                    if (paragraphs.isNotEmpty()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "${currentParagraphIndex + 1}/${paragraphs.size}",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                            Slider(
                                value = currentParagraphIndex.toFloat(),
                                onValueChange = { onSeekParagraph(it.toInt()) },
                                valueRange = 0f..(paragraphs.size - 1).coerceAtLeast(1).toFloat(),
                                steps = (paragraphs.size - 2).coerceAtLeast(0),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp),
                                colors = SliderDefaults.colors(
                                    thumbColor = NeonPurple,
                                    activeTrackColor = ElectricViolet,
                                    inactiveTrackColor = Color.White.copy(alpha = 0.2f)
                                )
                            )
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = "Audio",
                                tint = AmberGold,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    // Main Floating Play / Pause Button Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Play/Pause Big Button
                        Box(
                            modifier = Modifier
                                .testTag("play_pause_button")
                                .scale(if (isPlaying) pulseScale else 1.0f)
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(
                                        if (isPlaying) listOf(CoralRose, NeonPurple)
                                        else listOf(NeonPurple, ElectricViolet)
                                    )
                                )
                                .clickable { onPlayPauseToggle() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = "Play/Pause",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        // Mode indicator
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Color.White.copy(alpha = 0.15f),
                            modifier = Modifier.clickable { onReadingModeToggle() }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = if (isReadingMode) Icons.Default.Headphones else Icons.Default.MenuBook,
                                    contentDescription = "Mode",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isReadingMode) "Switch to Audio Reel" else "Reader View",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "↑ Swipe up for next story reel",
                        fontSize = 11.sp,
                        color = Color.White.copy(alpha = 0.5f),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right Vertical Reel Actions Sidebar (Instagram / Shorts Style)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 60.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Like Button
                IconButton(
                    onClick = {
                        isLiked = !isLiked
                        likesCount += if (isLiked) 1 else -1
                    },
                    modifier = Modifier.testTag("like_button")
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = if (isLiked) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Like",
                            tint = if (isLiked) CoralRose else Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = "${likesCount / 1000}k",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Bookmark Button
                IconButton(
                    onClick = onBookmarkToggle,
                    modifier = Modifier.testTag("bookmark_button")
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) AmberGold else Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = if (isBookmarked) "Saved" else "Save",
                            fontSize = 10.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Share Quote Button
                IconButton(
                    onClick = {
                        val activeQuote = if (currentParagraphIndex in paragraphs.indices) {
                            paragraphs[currentParagraphIndex]
                        } else {
                            paragraphs.firstOrNull() ?: story.title
                        }
                        onShareQuote(activeQuote)
                    },
                    modifier = Modifier.testTag("share_quote_button")
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.FormatQuote,
                            contentDescription = "Quote",
                            tint = ElectricViolet,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = "Quote",
                            fontSize = 10.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

// Waveform equalizer bars animation
@Composable
private fun AnimatedWaveform() {
    val transition = rememberInfiniteTransition(label = "waveform")
    val h1 by transition.animateFloat(0.3f, 1.0f, infiniteRepeatable(tween(400), RepeatMode.Reverse), label = "h1")
    val h2 by transition.animateFloat(0.2f, 0.9f, infiniteRepeatable(tween(300), RepeatMode.Reverse), label = "h2")
    val h3 by transition.animateFloat(0.4f, 1.0f, infiniteRepeatable(tween(500), RepeatMode.Reverse), label = "h3")
    val h4 by transition.animateFloat(0.1f, 0.8f, infiniteRepeatable(tween(350), RepeatMode.Reverse), label = "h4")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        WaveBar(h1, AmberGold)
        WaveBar(h2, NeonPurple)
        WaveBar(h3, ElectricViolet)
        WaveBar(h4, CoralRose)
        WaveBar(h2, AmberGold)
    }
}

@Composable
private fun WaveBar(heightFactor: Float, color: Color) {
    Box(
        modifier = Modifier
            .width(4.dp)
            .height((24 * heightFactor).dp)
            .clip(CircleShape)
            .background(color)
    )
}
