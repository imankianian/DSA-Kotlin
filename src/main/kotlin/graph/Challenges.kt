package graph

fun <T: Any> Graph<T>.numberOfPaths(source: Vertex<T>, destination: Vertex<T>): Int {
    val numberOfPaths = Ref(0)
    val visited = mutableSetOf<Vertex<T>>()
    paths(source, destination, visited, numberOfPaths)
    return numberOfPaths.value
}

private fun <T: Any> Graph<T>.paths(source: Vertex<T>,
                   destination: Vertex<T>,
                   visited: MutableSet<Vertex<T>>,
                   pathCount: Ref<Int>) {
    visited.add(source)
    if (source == destination) {
        pathCount.value++
    } else {
        val neighbors = edges(source)
        neighbors.forEach { edge ->
            if (edge.destination !in visited) {
                paths(edge.destination, destination, visited, pathCount)
            }
        }
    }
    visited.remove(source)
}

data class Ref<T: Any>(var value: T)