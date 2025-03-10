package space.reul.cleanarchitectureexample.app.ui.composables

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import space.reul.cleanarchitectureexample.app.ui.shared.Dimensions
import space.reul.cleanarchitectureexample.app.ui.theme.CleanArchitectureExampleTheme
import space.reul.cleanarchitectureexample.domain.model.Cat

@Composable
fun ImageGrid(
    cats: List<Cat>,
    modifier: Modifier = Modifier,
) {
    val urls = cats.map { it.url }
    ImageGridContents(urls = urls, modifier = modifier)
}

@Composable
private fun ImageGridContents(
    urls: List<String>,
    modifier: Modifier = Modifier,
) {
    val columns = GridCells.Adaptive(Dimensions.gridCellSize)
    LazyVerticalGrid(modifier = modifier, columns = columns) {
        urls.forEach { url -> item { AsyncImageCell(url) } }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageGridPreview() {
    CleanArchitectureExampleTheme {
        ImageGridContents(
            listOf(
                "https://cataas.com/cat/KjXFF7AvE2wrtEcs?type=square&position=center",
            ),
        )
    }
}
