package com.example.data

data class Narrator(
    val id: String,
    val name: String,
    val title: String,
    val style: String,
    val bio: String,
    val avatarBgColorHex: Long,
    val defaultPitch: Float,
    val defaultSpeed: Float,
    val sampleQuote: String
)

object PreloadedData {
    val NARRATORS = listOf(
        Narrator(
            id = "aria",
            name = "Aria Vance",
            title = "Velvet Sci-Fi Voice",
            style = "Atmospheric & Cinematic",
            bio = "Master of dark cyberpunk tales, cosmic mysteries, and futuristic sagas with a hypnotic, velvety voice.",
            avatarBgColorHex = 0xFF7C4DFF,
            defaultPitch = 0.9f,
            defaultSpeed = 1.0f,
            sampleQuote = "Step into the neon mist where stars fade into artificial consciousness..."
        ),
        Narrator(
            id = "vikram",
            name = "Vikram Sharma",
            title = "Epic Drama & Folklore",
            style = "Resonant & Powerful",
            bio = "Brings ancient mythologies, royal intrigue, and legendary warrior folklore to life with dramatic intensity.",
            avatarBgColorHex = 0xFFFF6D00,
            defaultPitch = 0.85f,
            defaultSpeed = 0.95f,
            sampleQuote = "Listen closely, for the echoes of forgotten kingdoms rumble through the mountains..."
        ),
        Narrator(
            id = "elena",
            name = "Elena Rostova",
            title = "Cozy Mystery & Whispers",
            style = "Soothing & Intimate",
            bio = "Specializes in midnight mystery thrillers and relaxing bedtime stories that wrap listeners in calm intrigue.",
            avatarBgColorHex = 0xFF00BFA5,
            defaultPitch = 1.05f,
            defaultSpeed = 0.9f,
            sampleQuote = "Rain tapped softly against the attic window as the leather-bound diary fell open..."
        ),
        Narrator(
            id = "kenji",
            name = "Kenji Sato",
            title = "Anime & High Fantasy",
            style = "Dynamic & Energetic",
            bio = "High-octane narrator for magical academy chronicles, dimensional rift adventures, and heroic quests.",
            avatarBgColorHex = 0xFF2979FF,
            defaultPitch = 1.1f,
            defaultSpeed = 1.1f,
            sampleQuote = "The spell circle ignited with azure flames, cutting through the shadows!"
        ),
        Narrator(
            id = "amara",
            name = "Amara Okafor",
            title = "Warm Heritage & Wisdom",
            style = "Rich & Inspiring",
            bio = "Narrates heartwarming life tales, ancestral folklore, and philosophical journey shorts with melodic warmth.",
            avatarBgColorHex = 0xFFFFAB00,
            defaultPitch = 1.0f,
            defaultSpeed = 1.0f,
            sampleQuote = "Wisdom is like a baobab tree; no single pair of arms can encompass it all..."
        ),
        Narrator(
            id = "sophie",
            name = "Sophie Dubois",
            title = "Melodic Romance & Verse",
            style = "Soft & Elegant",
            bio = "Creates magical soundscapes for romantic encounters, Parisian sunsets, and poetic micro-stories.",
            avatarBgColorHex = 0xFFF50057,
            defaultPitch = 1.15f,
            defaultSpeed = 0.95f,
            sampleQuote = "Beneath the glowing streetlamps of Paris, two strangers shared a quiet promise..."
        )
    )

