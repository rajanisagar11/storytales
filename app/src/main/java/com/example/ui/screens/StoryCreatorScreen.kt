package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.StoryViewModel
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.NeonPurple

@Composable
fun StoryCreatorScreen(
    viewModel: StoryViewModel,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("Sci-Fi Thriller") }
    var selectedNarratorId by remember { mutableStateOf("aria") }
    var contentEn by remember { mutableStateOf("") }
    var contentHi by remember { mutableStateOf("") }
    var coverResName by remember { mutableStateOf("img_story_sci_fi") }

    val narrators = viewModel.narrators
    val genreOptions = listOf("Sci-Fi Thriller", "Enchanted Fantasy", "Cozy Mystery", "Folklore & Legend", "Mythology & Wisdom")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 70.dp)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("story_creator_screen")
    ) {
        // Banner Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Studio",
                tint = AmberGold,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Story Studio & Reel Creator",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Publish your custom story reel with voice narration and multi-language support.",
                    fontSize = 12.sp,
                    color = ElectricViolet
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quick AI Prompt Templates Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, NeonPurple.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF18102A)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = "Templates",
                        tint = AmberGold,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Quick Story Reel Starters",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TemplateChip(
                        label = "⚡ Cyberpunk Heist",
                        onClick = {
                            title = "The Quantum Ledger"
                            author = "Elena Vance"
                            genre = "Sci-Fi Thriller"
                            selectedNarratorId = "aria"
                            coverResName = "img_story_sci_fi"
                            contentEn = """
                                Rain washed over the high-altitude skybridge as Cipher cracked the quantum firewall. Inside lay thirty gigabytes of suppressed human consciousness.

                                "We have forty seconds before orbital drones intercept us," warned Lynx, reloading her plasma rifle.

                                Cipher pressed execute. The city lights below pulsed in blue synchronicity as the truth broke free across every holographic screen.
                            """.trimIndent()
                            contentHi = """
                                वर्षा उच्च ऊंचाई वाले स्काईब्रिज पर बह रही थी क्योंकि सिफर ने क्वांटम फ़ायरवॉल को क्रैक कर दिया था। इसके अंदर तीस गीगाबाइट दमित मानव चेतना थी।

                                "ऑर्बिटल ड्रोन द्वारा हमें रोकने से पहले हमारे पास चालीस सेकंड हैं," लिंक्स ने चेतावनी दी।

                                सिफर ने निष्पादित दबाया। नीचे शहर की बत्तियाँ नीले सामंजस्य में धड़कने लगीं क्योंकि सच्चाई हर होलोग्राफिक स्क्रीन पर फैल गई।
                            """.trimIndent()
                        }
                    )

                    TemplateChip(
                        label = "✨ Dragon Keeper",
                        onClick = {
                            title = "The Emerald Wyrm"
                            author = "Cassian Reed"
                            genre = "Enchanted Fantasy"
                            selectedNarratorId = "kenji"
                            coverResName = "img_story_fantasy"
                            contentEn = """
                                Deep inside the hollow dragon's peak, the ancient egg glowed with emerald lightning. Nora held her breath, placing her palm against the warm scale.

                                "The prophecy spoke of a rider born during the blood moon," whispered Master Theron.

                                As the shell cracked, a soft dragon purr vibrated through Nora's mind, cementing a soul bond older than kingdoms.
                            """.trimIndent()
                            contentHi = """
                                खोखले ड्रैगन के शिखर के भीतर, प्राचीन अंडा पन्ने जैसी बिजली से चमक उठा। नोरा ने अपनी सांसें रोक लीं, और गर्म तराजू पर अपनी हथेली रखी।

                                "भविष्यवाणी में ब्लड मून के दौरान पैदा हुए एक सवार की बात की गई थी," मास्टर थेरॉन ने फुसफुसाया।

                                जैसे ही खोल टूटा, एक नरम ड्रैगन की आवाज नोरा के दिमाग में गूंजी, जो साम्राज्यों से भी पुराने एक आत्मिक बंधन को मजबूत कर रही थी।
                            """.trimIndent()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Story Title Input
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Story Title *", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("story_title_input"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeonPurple,
                unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Author Input
        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author / Creator Name", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("story_author_input"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeonPurple,
                unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Genre Selector
        Text("Select Genre:", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genreOptions.forEach { option ->
                val isSelected = (genre == option)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) NeonPurple else Color.White.copy(alpha = 0.12f))
                        .clickable { genre = option }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = option,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Voice Narrator Selector
        Text("Select Voice Narrator Artist:", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            narrators.forEach { narrator ->
                val isSelected = (selectedNarratorId == narrator.id)
                Card(
                    modifier = Modifier
                        .width(130.dp)
                        .border(
                            1.dp,
                            if (isSelected) AmberGold else Color.Transparent,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { selectedNarratorId = narrator.id },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) Color(0xFF281C45) else Color(0xFF140D26)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "Voice",
                            tint = if (isSelected) AmberGold else ElectricViolet,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = narrator.name,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = narrator.title,
                            fontSize = 10.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // English Content Input
        OutlinedTextField(
            value = contentEn,
            onValueChange = { contentEn = it },
            label = { Text("English Story Text (Paragraphs separated by line breaks) *", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .testTag("story_text_en_input"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeonPurple,
                unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Hindi Translation Input (Optional)
        OutlinedTextField(
            value = contentHi,
            onValueChange = { contentHi = it },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Translate, contentDescription = "Lang", tint = AmberGold, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Hindi Translation (Optional - हिन्दी अनुवाद)", color = Color.Gray)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .testTag("story_text_hi_input"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeonPurple,
                unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Publish Button
        Button(
            onClick = {
                if (title.isNotBlank() && contentEn.isNotBlank()) {
                    viewModel.createCustomStory(
                        title = title,
                        genre = genre,
                        author = author,
                        narratorId = selectedNarratorId,
                        contentEn = contentEn,
                        contentHi = contentHi,
                        coverImageRes = coverResName
                    )
                }
            },
            enabled = (title.isNotBlank() && contentEn.isNotBlank()),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("publish_story_button"),
            colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(Icons.Default.Check, contentDescription = "Publish", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Publish Story Reel to Feed", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun TemplateChip(label: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.12f))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
    }
}
