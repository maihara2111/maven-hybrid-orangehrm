package javaSDET;

public class Topic_11_String {
    public static void main(String[] args) {
        String a = "Automation Testing";
        String b = a;
        String c = b;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        String locator = "XPATH=//a[text()='Add Employee']";
        //locator = locator.substring(3);

        String[] locatorArr = locator.split("=",2);
        System.out.println(locatorArr[0]);
        System.out.println(locatorArr[1]);
        //System.out.println(locatorArr[2].trim());

        //Null
        locator = null;

        //empty
        locator = "";

    }
}
