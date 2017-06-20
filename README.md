# OptimalTravel
Solution to optimal Travel problem
*
 * This revision improves the performance of my original solution to a route finding problem, by taking the best features from my solution and incorporating ideas from
 * Dijkstra algorithm suggested as a solution by HeadFirst Design book
 * The problem statement:
 * Given a network of randomly intersecting transportation lines each of which has at least two stations on it find an optimal route between two stations on a network (one involving the least stops),
 * keeping track of how each station was reached so that complete directions can be given to a person traveling on a network.
 * 
 * Review.
 * Both my original algorithm and Djikstra algorithm work in a similar manner by attempting to travel from the starting station to all its neighbors recursively until the destination is reached. Both algorithms
 * keep track of the stations that were already visited to reduce the number of next stations checked at each step from blowing up exponentially.
 * As described in documentation to my original solutions both solutions have some tradeoffs
 * while my algorithm was significantly faster at finding the route O(N) vs O(N^2) for the first time, the final structure that keeps track of how the stations were reached was
 * much better for reuse (given the same starting station find how to travel to a different destination station) in Dijkstra algorithm O(L+N) vs O(L)
 * N - number of stations, L - distance in stops between starting station and destination
 * 
 * This solution maintains the O(N) time for finding the route, while at the same time uses ideas from Dijkstra to keep the reuse time at O(L)!
 * 
 * Design overview:
 * The program reads in the user supplied file that has information about the transportation network:
 * Line names and the stations that are situated on each line.
 * It creates a LineNetwork (map with lineName keys and values @Line objects. Each Line object stores line name, line type (i.e Subway).
 * whether line is circular, and list of Stations in order on each line)
 * The LineNetwork is used to create NextStationsMap. NextStations Map has a map as a basis. The keys of this map are stations names
 * and values are Lists of neighbors within one stop of a given Station. Each neighbor (@LineStationPair) stores its stationName and the lineName by which it can be reached.
 * After LineNetwork and NextStationMap are complete the route can be easily found by RouteFinder
 * Route finder takes the starting station and destination station as input.
 * And then attempts to go from starting station to each neighbor and then from there to each of neighbors neighbors and so on until the destination is reached
 * The algorithm uses a queue for storing current neighbors names.
 * A usedStations map to keep track of the stations already reached (StationName-boolean, to reduce the number of nextNeigbours that have to be considered at each step)
 * And routeMap that for each new station that is reached saves the data on how it was reached 
 * newStationName -> (previousStationName, lineName) stationName from which and line by which we arrived to new station
 * After the destination station is reached we can use the routeMap to trace the route backwards from destination to starting station
 * Saving the backward trace in a stack gives the final solution.
 */
