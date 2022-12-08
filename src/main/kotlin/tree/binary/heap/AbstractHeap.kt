package tree.binary.heap

import java.util.Collections

abstract class AbstractHeap<T: Any>(): Heap<T> {

    private var elements: ArrayList<T> = ArrayList<T>()

    override val count: Int
        get() = elements.size

    abstract fun compare(a: T, b: T): Int

    override fun peak(): T? = elements.firstOrNull()

    private fun leftChildIndex(index: Int) = 2 * index + 1
    private fun rightChildIndex(index: Int) = 2 * index + 2
    private fun parentIndex(index: Int) = (index - 1) / 2

    override fun insert(element: T) {
        elements.add(element)
        siftUp(count - 1)
    }

    private fun siftUp(index: Int) {
        var child = index
        var parent = parentIndex(child)
        while (child > 0 && compare(elements[child], elements[parent]) > 0) {
            Collections.swap(elements, child, parent)
            child = parent
            parent = parentIndex(child)
        }
    }

    override fun remove(): T? {
        if (isEmpty) return null
        Collections.swap(elements, 0, count - 1)
        val item = elements.removeAt(count - 1)
        siftDown(0)
        return item
    }

    private fun siftDown(index: Int) {
        var parent = index
        while (true) {
            val left = leftChildIndex(parent)
            val right = rightChildIndex(parent)
            var candidate = parent
            if (left < count && compare(elements[left], elements[candidate]) > 0) {
                candidate = left
            }
            if (right < count && compare(elements[right], elements[candidate]) > 0) {
                candidate = right
            }
            if (candidate == parent) return
            Collections.swap(elements, parent, candidate)
            parent = candidate
        }
    }

    override fun remove(index: Int): T? {
        if (index >= count) return null
        return if (index == count - 1) {
            elements.removeAt(index)
        } else {
            Collections.swap(elements, index, count - 1)
            val item = elements.removeAt(count - 1)
            siftUp(index)
            siftDown(index)
            item
        }
    }

    private fun index(element: T, i: Int): Int? {
        if (i > count) return null
        if (compare(element, elements[i]) > 0) return null
        if (element == elements[i]) return i
        val leftChildIndex  = index(element, leftChildIndex(i))
        if (leftChildIndex != null) return leftChildIndex
        val rightChildIndex = index(element, rightChildIndex(i))
        if (rightChildIndex != null) return rightChildIndex
        return null
    }

    protected fun heapify(values: ArrayList<T>) {
        elements = values
        (count / 2 downTo 0).forEach {
            siftDown(it)
        }
    }

    fun getNthSmallestElement(n: Int, elements: ArrayList<Int>): Int? {
        if (n < 0 || elements.isEmpty()) return null
        val heap = ComparableHeapImpl.create(arrayListOf<Int>())
        elements.forEach {
            val maxElement = heap.peak()
            if (heap.count < n) {
                heap.insert(it)
            } else if (maxElement != null && maxElement > it) {
                heap.remove()
                heap.insert(it)
            }
        }
        return heap.peak()
    }

    override fun merge(heap: AbstractHeap<T>) {
        elements.addAll(heap.elements)
        buildHeap()
    }

    private fun buildHeap() {
        if (!elements.isEmpty()) {
            (count / 2 downTo 0).forEach {
                siftDown(it)
            }
        }
    }

    override fun isMinHeap(): Boolean {
        if (isEmpty) return true
        (count / 2 downTo 0).forEach {
            val left = leftChildIndex(it)
            val right = rightChildIndex(it)
            if (left < count && compare(elements[left], elements[it]) < 0) {
                return false
            }
            if (right < count && compare(elements[right], elements[it]) < 0) {
                return false
            }
        }
        return true
    }
}