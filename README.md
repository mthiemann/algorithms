# Algorithms and Data Structures
A collection of personally programmed data structures and algorithms for quick lookup and reuse programmed in Java. Later, C++ solutions might be added.
Implementations can be found in the `src` directory and corresponding tests in the `test` directory.
For some algorithms no test cases are provided because these solutions already ran through test cases on websites such as Hackerrank or Hackerearth.


## Data Structures
From scratch implementations of common data structures in Java including complexity for operations.
<br>

Data Structure | Implementation | Test | Add | Access | Delete | Optimized For
--- | --- | --- | --- | --- | --- | ---
HashMap | [Java](src/DataStructures/Map) | [Java](tests/DataStructures/Map/HashMapTest.java) | O(N) | O(N) | O(N) | O(1) access of a specified object
Heap | [Java](src/DataStructures/Heap) | [Java](tests/DataStructures/Heap/ArrayHeapTest.java) | O(log N) | O(N) | O(N) | O(1) Peek <br> max (Max Heap) <br> min (Min Heap)
Stack | [Java](src/DataStructures/Stack) | [Java](tests/DataStructures/Stack/StackTest.java) | O(1) | O(N) | O(N) | Get last inserted O(1)
Queue | [Java](src/DataStructures/Queue) | [Java](tests/DataStructures/Queue/DequeTest.java) | O(1) | O(N) | O(N) | Get first inserted O(1) <br> Deque: Get last inserted O(1)
BST | [Java](src/DataStructures/Trees/BST.java) | [Java](tests/DataStructures/Trees/BSTTest.java) | O(N) | O(N) | O(N) | Dynamic insertion and fast retrieval


## Algorithms

### Recursion & Backtracking
- [NQueens](src/Algorithms/RecursionAndBacktracking/NQueens.java)

### Sorting
Ranking | Algorithm | Implementation | Time Complexity <br> Formal <br> Detailed | Space Complexity <br> Formal <br> Detailed | Stability
--- | --- | --- | --- | --- | --- 
1 | Quick Sort | [Java](src/Algorithms/Sorting/QuickSort.java) | O(N<sup>2</sup>) <br><br> O(N log N) <br> on average if pivot element is picked at random | O(N) <br><br> O(N) + O(log N) (on average) <br> O(N) + O(N-1) (worst case) | Unstable
2 | Merge Sort | [Java](src/Algorithms/Sorting/MergeSort.java) | O(N log N) | O(N) <br><br> O(2N) + O(log N) | Stable
3 | Heap Sort | [Java](src/Algorithms/Sorting/HeapSort.java) | O(N log N) <br><br> O(N log N) + O(N) <br> Build Max Heap <br> <a href="https://www.codecogs.com/eqnedit.php?latex=O(N)&space;=&space;\sum_{i=0}^{log_2(N)&space;-&space;1}&space;2^i&space;*&space;(log_2(N)&space;-&space;i)" target="_blank"><img src="https://latex.codecogs.com/gif.latex?O(N)&space;=&space;\sum_{i=0}^{log_2(N)&space;-&space;1}&space;2^i&space;*&space;(log_2(N)&space;-&space;i)" width="70%" title="O(N) = \sum_{i=0}^{log_2(N) - 1} 2^i * (log_2(N) - i)" /></a> <br> Extract & Sort <br> <a href="https://www.codecogs.com/eqnedit.php?latex=O(N&space;log&space;N)&space;=&space;\sum_{i=0}^{N-1}&space;log_2&space;(N&space;-&space;i)" target="_blank"><img src="https://latex.codecogs.com/gif.latex?O(N&space;log&space;N)&space;=&space;\sum_{i=0}^{N-1}&space;log_2&space;(N&space;-&space;i)" width="70%" title="O(N log N) = \sum_{i=0}^{N-1} log_2 (N - i)" /></a> | O(N) <br><br> O(N) + O(log N) | Unstable
4 | Insertion Sort | [Java](src/Algorithms/Sorting/InsertionSort.java) | O(N<sup>2</sup>) <br><br> <a href="https://www.codecogs.com/eqnedit.php?latex=O(\frac{N^2&space;&plus;&space;N}{2})" target="_blank"><img src="https://latex.codecogs.com/gif.latex?O(\frac{N^2&space;&plus;&space;N}{2})" width="40%" title="O(\frac{N^2 + N}{2})" /></a> | O(N) | Stable
5 | Bubble Sort | [Java](src/Algorithms/Sorting/BubbleSort.java) | O(N<sup>2</sup>) <br><br> <a href="https://www.codecogs.com/eqnedit.php?latex=O(\frac{N^2&space;&plus;&space;N}{2})" target="_blank"><img src="https://latex.codecogs.com/gif.latex?O(\frac{N^2&space;&plus;&space;N}{2})" width="40%" title="O(\frac{N^2 + N}{2})" /></a> | O(N) | Stable
6 | Selection Sort | [Java](src/Algorithms/Sorting/SelectionSort.java) | <a href="https://www.codecogs.com/eqnedit.php?latex=\theta(N^2)" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\theta(N^2)" title="\theta(N^2)" /></a> <br><br> <a href="https://www.codecogs.com/eqnedit.php?latex=\theta(\frac{N^2&space;&plus;&space;N}{2})" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\theta(\frac{N^2&space;&plus;&space;N}{2})" width="40%" title="\theta(\frac{N^2 + N}{2})" /></a> | <a href="https://www.codecogs.com/eqnedit.php?latex=\theta(N)" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\theta(N)" title="\theta(N)" /></a> | Stable

