# Sudoku-Backtracking-basic

Written as an illustration of **Backtracking** for the lecture _Algorithmen und Datenstrukturen_ at TU Berlin.

The goal of this implementation is to demonstrate, that a Sudoku solver can be written easily with a
backtacking approach reaching a reasonable performance. However, there are many aspects in which the
efficiency can be increased drastically (e.g., using an IndexPriorityQueue instead of a PriorityQueue
in combination with updating the candidate lists instead of populating them from scratch after each 
move / withdraw move).

CC-BY-SA 2023 Benjamin Blankertz