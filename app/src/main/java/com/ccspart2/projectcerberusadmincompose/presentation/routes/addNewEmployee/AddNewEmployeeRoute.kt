package com.ccspart2.projectcerberusadmincompose.presentation.routes.addNewEmployee

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ccspart2.projectcerberusadmincompose.R
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.topbars.MainTopBar
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview.PreviewScreen

@Composable
fun AddNewEmployeeRoute(navController: NavController){
    AddNewEmployeeScreen()
}

@Composable
fun AddNewEmployeeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MainTopBar(
            title = "Add Employee",
            actionImageResource = null,
            onActionClick = {}
        )
    }
}

@Composable
@Preview(widthDp = 1024, heightDp = 600, showBackground = true)
fun AddNewEmployeeScreenPreview(){
    PreviewScreen {
        AddNewEmployeeScreen()
    }
}