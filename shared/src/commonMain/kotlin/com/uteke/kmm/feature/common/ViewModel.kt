package com.uteke.kmm.feature.common

import co.touchlab.stately.ensureNeverFrozen
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

abstract class ViewModel<S : State, E: Event, A : Action>(
    initialState: S,
    private val schedulerProvider: SchedulerProvider
) : KoinComponent, CoroutineScope {
    private val job: CompletableJob = SupervisorJob()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable)
    }

    init {
        ensureNeverFrozen()
    }

    override val coroutineContext: CoroutineContext
        get() = job + schedulerProvider.ui() + exceptionHandler

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
        job.cancel()
    }

    abstract fun handle(action: A)

    protected fun transform(handle: (S) -> S) {
        val state = handle(stateFlow.value)
        stateFlow.value = state
    }

    protected fun post(event: E) {
        launch { eventFlow.emit(event) }
    }

    protected fun <T> Flow<T>.launch(
        onSuccess: (T) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    ) {
        launch {
            flowOn(schedulerProvider.io())
                .catch { throwable ->
                    onError?.invoke(throwable)
                }
                .collect { result ->
                    onSuccess(result)
                }
        }
    }

    fun <T> collect(flow: Flow<T>, collector: (data: T) -> Unit) {
        launch {
            flow.collect { collector(it) }
        }
    }
}