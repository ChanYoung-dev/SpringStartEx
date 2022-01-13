package hello.core.singleton;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private static final Map<String, Object> mutableMap = new HashMap<>();

    public void testFinal() {
        // 재할당 안됨
        // mutableMap = new HashMap<>();

        mutableMap.put("choco", "M&M");
        mutableMap.put("jelly", "haribo");

        System.out.println(mutableMap);
    }

    public static void main(String[] args) {
        Test mutableTest = new Test();
        mutableTest.testFinal();
    }
}
