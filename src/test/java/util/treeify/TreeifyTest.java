package util.treeify;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.treeify.MockData.Node;
import static util.treeify.MockData.Node1;
import static util.treeify.MockData.Person;

public class TreeifyTest {

    @Test
    public void testMakeTreeWithClass1() throws NoSuchFieldException, IllegalAccessException {

        int rootId = 100;
        Node root = new Node(rootId, null, "root");
        Node n1 = new Node(101, 100, "c1");
        Node n2 = new Node(102, 100, "c2");
        List<Node> list = Arrays.asList(root, n1, n2);

        Node res = Treeify.makeTree(list);
        Assert.assertEquals(res.id, rootId );
        Assert.assertEquals(res.nodes.size(), 2);
    }

    @Test
    public void testMakeTreeWithClass2() throws NoSuchFieldException, IllegalAccessException {

        int rootId = 100;
        Person root = new Person(rootId, null, "root");
        Person n1 = new Person(101, 100, "c1");
        List<Person> list = Arrays.asList(root, n1);

        Person res = Treeify.makeTree(list);
        Assert.assertEquals(res.id, rootId );
        Assert.assertEquals(res.nodes.size(), 1);
    }

    @Test
    public void testMakeTreeWithThreeArg() throws NoSuchFieldException, IllegalAccessException {

        int rootId = 100;
        Node1 root = new Node1(rootId, null, "root");
        Node1 n1 = new Node1(101, 100, "c1");
        Node1 n2 = new Node1(102, 100, "c2");
        List<Node1> list = Arrays.asList(root, n1, n2);

        Node1 res = Treeify.makeTree(list, "id", "pId", "child");
        Assert.assertEquals(res.id, rootId );
        Assert.assertEquals(res.child.size(), 2);
    }

    @Test
    public void testMakeTreeForEmptyList() throws NoSuchFieldException, IllegalAccessException {
        List l = new ArrayList();
        Object res = Treeify.makeTree(l);
        Assert.assertEquals(res, null);
    }

    @Test
    public void testMakeTreeForNull() throws NoSuchFieldException, IllegalAccessException {
        Object res = Treeify.makeTree(null);
        Assert.assertEquals(res, null);
    }

    @Test(expected = NoSuchFieldException.class)
    public void shouldThrowException() throws NoSuchFieldException, IllegalAccessException {
        Node1 root = new Node1(10, null, "root");
        List<Node1> list = Arrays.asList(root);
        Treeify.makeTree(list, "id");
    }
}
