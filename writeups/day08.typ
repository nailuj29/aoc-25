== Day 8: Playground
Of the 16 solutions I've written so far this year(including today), 8 of them have required me to use 64-bit integers to store my solution. You'd think I'd have learned by now, just to use them for all of my solutions. No. I have not. And it lead to me spending way more time than necessary on Part 2 today.

=== Part 1
The main challenge for part 1 was figuring out how to best represent the current circuits. Since each singleton junction box formed its own circuit, I chose to represent the current circuits as a list of sets of `Point3`s (a custom class I made consisting of 3 `Ints`). Then, I looped over the smallest loops either 10 or 1000 times, depending on if I'm running the test or real input. After finding a pair, I merged the two sets together, removing one from the list:
```kt
val circuitA = circuits.first { shortestPair.first in it }
val circuitB = circuits.first { shortestPair.second in it }

if (circuitA == circuitB) continue

circuits.remove(circuitB)
circuitA.addAll(circuitB)
```
The check to make sure the found circuits are not the same was technically not necessary, and wasa removed in the code for Part 2.

Total time: *00:24:31*

=== Part 2
My first idea for this, as it is on many problems, was to brute-force it. I threw my code from part 1 in a loop until there was one circuit remaining, and stored which pair was connected last. This worked on the test input, but not on my real input. So, I ended up debugging, and overall making my code from Part 1 significantly better hoping I'd fix the bug. I did not. When I grab my inputs, I tend not to look too deeply at them. Had I done so, I would have quickly realized that multiplying two of the x-positions in the input would very likely result in an integer overflow if I was using a 32-bit integer. Yet again, I defaulted to 32-bit integers, and spent way too much time debugging working code, that happened to be experiencing an integer overflow. I changed my template code to use `Long`s as soon as I finished today's problems.

Total time: *00:47:27*