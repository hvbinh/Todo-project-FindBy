package TestCases;

import Objects.TodoObject;
import Supports.Browsers;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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
    @Test(priority = 2,description = "Verify that add task works correctly")
    public void addTask()
    {
        todo.addTask("task 1");
    }
    @Test(priority = 3,description = "Verify that add task works correctly")
    public void addTask1()
    {
        todo.addTask("task 2");
    }
    @Test(priority = 4,description = "Verify that active list shows correctly")
    public void showActiveList()
    {
        int active = todo.showListBaseOnTab("Active");
        int activeOnAllList = todo.activeOnAllList();
        Assert.assertEquals(active, activeOnAllList);
    }
    @Test(priority = 5,description = "Verify that completed list shows correctly.")
    public void markTaskComplete()
    {
        todo.selectTask("task 2");
    }

}
