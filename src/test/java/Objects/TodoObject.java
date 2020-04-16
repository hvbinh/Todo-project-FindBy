package Objects;

import Supports.Browsers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class TodoObject extends LoadableComponent<TodoObject> {

    public TodoObject()
    {
        PageFactory.initElements(Browsers.getDriver(), this);
    }

    @Override
    public void load() {
        Browsers.getDriver().get("http://todomvc.com/examples/vanillajs");
    }

    @Override
    protected void isLoaded() throws Error {

    }
    //expose element
    @FindBy(className = "new-todo")
    WebElement textField_todo;
    @FindBy(xpath = "//header[@class='header']/h1")
    WebElement titleHeader_todo;

    public boolean checkHeaderTitle(String text)
    {
        String str = textField_todo.getAttribute("placeholder").toString();
        if(str.equalsIgnoreCase(text))
            return  true;
        else
            return false;

    }
    public String checkPageLoad()
    {
        return titleHeader_todo.getText();

    }
    public void addTask(String task)
    {
        textField_todo.sendKeys(task);
        textField_todo.sendKeys(Keys.ENTER);
    }

}
