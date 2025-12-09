== Day 9: Movie Theater
This one felt way harder than it was. This is because for what feels like the 100#super[th] time, I implemented the correct solution rather fast today, only for a bug in my calculation of the answer to lead to my answer actually being incorrect (it wasn't the wrong integer type this time).

=== Part 1
Part 1 was pretty easy, to the point there's not even much to talk about. I simply looped through all possible rectangles, returning the area of the largest one. (It's Day 9, shouldn't I be a little concerned at how easy Part 1 was?)

Total time: *00:03:19*

=== Part 2
Yes, I should have been concerned about how easy Part 1 was. Every year, Advent of Code has a problem that has something to do with processing a polygon. And every year, I struggle with it immensely. This year was no different. Well, other than a fairly cheap trick I used. At first, I tried looping through every point in every rectangle, stopping immediately if I found a point outside the polygon. This took forever, and also returned the wrong answer. I tried debugging it for a while, but couldn't find much wrong with it. In retrospect, it was likely the shortcut I took of only storing the "edges" of the polygon, and just checking how many edges were on each side of a point (even number of edges #sym.arrow.r.double outside the polygon, odd number of edges #sym.arrow.r.double inside the polygon). There was likely a bug in this logic somewhere.

After debugging this solution for a while, I remembered something: I have access to the entire Java standard library in Kotlin. Included in that standard library is an interface `java.awt.Shape` which contains a `.contains` method which allows a Rectangle as an argument. Exactly what I needed! Most of the `Shape` classes are allowed to take a shortcut and return `false` if they determine the calculation for contaimnent would be too expensive, except `Area`. So I chose to use it to represent my polygon. I plugged it into my check, ran my code, and... it was too low. Lovely. I spent probably close to an hour debugging this solution, also trying to check if the edges were completely filled in, since this would mean the shape is completely filled in as well. Nothing worked.

At this moment, I remembered I had a final during the day, and I should probably try to be well rested for it. So I went to sleep. When I woke up, I came to a realization: I'm likely not solving this one. I have always struggled with the polygon-based problems, and this one was no exception. So, I decided to look for some clues on the Advent of Code Subreddit, hoping to at least learn something from this. Learn something I did. Turns out, adding one to a negative number decreases its absolute value. I had the following line to calculate the area:
```kt
val area = abs(tile_i[0] - tile_j[0] + 1) * abs(tile_i[1] - tile_j[1] + 1)
```
If a component of `tile_j` is greater than its counterpart in `tile_i`, my resulting length/width will be 2 less than it should be, since I was adding one inside the absolute value. My code using `java.awt.Area` was in fact, perfectly correct. All that I was missing was actually calculating the correct area of the rectangle I found.

Total time (including sleeping and atttending a lecture): *10:48:27*