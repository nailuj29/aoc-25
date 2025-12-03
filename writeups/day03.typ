== Day 3: Lobby
This one was a _doozy_. Well at least Part 2 was. Part 1 was genuinely the fastest I've ever solved an AoC problem. It came as quite the shock when I spent close to 20x the time working on Part 2 as I did part 1.

=== Part 1
For Part 1, I created a simple helper function to brute-force all combinations of 2 digits. 
Simple, and allowed some Kotlin higher-order function magic to make my primary function (the one that actually processes the inputs) incredibly concise.

=== Part 2
Part 2 seemed like quite a simple change, but this was one of those problems where a simple change completely eliminates the ability to brute-force (to think its only day 3...). 
Were I to write a brute-force solution for this problem, I'd need to process roughly 1,050,420,000,000,000 possible combinations, or $mat(100; 12)$ on the "real-deal" input. 
This was obviously not ideal.

Roughly in order, I tried these possible solutions:

+ A recursive solution (very briefly entertained this idea, this would go exactly nowhere)
+ Running my solution for part 1, excluding the two digits it selects, and repeating until 12 digits are chosen
+ Removing the smallest digits until 12 remain

It was at this point I began to grow hopeless and ask for help in a programming Discord server I frequent. 
Most of the help I recieved today came in the form of rubber duck debugging. 
While talking over my solution, I realized that if I could identify the first digit, I could likely apply my previous 
(at the time I believed this was the closest) solution, while forcing that digit to be the first.

Finding the first digit was easy. I'll talk more about the code for that shortly, since this _did_ end up being a part of my final solution. 
This worked for the test input, but on the "real-deal" input, did not give the correct answer. 

This solution led me to try what would become my final solution. If getting the first digit was so easy, why not just apply that same algorithm 12 times, shortening the input size each time? 
I tried to implement this, and for some reason, the code would get itself "stuck" between a smaller number of comparatively large digits, leading to outputs with not nearly enough digits.

Obviously, if the code isn't outputting enough digits to satisfy the problem description, that's an issue with the entire algorithm, and definitely not a bug in the code. 
I told myself this, and proceeded to bang my head against the "first digit then remove smallest digits" algorithm until I got sick and tired of it.

Finally, I decided to actually revisit the solution I tried earlier. 
This time, I ensured I wouldn't take from the end too early, forcing myself into a smaller amount of text. 
Truthfully, I have no clue how my method didn't work the first time I tried it. I already handled the logic to ensure enough digits remained when I first implemented the first digit. 
My final solution ended up making heavy use of Kotlin's `drop` family of methods to limit the "window" I could search for a first digit in, which was where the majority of the refinement for this algorithm took place.

#pagebreak()

```kt
var currentFirst = -1
val max = StringBuilder()
for (i in 1 .. 12) {
    val digit = line.drop(currentFirst + 1).dropLast(12 - i).max()
    currentFirst = line.indexOf(digit, currentFirst + 1)

    max.append(digit)
}
```

The first call to `drop` removed the digits that were already "considered" and prevent them from being duplicated. 
The `dropLast` call ensures the last $n$ digits remain, where $n$ is the number of digits that still to be added to the string. 
Other than those calls, the rest is fairly straightforward, and after the loop exits, 
`max` contains a string representation of the biggest 12-digit number possible to make with the digits provided, solving the problem. 
Maybe next time I'll actually ensure something is implemented correctly before giving up on it.