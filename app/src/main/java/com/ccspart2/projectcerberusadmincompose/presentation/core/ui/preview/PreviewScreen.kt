package com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.ccspart2.projectcerberusadmincompose.ui.theme.ProjectCerberusAdminComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewScreen(
    content: @Composable () -> Unit
) {
    ProjectCerberusAdminComposeTheme {
        Scaffold {
            content()
        }
    }
}
