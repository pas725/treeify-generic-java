package util.treeify;

import java.util.List;

public class MockData {
    public static class Node {
        public int id;
        public Integer parentId;
        public String name;
        public List<Node> nodes;

        public Node(int id, Integer parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }
    }

    public static class Node1 {
        public int id;
        public Integer pId;
        public String name;
        public List<Node> child;

        public Node1(int id, Integer parentId, String name) {
            this.id = id;
            this.pId = parentId;
            this.name = name;
        }
    }

    public static class Person {
        public int id;
        public Integer parentId;
        public String name;
        public List<Person> nodes;

        public Person(int id, Integer parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }
    }


}
