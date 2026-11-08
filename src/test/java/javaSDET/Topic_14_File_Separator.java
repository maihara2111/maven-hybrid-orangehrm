package javaSDET;

import java.io.File;

public class Topic_14_File_Separator {
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String UPLOAD_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;

    public static void main(String[] args) {
        System.out.println(PROJECT_PATH);
        System.out.println(UPLOAD_PATH);
    }
}
