package com.school_of_company.expoqrandroid.qr.view.util

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.school_of_company.expoqrandroid.R

@Composable
fun QrGuideImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.qr_guide),
        contentDescription = stringResource(id = R.string.qr_guide),
        modifier = modifier
    )
}