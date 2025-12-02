== Day 2: Gift Shop
Overall today was way easier than day 1 in my opinion. Most of my pitfalls were due more to me misreading the problem than anything else.

=== Part 1
My initial solution to this completely ignored everything except the first and last in the range. For some reason, I completely missed that the input was a range and not two unrelated numbers. This wasn't that large a change however, since I was already using `flatMap` to parse the input (just returning a list of the first and second numbers in the list).

To actually process the numbers, I initially used Kotlin's `take` and `drop` methods, roughly as follows:
```kt
val first = id.take(id.length / 2)
val second = id.drop(id.length / 2)

if (first == second) count += id.toInt()
```

This worked for Part 1, but would later be changed after I introduced a helper function in Part 2.

Total time: *00:05:49*

=== Part 2
For this part, I quickly realized a helper function to "chunkify" a string and check the uniqueness of these "chunks" would be helpful, allowing me to refactor Part 1 as well. Luckily, Kotlin provides a `String.chunk` method that does just that. To check for a repeating pattern, I simply turned the chunks into a set, and checked if its length was 1, indicating there was only 1 unique "chunk":
```kt
fun checkRepeats(string: String, num: Int): Boolean {
    val chunks = string.chunked(num)

    return chunks.toSet().size == 1
}
```
After writing this function, all I needed to change from my part 1 code was loop from 1 to half the length of each id (any further and there was no possibility for a repeating pattern), checking for a repeating pattern iff the current size evenly divides the id's size (another minor optimization, albeit important for size=1 on part 1, where ids such as `111` would be mistakenly verified since `1`, the first "half" of the string, would cause the remainder to be split into thirds instead of halves).

Total time: *00:14:32*