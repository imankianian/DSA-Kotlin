package Trie

class Trie<Key: Any> {

    private val root = TrieNode<Key>(null, null)
    private val storedLists: MutableSet<List<Key>> = mutableSetOf()
    val lists: List<List<Key>>
        get() = storedLists.toList()
    val count: Int
        get() = storedLists.count()
    val isEmpty: Boolean
        get() = storedLists.isEmpty()

    fun insert(list: List<Key>) {
        var current  = root
        list.forEach { element ->
            val child = current.children[element] ?: TrieNode(element, current)
            current.children[element] = child
            current = child
        }
        current.isTerminating = true
        storedLists.add(list)
    }

    fun contains(list: List<Key>): Boolean {
        var current = root
        list.forEach { element ->
            var child = current.children[element] ?: return false
            current = child
        }
        return current.isTerminating
    }

    fun remove(list: List<Key>) {
        var current = root
        list.forEach { element ->
            var child = current.children[element] ?: return
            current = child
        }
        if (!current.isTerminating) return
        storedLists.remove(list)
        current.isTerminating = false
        var parent = current.parent
        while (parent != null && current.children.isEmpty() && !current.isTerminating) {
            println("looping")
            parent.children.remove(current.key)
            current = parent
            parent = parent.parent
        }
    }

    fun collections(prefix: List<Key>): List<List<Key>> {
        var current = root
        prefix.forEach { element ->
            val child = current.children[element] ?: return emptyList()
            current = child
        }
        return collections(prefix, current)
    }

    private fun collections(prefix: List<Key>, node: TrieNode<Key>?): List<List<Key>> {
        val results = mutableListOf<List<Key>>()
        if (node?.isTerminating == true) results.add(prefix)
        node?.children?.forEach { key, node ->
            results.addAll(collections(prefix + key, node))
        }
        return results
    }
}