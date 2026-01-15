package phuongHoaAn;

public class TPBank {
    //Pham vi ngoài Class nhung cùng 1 package
    public void showTVName() {
        FPTCorporation fpt = new FPTCorporation();
        fpt.tvName = "TPBank LCD"; //Thuoc tinh

        fpt.setTvName(); //Phuong thức

        fpt.tvColor = "";
        fpt.setTvColor();
        fpt.tvChannel = "";
        fpt.setTvChannel();
    }

}
