package com.example.hw4superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.hw4superheroes.data.DataSource
import com.example.hw4superheroes.model.Hero


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroApp() {
    Scaffold (
        topBar = {
            HeroTopAppBar()
        }
    ) { it ->
        LazyColumn (contentPadding = it) {
            items(DataSource.heroes) {
                HeroItem(hero = it, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier.clickable { expanded = !expanded }) {
        Column (modifier = modifier.padding(8.dp).animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = modifier.weight(1f)) {
                    Text(
                        text = stringResource(hero.name),
                        style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        text = stringResource(hero.description),
                        style =  MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(MaterialTheme.shapes.small),
                    painter = painterResource(hero.imageResourceId),
                    contentDescription = null
                )
            }
            if (expanded) {
                HeroVulnerability(heroVulnerability = hero.vulnerability, heroVulnerabilityDetail = hero.vulnerabilityDetail, modifier = modifier.padding(4.dp))
            }
        }
    }
}

@Composable
fun HeroVulnerability(
    @StringRes heroVulnerability: Int,
    @StringRes heroVulnerabilityDetail: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Vulnerability: " + stringResource(heroVulnerability),
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = stringResource(heroVulnerabilityDetail),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
                Text(
                    text = "Superheroes",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier =modifier
    )
}

@Preview
@Composable
fun HeroPreview() {
    AppTheme {
        HeroApp()
    }
}


@Preview
@Composable
fun HeroPreviewDark() {
    AppTheme(darkTheme = true) {
        HeroApp()
    }
}