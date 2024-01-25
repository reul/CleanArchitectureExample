package space.reul.cleanarchitectureexample.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import space.reul.cleanarchitectureexample.data.repository.ShibaRepositoryImpl
import space.reul.cleanarchitectureexample.domain.model.Urls
import space.reul.cleanarchitectureexample.domain.usecase.ListShibas

class MainActivityViewModel : ViewModel() {
    private val shibaRepository: ListShibas.ShibasRepository = ShibaRepositoryImpl()
    private val shibaStateFlow: MutableStateFlow<Urls> = MutableStateFlow(arrayListOf())
    val shibaFlow: Flow<List<String>> = shibaStateFlow

    private var loadTask: Job? = null

    fun onResume() {
        if (loadTask?.isActive == true) return

        loadTask = viewModelScope.launch {
            val listShibas = newUseCase()
            val output = listShibas()
            shibaStateFlow.value = output
        }
    }

    private fun newUseCase() = ListShibas(Dispatchers.IO, shibaRepository)
}
