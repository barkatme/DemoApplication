package com.barkatme.demo.ui.main.pdm1.coroutines.flow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barkatme.demo.domain.usecase.flow.GetTodoFlowUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@FlowPreview
class CoroutinesFlowViewModel(private val getTodoFlowInteractor: GetTodoFlowUseCase) :
    ViewModel(),
    CoroutineScope {

    companion object {
        const val REQUEST_MOCK_DELAY: Long = 2000
        const val INPUT_REQUEST_TIMEOUT: Long = 1000
    }

    val output = MutableLiveData<String>()
    val todoFrom = MutableLiveData<String>()
    val todoTo = MutableLiveData<String>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        output.value += "\n" + throwable.message
    }
    private val job = Job() + exceptionHandler

    fun getTodo() {
        launch {
            var result = ""
            val from = todoFrom.value?.toInt() ?: 1
            val to = todoTo.value?.toInt() ?: 1
            getTodoFlowInteractor.getTodo(from, to)
                .onStart { output.value = "loading..." }
                    //if we don't use catch, the exceptionHandler will get this throwable
                    //but this flow will stop
                .catch { result = it.message.toString() }
                .flowOn(Dispatchers.Main)
                .collect {
                    result += it.title + "\n"
                }
            output.value = result
        }
    }

    private fun requestMock(query: String): Flow<String> {
        return flow {
            delay(REQUEST_MOCK_DELAY)
            emit(query)
        }
    }

    fun setSearchFlow(searchStateFlow: StateFlow<String>) {
        launch {
            searchStateFlow.debounce(INPUT_REQUEST_TIMEOUT)
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