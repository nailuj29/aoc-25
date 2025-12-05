== Day 5: Cafeteria
Another fairly difficult task today! I think with the shortened calendar this year, we're definitely getting a steeper difficulty curve (or my programming skills got rusty after nearly a year of not coding much outside of classwork).

=== Part 1
Despite the difficulty of this problem overall, I found Part 1 pretty easy (Part 2 was where the real challenge lied). The main part of the task was to check if a number was with a range, so I extracted this logic into a helper function expecting to use it in Part 2.

Total time: *00:03:52*

=== Part 2
My first impression on Part 2 was that it looked a lot like `reduce` or `fold`. It did not. Well, at least not the built in `reduce` and `fold` methods. The "reduction" function I'd need to pass to these can either return one element (the ranges were merged successfully), or two (they were not). This isn't possible with the `reduce` or `fold` methods Kotlin provides on `List`s. So I tried to implement it myself. I made a `mergeRanges` function that would return a boolean (whether the ranges were merged) and a range (either (-1,-1) or the actually merged range). At first, it looked like this:
```kt
fun mergeRanges(range1: Pair<Long, Long>, range2: Pair<Long, Long>): Pair<Boolean, Pair<Long, Long>> {
    if (!rangeIncludes(range1, range2.first)) return false to (-1L to -1L)

    return true to (range1.first to range2.second)
}
```
I made a simplifying assumption here. If I sorted the ranges by their first element, I could assume the ranges are mergable if the second ranges start is within the first, and that the merged range had the same start as the first range (still technically correct) and (this is where my assumption was wrong) the merged range's end would be the same as thhat of the second range.

Using this incorrect merge function, I tried 3 different algorithms to solve this, 2 of which worked (I didn't know because of the incorrect function)

First, I tried to implement `reduce` myself, using `map` and `filter` (to remove already-merged ranges, since each range other than the first and last appears in two pairs) over a list of every adjacent pair in the list of ranges, until it didn't change. Honestly, I am glad this didnt work, since the code was genuinely terrible.

My next stab at a solution ended up being an actually functional one. Due to my messed up merge logic, I ended up feeling forced to try one more solution after it, though. By iterating through every pair, I could "clump" as many ranges as I could together, then add their size to the running total. After each iteration, every range used in the clump was marked in a set of used ranges, so they could not be used again. 

My final solution ended up using the fact that due to the list of ranges being sorted in my code, any range that could be merged with another would be adjacent to it. This creates "chains" of ranges that can be merged. Due to these "chains," I can keep merging ranges together until I find one that cannot be merged, then stash that "chain" and start a new one. The code is actually pretty simple, despite how much time it took me to come up with: 
```kt
val mergedRanges = mutableListOf<Pair<Long, Long>>()
var current = ranges.first()
for (i in ranges.drop(1)) {
    val mergeResult = mergeRanges(current, i)
    if (!mergeResult.first) {
        mergedRanges.add(current)
        current = i
    } else {
        current = mergeResult.second
    }
}
mergedRanges.add(current)
```

To think essentially a 17 line solution (counting the merge logic) took me nearly an hour to write... Of course, to get this to work, I needed to fix my `mergeRanges` function. Since _technically_ the assumption I made regarding the condition for merging ranges was still true, I didn't need to change the logic there, but I did anyway, just to be safe. The only line that needed to be changed was the last one:
```kt
return true to (min(range1.first, range2.first) to max(range1.second, range2.second))
```
`range1.first` will always be less than `range2.first`, but I made the changes such that it would work on all pairs, even unsorted ones. With these changes made, I had (two) working solutions. I didn't go too in-depth on my first solution, which isn't in the `main` branch on the github repo, but since I commited with the commented-out version I had while testing, it is not lost to history. If you are curious, you can view it #link("https://github.com/nailuj29/aoc-25/blob/85d819e9c4b239a6a00158e1de7571f33cbb2567/src/Day05.kt#L46-L63")[here].

Total time: *00:59:06*