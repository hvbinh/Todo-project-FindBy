package Objects;

import Supports.Browsers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
    @FindBy(linkText = "Active")
    WebElement active_link;
    @FindBy (linkText = "All")
    WebElement all_link;
    @FindBy (linkText = "Completed")
    WebElement completed_link;
    @FindBy(className = "todo-list")
    WebElement todo_List;
    @FindBy(xpath = "//*[@class='todo-count']/strong")
    WebElement countActive;

    public void selectTabLink(String link)
    {
        switch (link)
        {
            case "all": {
                all_link.click();
                break;
            }
            case "active":
            {
                active_link.click();
                break;
            }
            case "completed":
            {
                completed_link.click();;
                break;
            }
        }
    }


    public void markCompleted(String task)
    {
        Browsers.getDriver().findElement(By.xpath(String.format("//*[.='%s']/preceding-sibling::input", task))).click();
    }
    public WebElement removeTask(String task)
    {
        return Browsers.getElement(How.XPATH, String.format("//div/label[.='%s']/following-sibling::button",task));
    }
    public List<WebElement> allList()
    {
        return todo_List.findElements(By.tagName("li"));
    }
    public int activeOnAllList()
    {
        all_link.click();
        List<WebElement> allList = allList();
        List<WebElement> actList = new ArrayList<>();
        for(WebElement e : allList)
        {
            if(e.getAttribute("class").toString().isEmpty())
            {
                actList.add(e);
            }
        }
        return actList.size();
    }
    public void  showList(List<WebElement> list)
    {
        System.out.println("list: ");
        for(WebElement e : list)
        {
            System.out.println(e.getText());
        }

    }

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
    public int showListBaseOnTab(String txt)
    {
        if(txt.equalsIgnoreCase(active_link.getText()))
        {
            active_link.click();
            List<WebElement> activeList = allList();
            showList(activeList);
            return activeList.size();
        }
        else if(txt.equalsIgnoreCase(all_link.getText()))
        {
            all_link.click();
            List<WebElement> allList = allList();
            showList(allList);
            return allList.size();
        }
        else if(txt.equalsIgnoreCase(completed_link.getText()))
        {
            completed_link.click();
            List<WebElement> allList = allList();
            showList(allList);
            return allList.size();
        }
        return 0;
    }
    public void selectTask(String task)
    {
        List<WebElement> list = allList();
        for(WebElement e: list)
        {
            if(e.getText().equalsIgnoreCase(task))
            {
                System.out.println(e.getText());
                markCompleted(e.getText());
                //e.findElement(By.xpath(String.format("//*[.='%s']/preceding-sibling::input", e.getText()))).click();

            }
        }
    }
    public int countActiveItem()
    {
        return Integer.parseInt(countActive.getText());
    }
    public void deleteActiveTask(String task)
    {

        Actions act = new Actions(Browsers.getDriver());
        List<WebElement> list = allList();
        for(WebElement e:list)
        {
           if(e.getText().equalsIgnoreCase(task))
           {
               act.moveToElement(e).click(removeTask(task)).perform();
               break;
           }
        }
    }

}
