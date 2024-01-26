package space.reul.cleanarchitectureexample.app.ui.composables

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import space.reul.cleanarchitectureexample.app.ui.theme.CleanArchitectureExampleTheme

@Composable
fun ImageGrid(urls: List<String>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp)
    ) {
        urls.forEach { url ->
            item { AsyncImageCell(url) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageGridPreview() {
    CleanArchitectureExampleTheme {
        ImageGrid(listOf("https://cdn.shibe.online/shibes/36083b6b1f07865085681235e4b4b174f60b7db1.jpg"))
    }
}
