package Creatures.Ants.AntLabor;

import java.util.*;

public class LaborPathfind {

    /**
     * Node class to represent a position in the grid, including the floor, cost, and heuristic value.
     */
    private static class Node {
        int x, y, floor;
        float cost;
        float heuristic;
        Node parent;

        public Node(int x, int y, int floor, float cost, float heuristic, Node parent) {
            this.x = x;
            this.y = y;
            this.floor = floor;
            this.cost = cost;
            this.heuristic = heuristic;
            this.parent = parent;
        }

        public float totalCost() {
            return cost + heuristic;
        }
    }
    public List<Node> findPath(float startX, float startY, int startFloor, double targetX, double targetY, int targetFloor) {
        return findPath((int) startX, (int) startY, startFloor, (int) targetX, (int) targetY, targetFloor);
    }

    /**
     * Finds the shortest path between a start and end position using A*.
     *
     * @param startX      Starting X coordinate
     * @param startY      Starting Y coordinate
     * @param startFloor  Starting floor level
     * @param targetX     Target X coordinate
     * @param targetY     Target Y coordinate
     * @param targetFloor Target floor level
     * @return List of Nodes representing the path from start to target
     */
    public List<Node> findPath(int startX, int startY, int startFloor, int targetX, int targetY, int targetFloor) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(Node::totalCost));
        Set<String> closedSet = new HashSet<>();

        Node startNode = new Node(startX, startY, startFloor, 0, heuristic(startX, startY, startFloor, targetX, targetY, targetFloor), null);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            // Stop and return the path if we reach the target
            if (current.x == targetX && current.y == targetY && current.floor == targetFloor) {
                return reconstructPath(current);
            }

            // Add to closed set to avoid revisiting
            closedSet.add(generateNodeKey(current.x, current.y, current.floor));

            // Process neighbors
            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(generateNodeKey(neighbor.x, neighbor.y, neighbor.floor))) {
                    continue; // Skip nodes already visited
                }

                float tentativeCost = current.cost + calculateMovementCost(current, neighbor);
                boolean isOpen = openSet.contains(neighbor);

                // Update cost if better or not already processed
                if (!isOpen || tentativeCost < neighbor.cost) {
                    neighbor.cost = tentativeCost;
                    neighbor.heuristic = heuristic(neighbor.x, neighbor.y, neighbor.floor, targetX, targetY, targetFloor);
                    neighbor.parent = current;

                    if (!isOpen) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        // Return empty path if the target can't be reached
        return new ArrayList<>();
    }

    /**
     * Calculates just the distance to the target without requiring the entire path.
     *
     * @param startX      Starting X coordinate
     * @param startY      Starting Y coordinate
     * @param startFloor  Starting floor level
     * @param targetX     Target X coordinate
     * @param targetY     Target Y coordinate
     * @param targetFloor Target floor level
     * @return Total distance as a float
     */
    public float calculateDistance(float startX, float startY, int startFloor, double targetX, double targetY, int targetFloor) {
        List<Node> path = findPath(startX, startY, startFloor, targetX, targetY, targetFloor);
        if (path.isEmpty()) {
            return Float.MAX_VALUE; // Return a large value if path isn't found
        }
        return path.get(path.size() - 1).cost; // Total cost of the path
    }

    /**
     * Outputs the path to the console.
     *
     * @param path List of Nodes in the path
     */
    public void printPath(List<Node> path) {
        if (path.isEmpty()) {
            System.out.println("No path found!");
            return;
        }

        System.out.println("Path:");
        for (Node node : path) {
            System.out.printf("Position: (%d, %d) on Floor %d, Cost: %.2f\n", node.x, node.y, node.floor, node.cost);
        }
    }

    // Private utility methods

    private List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node); // Add nodes in reverse order
            node = node.parent;
        }
        return path;
    }

    private float heuristic(int x1, int y1, int floor1, int x2, int y2, int floor2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(floor1 - floor2);
    }

    private float calculateMovementCost(Node from, Node to) {
        // Future: Add logic for terrain, tunnels, or other factors that might affect cost
        return 1.0f; // Default cost
    }

    private String generateNodeKey(int x, int y, int floor) {
        return x + "," + y + "," + floor;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        // Example neighbors; logic can be expanded for tunnels, staircases, etc.
        neighbors.add(new Node(node.x + 1, node.y, node.floor, 0, 0, null));
        neighbors.add(new Node(node.x - 1, node.y, node.floor, 0, 0, null));
        neighbors.add(new Node(node.x, node.y + 1, node.floor, 0, 0, null));
        neighbors.add(new Node(node.x, node.y - 1, node.floor, 0, 0, null));

        return neighbors;
    }
}
