package com.zhy.frame.hugegraph.test;

import com.baidu.hugegraph.driver.GraphManager;
import com.baidu.hugegraph.driver.HugeClient;
import com.baidu.hugegraph.driver.SchemaManager;
import com.baidu.hugegraph.structure.graph.Edge;
import com.baidu.hugegraph.structure.graph.Vertex;

import java.util.LinkedList;
import java.util.List;

public class BatchExample {
    public static void main(String[] args) {
        // If connect failed will throw a exception.
        HugeClient hugeClient = new HugeClient("http://10.10.10.171:8080", "hugegraph");

        SchemaManager schema = hugeClient.schema();

        schema.propertyKey("name").asText().ifNotExist().create();
        schema.propertyKey("age").asInt().ifNotExist().create();
        schema.propertyKey("lang").asText().ifNotExist().create();
        schema.propertyKey("date").asText().ifNotExist().create();
        schema.propertyKey("price").asInt().ifNotExist().create();

        schema.vertexLabel("person")
                .properties("name", "age")
                .primaryKeys("name")
                .ifNotExist()
                .create();

        schema.vertexLabel("person")
                .properties("price")
                .nullableKeys("price")
                .append();

        schema.vertexLabel("software")
                .properties("name", "lang", "price")
                .primaryKeys("name")
                .ifNotExist()
                .create();

        schema.indexLabel("softwareByPrice")
                .onV("software").by("price")
                .range()
                .ifNotExist()
                .create();

        schema.edgeLabel("knows")
                .link("person", "person")
                .properties("date")
                .ifNotExist()
                .create();

        schema.edgeLabel("created")
                .link("person", "software")
                .properties("date")
                .ifNotExist()
                .create();

        schema.indexLabel("createdByDate")
                .onE("created").by("date")
                .secondary()
                .ifNotExist()
                .create();

        GraphManager graph = hugeClient.graph();

        Vertex marko = new Vertex("person").property("name", "marko")
                .property("age", 29);
        Vertex vadas = new Vertex("person").property("name", "vadas")
                .property("age", 27);
        Vertex lop = new Vertex("software").property("name", "lop")
                .property("lang", "java")
                .property("price", 328);
        Vertex josh = new Vertex("person").property("name", "josh")
                .property("age", 32);
        Vertex ripple = new Vertex("software").property("name", "ripple")
                .property("lang", "java")
                .property("price", 199);
        Vertex peter = new Vertex("person").property("name", "peter")
                .property("age", 35);

        // Create a list to put vertex(Default max size is 500)
        List<Vertex> vertices = new LinkedList<>();
        vertices.add(marko);
        vertices.add(vadas);
        vertices.add(lop);
        vertices.add(josh);
        vertices.add(ripple);
        vertices.add(peter);

        // Post a vertex list to server
        vertices = graph.addVertices(vertices);
        vertices.forEach(vertex -> System.out.println(vertex));

        Edge markoKnowsVadas = new Edge("knows").source(marko).target(vadas)
                .property("date", "20160110");
        Edge markoKnowsJosh = new Edge("knows").source(marko).target(josh)
                .property("date", "20130220");
        Edge markoCreateLop = new Edge("created").source(marko).target(lop)
                .property("date", "20171210");
        Edge joshCreateRipple = new Edge("created").source(josh).target(ripple)
                .property("date", "20171210");
        Edge joshCreateLop = new Edge("created").source(josh).target(lop)
                .property("date", "20091111");
        Edge peterCreateLop = new Edge("created").source(peter).target(lop)
                .property("date", "20170324");

        // Create a list to put edge(Default max size is 500)
        List<Edge> edges = new LinkedList<>();
        edges.add(markoKnowsVadas);
        edges.add(markoKnowsJosh);
        edges.add(markoCreateLop);
        edges.add(joshCreateRipple);
        edges.add(joshCreateLop);
        edges.add(peterCreateLop);

        // Post a edge list to server
        edges = graph.addEdges(edges, false);
        edges.forEach(edge -> System.out.println(edge));
    }
}
