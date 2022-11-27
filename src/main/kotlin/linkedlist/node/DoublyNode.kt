package linkedlist.node

data class DoublyNode<T: Any>(
    var prev: DoublyNode<T>? = null,
    var value: T,
    var next: DoublyNode<T>? = null
) {
    override fun toString(): String {
        return if (next != null) {
            "$value <-> ${next.toString()}"
        } else {
            "$value"
        }
    }
}
