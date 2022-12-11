package sorting

import java.lang.Math.pow
import java.text.FieldPosition
import kotlin.math.pow

fun MutableList<Int>.radixSort() {
    val base = 10
    var done = false
    var digits = 1
    while (!done) {
        done = true
        val buckets = MutableList<MutableList<Int>>(base) { mutableListOf() }
        this.forEach { number ->
            val remainingPart = number / digits
            val digit = remainingPart % base
            buckets[digit].add(number)
            if (remainingPart > 0) done = false
        }
        digits *= base
        this.clear()
        this.addAll(buckets.flatten())
    }
}

private fun Int.digits(): Int {
    var count = 0
    var num = this
    while (num != 0) {
        count++
        num /= 10
    }
    return count
}

private fun Int.digit(atPosition: Int): Int? {
    val correctedPosition = (atPosition + 1).toDouble()
    if (correctedPosition > digits()) return null
    var num = this
    while (num / 10.0.pow(correctedPosition).toInt() != 0) {
            num /= 10
    }
    return num % 10
}

private fun List<Int>.maxDigits(): Int {
    return this.maxOrNull()?.digits() ?: 0
}

private fun msdRadixSorted(list: MutableList<Int>, position: Int): MutableList<Int> {
    if (position > list.maxDigits()) return list
    val buckets = MutableList<MutableList<Int>>(10) { mutableListOf() }
    val priorityBucket = arrayListOf<Int>()
    list.forEach { number ->
        val digit = number.digit(position)
        if (digit == null) {
            priorityBucket.add(number)
            return@forEach
        }
        buckets[digit].add(number)
    }
    val newValues = buckets.reduce { result, bucket ->
        if (bucket.isEmpty()) return@reduce result
        result.addAll(msdRadixSorted(bucket, position + 1))
        result
    }
    priorityBucket.addAll(newValues)
    return priorityBucket
}

fun MutableList<Int>.lexicographicalSort() {
    val newList = msdRadixSorted(this, 0)
    this.clear()
    this.addAll(newList)
}