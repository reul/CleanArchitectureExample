package space.reul.cleanarchitectureexample.app.ui.composables

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import space.reul.cleanarchitectureexample.app.ui.theme.Spacing

@Composable
fun AsyncImageCell(url: String) {
    Card(
        Modifier
            .padding(Spacing.quarter)
            .height(150.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(),
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun AsyncImageCellPreview() {
    AsyncImageCell(
        "https://cdn.shibe.online/shibes/36083b6b1f07865085681235e4b4b174f60b7db1.jpg"
    )
}
