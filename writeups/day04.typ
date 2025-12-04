== Day 4
Today was probably the easiest day so far for me. In fact, the hardest part was problably being able to actually work on it. My apartment's power went out about 45 minutes before the problem release, so I had no Wi-Fi and a dead laptop when it came time for the problem to be released. As such, I waited until 4:30pm to solve it, and in the times I list here, will subtract accordingly. (Unfortunately, this means I was fully awake during the time I spent solving this problem, so my code makes significantly more sense and is less interesting as a result).

=== Part 1
This was a rather standard "count the neighbors" task on a grid. I threw together two helper functions, one to check if a cell, and another to count them. Other than that, theres not much interesting about my solution, other than my method of handling out of bounds errors (which is why checking a cell is a separate function):
```kt
fun check(grid: List<String>, coords: Pair<Int, Int>): Boolean {
    try {
        return grid[coords.first][coords.second] == '@'
    } catch (e: Exception) {
        return false
    }
}
```
By wrapping the check in a `try` block, I don't need to check the bounds. If my coordinates are out of bounds, I simply catch the error, and return `false`.

Total time: *00:08:12*

=== Part 2
Part 2 really gave me a benefit from these helper functions. However, I had to make a few changes to my part 1 solution to allow me to adapt my helper functions to a mutable data structure (Since strings are indexable, I typically can represent a grid as a `List<String>` if it doesnt need to be mutable). After the changes, the grid was represented as a `List<List<Boolean>>` where `true` represented a roll being present.

Other than these changes, the rest was simply a loop over the part 1 solution, terminating when no further rolls are removed, and removing a roll (hence the requirement for mutablilty) if it has less than 4 neighbors.

Total time: *00:13:00*