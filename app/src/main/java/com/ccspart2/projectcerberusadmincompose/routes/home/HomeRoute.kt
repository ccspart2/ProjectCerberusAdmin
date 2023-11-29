package com.ccspart2.projectcerberusadmincompose.routes.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavController
import com.ccspart2.projectcerberusadmincompose.R
import com.ccspart2.projectcerberusadmincompose.core.navigation.Screen
import com.ccspart2.projectcerberusadmincompose.core.ui.preview.PreviewScreen

@Composable
fun HomeRoute(
    navController: NavController,
) {
    HomeScreen(
        onEmployeesClick = {
            navController.navigate(Screen.Employees.route)
        },
    )
}

@Composable
private fun HomeScreen(
    onEmployeesClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
            .background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.cerberus_logo_color),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = 100.dp,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.clickable {
                    onEmployeesClick()
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_people_alt_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "Employees",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.clickable {
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_calendar_month_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "Events",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.clickable {
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_place_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "Locations",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}

@Preview(widthDp = 1024, heightDp = 600)
@Composable
private fun HomeScreenPreview() {
    PreviewScreen {
        HomeScreen(
            onEmployeesClick = {},
        )
    }
}
