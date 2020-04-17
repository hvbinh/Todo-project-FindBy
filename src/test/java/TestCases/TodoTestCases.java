package TestCases;

import Objects.TodoObject;
import Supports.Browsers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TodoTestCases {
    TodoObject todo;
    @BeforeClass
    public  void setUp()
    {
        Browsers.open("chrome");
    }
    @BeforeMethod
    public void precondition()
    {
        todo = new TodoObject();
        todo.load();
    }
    @Test(priority = 1, description = "Verify that the page is loaded correctly")
    public void todoDispplay()
    {
        boolean boo = todo.checkHeaderTitle("What needs to be done?");
        Assert.assertTrue(boo);
        String str = todo.checkPageLoad();
        Assert.assertEquals(str, "todos");
    }
    @Test(priority = 2,description = "Verify that add task works correctly1 ")
    public void addTask()
    {
        todo.addTask("task 1");
    }
}
