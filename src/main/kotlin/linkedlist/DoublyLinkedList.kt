package linkedlist

import linkedlist.node.DoublyNode

class DoublyLinkedList<T: Any>: MutableIterable<T>, MutableCollection<T> {

    private var head: DoublyNode<T>? = null
    private var tail: DoublyNode<T>? = null
    override var size = 0
        private set

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            append(element)
        }
        return true
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            if (!contains(element)) return false
        }
        return true
    }

    override fun contains(element: T): Boolean {
        for (item in this) {
            if (item == element) return true
        }
        return  false
    }

    override fun isEmpty() = size == 0

    fun push(value: T) {
        val newNode = DoublyNode(value = value, next = head)
        head?.prev = newNode
        head = newNode
        if (tail == null) tail = head
        size++
    }

    fun append(value: T) {
        if (isEmpty()) {
            push(value)
            return
        }
        val newNode = DoublyNode(prev = tail, value = value)
        tail!!.next = newNode
        tail = newNode
        size++
    }

    fun insert(value: T, afterNode: DoublyNode<T>): DoublyNode<T> {
        val newNode = DoublyNode(afterNode, value, afterNode.next)
        afterNode.next?.prev = newNode
        afterNode.next = newNode
        if (newNode.next == null) tail = newNode
        size++
        return newNode
    }

    fun pop(): T? {
        if (isEmpty()) return null
        val node = head
        head = head?.next
        head?.prev = null
        node?.next = null
        if (head == null) tail = null
        size--
        return node!!.value
    }

    fun removeAfter(afterNode: DoublyNode<T>): T? {
        val node = afterNode.next
        afterNode.next = afterNode.next?.next
        afterNode.next?.next?.prev = afterNode
        if (node?.next == null) tail = afterNode
        node?.next = null
        node?.prev = null
        size--
        return node?.value
    }

    fun removeLast(): T? {
        val node = tail
        tail = node?.prev
        if (tail == null) {
            head = null
        } else {
            tail?.next = null
            node?.prev = null
        }
        size--
        return node?.value
    }

    fun nodeAt(index: Int): DoublyNode<T>? {
        require(index >= 0)
        if (index >= size) throw IndexOutOfBoundsException()
        return when (index) {
            0 -> head
            size - 1 -> tail
            else -> {
                var count = 0
                var node = head
                while (count < index) {
                    node = node?.next
                    count++
                }
                return node
            }
        }
    }

    override fun iterator(): MutableIterator<T> {
        return DoublyLinkedListIterator<T>(this)
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return result
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (element in elements) {
            result = remove(element) || result
        }
        return result
    }

    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while (iterator.hasNext()) {
            if (iterator.next() == element) {
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        return if (isEmpty()) {
            "Empty List"
        } else {
            head.toString()
        }
    }
}

class DoublyLinkedListIterator<T: Any>(private val linkedList: DoublyLinkedList<T>): MutableIterator<T> {

    private var index = 0
    private var lastNode: DoublyNode<T>? = null

    override fun hasNext(): Boolean {
        return index < linkedList.size
    }

    override fun next(): T {
        if (index >= linkedList.size) throw IndexOutOfBoundsException()
        lastNode = if (index == 0) linkedList.nodeAt(0) else lastNode?.next
        index++
        return lastNode!!.value
    }

    override fun remove() {
        if (index == 1) {
            linkedList.pop()
        } else {
            val prevNode = linkedList.nodeAt(index - 2) ?: return
            linkedList.removeAfter(prevNode)
            lastNode = prevNode
        }
        index--
    }
}