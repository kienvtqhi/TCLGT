package com.kienvt.tclgt.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnvi on 1/24/16.
 */
public class DataSource {
    public static List<MOffences> getOffencesSample() {
        List<MOffences> offences = new ArrayList<>();
        for (int i=0; i<15; i++) {
            MOffences offences1 = new MOffences();
            offences1.detail  = "Chiếm dụng lòng đường đô thị hoặc hè phố từ 10 m 2 đến dưới 20 m 2 làm nơi trông, giữ xe.";
            offences1.info = "Nghị định số 171/2013/NĐ-CP có hiệu lực 01/01/2014 - Chương 2 mục 2 - Điều 15 Khoản 5 điểm b";
            offences1.money = "<strong>Cá nhân: 5.000.000 - 7.000.000</strong><br/> <strong>Tổ chức: 10.000.000 - 14.000.000</strong><br/> - Biện pháp khắc phục hậu quả: buộc phải khôi phục lại tình trạng ban đầu đã bị thay đổi do vi phạm hành chính gây ra. (Điều 15 khoản 6 điểm b)";

            offences.add(offences1);
        }

        return offences;
    }
}
