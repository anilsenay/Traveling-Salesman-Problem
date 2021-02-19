# Traveling Salesman Problem

The travelling salesman problem (also called the traveling salesperson problem or TSP) asks the following question: "Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city exactly once and returns to the origin city?" It is an NP-hard problem in combinatorial optimization, important in theoretical computer science and operations research.

In order to optimize the Traveling Salesman Problem results, Randomized Nearest Neighbor Algorithm and 2-Opt Optimization Algorithm.

### Randomized Nearest Neighbor Algorithm

The nearest neighbor algorithm was one of the first algorithms used to solve the travelling salesman problem approximately. In that problem, the salesman starts at a random city and repeatedly visits the nearest city until all have been visited. The algorithm quickly yields a short tour, but usually not the optimal one. The nearest neighbor algorithm is easy to implement and executes quickly, but it can sometimes miss shorter routes which are easily noticed with human insight, due to its "greedy" nature.

### 2-Opt Optimization Algorithm

The 2-opt algorithm is a local search algorithm probably the most basic and widely used algorithm for solving Traveling Salesman Problems. Basically, it can be defined as deleting the two edges in the tour and connecting the tour, which is divided into two parts, in a way that reduces length of the tour.
