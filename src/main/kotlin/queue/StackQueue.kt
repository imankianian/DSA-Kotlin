package queue

import stack.StackImpl

class StackQueue<T: Any>: Queue<T> {

    private val leftStack = StackImpl<T>()
    private val rightStack = StackImpl<T>()

    override val isEmpty: Boolean
        get() = leftStack.isEmpty && rightStack.isEmpty

    override val count: Int
        get() = leftStack.count + rightStack.count

    private fun transferElements() {
        var nextElement = rightStack.pop()
        while (nextElement != null) {
            leftStack.push(nextElement)
            nextElement = rightStack.pop()
        }
    }

    override fun peek():T? {
        if (leftStack.isEmpty) transferElements()
        return leftStack.peek()
    }

    override fun enqueue(element: T): Boolean {
        rightStack.push(element)
        return true
    }

    override fun dequeue(): T? {
        if (leftStack.isEmpty) transferElements()
        return leftStack.pop()
    }

    override fun toString(): String {
        return "left stack: \n$leftStack right stack:\n$rightStack"
    }
}