package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Topic_08 {
    //non-static
    String address = "190 LTK";

    //Phạm vi static là chia sẻ cho toàn bộ system sử dụng
    static String name = "Mai Hara";

    //HẰNG SỐ: ko được gán lại, ko được thay đổi giá trị
    static final String AGE = "22";


    @Test
    public void TC_01() throws InterruptedException {
        //Đối tượng là tp
        Topic_08 tp = new Topic_08();
        tp.address = "";

        //Phạm vi thuộc Class
        Topic_08.name = "";

        String osName = "Windows 11";
        String separator = null;
        WebDriver driver;

        //Condition Statement
        //if-else
        if (osName.contains("Windows")) {
            separator = "\\";
        } else {
            separator = "/";
        }

        String browserName = "Chrome";
        //switch-case
        switch (browserName) {
            case "Chrome":
                driver = new ChromeDriver();
                break; // gặp case nào đúng thì dừng lại, ko ktra case khác nữa
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("");
        }

        //Loop Statement

        int studentNumber = 10;
        //Classic for
        //Iteration for
        for (int i = 0; i < studentNumber; i++) {
            System.out.println(i);
        }

        for (int i = 0; i < studentNumber; i++) {
            if (i == 5) {
                System.out.println(i);
            }
        }

        List<String> stdName = new ArrayList<>();
        //For-Each
        for (String std : stdName) {
            System.out.println(std);
        }

        //while
        int x = 0;
        while (x < studentNumber) {
            System.out.println(x);
            x++;
        }

        //do-while
        int z = 11;
        do {
            System.out.println(z);
            z++;
        } while (z < studentNumber);

        try {
            //Happy Case
            driver.findElement(By.cssSelector("")).isDisplayed();
        } catch (NoSuchElementException exception) {
            //Edge Case
            System.out.println(exception.getMessage());
        } finally {
            //Close Connection: DB/File/...
        }
        Thread.sleep(5000);

    }
}
