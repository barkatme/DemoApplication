package com.barkatme.demo.domain

import com.barkatme.data.flow.FlowRepository
import kotlinx.coroutines.flow.flow

class TestFlowInteractor(private val flowRepository: FlowRepository) {
    fun getTodo(from: Int, to: Int) = flow {
        for (i in from..to) {
            emit(flowRepository.todo(i))
        }
    }
}