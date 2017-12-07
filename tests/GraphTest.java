import Additional.Graph;
import Additional.GraphBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    @Test
    void GraphTest() {
       GraphBuilder g =  new GraphBuilder();
       Graph.Vertex v1 = g.addVertex("1");
       Graph.Vertex v2 = g.addVertex("2");

       g.addConnection(v1, v2, 30);
       Graph gg = g.build();
       System.out.println(gg.getConnection(v1, v2).equals(gg.getConnection(v2, v1)));
       System.out.println(gg.getConnections(v2));
    }
}
