== Day 7: Laboratories
Yet another day I couldn't start on time, due to the same reason as yesterday. I started at the same time as I did yesterday (12:30pm), so I subtracted from my times as I did yesterday.

=== Part 1
A simple simulation was enough to solve this one. By maintaining a set of all positions currently occupied by a beam, I could guarantee overlapping beams only counted for one beam. Each time I reached a splitter, I incremented a counter by one.

Total time: *00:03:56*

== Part 2
This is my 5th year doing Advent of Code. Every year so far, theres been a similar problem to this one, which seems easy at first glance, but very quickly runs into massive memory issues due to the sheer size of what you're asked to simulate. For all of this style of problem, there's two main methods I've used to solve it. The first, using an alternate data structure to represent the current simulation state, takes a bit more thinking to craft a form of representing the solution. For this reason, I chose to go with a recursive solution, with a bit of memoization to save myself from evaluating what turned out to be on the order of $10^14$ realities

My solution was fairly simple, and very likely would have worked first try, were it not for some (rather stupid) bugs. I created a recursive function, which took in the rows of the manifold yet to be evaluated, and the current x-position of a beam. Upon reaching a splitter, I call the function once (at the same level) for each new beam created, and sum their results. When there are no rows remaining, I return 1.

Since the actual rows are constant, I could represent my function's cache's keys as a `Pair<Int, Int>`. This pair represents the beam's exact position on the grid, and is all that is needed to uniquely identify the result of a call to my recursive solution.

Now for the part where I talk about the stupid mistakes I made.
+ I used an `Int` instead of a `Long`. I don't know how I haven't learned this lesson by now, but when massive amounts of simulation are involved, the answer is very likely to exceed the 32-bit integer limit. Since the answer my solution gave me was a positive number, I saw no issue with it, submitted it, and proceeded to immediately realize what I did wrong when I was told my answer was too low.
+ This issue took me far longer to rectify. My answer was too low, and no matter what I did, I couldn't make it change, since even when I closely inspected my function, I couldn't find anything wrong with it. The issue was that I was not clearing the cache after executing the test case. Since the cache's keys were not based on the input, this lead to my solution falsely identifying parts of the test case to be relevant to the actual solution.

The issue of previous cache data being used was fixed with one line: `cache.clear()`. Had I not made these stupid mistakes, I would probably have solved this problem first try, since neither of them required any change to my actual solution, only how I was using it.

Total time: *00:22:04*