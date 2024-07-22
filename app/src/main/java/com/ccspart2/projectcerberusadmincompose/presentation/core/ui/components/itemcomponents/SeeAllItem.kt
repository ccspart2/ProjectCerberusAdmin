package com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.itemcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccspart2.projectcerberusadmincompose.R
import com.ccspart2.projectcerberusadmincompose.ui.theme.ProjectCerberusAdminComposeTheme

@Composable
fun SeeAllItem(
    itemLabel: String = "",
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                onClick()
            }
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = itemLabel,
                    style = MaterialTheme.typography.bodyLarge
                )
                Icon(
                    painter = painterResource(id = R.drawable.info_outline_24),
                    // TODO Fix This
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(30.dp)
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.outlineVariant),
                thickness = 1.dp
            )
        }
    }
}

@Preview
@Composable
private fun SeeAllItemPreview() {
    ProjectCerberusAdminComposeTheme {
        SeeAllItem(
            itemLabel = "Charlie Castro",
            onClick = {}
        )
    }
}
