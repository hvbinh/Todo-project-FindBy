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
    public void ShowCompletedList()
    {
        int activeOnAllList = todo.countActiveItem();
        todo.selectTask("task 2");
        todo.showListBaseOnTab("Completed");
        int count = todo.countActiveItem();
        Assert.assertEquals(count, activeOnAllList-1);
    }
    @Test(priority = 6,description = "Verify that All list will show both active and completed task")
    public void AllListContainActiveCompletedTask()
    {
        int active= todo.showListBaseOnTab("Active");
        int completed = todo.showListBaseOnTab("Completed");
        int all = todo.showListBaseOnTab("All");
        Assert.assertEquals(all, active+completed);
    }
    @Test(priority = 7,description = "Verify that 'count item left' wil increase after adding new task")
    public void countIncreaseAfterAddTask()
    {
        int i = todo.countActiveItem();
        todo.showListBaseOnTab("All");
        todo.addTask("task 3");
        int j = todo.countActiveItem();
        Assert.assertEquals(i+1, j);
    }
    @Test(priority = 8,description = "Verify that 'count item left' wil decreased after check active task")
    public void countDecreaseAfterMarkCompleted()
    {
        int i = todo.countActiveItem();
        todo.showListBaseOnTab("All");
        todo.markCompleted("task 3");
        int j = todo.countActiveItem();
        Assert.assertEquals(j, i-1);
    }
    @Test(priority = 9, description = "Verify that 'count item left' wil decreased after remove an active task")
    public void countDecreaseAfterDeletedActiveTask()
    {
        int i = todo.countActiveItem();
        todo.selectTabLink("all");
        todo.deleteActiveTask("task 1");
        int j = todo.countActiveItem();
        Assert.assertEquals(j, i-1);

    }
    @Test(priority = 10, description = "Verify that remove task on all list works correctly")
    public void removeTaskOnAllList()
    {

        int i = todo.countActiveItem();
        todo.deleteActiveTask("task 3");
        int j = todo.countActiveItem();
        Assert.assertEquals(j, i);
    }


}
