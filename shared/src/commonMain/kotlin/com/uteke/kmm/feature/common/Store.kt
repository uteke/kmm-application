package com.uteke.kmm.feature.common

import co.touchlab.stately.ensureNeverFrozen
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.qualifier
import kotlin.coroutines.CoroutineContext

abstract class Store<S : State, E: Event, A : Action>(
    initialState: S,
    private val scope: CoroutineScope
) : KoinComponent {
    private val stateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)
    val stateChanges: StateFlow<S>
        get() = stateFlow.asStateFlow()

    private val eventFlow: MutableSharedFlow<E> = MutableSharedFlow()
    val eventChanges: SharedFlow<E>
        get() = eventFlow.asSharedFlow()

    init {
        ensureNeverFrozen()
    }

    fun cancel() {
        scope.cancel()
    }

    abstract fun dispatch(action: A)

    protected fun transform(handle: (S) -> S) {
        val state = handle(stateFlow.value)
        stateFlow.value = state
    }

    protected fun post(event: E) {
        scope.launch { eventFlow.emit(event) }
    }

    protected fun <T> Flow<T>.launch(
        onSuccess: (T) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    ) {
        scope.launch {
            this@launch
                .catch { throwable ->
                    onError?.invoke(throwable)
                }
                .collect { result ->
                    onSuccess(result)
                }
        }
    }

    fun <T> collect(flow: Flow<T>, collector: (data: T) -> Unit) {
        scope.launch {
            flow.collect { collector(it) }
        }
    }
}