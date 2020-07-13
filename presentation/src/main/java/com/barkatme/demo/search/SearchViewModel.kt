package com.barkatme.demo.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barkatme.demo.domain.TestFlowInteractor
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@FlowPreview
class SearchViewModel(private val testFlowInteractor: TestFlowInteractor) : ViewModel(),
    CoroutineScope {

    val output = MutableLiveData<String>()

    val todoFrom = MutableLiveData<String>()
    val todoTo = MutableLiveData<String>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job = Job()

    fun getTodo() {
        launch {
            var result = ""
            val from = todoFrom.value?.toInt() ?: 0
            val to = todoTo.value?.toInt() ?: 0
                testFlowInteractor.getTodo(from, to)
                    .flowOn(Dispatchers.IO)
                    .collect {
                        result += "\n${it.title}"
                    }
            output.value = result
        }
    }

    private fun requestMock(query: String): Flow<String> {
        return flow {
            delay(2000)
            emit(query)
        }
    }

    fun setSearchFlow(searchStateFlow: StateFlow<String>) {
        output.value = "test"
        launch {
            searchStateFlow.debounce(1000)
                .filter { query ->
                    if (query.isEmpty()) {
                        output.postValue("")
                        false
                    } else {
                        true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    flow {
                        requestMock(query)
                            .collect {
                                emit(it)
                            }
                    }
                }
                //makes above operators executing with that Dispatcher
                .flowOn(Dispatchers.Default)
                .collect { result ->
                    output.value = result
                }
        }
    }
}