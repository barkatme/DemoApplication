package com.barkatme.demo.domain.usecase.flow


import com.barkatme.demo.domain.api.PlaceholderFlowApi
import kotlinx.coroutines.flow.flow

class GetTodoFlowUseCase(private val placeholderFlowApi: PlaceholderFlowApi) {
    fun getTodo(from: Int, to: Int) = flow {
        for (i in from..to) {
            emit(placeholderFlowApi.todo(i))
        }
    }
}