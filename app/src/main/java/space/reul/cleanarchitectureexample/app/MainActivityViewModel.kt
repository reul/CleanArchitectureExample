package space.reul.cleanarchitectureexample.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import space.reul.cleanarchitectureexample.data.repository.ShibaRepository

class MainActivityViewModel : ViewModel() {
    private val shibaRepository = ShibaRepository()
    private val shibaStateFlow: MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    val shibaFlow: Flow<List<String>> = shibaStateFlow


    private var loadTask: Job? = null

    fun onResume() {
        if (loadTask?.isActive == true) return

        loadTask = viewModelScope.launch {
            val shibas = shibaRepository.listShibas()
            shibaStateFlow.value = shibas
        }


    }
}
