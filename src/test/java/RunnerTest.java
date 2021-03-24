import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.*;

public class RunnerTest {
    private static Runner runner;
    private static String inputFileName;
    private static String outputFileName;

    @BeforeClass
    public static void initRunner(){
        runner = new Runner();
        inputFileName = "INPUT.TXT";
        outputFileName= "OUTPUT.TXT";
    }

    @Test(expected = RuntimeException.class)
    public void checkFileExistTest() throws Exception {
        runner.parseFile("doesn'tExist");
    }

    @Test
    public void CheckEmptyFileTest() throws Exception {
        runner.saveFile(runner.groupV(runner.parseFile("emptyType.txt")), outputFileName);
        File file = new File(outputFileName);
        Assert.assertEquals(file.length(), 0);
    }

    @Test
    public void CheckGroupVTest() throws Exception {
        Map<Set<Character>, Map<Integer, Double>> dMap = new HashMap<>();
        Map<Integer, Double> map1 = new HashMap<>();
        Map<Integer, Double> map2 = new HashMap<>();
        map1.put(6, 2.5);
        map1.put(5, 2.0);
        map2.put(4, 2.0);
        dMap.put(new HashSet<>(Arrays.asList('a', 'o')), map1);
        dMap.put(new HashSet<>(Arrays.asList('a', 'e')), map2);
        Assert.assertEquals(runner.groupV(runner.parseFile(inputFileName)), dMap);
    }
}
