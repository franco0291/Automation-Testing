package unitTests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class firstTest {
    @Test
    public void firstTest(){

        int x = 4+10;

        Assert.assertEquals(x,14);
    }

}
