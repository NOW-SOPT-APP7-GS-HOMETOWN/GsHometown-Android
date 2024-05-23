package com.sopt.now.gs.feature.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

inline fun View.setOnSingleClickListener(
    delay: Long = 1000L,
    crossinline block: (View) -> Unit,
) {
    var previousClickedTime = 0L
    setOnClickListener { view ->
        val clickedTime = System.currentTimeMillis()
        if (clickedTime - previousClickedTime >= delay) {
            block(view)
            previousClickedTime = clickedTime
        }
    }
}

fun View.setOnDebounceClick(): Flow<Unit> = callbackFlow {
    setOnClickListener {
        trySend(Unit).isSuccess
    }
    awaitClose { setOnClickListener(null) }
}

fun View.clicks(): Flow<Unit> = callbackFlow {
    setOnClickListener {
        trySend(Unit).isSuccess
    }
    awaitClose { setOnClickListener(null) }
}

@OptIn(FlowPreview::class)
fun View.setOnDebouncedClickListener(
    lifecycleOwner: LifecycleOwner,
    debounceTime: Long = 1000L,
    onClick: (View) -> Unit,
) {
    val lifecycle = when (lifecycleOwner) {
        is Fragment -> lifecycleOwner.viewLifecycleOwner.lifecycle
        else -> lifecycleOwner.lifecycle
    }
    val lifecycleScope = when (lifecycleOwner) {
        is Fragment -> lifecycleOwner.viewLifecycleOwner.lifecycleScope
        else -> lifecycleOwner.lifecycleScope
    }

    lifecycleOwner.lifecycleScope.launch {
        this@setOnDebouncedClickListener.clicks()
            .debounce(debounceTime)
            .flowWithLifecycle(lifecycle)
            .onEach { onClick(this@setOnDebouncedClickListener) }
            .launchIn(lifecycleScope)
    }
}
