package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_10 extends Topic_09 {
    //Phạm vi toàn cục (Class)
    String address;

    public Topic_10(String name, String address) { //Phạm vi cục bộ (hàm)
        super(name);
        this.address = address;
    }

    //Khi chạy đa luồng và goọi đến hàm này
    //Bắt buộc phải chạy theo thứ tự
    public synchronized WebDriver getDriver() {
        WebDriver driver = new FirefoxDriver();

        if (driver instanceof FirefoxDriver) {
            driver = new FirefoxDriver();
        }
        return new FirefoxDriver();
    }
}
