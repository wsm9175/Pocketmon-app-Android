package com.wsm9175.android.pocketmon.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.*
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.wsm9175.android.pocketmon.ui.theme.PocketmonTheme
import com.wsm9175.android.pocketmon.viewmodel.PokemonViewModel


@Composable
fun MainScreen(
    onPokemonClick: (String) -> Unit,
    // navigation에 소속돼있으면 viewModel이 아닌 hiltViewModel()사용
    viewModel: PokemonViewModel = hiltViewModel()
){
    val items = viewModel.pokemonList.collectAsLazyPagingItems()
    LazyColumn{
        items(items, key = {it.url}){
            it?.let {
                PokemonInfo(
                    name = it.name,
                    url = it.url,
                    onPokemonClick = onPokemonClick
                )
            }
        }
    }
}

@Composable
fun PokemonInfo(
    name : String,
    url : String,
    onPokemonClick: (String) -> Unit
){
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column {
                Text("포케몬: $name")
                Text(
                    text = url,
                    Modifier.alpha(0.4f)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onPokemonClick(url)
                }) {
                    Text(text = "보기")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMain(){
    PocketmonTheme {
        MainScreen(onPokemonClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPockmonInfo(){
    PocketmonTheme {
        PocketmonTheme {
            PokemonInfo("test", "test url", {})
        }
    }
}