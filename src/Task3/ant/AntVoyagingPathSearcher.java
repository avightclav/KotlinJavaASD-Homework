package Task3.ant;

import Additional.Graph;
import Additional.Graph.Vertex;
import Additional.Graph.Edge;
import Additional.Path;
import Task3.AbstractVoyagingPathSearcher;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class AntVoyagingPathSearcher {
    int step = 0;
    private Random random = new Random();
    private final int FINE = 500;
    private double prohibitedEdgePheromone = 1.0;
    private final Graph graph;
    private final int graphSize;
    private int currentIndex;
    private int bestLength;
    private List<Vertex> bestPath;
    private List<Ant> ants = new ArrayList<Ant>();
    private Map<Edge, Double> pheromones = new HashMap<Edge, Double>();
    private Map<Vertex, Double> probabilities = new HashMap<Vertex, Double>();
    private final int greedyCoefficient;
    private final double pheromoneCoefficient;
    private final int antQuantity;
    private final int exponentOfOptimal;
    private final double initialPheromoneCoefficient = 1.0;
    private final double evaporationCoefficient = 0.8;
    private final double randomChoiceCoefficient = 0.01;
    private final int maxIterations;

    public List<Vertex> findVoyagingPath() {
        int iteration = 0;

        while (iteration < maxIterations) {
            setupAnts();
            moveAnts();
            updatePheromones();
            updateBest();
            iteration++;
        }
        System.out.println("Best tour length: " + (bestLength));
        System.out.println("Best tour:" + bestPath);
        return bestPath;
    }

    public class Ant {
        List<Vertex> visited = new ArrayList<>();

        void visit(Vertex vertex) {
            visited.add(vertex);
        }

        public List<Vertex> getVisited() {
            return visited;
        }

        public Vertex getLastVisited() {
            return visited.get(visited.size() - 1);
        }

        boolean isVisited(Vertex vertex) {
            return visited.contains(vertex);
        }

        public int tourLength() {
            int length = 0;
            int j = 0;

            while (j < visited.size() - 1) {
                length += (graph.getConnection(visited.get(j), visited.get(j + 1)) != null) ? graph.getConnection(visited.get(j), visited.get(j + 1)).getWeight() : FINE;
                j++;
            }
            length += (graph.getConnection(visited.get(0), visited.get(visited.size() - 1)) != null) ? graph.getConnection(visited.get(0), visited.get(visited.size() - 1)).getWeight() : FINE;
            return length;
        }

        void clear() {
            visited.clear();
        }

    }

    public AntVoyagingPathSearcher(Graph graph, int greedyCoefficient, double pheromoneCoefficient, int exponentOfOptimal, int maxIterations) {
        this.graph = graph;
        this.greedyCoefficient = greedyCoefficient;
        this.pheromoneCoefficient = pheromoneCoefficient;
        this.graphSize = graph.getVertices().size();
        this.antQuantity = (int) (graphSize * 0.8);
        this.exponentOfOptimal = exponentOfOptimal;
        this.maxIterations = maxIterations;

        for (Vertex vertex : graph.getVertices()) {
            for (Edge edge : graph.getConnections(vertex).values()) {
                pheromones.put(edge, initialPheromoneCoefficient);
            }
        }

        for (int i = 0; i < antQuantity; i++)
            ants.add(new Ant());
    }

    private void generateProbabilities(Ant ant) {

        probabilities.clear();
        double denom = 0.0;

        for (Vertex vertex : graph.getVertices()) {
            if (!ant.isVisited(vertex)) {
                Edge connection = graph.getConnection(ant.getLastVisited(), vertex);
                if (connection != null)
                    denom += Math.pow(pheromones.get(connection), pheromoneCoefficient) * Math.pow(1.0 / connection.getWeight(), greedyCoefficient);
                else
                    denom += Math.pow(prohibitedEdgePheromone, pheromoneCoefficient) * Math.pow(1.0 / FINE, greedyCoefficient);
            }
        }

        for (Vertex vertex : graph.getVertices()) {
            if (!ant.isVisited(vertex)) {
                double numerator;
                Edge connection = graph.getConnection(ant.getLastVisited(), vertex);
                if (connection != null)
                    numerator = Math.pow(pheromones.get(connection), pheromoneCoefficient) * Math.pow(1.0 / connection.getWeight(), greedyCoefficient);
                else
                    numerator = Math.pow(prohibitedEdgePheromone, pheromoneCoefficient) * Math.pow(1.0 / FINE, greedyCoefficient);
                probabilities.put(vertex, numerator / denom);
            } else
                probabilities.put(vertex, 0.0);
        }
    }

    private Vertex selectNextTown(Ant ant) {
        if (random.nextDouble() < randomChoiceCoefficient) {
            int t = random.nextInt(graphSize - currentIndex); // random town
            int j = -1;
            for (Vertex vertex : graph.getVertices()) {
                if (!ant.isVisited(vertex))
                    j++;
                if (j == t)
                    return vertex;
            }
        }

        generateProbabilities(ant);

        double p = random.nextDouble();
        double toP = 0;
        for (Vertex vertex : graph.getVertices()) {
            toP += probabilities.get(vertex);
            if (toP >= p)
                return vertex;
        }
        System.out.println(toP);
        System.out.println(p);
        System.out.println(currentIndex);
        System.out.println(probabilities);
        throw new IllegalArgumentException();
    }

    private void updatePheromones() {
        for (Map.Entry<Edge, Double> entry : pheromones.entrySet()) {
            entry.setValue(entry.getValue() * evaporationCoefficient);
        }
        prohibitedEdgePheromone = prohibitedEdgePheromone * evaporationCoefficient;

        for (Ant ant : ants) {
            int pheromone = exponentOfOptimal / ant.tourLength();

            int j = 0;
            while (j < ant.getVisited().size()) {
                Edge edge = graph.getConnection(ant.getVisited().get(j), ant.getVisited().get(j++));
                Double old = pheromones.get(edge);
                if (old != null) {
                    pheromones.put(edge, old + pheromone);
                }
            }
            Edge edge = graph.getConnection(ant.getVisited().get(0), ant.getVisited().get(ant.getVisited().size() - 1));
            Double old = pheromones.get(edge);
            if (old != null) {
                pheromones.put(edge, old + pheromone);
            }
        }
    }

    private void moveAnts() {
        while (currentIndex < graphSize - 1) {
            for (Ant ant : ants)
                ant.visit(selectNextTown(ant));
            currentIndex++;
        }
    }

    private void setupAnts() {
        currentIndex = -1;
        for (int i = 0; i < antQuantity; i++) {
            ants.get(i).clear();
            int r = random.nextInt(graphSize - 1);
            ants.get(i).visit(graph.getVertices().toArray(new Vertex[0])[r]);
        }
        currentIndex++;
    }

    private void updateBest() {
        if (bestPath == null) {
            bestPath = ants.get(0).visited;
            bestLength = ants.get(0).tourLength();
        }
        for (Ant ant : ants) {
            if (ant.tourLength() < bestLength) {
                bestLength = ant.tourLength();
                bestPath = new ArrayList<Vertex>();
                bestPath.addAll(ant.getVisited());
            }
        }
    }

}
