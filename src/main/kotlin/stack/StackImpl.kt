package stack

class StackImpl<T: Any>: Stack<T> {

    private val storage = ArrayList<T>()

    override val count: Int
        get() = storage.size

    override fun peek(): T? {
        return storage.lastOrNull()
    }

    override fun push(element: T) {
        storage.add(element)
    }

    override fun pop(): T? {
        if (isEmpty) return null
        return storage.removeAt(count - 1)
    }

    override fun toString() = buildString {
        appendLine("----top----")
        storage.asReversed().forEach {
            appendLine("$it")
        }
        appendLine("-----------")
    }

    companion object {
        fun <T: Any> create(items: Iterable<T>): Stack<T> {
            val stack = StackImpl<T>()
            for (item in items) {
                stack.push(item)
            }
            return stack
        }
    }
}

fun <T: Any> stackOf(vararg elements: T): Stack<T> {
    return StackImpl.create(elements.asList())
}