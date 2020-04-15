package Objects;

import Supports.Browsers;
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
    @FindBy(className = "header")
    WebElement titleHeader_todo;

    public boolean checkHeaderTitle()
    {
        Browsers.getElement(How.CLASS_NAME, "")

    }
    public TodoObject checkPageLoad(String browser)
    {

        return this;
    }

}
