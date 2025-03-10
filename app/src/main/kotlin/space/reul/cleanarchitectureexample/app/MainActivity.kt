package space.reul.cleanarchitectureexample.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import space.reul.cleanarchitectureexample.app.ui.composables.ImageGrid
import space.reul.cleanarchitectureexample.app.ui.shared.Dimensions
import space.reul.cleanarchitectureexample.app.ui.theme.CleanArchitectureExampleTheme
import space.reul.cleanarchitectureexample.domain.model.Cats

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val flow = viewModel.catFlow.collectAsState(MainActivityStatus.Loading)

            CleanArchitectureExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    when (val status = flow.value) {
                        is MainActivityStatus.Error -> ErrorView(status)

                        is MainActivityStatus.Loading -> {
                            LoadingView()
                        }

                        is MainActivityStatus.Success -> {
                            Contents(status.cats)
                        }

                        is MainActivityStatus.Reloading -> {
                            Contents(status.staleData, true)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ErrorView(status: MainActivityStatus.Error) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(Dimensions.screenPadding),
        ) {
            item {
                Text(text = status.message)
            }
        }
    }

    @Composable
    private fun LoadingView() {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun Contents(
        cat: Cats,
        reloading: Boolean = false,
    ) {
        Column(Modifier.fillMaxSize()) {
            if (reloading) {
                LinearProgressIndicator()
            }

            ImageGrid(cat)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}
