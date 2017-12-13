package util.treeify;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Treeify {

    private static String defaultIdPropName = "id";
    private static String defaultParentIdPropName = "parentId";
    private static String defaultChildPropName = "nodes";

    public static <T> T makeTree(List<T> list) throws NoSuchFieldException, IllegalAccessException {
        return convert(list, defaultIdPropName, defaultParentIdPropName, defaultChildPropName);
    }

    public static <T> T makeTree(List<T> list, String idAccessor) throws NoSuchFieldException, IllegalAccessException {
        return convert(list, idAccessor, defaultParentIdPropName, defaultChildPropName);
    }

    public static <T> T makeTree(List<T> list, String idAccessor, String parentIdAccessor) throws NoSuchFieldException, IllegalAccessException {
        return convert(list, idAccessor, parentIdAccessor, defaultChildPropName);
    }

    public static <T> T makeTree(List<T> list, String idAccessor, String parentIdAccessor, String childAccessor) throws NoSuchFieldException, IllegalAccessException {
        return convert(list, idAccessor, parentIdAccessor, childAccessor);
    }

    private static Field getField(String fieldName, Class aClass) throws NoSuchFieldException {
        Field f = aClass.getDeclaredField(fieldName);
        f.setAccessible(true);
        return f;
    }

    private static <T> T convert(List<T> list, String id, String parentId, String child) throws NoSuchFieldException, IllegalAccessException {

        if (list == null || list.size() == 0)
            return null;

        Class c = list.get(0).getClass();
        Map<Object, T> hash = new HashMap<Object,T>();
        Field idField = getField(id, c);
        Field parentIdField = getField(parentId, c);
        Field childField = getField(child, c);
        Object rootId = null;
        for(T ele : list) {
            hash.put(idField.get(ele), ele);
        }

        for (T ele : list) {
            Object parentIdObj = parentIdField.get(ele);
            if (parentIdObj == null) {
                rootId = idField.get(ele);
            }
            if (hash.containsKey(parentIdObj)) {
                Object node =  hash.get(parentIdObj);
                List val = (List) childField.get(node);
                if (val == null) {
                    val = new ArrayList();
                    childField.set(node, val);
                }
                val.add(ele);
            }
        }

        return hash.get(rootId);
    }
}
