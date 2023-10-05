package com.example.hw4superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

                }
            }
        }
    }
}

@Composable
fun HeroApp() {
    LazyColumn {
        items(DataSource.heroes) {
            HeroItem(hero = it, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card (modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = stringResource(hero.name, MaterialTheme.typography.displaySmall),
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = stringResource(hero.description, MaterialTheme.typography.bodyLarge),
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
            HeroItemButton(
                expanded = expanded,
                onClick = { /*TODO*/ },
                hero = hero
            )
        }
    }
}

@Composable
private fun HeroItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    hero: Hero,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = Icons.Filled.ExpandMore,
            contentDescription = stringResource(hero.vulnerabilityDetail),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview
@Composable
fun HeroPreview() {
    AppTheme {
        HeroApp()
    }
}


@Composable
fun HeroPreviewDark() {
    AppTheme (useDarkTheme = true) {
        HeroApp()
    }
}