package phuongKhueTrung;

import phuongHoaAn.FPTCorporation;

public class VinGroup {
    //Pham vi ngoài class nhưng khác package
    public void showTVName(){
        FPTCorporation fpt = new FPTCorporation();
        fpt.tvName = "Vin LCD";

        fpt.setTvName();
    }
}
