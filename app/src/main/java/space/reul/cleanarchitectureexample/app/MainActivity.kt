package space.reul.cleanarchitectureexample.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import space.reul.cleanarchitectureexample.app.ui.theme.CleanArchitectureExampleTheme
import space.reul.cleanarchitectureexample.app.ui.theme.Spacing

class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            CleanArchitectureExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val flow = viewModel.shibaFlow.collectAsState(emptyList())

                    ImageGrid(flow.value)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}

@Composable
fun ImageGrid(urls: List<String>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
        urls.forEach { url ->
            item {
                Card(Modifier.padding(Spacing.quarter).height(150.dp)) {
                    AsyncImage(
                        modifier= Modifier.fillMaxHeight(),
                        model = url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShibaGridPreview() {
    CleanArchitectureExampleTheme {
        ImageGrid(listOf("https://cdn.shibe.online/shibes/36083b6b1f07865085681235e4b4b174f60b7db1.jpg"))
    }
}