package unitTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tickets.TicketPrice;

public class TestTicketCalculation {


    @DataProvider(name="Ages")
    public Object[][] createData(){
        return new Object[][]{
            {1,1000,0.0},
            {2,1000,0.0},
            {3,1000,500.0},
            {10,1000,500.0},
            {20,1000,1000.0},
            {70,1000,800.0}
        };
    }

    @Test(dataProvider = "Ages")
    public void testTicketPrice(int age, int price, double fare){
        // Arrange
        TicketPrice ticketPrice = new TicketPrice(price);

        // Act
        double result = ticketPrice.getPrice(age);

        // Assert
        Assert.assertEquals(result,fare);
    }
}