    val INITIAL_STORIES = listOf(
        StoryEntity(
            id = "story_1",
            title = "The Neon Signal",
            genre = "Sci-Fi Thriller",
            author = "Marcus Cole",
            narratorId = "aria",
            narratorName = "Aria Vance",
            narratorTitle = "Velvet Sci-Fi Voice",
            narratorPitch = 0.9f,
            narratorSpeed = 1.0f,
            coverImageRes = "img_story_sci_fi",
            readTimeMinutes = 3,
            defaultLanguage = "en",
            likesCount = 14200,
            isFeatured = true,
            contentEn = """
                Deep beneath Sector 9, rain fell upwards from the anti-gravity exhaust vents. Kai adjusted his glowing visor, watching the crimson signal pulse across his cybernetic palm.

                "It's an encrypted broadcast," whispered Nya, her optical implants flickering in the dark alley. "Originating from the lost orbital station Zenith."

                Kai decrypted the first audio packet. A voice from forty years ago crackled through their neural link: "If you are hearing this, the stars are not silent. They are singing."

                Together, they stepped into the elevator shaft, descending into the forgotten core of the neon metropolis where humanity's greatest secret slept beneath the light.
            """.trimIndent(),
            contentHi = """
                सेक्टर 9 के बहुत नीचे, एंटी-ग्रेविटी वेंट्स से बारिश ऊपर की ओर गिर रही थी। काई ने अपने चमकते हुए वाइज़र को ठीक किया और अपनी साइबरनेटिक हथेली पर लाल सिग्नल को धड़कते हुए देखा।

                "यह एक एन्क्रिप्टेड प्रसारण है," न्या ने फुसफुसाया। "यह खोए हुए ऑर्बिटल स्टेशन ज़ैनिथ से आ रहा है।"

                काई ने पहले ऑडियो पैकेट को डिक्रिप्ट किया। चालीस साल पुरानी एक आवाज उनके न्यूरल लिंक से गूंजी: "यदि आप यह सुन रहे हैं, तो तारे चुप नहीं हैं। वे गा रहे हैं।"

                वे एक साथ लिफ्ट में चले गए, उस नियॉन महानगर के भूले हुए कोर में उतरते हुए जहाँ मानवता का सबसे बड़ा रहस्य रोशनी के नीचे सो रहा था।
            """.trimIndent(),
            contentEs = """
                En lo profundo del Sector 9, la lluvia caía hacia arriba desde las ventilaciones de antigravedad. Kai se ajustó el visor brillante, observando la señal carmesí pulsando en su palma cibernética.

                "Es una transmisión cifrada", susurró Nya. "Proviene de la estación orbital perdida Zenith".

                Kai descifró el primer paquete de audio. Una voz de hace cuarenta años resonó a través de su enlace neuronal: "Si escuchas esto, las estrellas no están en silencio. Están cantando".

                Juntos, entraron en el pozo del ascensor, descendiendo al núcleo olvidado de la metrópolis de neón donde el mayor secreto de la humanidad dormía bajo la luz.
            """.trimIndent(),
            contentFr = """
                Au plus profond du Secteur 9, la pluie tombait vers le haut depuis les évents anti-gravité. Kai ajusta sa visière lumineuse, observant le signal cramoisi pulser sur sa paume cybernétique.

                "C'est une transmission cryptée," chuchota Nya. "Elle provient de la station orbitale perdue Zenith."

                Kai décrypta le premier paquet audio. Une voix datant d'il y a quarante ans résonna dans leur lien neuronal : "Si vous entendez ceci, les étoiles ne sont pas silencieuses. Elles chantent."

                Ensemble, ils entrèrent dans la cage d'ascenseur, descendant vers le cœur oublié de la métropole néon où le plus grand secret de l'humanité dormait sous la lumière.
            """.trimIndent(),
            contentDe = """
                Tief unter Sektor 9 fiel der Regen aus den Antigravitations-Schächten nach oben. Kai stellte sein leuchtendes Visier ein und beobachtete das rote Signal, das auf seiner kybernetischen Handfläche pulsierte.

                "Es ist eine verschlüsselte Übertragung", flüsterte Nya. "Sie stammt von der verlorenen Orbitalstation Zenith."

                Kai entschlüsselte das erste Audiopaket. Eine Stimme von vor vierzig Jahren knisterte durch ihre neuronale Verbindung: "Wenn du das hörst, schweigen die Sterne nicht. Sie singen."

                Gemeinsam betraten sie den Aufzugsschacht und stiegen in den verlassenen Kern der Neon-Metropole hinab.
            """.trimIndent(),
            contentJa = """
                セクター9の深部で、反重力換気口から雨が空に向かって降っていた。カイは光るバイザーを調整し、サイバネティックな手のひらで真紅の信号が点滅するのを見つめた。

                「暗号化された放送よ」と二重インプラントの暗闇でニャが囁いた。「失われた軌道ステーション・ゼニスからのものだわ」

                カイは最初の音声パケットを解読した。40年前の声が彼らの神経リンクから響いた。「これを聞いているなら、星々は沈黙していない。彼らは歌っている」
            """.trimIndent()
        ),
        StoryEntity(
            id = "story_2",
            title = "The Whispering Woods",
            genre = "Enchanted Fantasy",
            author = "Evelyn Thorne",
            narratorId = "kenji",
            narratorName = "Kenji Sato",
            narratorTitle = "Anime & High Fantasy",
            narratorPitch = 1.1f,
            narratorSpeed = 1.05f,
            coverImageRes = "img_story_fantasy",
            readTimeMinutes = 4,
            defaultLanguage = "en",
            likesCount = 28900,
            isFeatured = true,
            contentEn = """
                At twilight, the leaves of the Moonveil Forest spoke in silver riddles. Rowan drew his spell-forged blade, its blue rune glowing brighter with every heartbeat.

                "Keep to the paved moss track," warned his companion, an ancient spirit fox with flickering blue tail flames. "Step off, and the forest will re-weave your memories."

                Suddenly, a shimmering spirit barrier dissolved before them, revealing a sunken crystal sanctuary filled with forgotten starlight jars. Each jar vibrated with a song sung centuries ago.

                Rowan reached toward the central jar, knowing that opening it would awaken the sleeping guardian of the ancient realm.
            """.trimIndent(),
            contentHi = """
                गोधूलि के समय, मूनवील जंगल की पत्तियाँ चाँदी की पहेलियों में बोलती थीं। रोवन ने अपनी जादू से बनी तलवार खींची, जिसकी नीली रन हर धड़कन के साथ और अधिक चमक रही थी।

                "पक्के काई वाले रास्ते पर रहो," उसके साथी, नीली पूंछ की लपटों वाले एक प्राचीन आत्मा लोमड़ी ने चेतावनी दी। "कदम हटाओ, और जंगल आपकी यादों को फिर से बुन देगा।"

                अचानक, उनके सामने एक जगमगाती आत्मा बाधा घुल गई, जिससे भूली हुई तारों की रोशनी के जारों से भरा एक धंसा हुआ क्रिस्टल अभयारण्य दिखाई दिया।

                रोवन ने केंद्रीय जार की ओर हाथ बढ़ाया, यह जानते हुए कि इसे खोलने से प्राचीन साम्राज्य का सोता हुआ रक्षक जाग जाएगा।
            """.trimIndent(),
            contentEs = """
                Al anochecer, las hojas del Bosque Velo de Luna hablaban en acertijos plateados. Rowan desenvainó su espada forjada con hechizos, cuya runa azul brillaba con cada latido.

                "Mantente en el camino de musgo", advirtió su compañero, un antiguo zorro espiritual con llamas en la cola. "Si te desvías, el bosque reescribirá tus recuerdos".

                De repente, una barrera espiritual resplandeciente se disolvió ante ellos, revelando un santuario de cristal lleno de frascos de luz estelar olvidados.

                Rowan se acercó al frasco central, sabiendo que abrirlo despertaría al guardián durmiente del antiguo reino.
            """.trimIndent(),
            contentFr = """
                Au crépuscule, les feuilles de la Forêt Voile de Lune parlaient en énigmes d'argent. Rowan dégaina sa lame forgée par la magie, sa rune bleue brilla plus fort à chaque battement de cœur.

                "Reste sur le chemin de mousse," avertit son compagnon, un esprit renard aux flammes bleues. "Fais un pas de côté, et la forêt réécrira tes souvenirs."

                Soudain, une barrière spirituelle miroitante se dissout devant eux, révélant un sanctuaire de cristal rempli de bocaux de lumière stellaire oubliée.

                Rowan tendit la main vers le bocal central, sachant qu'en l'ouvrant, il réveillerait le gardien endormi de l'ancien royaume.
            """.trimIndent(),
            contentDe = """
                In der Dämmerung sprachen die Blätter des Mondschleier-Waldes in silbernen Rätseln. Rowan zog sein zaubergeschmiedetes Schwert, dessen blaue Rune mit jedem Herzschlag heller glühte.

                "Bleib auf dem Moospfad", warnte sein Begleiter, ein alter Geistfuchs mit blauen Schwanzflammen. "Tritt ab, und der Wald wird deine Erinnerungen umweben."

                Plötzlich löste sich eine schimmernde Geistbarriere vor ihnen auf und enthüllte ein versunkenes Kristallheiligtum voller vergessener Sternenlichtgläser.
            """.trimIndent(),
            contentJa = """
                黄昏時、ムーンベイルの森の葉は銀色の謎掛けで囁いていた。ローワンは魔法で鍛えられた剣を抜き、その青いルーン文字は鼓動のたびに明るく輝いた。

                「苔の小道を外れるな」と連れの精霊狐が警告した。「足を踏み外せば、森があなたの記憶を書き換えてしまう」

                突如として、彼らの前で揺らめく精神の結界が溶け去り、忘れられた星屑の瓶で満たされた沈んだ水晶の聖域が現れた。
            """.trimIndent()
        ),
        StoryEntity(
            id = "story_3",
            title = "The Midnight Alchemist",
            genre = "Cozy Mystery",
            author = "Clara Bennett",
            narratorId = "elena",
            narratorName = "Elena Rostova",
            narratorTitle = "Cozy Mystery & Whispers",
            narratorPitch = 1.05f,
            narratorSpeed = 0.9f,
            coverImageRes = "img_app_icon",
            readTimeMinutes = 3,
            defaultLanguage = "en",
            likesCount = 19400,
            isFeatured = false,
            contentEn = """
                When the clock struck midnight in the dusty antique shop, the brass clockwork pocket watch began ticking backwards.

                Inspector Julian smoothed his coat, picking up a silver vial labelled 'Memory Essence: October 1892'. A faint aroma of lavender and old parchment drifted into the room.

                "The shopkeeper didn't vanish," Julian realized, looking at the glowing mirror on the wall. "He stepped through time to leave us this final clue."

                As he held the vial to the moonlight, liquid silver inside revealed a map of underground tunnels beneath the city cathedral.
            """.trimIndent(),
            contentHi = """
                जब धूल भरी पुरानी दुकान में आधी रात का घंटा बजा, तो पीतल की जेब घड़ी उल्टी दिशा में टिक-टिक करने लगी।

                इंस्पेक्टर जुलियन ने अपने कोट को ठीक किया, 'स्मृति सार: अक्टूबर 1892' लेबल वाली एक चांदी की शीशी उठाई। लेवेंडर और पुराने चर्मपत्र की हल्की सुगंध कमरे में तैरने लगी।

                "दुकानदार गायब नहीं हुआ," जुलियन ने दीवार पर चमकते आईने को देखते हुए महसूस किया। "वह हमें यह अंतिम सुराग देने के लिए समय के पार चला गया।"

                जैसे ही उसने शीशी को चाँदनी में पकड़ा, उसके अंदर के तरल चाँदी ने शहर के कैथेड्रल के नीचे की भूमिगत सुरंगों का नक्शा प्रकट कर दिया।
            """.trimIndent(),
            contentEs = """
                Cuando el reloj dio la medianoche en la empolvada tienda de antigüedades, el reloj de bolsillo de latón comenzó a marchar hacia atrás.

                El inspector Julian se arregló el abrigo y tomó un frasco de plata etiquetado como 'Esencia de Memoria: Octubre de 1892'. Un tenue aroma a lavanda y pergamino viejo inundó la habitación.

                "El tendero no desapareció", se dio cuenta Julian, mirando el espejo brillante. "Viajó en el tiempo para dejarnos esta pista final".
            """.trimIndent(),
            contentFr = """
                Quand minuit sonna dans la boutique d'antiquités poussiéreuse, la montre à gousset en laiton commença à tourner à l'envers.

                L'inspecteur Julian ajusta son manteau, ramassant une fiole en argent étiquetée 'Essence de Mémoire : Octobre 1892'. Un doux parfum de lavande et de vieux parchemin se répongit dans la pièce.

                "Le commerçant n'a pas disparu," réalisa Julian. "Il a traversé le temps pour nous laisser ce dernier indice."
            """.trimIndent(),
            contentDe = """
                Als die Uhr im staubigen Antiquitätengeschäft Mitternacht schlug, begann die Messing-Taschenuhr rückwärts zu ticken.

                Inspektor Julian strich seinen Mantel glatt und hob ein silbernes Fläschchen mit der Aufschrift 'Erinnerungsessenz: Oktober 1892' auf. Ein feiner Duft von Lavendel erfüllte den Raum.
            """.trimIndent(),
            contentJa = """
                埃っぽい骨董品店で午前零時の鐘が鳴った時、真鍮の懐中時計が逆回転を始めた。

                ジュリアン警部はコートを整え、「記憶のエッセンス：1892年10月」と書かれた銀の小瓶を持ち上げた。ラベンダーと古い羊皮紙の淡い香りが部屋に漂った。
            """.trimIndent()
        ),
        StoryEntity(
            id = "story_4",
            title = "The Song of Emperor Vikram",
            genre = "Folklore & Legend",
            author = "Rajesh Verma",
            narratorId = "vikram",
            narratorName = "Vikram Sharma",
            narratorTitle = "Epic Drama & Folklore",
            narratorPitch = 0.85f,
            narratorSpeed = 0.95f,
            coverImageRes = "img_story_sci_fi",
            readTimeMinutes = 5,
            defaultLanguage = "hi",
            likesCount = 31200,
            isFeatured = true,
            contentEn = """
                Across the roaring rivers of Ujjain, King Vikramaditya rode his black steed through the moonlit storm. Upon his shoulders rested the mysterious celestial phantom, Vetal.

                "O King," chuckled the phantom with eyes like burning embers. "Hear this riddle of love and duty, or your crown shall shatter into dust!"

                Vikram smiled calmly, holding his royal sword firm against the night wind. He listened as the phantom wove a saga of brave princes, hidden golden palaces, and eternal sacrifice.

                When the story ended, Vikram spoke the infallible truth of justice, demonstrating why his wisdom echoed through centuries.
            """.trimIndent(),
            contentHi = """
                उज्जैन की गरजती नदियों के पार, राजा विक्रमादित्य ने चाँदनी रात के तूफान में अपने काले घोड़े को दौड़ाया। उनके कंधों पर रहस्यमयी आकाशीय बेताल बैठा था।

                "हे राजा," जलते हुए अंगारों जैसी आँखों से बेताल हँसा। "प्रेम और कर्तव्य की यह पहेली सुनो, अन्यथा आपका मुकुट धूल में बदल जाएगा!"

                विक्रम शांत भाव से मुस्कुराए, रात की हवा के खिलाफ अपनी शाही तलवार को मजबूती से पकड़े रखा। उन्होंने ध्यान से सुना जब बेताल ने बहादुर राजकुमारों, छिपे हुए सुनहरे महलों और शाश्वत बलिदान की गाथा बुनी।

                जब कहानी समाप्त हुई, तो विक्रम ने न्याय का अकाट्य सत्य कहा, यह दिखाते हुए कि क्यों उनका ज्ञान सदियों से गूंजता आ रहा है।
            """.trimIndent(),
            contentEs = """
                A través de los rugientes ríos de Ujjain, el rey Vikramaditya montó su corcel negro en medio de la tormenta. Sobre sus hombros descansaba el misterioso fantasma Vetal.

                "¡Oh Rey!", se rio el fantasma con ojos de brasas ardientes. "Escucha este acertijo sobre el amor y el deber, ¡o tu corona se convertirá en polvo!"
            """.trimIndent(),
            contentFr = """
                À travers les rivières rugissantes d'Ujjain, le roi Vikramaditya chevauchait son étalon noir dans la tempête éclairée par la lune. Sur ses épaules reposait le mystérieux fantôme Vetal.
            """.trimIndent(),
            contentDe = """
                Über die reißenden Flüsse von Ujjain ritt König Vikramaditya sein schwarzes Ross durch den stürmischen Mondschein. Auf seinen Schultern ruhte der geheimnisvolle Geist Vetal.
            """.trimIndent(),
            contentJa = """
                ウッジャインの轟く川を越えて、ヴィクラマーディティヤ王は月光の嵐の中、黒い愛馬を走らせた。彼の肩には謎めいた精霊ヴェータルが乗っていた。
            """.trimIndent()
        ),
        StoryEntity(
            id = "story_5",
            title = "Whispers Under the Baobab",
            genre = "Mythology & Wisdom",
            author = "Nia Diallo",
            narratorId = "amara",
            narratorName = "Amara Okafor",
            narratorTitle = "Warm Heritage & Wisdom",
            narratorPitch = 1.0f,
            narratorSpeed = 1.0f,
            coverImageRes = "img_story_fantasy",
            readTimeMinutes = 3,
            defaultLanguage = "en",
            likesCount = 16800,
            isFeatured = false,
            contentEn = """
                Under the ancient golden baobab tree at sunset, village elders gathered the children as the drums began their gentle rhythmic pulse.

                "Long ago," Amara began, her voice floating like golden embers into the dusk, "the sun and the moon were brother and sister who painted the twilight together."

                She told of how patience brought rain to the drought-parched lands, and how courage was not the absence of fear, but the willingness to sing even in darkness.

                The children listened spellbound as golden fireflies danced around the baobab leaves, keeping time with the eternal story.
            """.trimIndent(),
            contentHi = """
                सूर्यास्त के समय प्राचीन सुनहरे बाओबाब पेड़ के नीचे, गाँव के बुजुर्गों ने बच्चों को इकट्ठा किया क्योंकि ड्रमों ने अपनी धीमी लयबद्ध स्पंदन शुरू कर दी थी।

                "बहुत समय पहले," अमरा ने शुरू किया, उसकी आवाज शाम के समय सुनहरे अंगारों की तरह तैर रही थी, "सूरज और चाँद भाई-बहन थे जो एक साथ शाम को चित्रित करते थे।"

                उसने बताया कि कैसे धैर्य ने सूखे से प्रभावित भूमि में बारिश लाई, और कैसे साहस डर की अनुपस्थिति नहीं था, बल्कि अंधेरे में भी गाने की इच्छा थी।
            """.trimIndent(),
            contentEs = """
                Bajo el antiguo baobab dorado al atardecer, los ancianos de la aldea reunieron a los niños mientras los tambores comenzaban su suave pulso rítmico.
            """.trimIndent(),
            contentFr = """
                Sous l'ancien baobab doré au coucher du soleil, les anciens du village rassemblèrent les enfants alors que les tambours commençaient leur doux rythme.
            """.trimIndent(),
            contentDe = """
                Unter dem alten goldenen Baobab-Baum bei Sonnenuntergang versammelten die Dorfältesten die Kinder, während die Trommeln sanft zu schlagen begannen.
            """.trimIndent(),
            contentJa = """
                夕暮れ時、古代の黄金のバオバブの木の下で、太鼓の優しくリズムある鼓動が始まると、村の長老たちが子供たちを集めた。
            """.trimIndent()
        )
    )
}
