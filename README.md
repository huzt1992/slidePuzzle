# Slide Puzzle Game
The 8-puzzle is a sliding puzzle that is played on a 3-by-3 grid with 8 square tiles labeled 1 through 8, plus a blank square. The goal is to rearrange the tiles so that they are in row-major order, using
as few moves as possible. In this program, user can provide any puzzle, the computer will provide step by step solution

(*This java project is based on a programming assignment by Princeton Algorithm public course.)

## Java Project
There are four java classes for this project:
* `GameControl.java`
  * This is the main file to run the game
* `SolvePuzzle.java`
  * This data type implement A* search to solve n-by-n slider puzzles.
* `Puzzle.java`
  * This data type models an n-by-n board with sliding tiles.
* `PriorityQueue`
  * This data type is customized priority queue algorithm for puzzle solution.

## Browse Online
The main file is `GameControl.java`.

## Setup
* You must also be able to work with GitHub repositories.
* Clone repository
```
https://github.com/huzt1992/slidePuzzle.git
```

## Eclipse Instructions
* Prerequisites:
    * Install the latest version of Java and Eclipse
* Run
  * Right click on `GameControl.java`
  * Run As > `Java Application`
  * Follow the instruction in console, and play the game
