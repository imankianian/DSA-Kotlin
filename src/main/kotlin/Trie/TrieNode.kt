package Trie

class TrieNode<Key: Any> (var key: Key?, var parent: TrieNode<Key>?) {

    val children: HashMap<Key, TrieNode<Key>> = HashMap()
    var isTerminating = false
}