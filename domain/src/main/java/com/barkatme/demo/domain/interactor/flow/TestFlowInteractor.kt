package com.barkatme.demo.domain.interactor.flow


import com.barkatme.demo.domain.data.api.PlaceholderFlowApi
import kotlinx.coroutines.flow.flow

class TestFlowInteractor(private val placeholderFlowApi: PlaceholderFlowApi) {
    fun getTodo(from: Int, to: Int) = flow {
        for (i in from..to) {
            emit(placeholderFlowApi.todo(i))
        }
    }
}