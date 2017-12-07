package Task3;

import Additional.Graph;
import Additional.GraphBuilder;
import Additional.Path;
import Task3.ant.AntVoyagingPathSearcher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Additional.VoyagerKt.*;

class AntVoyagingPathSearcherTest {

    @Test
    void solve() {
        GraphBuilder gb = new GraphBuilder();
        Graph.Vertex v1 =  gb.addVertex("1");
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

        Path path = findVoyagingPath(g, new Path(g.getVertices().toArray(new Graph.Vertex[0])[0]), null);
        System.out.println(path);
        System.out.println(path.getLength());

        AntVoyagingPathSearcher searcher = new AntVoyagingPathSearcher(g, 1, 5, 1, 500);
        searcher.findVoyagingPath();
    }

    @Test
    void solve2() {

        Random random = new Random();
        ArrayList<Graph.Vertex> vertices = new ArrayList<>();
        GraphBuilder gb = new GraphBuilder();
        for (int i = 0; i < 5; i++) {
            vertices.add(gb.addVertex(Integer.toString(i)));
        }

        for (int i = 0; i < 10; i++) {
            int[] ints = new int[10];
            ints[i] = 1;
            for (Graph.Vertex vertex : vertices) {
                int rand = new Random().nextInt(4);
                if (ints[rand] != 1) {
                    gb.addConnection(vertex, vertices.get(rand), 40);
                    ints[rand] = 1;
                }
            }
        }

        Graph g = gb.build();
        AntVoyagingPathSearcher searcher = new AntVoyagingPathSearcher(g, 1, 5, 1, 60);
        List<Graph.Vertex> list = searcher.findVoyagingPath();

        Path path = findVoyagingPath(g, new Path(list.get(0)), null);
        System.out.println(path);
        System.out.println(path.getLength());
    }

}