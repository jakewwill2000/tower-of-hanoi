# Tower of Hanoi

This repository contains a single Java file with my recursive solution to the Tower of Hanoi problem. It takes in an arbitrary number of positive integer command line arguments, which represent the sizes of the disks placed onto the first peg. Then, by performing only legal moves for this problem (which can be found at https://en.wikipedia.org/wiki/Tower_of_Hanoi), it transfers every one of these disks onto the second peg. After each move, this program prints out the state of the problem.

## Example Run

Running
```
./program2 5 4 3 2 1
```
at the command line will first print out

```
A: 5 4 3 2 1
B:
C:
```
and eventually wind up at
```
A:
B: 5 4 3 2 1
C:
```
