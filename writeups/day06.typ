== Day 6: Trash Compactor
This was another one I couldn't start on time. I had a lifeguarding shift early in the morning, and it is significantly easier to lifeguard on a full night's sleep, so I regrettably had to wait until my shift ended (in hindsight, I would have gone to sleep at the same time). Since I waited until 12:30pm to start today's problems, I will be subtracting that amount of time from the times I list.

=== Part 1
This was a fairly simple input parsing problem. Since the extra spaces between numbers didn't matter for this part, I used a regex (`\s+`) to split my lines, rather than my usual `" "`. This removed the extra spaces, and I could simply loop over the lists of numbers, applying the specified operation as I went.

Total time: *00:05:30*

=== Part 2
The main goal of this problem was the same, but the difference was the input's "formatting". Numbers to be added or multiplied were listed right to left, vertically. Since addition and multiplication are commutative, I could safely ignore the right-to-left nature of the values.

To parse the columns out of the file, I first figured out where the columns ended. Since a column ends where every line has a space in the same spot, this was fairly trivial with some list manipulation methods:
```kt
val columnEnds = (0 until input[0].lastIndex).filter { colmn ->
    input.all { line ->
        line[colmn] == ' '
    }
}
```
Technically, the lines don't have to be of the same length, but since they'll all have the space in the same spot, this was good enough to find all of the spaces. I prepended 0 to this list, since all of the columns begin at index 0.

Next, I split the list into columns using a loop over adjacent pairs of column start and end and `substring`. This excluded the very last pair, since the end of the last column is not a constant. I had to add that one manually.

Now that I had a list of columns, I had to transpose them into a list of numbers. One issue I ran into here was a shorter initial row lead to the end of a column being excluded in the last column. To solve this, I looped over 0 to the maximum index in all of the column's rows, using the `try`-`catch` trick I used in Day 4 to ignore out of bounds errors.

The rest of my code was essentially the same, except I made use of `reduce` since I had a list of numbers in each column, rather than a list of rows. 

Total time: *00:27:16*