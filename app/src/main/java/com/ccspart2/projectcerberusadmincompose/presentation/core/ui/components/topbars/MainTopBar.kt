package com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.topbars

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.ccspart2.projectcerberusadmincompose.R
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview.PreviewScreen

@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onActionClick: () -> Unit = {},
    actionImageResource: Int? = null,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 25.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (actionImageResource != null) {
            Icon(
                painter = painterResource(id = actionImageResource),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clickable {
                        onActionClick()
                    },
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            Spacer(modifier = Modifier.size(150.dp))
        }

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.cerberus_logo_color),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )
    }
}



@Preview(widthDp = 1024, heightDp = 200)
@Composable
private fun MainTopBarPreview() {
    PreviewScreen {
        MainTopBar(
            title = "Employees",
            actionImageResource = R.drawable.outline_place_24,
            onActionClick = {}
        )
    }
}

@Preview(widthDp = 1024, heightDp = 200)
@Composable
private fun MainTopBarPreviewWithoutActionIcon() {
    PreviewScreen {
        MainTopBar(
            title = "Employees",
            actionImageResource = null,
            onActionClick = {}
        )
    }
}
