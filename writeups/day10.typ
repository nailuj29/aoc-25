== Day 10: Factory
We've now reached the point where I have no clue how to approach part 2 of the problem! I'll eventually come back and finish this day, but I have finals this week, and really need to get some schoolwork done.

=== Part 1
I initially tried to solve this using a DFS, with some (in hindsight, useless) caching. Unfortunately, I had trouble getting it to reliably hit the base case, so I reread the problem desctiption a few times to try to find anything I could use. I noticed the following:
- The order of button presses doesn't matter
- Pressing a button 2 times is the same as not pressing it at all
- Pressing a button 2 times is the same as pressing it once (2 presses + 1 press)

Using this information, I was able to make the realization that the solution is the size of the smallest subset of buttons that makes the light pattern. I threw together a function to find all subsets of a certain size, and from there was able to quickly find the solution after coming to this realization:
```kt
fun <T> subsets(set: Set<T>, num: Int): Set<Set<T>> {
    if (num < 1) return emptySet()
    if (num == 1) return set.map { setOf(it) }.toSet()

    val subsetsNMinus1 = subsets(set, num - 1)
    return set.flatMap { item ->
        val subsetsWithoutItem = subsetsNMinus1.filter { item !in it }
        subsetsWithoutItem.map { it + item }
    }.toSet()
}
```
Once a list of subsets of order $n$ has been exhausted, my algorithm moves on to subsets of order $n+1$, returning the current $n$ as soon as it finds a subset that works.

=== Part 2
I was unable to solve this using a DFS (adapted from my first stab at Part 1). It ran perfectly on the test data, so I _assume_ it would run on the main data given enough time, but it makes my laptop sound like a jet engine, so it's probably less than ideal. I also though about using A\*, but this would likely take forever as well, as it would be pathing through a 13-dimensional space at times.

The likely solution is to use some form of linear algebra, which I would need an external library. When I get home for winter break, I will make an attempt at that and update this document with my findings.