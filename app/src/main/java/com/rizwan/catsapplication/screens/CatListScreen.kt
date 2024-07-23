package com.rizwan.catsapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.rizwan.catsapplication.R
import com.rizwan.catsapplication.models.Cat
import com.rizwan.catsapplication.models.CatsState
import com.rizwan.catsapplication.models.Favorite
import com.rizwan.catsapplication.navigation.CAT_DETAIL_SCREEN
import com.rizwan.catsapplication.viewModel.CatViewModel
import com.rizwan.catsapplication.viewModel.FavoritesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(
    navHostController: NavHostController
) {

    val viewModel: CatViewModel = hiltViewModel()
    val catsState: CatsState by viewModel.cats.collectAsState()

    val pullRefreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        coroutineScope. launch {
            viewModel.getCats()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            Header(isDarkBackground = true)
            PullToRefreshBox(
                state = pullRefreshState,
                isRefreshing = catsState.isLoading,
                onRefresh = onRefresh
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(1f)
                ) {
                    items(catsState.cats) {
                        CatItem(cat = it, isDarkBackground = true) {
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                "data",
                                it
                            )
                            navHostController.navigate(CAT_DETAIL_SCREEN)
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun Header(isDarkBackground: Boolean) {
    Column(
        modifier = Modifier
            .background(if (isDarkBackground) Color.Black else Color.White)
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp)
    ) {
        HeaderText(isDarkBackground)
    }
}

@Composable
fun HeaderText(isDarkBackground: Boolean) {

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = if (isDarkBackground) Color.White else Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W300
            )
        ) {
            append("Hi, ")
        }
        withStyle(
            style = SpanStyle(
                color = if (isDarkBackground) Color.White else Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        ) {
            append("Cats!")
        }
    }

    Text(text = annotatedString)

}

@Composable
fun CatItem(
    cat: Cat,
    isDarkBackground: Boolean,
    onClick: () -> Unit = {},
) {

    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
    val favourites: List<Favorite> by favoritesViewModel.getFavorites().observeAsState(emptyList())

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clickable { onClick() }
    ) {
        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp)
        ) {
            Box {
                AsyncImage(
                    model = cat.imageUrl,
                    contentDescription = cat.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(300.dp)
                )
                Icon(
                    imageVector = if (favourites.contains(Favorite(cat.id))) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.favorite),
                    modifier = Modifier
                        .padding(15.dp)
                        .size(40.dp)
                        .align(Alignment.BottomEnd)
                        .clickable {
                            if (favourites.contains(Favorite(cat.id))) {
                                coroutineScope.launch { favoritesViewModel.removeFavorite(cat) }
                            } else {
                                coroutineScope.launch { favoritesViewModel.addFavorite(cat) }
                            }
                        }
                )
            }
        }
        Text(
            text = cat.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            color = if (isDarkBackground) Color.White else Color.Black,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp, 0.dp)
        )
        Text(
            text = "Origin: ${cat.origin}",
            textAlign = TextAlign.Center,
            color = if (isDarkBackground) Color.White else Color.Black,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp, 4.dp)
        )
    }
}