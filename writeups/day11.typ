== Day 11: Reactor
Another Dynamic Programming problem, and another quite elegant solution. My favorite problems are always this kind of problem, because I always love the solution's elegance.

=== Part 1
This one was pretty simple: just count the number of ways to reach `out` from `you`. Since there were no cycles in the graph, a simple DFS sufficed:

```kt
fun numPaths(node: Node, allNodes: Map<String, Node>): Long {
    return if (node.children.first() == "out") 1
    else {
        node.children.sumOf { child -> numPaths(allNodes[child]!!, allNodes) }
    }
}
```

Total time: *00:07:07*

=== Part 2
At first, I thought it would be possible to actually figure out what all the paths from `svr` to `out` were, and see which ones contained both `dac` and `fft`. Considering that my final answer was over 300 trillion, and I only have 32GB of RAM, this would not have worked. The cache on the memoization alone would likely need petabytes of RAM. So I looked a little closer at the problem, and noticed the following: 
$
N = n_p("svr", "dac")n_p("dac", "fft")n_p("fft", "out") + n_p("svr", "fft")n_p("fft", "dac")n_p("dac", "out")
$
where $n_p (n_1, n_2)$ represents the number of paths from $n_1$ to $n_2$. Again, since there are no cycles in the graph, each node can only be visited exactly once, meaning the path can easily be broken into subpaths. 

Now that I figured out this representation of the solution, I could modify my solution to Part 1 to take a variable destination, and I'd be done.

We now reach the part where I mention the dumb mistakes I made, solely for your entertainment. To the best of my memory, there are two:
+ When I copied and pasted the code for Part 1, I left a call to Part 1's function as my recursive call, meaning my code was functionally identical to my code for Part 1. This lead to me doubting if this was even a feasible solution, and drawing out a diagram to prove that there were not in fact 8 paths from `svr` to `fft` in the test data.
+ I made the same mistake I did Day 7 (forgetting to clear my cache before running it on the actual data). There seems to be a running theme here with me making the same mistake repeatedly.

Total time: *00:53:26*