package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class Topic_06 {
    //Sẽ có cả hàm non-abstract và abstract
    //Ko cho phép khởi tạo
    //Chỉ cho kế thừa

    //OOP: Abstraction

    //Lấy dữ liệu ra (khác void)
    public String getFullName(){
        WebDriver driver = new FirefoxDriver();
        return null;
    }

    //Action (void)
    public void setFullname(){

    }
}
