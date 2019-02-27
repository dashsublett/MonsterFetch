package com.inmotionsoftware.promise

import android.os.Handler
import android.os.Looper
import com.inmotionsoftware.promise.Promise
import java.util.concurrent.*

//
//  Copyright © 2019 InMotion Software, LLC. All rights reserved.
//

enum class PromiseDispatch {

    MAIN, BACKGROUND;

    companion object {
        private val main: Executor by lazy {Executor{ command -> Handler(Looper.getMainLooper()).post(command) }}
        private val background: Executor by lazy {Executors.newCachedThreadPool()}
    }

    val executor: Executor get() {
        return when (this) {
            MAIN -> PromiseDispatch.main
            BACKGROUND -> PromiseDispatch.background
        }
    }
}

fun <T,OUT> Promise<OUT>.thenp(on: PromiseDispatch? = null, execute: (OUT) -> Promise<T>): Promise<T> = this.thenp(on=(on?: PromiseDispatch.MAIN).executor, execute=execute)
fun <OUT> Promise<OUT>.recoverp(on: PromiseDispatch? = null, execute: (Throwable) -> Promise<OUT>): Promise<OUT> = this.recoverp(on=(on?: PromiseDispatch.MAIN).executor, execute=execute)
fun <T,OUT> Promise<OUT>.then(on: PromiseDispatch? = null, execute: (OUT) -> T): Promise<T> = this.then(on=(on?: PromiseDispatch.MAIN).executor, execute=execute)
fun <OUT> Promise<OUT>.recover(on: PromiseDispatch? = null, execute: (Throwable) -> OUT): Promise<OUT> = this.recover(on=(on?: PromiseDispatch.MAIN).executor, execute=execute)
fun <OUT> Promise<OUT>.catch(on: PromiseDispatch? = null, execute: (Throwable) -> Unit): Promise<OUT> = this.catch(on=(on?: PromiseDispatch.MAIN).executor, execute=execute)
fun<OUT> Promise<OUT>.always(on: PromiseDispatch? = null, execute: () -> Unit ): Promise<OUT> = this.always(on=(on?: PromiseDispatch.MAIN).executor, execute=execute)

