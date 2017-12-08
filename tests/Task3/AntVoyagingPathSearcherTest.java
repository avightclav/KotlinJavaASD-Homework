package Task3;

import Additional.Graph;
import Additional.GraphBuilder;
import Additional.Path;
import Task3.ant.AntVoyagingPathSearcher;
import Task3.genetic.GeneticVoyagingPathSearcher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Additional.VoyagerKt.*;

class AntVoyagingPathSearcherTest {

    @Test
    void fingVoyagingPathDefinedGraph() {
        GraphBuilder gb = new GraphBuilder();
        Graph.Vertex v1 = gb.addVertex("1");
        Graph.Vertex v2 = gb.addVertex("2");
        Graph.Vertex v3 = gb.addVertex("3");
        Graph.Vertex v4 = gb.addVertex("4");
        Graph.Vertex v5 = gb.addVertex("5");
        Graph.Vertex v6 = gb.addVertex("6");
        Graph.Vertex v7 = gb.addVertex("7");
        Graph.Vertex v8 = gb.addVertex("8");
        Graph.Vertex v9 = gb.addVertex("9");
        Graph.Vertex v10 = gb.addVertex("10");
        gb.addConnection(v1, v2, 5);
        gb.addConnection(v2, v3, 5);
        gb.addConnection(v3, v4, 5);
        gb.addConnection(v4, v5, 5);
        gb.addConnection(v5, v6, 5);
        gb.addConnection(v6, v7, 5);
        gb.addConnection(v7, v8, 3);
        gb.addConnection(v8, v9, 3);
        gb.addConnection(v9, v1, 3);
        gb.addConnection(v9, v10, 3);
        gb.addConnection(v10, v3, 3);
        gb.addConnection(v8, v5, 3);
        gb.addConnection(v10, v4, 3);
        Graph g = gb.build();

        AntVoyagingPathSearcher searcher = new AntVoyagingPathSearcher(g, 1, 5, 1, 500);
        Path antPath = searcher.findVoyagingPath();
        System.out.println(antPath);

        Path path = findVoyagingPath(g, new Path(g.get("1")), null);
        System.out.println(path);
    }

    @Test
    void findVoyagingPathRandomGraph() {
        Random random = new Random();
        ArrayList<Graph.Vertex> vertices = new ArrayList<>();
        GraphBuilder gb = new GraphBuilder();
        int graphSize = 5;

        for (int i = 0; i < graphSize; i++) {
            vertices.add(gb.addVertex(Integer.toString(i)));
        }

        int i = 0;
        for (Graph.Vertex vertex : vertices) {
            i++;
            for (int j = 0 + i; j < graphSize; j++) {
                if ((Integer.parseInt(vertex.getName()) != j)) {
                    gb.addConnection(vertex, vertices.get(j), 10 + random.nextInt(60));
                }
            }
        }

        Graph g = gb.build();
        AntVoyagingPathSearcher searcher = new AntVoyagingPathSearcher(g, 2, 5, 500, 40);
        Path bestPath = searcher.findVoyagingPath();
        System.out.println(bestPath.getVertices().toString() + "length: " + bestPath.getLength());

        Path path = findVoyagingPath(g, new Path(g.get("0")), null);
        System.out.println(path);
    }

    @Test
    void findVoyagingPathRandomGraphPlusGenetic() {
        Random random = new Random();
        ArrayList<Graph.Vertex> vertices = new ArrayList<>();
        GraphBuilder gb = new GraphBuilder();
        int graphSize = 10;

        for (int i = 0; i < graphSize; i++) {
            vertices.add(gb.addVertex(Integer.toString(i)));
        }

        int i = 0;
        for (Graph.Vertex vertex : vertices) {
            i++;
            for (int j = 0 + i; j < graphSize; j++) {
                if ((Integer.parseInt(vertex.getName()) != j)) {
                    gb.addConnection(vertex, vertices.get(j), 10 + random.nextInt(60));
                }
            }
        }

        Graph g = gb.build();

        AntVoyagingPathSearcher searcherAnt = new AntVoyagingPathSearcher(g, 1, 5, 500, 20);
        Path antPath = searcherAnt.findVoyagingPath();
        System.out.println("Ant: " + antPath);

        GeneticVoyagingPathSearcher searcherGenetic = new GeneticVoyagingPathSearcher(g, 10, 20);
        Path pathGenetic = searcherGenetic.findVoyagingPath();
        System.out.println("Genetic: " + pathGenetic);
    }

}