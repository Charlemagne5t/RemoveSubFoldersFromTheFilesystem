import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SolutionTest {
    @Test
    public void test1(){
        String[] folder = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
        List<String> expected = List.of("/a","/c/d","/c/f");
        List<String> actual = new Solution().removeSubfolders(folder);

        Assert.assertEquals(expected, actual);
    }
}
