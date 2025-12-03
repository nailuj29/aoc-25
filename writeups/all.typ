#set page(paper: "us-letter")
#show raw.line: it => [
    #if it.count > 1 {
        [(#it.number)]
    } #it
]

= Advent of Code 2025
#outline(title: [Jump to problem])

#pagebreak()

#include "day01.typ"

#pagebreak()

#include "day02.typ"

#pagebreak()

#include "day03.typ"