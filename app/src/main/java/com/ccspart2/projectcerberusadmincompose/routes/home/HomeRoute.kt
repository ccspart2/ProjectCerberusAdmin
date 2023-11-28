package com.ccspart2.projectcerberusadmincompose.routes.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ccspart2.projectcerberusadmincompose.core.ui.preview.PreviewScreen

@Composable
fun HomeRoute(
    navController: NavController,
) {
    HomeScreen()
}

@Composable
private fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Charlie!")
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    PreviewScreen {
        HomeScreen()
    }
}
