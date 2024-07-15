package com.ccspart2.projectcerberusadmincompose.presentation.routes.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.projectcerberusadmincompose.R
import com.ccspart2.projectcerberusadmincompose.presentation.core.navigation.Screen
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.topbars.MainTopBar
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview.PreviewScreen

@Composable
fun HomeRoute(
    navController: NavController
) {
    val viewModel: HomeViewModel = hiltViewModel()

    HomeScreen(
        onEmployeesClick = {
            navController.navigate(Screen.SeeAllEmployees.route)
        }
    )
}

@Composable
private fun HomeScreen(
    onEmployeesClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        MainTopBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 75.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.clickable {
                        onEmployeesClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_people_alt_24),
                        contentDescription = "",
                        modifier = Modifier
                            .size(150.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Employees",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.clickable {
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_calendar_month_24),
                        contentDescription = "",
                        modifier = Modifier
                            .size(150.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Events",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.clickable {
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_place_24),
                        contentDescription = "",
                        modifier = Modifier
                            .size(150.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Locations",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }

    }
}

@Preview(widthDp = 1024, heightDp = 600)
@Composable
private fun HomeScreenPreview() {
    PreviewScreen {
        HomeScreen(
            onEmployeesClick = {}
        )
    }
}
