package TestCases;

import Objects.TodoObject;
import Supports.Browsers;
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
    @Test
    public void todoDispplay()
    {
        //todo.todoDisplay("chrome");
    }


}
