package core;

import java.io.File;

public class GlobalConstants {
    //System infor
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");

    //ORANGE HRM App infor
    public static final String ORANGEHRM_URL = "http://localhost:88/orangehrm";

    public static final String ORANGEHRM_ADMIN_USERNAME = "maihara";
    public static final String ORANGEHRM_ADMIN_PASSWORD = "M@idtn2111";


    //Opencart App infor
    public static final String OPENCART_USER_URL = "http://localhost:88/opencart";
    public static final String OPENCART_ADMIN_URL = "http://localhost:88/opencart/adm/";

    public static final String OPENCART_ADMIN_USERNAME = "maihara";
    public static final String OPENCART_ADMIN_PASSWORD = "M@idtn2111";

    //Wait Infor
    public static final int SHORT_TIMEOUT = 5;
    public static final int LONG_TIMEOUT = 15;

    //Download/Upload file
    public static final String UPLOAD_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + File.separator + "downloadFiles" + File.separator;

    //Retry Case Failed
    public static final int RETRY_NUMBER = 3;

    //Browser Logs/Extension
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + File.separator + "browserExtensions" + File.separator;

    //HTML Report Folder
    public static final String REPORTING_PATH = PROJECT_PATH + File.separator + "htmlReportNG" + File.separator;
    public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "htmlExtent" + File.separator;
    public static final String ALLURE_PATH = PROJECT_PATH + File.separator + "htmlAllure" + File.separator;

    //Data Test/Environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + File.separator + "dataTest" + File.separator;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + File.separator + "environmentConfig" + File.separator;
}
