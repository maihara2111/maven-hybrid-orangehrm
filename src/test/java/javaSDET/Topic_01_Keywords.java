package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Class kế thừa class: extends
//Clas kế thừa Interface
public class Topic_01_Keywords extends Topic_06 implements Topic_02{
    //Chỉ có non-abstract method thôi
    //ko được có abstract method
    //Khởi tạo bình thươờng
    //Chỉ cho kế thừa

    //Data Type
    char c = 'A';
    byte bNumber = 10;
    short sNumber = 100;
    int iNumber = 1400;
    long lNumber = 234324;
    float fNumber = 15.99f;
    double dNumber = 19.39934D;
    boolean marialStatus = true;

    String fullName = null;

    //Access Modifier

    //Variable
    private String studentName = "";
    String studentAddress = "";
    protected int studentAge = 30;
    public double studentPoint = 9.5;

    //Method
    //Ko có kiểu trả về (void)
    private void TC_01() {
        WebDriver driver = new FirefoxDriver();

        Topic_01_Keywords topic = new Topic_01_Keywords();


    }

    void TC_02(){

    }

    protected void TC_03(){

    }

    public void TC_04(){

    }

    @Override
    public void clearStudent() {
    //Tự implement lại ở đây

    }
}
