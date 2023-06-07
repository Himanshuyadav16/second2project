import org.testng.annotations.*;

public class Test3 {
    @Test
    public void test(){
        System.out.println("Test");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test");
    }
    @BeforeClass
    public  void beforeClass(){
        System.out.println("Before Class");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before method");
    }
    @BeforeGroups
    public void beforeGroups(){
        System.out.println("Before Group");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite");
    }

    @AfterMethod
    public  void afterMethod(){
        System.out.println("After Method");
    }
    @AfterGroups
    public void afterGroups() {
        System.out.println("After Groups");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite");
    }

    @AfterClass
    public  void AfterClass(){
        System.out.println("After Class");
    }
    @AfterTest
    public void AfterTest(){
        System.out.println("After Test");
    }

}