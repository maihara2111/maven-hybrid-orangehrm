package javaSDET;

import org.testng.annotations.Test;

public class Topic_12_Dynamic_Locator {
    public static void main(String[] args) {
        System.out.println(String.format("//a[text()='%s']", "Personal Details"));
        System.out.println(String.format("//a[text()='%s']", "Dependent"));
        System.out.println(String.format("//a[text()='%s']", "Jobs"));
    }

    @Test
    public void testDynamicLocator() {
        String personalDetailPage = "//a[text()='Personal Details']";
        String dependentPage = "//a[text()='Dependent']";
        String jobPage = "//a[text()='Jobs']";

        String dynamicPage = "//a[text()='%s']";
        String dynamicPageTwoParam = "//div[@class='orangehrm-%s-employee-navigation']//a[text()='%s']";
        String dynamicPageThreeParam = "//td[@data-key='female' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']" +
                "/following-sibling::td[@data-key='males' and text()='%s']";
        String dynamicPageFourParam = "//td[@data-key='female' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']" +
                "/following-sibling::td[@data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";

        //1-Mở trang cụ thể
        openPage(personalDetailPage);
        openPage(dependentPage);
        openPage(jobPage);

        //2-Mở trang theo tên truyền vào (Re-usable)
        //Tham số động là tên page
        //Hàm + Locator
        openPageByName(dynamicPage, "Personal Details");
        openPageByName(dynamicPage, "Dependent");
        openPageByName(dynamicPage, "Jobs");

        //3-Locator có nhiều hơn 1 tham số động câ truyền vào thì sao
        openPageByName(dynamicPageTwoParam, "edit", "Personal Details");
        openPageByName(dynamicPageTwoParam, "new", "Dependent");
        openPageByName(dynamicPageTwoParam, "view", "Jobs");

        //4-Locator có 3 tham số động cần truyền vào
        openPageByName(dynamicPageThreeParam, "64236","Afghanistan","407123");
        openPageByName(dynamicPageThreeParam, "454545","AFRICA","87632");
        openPageByName(dynamicPageThreeParam, "54533","Aegentina","42553");

        //5-Locator có 4 tham số động cần truyền vào
        openPageByName(dynamicPageThreeParam, "64236","Afghanistan","407123", "40723123");
        openPageByName(dynamicPageThreeParam, "454545","AFRICA","87632","2387632");
        openPageByName(dynamicPageThreeParam, "54533","Aegentina","42553","817632");
    }

    public void openPage(String pageLocator) {
        System.out.println("Click to Page: " + pageLocator);
    }

    //Thay thế được
//    public void openPageByName(String pageLocator, String pageName) {
//        System.out.println("Click to Page: " + String.format(pageLocator, pageName));
//    }
//
//    public void openPageByName(String pageLocator, String pageFunction, String pageName) {
//        System.out.println("Click to Page: " + String.format(pageLocator, pageFunction, pageName));
//    }
//
//    public void openPageByName(String pageLocator, String firstParam, String secondParam, String thirdParam) {
//        System.out.println("Click to Page: " + String.format(pageLocator, firstParam, secondParam, thirdParam));
//    }

    public void openPageByName(String pageLocator, String ... restParams) {
        System.out.println("Click to Page: " + String.format(pageLocator, (Object[]) restParams));
    }


}
