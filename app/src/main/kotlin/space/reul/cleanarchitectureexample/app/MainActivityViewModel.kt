package space.reul.cleanarchitectureexample.app

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import space.reul.cleanarchitectureexample.domain.model.Cats
import space.reul.cleanarchitectureexample.domain.usecase.ListCats
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
    @Inject
    constructor(
        private val catRepository: ListCats.Repository,
        private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val _catFlow: MutableStateFlow<MainActivityStatus> =
            MutableStateFlow(MainActivityStatus.Loading)

        val catFlow: Flow<MainActivityStatus> = _catFlow

        private var loadTask: Job? = null
        private val data: Cats? = null

        fun onResume() {
            if (loadTask?.isActive == true) return

            loadTask =
                viewModelScope.launch {
                    _catFlow.tryEmit(
                        if (data != null) {
                            MainActivityStatus.Reloading(data)
                        } else {
                            MainActivityStatus.Loading
                        },
                    )

                    val listCats = newUseCase()
                    val output = listCats()

                    _catFlow.value =
                        output.fold(
                            onSuccess = {
                                MainActivityStatus.Success(it)
                            },
                            onFailure = {
                                MainActivityStatus.Error(it.stackTraceToString())
                            },
                        )
                }
        }

        private fun newUseCase() = ListCats(Dispatchers.IO, catRepository)
    }

sealed interface MainActivityStatus {
    data class Success(
        val cats: Cats,
    ) : MainActivityStatus

    data class Reloading(
        val staleData: Cats,
    ) : MainActivityStatus

    data class Error(
        val message: String,
    ) : MainActivityStatus

    data object Loading : MainActivityStatus
}
