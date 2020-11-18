package ru.otus.otuskotlin.favorites.mp.cor

import ru.otus.otuskotlin.favorites.mp.validators.cor.IExec

open class Handler<T> private constructor(
    private val matcher: CorMather<T> = {true},
    private val handler: CorHandler<T> = {},
    private val onError: CorOnError<T> = { e -> throw e}
) : IExec<T> {

    override suspend fun exec(ctx: T) {
        try {
            if (matcher(ctx)) handler(ctx)
        } catch (e: Throwable) {
            onError(ctx, e)
        }
    }

    @CorDslMarker
    class Builder<T> {
        private var matcher: CorMather<T> = {true}
        private var handler: CorHandler<T> = {}
        private var onError: CorOnError<T> = { e -> throw e}

        fun isApplicable(block: CorMather<T>) {
            matcher = block
        }

        fun exec(block: CorHandler<T>) {
            handler = block;
        }

        fun onError(block: CorOnError<T>) {
            onError = block
        }

        fun build() = Handler<T> (
            matcher = matcher,
            handler = handler,
            onError = onError
        )
    }
}

