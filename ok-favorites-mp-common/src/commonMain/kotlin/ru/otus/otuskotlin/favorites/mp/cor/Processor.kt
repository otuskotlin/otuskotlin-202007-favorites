package ru.otus.otuskotlin.favorites.mp.cor

import ru.otus.otuskotlin.favorites.mp.validators.cor.IExec

class Processor<T> private constructor(
    private val matcher: CorMather<T> = {true},
    private val handlers: Collection<IExec<T>>,
    private val onError: CorOnError<T> = { e -> throw e}
) : IExec<T> {
    override suspend fun exec(ctx: T) {
        try {
            if(matcher(ctx)) {
                handlers.forEach {
                    it.exec(ctx)
                }
            }
        } catch (e: Throwable) {
            onError(ctx, e)
        }
    }

    @CorDslMarker
    class Builder<T> {
        private var matcher: CorMather<T> = {true}
        private var handlers: MutableList<IExec<T>> = mutableListOf()
        private var onError: CorOnError<T> = { e -> throw e}

        fun isApplicable(block: CorMather<T>) {
            matcher = block
        }

        fun handler(block: Handler.Builder<T>.() -> Unit) = handlers.add(corHandler(block))

        fun onError(block: CorOnError<T>) {
            onError = block
        }

        fun exec(block: IExec<T>) = handlers.add(block)

        fun exec(block: CorHandler<T>) {
            handlers.add(
                corHandler { exec(block) }
            )
        }

        fun processor(block: Builder<T>.() -> Unit) = handlers.add(cor(block))

        fun build() = Processor<T>(
            matcher = matcher,
            handlers = handlers,
            onError = onError
        )
    }
}
