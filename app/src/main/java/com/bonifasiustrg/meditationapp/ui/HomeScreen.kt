package com.bonifasiustrg.meditationapp.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonifasiustrg.meditationapp.BottomMenuContent
import com.bonifasiustrg.meditationapp.Feature
import com.bonifasiustrg.meditationapp.R
import com.bonifasiustrg.meditationapp.standardQuadFromTo
import com.bonifasiustrg.meditationapp.ui.theme.*

@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)
//        .padding(16.dp)
    ) {
        Column {
            GreetingSection()

            val chipList = listOf<String>("Sweet Sleep", "Insomnia", "Depression")
            ChipSection(chipList)

            CurrentMeditation()
            val features = listOf(
                Feature(
                    title = "Sleep meditation",
                    R.drawable.ic_headphone,
                    BlueViolet1,
                    BlueViolet2,
                    BlueViolet3
                ),
                Feature(
                    title = "Tips for sleeping",
                    R.drawable.ic_videocam,
                    LightGreen1,
                    LightGreen2,
                    LightGreen3
                ),
                Feature(
                    title = "Night island",
                    R.drawable.ic_moon,
                    OrangeYellow1,
                    OrangeYellow2,
                    OrangeYellow3
                ),
                Feature(
                    title = "Calming sounds",
                    R.drawable.ic_music,
                    Beige1,
                    Beige2,
                    Beige3
                ),
                Feature("Coming Soon", R.drawable.ic_profile, Color.White, BlueViolet2, DarkerButtonBlue),
                Feature("Coming Soon", R.drawable.ic_headphone, Color.White, BlueViolet2, DarkerButtonBlue),
                Feature("Coming Soon", R.drawable.ic_moon, Color.White, BlueViolet2, DarkerButtonBlue),
            )

            FeaturedSection(feature = features)

        }
        val items = listOf<BottomMenuContent>(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Meditate", R.drawable.ic_bubble),
            BottomMenuContent("Sleep", R.drawable.ic_moon),
            BottomMenuContent("Music", R.drawable.ic_music),
            BottomMenuContent("Profile", R.drawable.ic_profile)
        )

        BottomNavMenu(items = items,
        modifier = Modifier.align(BottomCenter).fillMaxWidth())
    }

}


@Composable
fun BottomNavMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(modifier = modifier
        .fillMaxWidth()
        .background(DeepBlue)
        .padding(horizontal=15.dp, vertical=8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        items.forEachIndexed { index, item ->  
            BottomNavMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomNavMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
        ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isSelected) activeHighlightColor else Color.Transparent
                )
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)

            )
        }

        Text(text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )

    }
}

@Composable
fun GreetingSection() {
    Row(Modifier.fillMaxWidth().padding(15.dp),horizontalArrangement = Arrangement.SpaceBetween) {
        Column(){
            Text(text = "Good Morning, Bonifasius", style = Typography.h1)
            Text(text = "Wish you have a good day!", style = Typography.body1)
        }

        Icon(painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(
    chipList: List<String>
) {
    val selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow(modifier = Modifier.padding(horizontal = 15.dp)) {
        items(chipList.size) {idx ->
            Box(modifier = Modifier
                .padding(top = 15.dp, bottom = 15.dp, end = 15.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable() {

                }
                .background(
                    if (selectedChipIndex == idx) ButtonBlue
                    else DarkerButtonBlue
                )
                .padding(16.dp)

            ) {
                Text(text = chipList[idx], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation() {
    Row(
        Modifier
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(LightRed)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(){
            Text(text = "Daily Thought", style = Typography.h2)
            Text(text = "Meditation", style = Typography.body1, color = TextWhite)
        }

        Icon(painter = painterResource(id = R.drawable.ic_play),
            contentDescription = "Play Icon",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)

        )
    }
}

@Composable
fun FeaturedSection(feature: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Featured",
            style = Typography.h1,
            modifier = Modifier.padding(15.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start=7.5.dp, end=7.5.dp, bottom=7.5.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(feature.size) {
                FeatureItem(feature = feature[it])
            }
        }


    }
}

@Composable
fun FeatureItem(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium color path
        val mediumColorPoint1 = Offset(0f, height * 0.3f)
        val mediumColorPoint2 = Offset(0.1f, height * 0.35f)
        val mediumColorPoint3 = Offset(0.4f, height * 0.05f)
        val mediumColorPoint4 = Offset(0.75f, height * 0.7f)
        val mediumColorPoint5 = Offset(1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColorPoint1.x, mediumColorPoint1.y)
            standardQuadFromTo(mediumColorPoint1, mediumColorPoint2)
            standardQuadFromTo(mediumColorPoint2, mediumColorPoint3)
            standardQuadFromTo(mediumColorPoint3, mediumColorPoint4)
            standardQuadFromTo(mediumColorPoint4, mediumColorPoint5)

            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        //Light color path
        val lightColorPoint1 = Offset(0f, height * 0.3f)
        val lightColorPoint2 = Offset(0.1f, height * 0.35f)
        val lightColorPoint3 = Offset(0.4f, height * 0.05f)
        val lightColorPoint4 = Offset(0.75f, height * 0.7f)
        val lightColorPoint5 = Offset(1.4f, -height.toFloat())

        val lightColoredPath = Path().apply {
            moveTo(lightColorPoint1.x, lightColorPoint1.y)
            standardQuadFromTo(lightColorPoint1, lightColorPoint2)
            standardQuadFromTo(lightColorPoint2, lightColorPoint3)
            standardQuadFromTo(lightColorPoint3, lightColorPoint4)
            standardQuadFromTo(lightColorPoint4, lightColorPoint5)

            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumColoredPath,
                color = feature.mediumColor)

           drawPath(path = lightColoredPath,
                color = feature.lightColor)
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)) {
            Text(text = feature.title, style =  Typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(TopStart)
            )


            Icon(painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // Handle the click

                    }
                    .align(BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)
    ) {
        Column(modifier = Modifier.padding(16.dp)
        ) {
            GreetingSection()
            val chipList = listOf<String>("Sweet Sleep", "Insomnia", "Depression")
            ChipSection(chipList)
            CurrentMeditation()

        }
    }
}
