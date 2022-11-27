package linkedlist

import linkedlist.node.Node

class LinkedList<T: Any>: MutableIterable<T>, MutableCollection<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override var size = 0
        private set

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
        return false
    }

    override fun isEmpty() = size == 0

    fun removeAfter(node: Node<T>): T? {
        if (isEmpty() || node == tail) return null
        val result = node.next?.value
        node.next = node.next?.next
        if (node.next == null) tail = node
        size--
        return result
    }

    fun removeLast():T? {
        if (isEmpty()) return null
        var node = head
        if (head == tail) {
            val result = head!!.value
            head = head!!.next
            tail = head
            size--
            return result
        }
        while (node!!.next != tail) {
            node = node.next
        }
        val result = node!!.next!!.value
        node!!.next = null
        tail = node
        size--
        return result
    }

    fun pop(): T? {
        if (isEmpty()) return null
        val result = head!!.value
        head = head?.next
        size--
        if (isEmpty()) tail = null
        return result
    }

    fun insert(value: T, afterNode: Node<T>): Node<T> {
        if (afterNode == tail) {
            append(value)
            return tail!!
        }
        val newNode = Node(value, next = afterNode.next)
        afterNode.next = newNode
        size++
        return newNode
    }

    fun nodeAt(index: Int): Node<T>? {
        require(index >= 0)
        if (isEmpty() || index >= size) return null
        var node = head
        var counter = 0
        while (counter < index) {
            node = node!!.next
            counter++
        }
        return node
    }

    fun append(value: T) {
        if (isEmpty()) {
            push(value)
            return
        }
        val newNode = Node(value)
        tail!!.next = newNode
        tail = newNode
        size++
    }

    fun push(value: T) {
        head = Node(value, head)
        if (tail == null) {
            tail = head
        }
        size++
    }

    override fun iterator(): MutableIterator<T> {
        return LinkedListIterator(this)
    }

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
        for (item in elements) {
            result = remove(item) || result
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

class LinkedListIterator<T: Any>(private val linkedList: LinkedList<T>): MutableIterator<T> {

    private var index = 0
    private var lastNode: Node<T>? = null

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