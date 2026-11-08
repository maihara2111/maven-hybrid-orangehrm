package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_13_NullPointer {
    //Biến global - ko cần khởi tạo đối tượng như local
    //Vì hàm main là hàm static nên biến phải là biến static thì mới sử dụng được trong hàm main
    static WebDriver driver =  new FirefoxDriver();

    //Hàm static ko gọi đến 1 bieến non-static được
    public static void main(String[] args) {
        //Biến local - bắt buộc phải khởi tạo móới dùng đc
        //Webdriver driver

        driver.get("http://localhost:88/orangehrm");
    }
}
