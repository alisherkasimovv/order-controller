package uz.orders.configs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ReferenceGenerator {

    private int reference;

    /**
     * Returns generated reference number which consist of:
     *
     * REF0000      - reference number
     * D            - from that letter starts date
     * 2020         - year
     * 01           - month
     * 01           - day
     * DI,DG,DO     - Document type (DI - income, DG - outgo, DO - order)
     *
     * @return REF0000D20200101DI
     */
    public String generateReferenceNumber(LocalDateTime localDateTime, String documentType) {

        return "REF"
                + generator()
                + "D"
                + localDateTime.getYear()
                + localDateTime.getMonthValue()
                + localDateTime.getDayOfMonth()
                + "D"
                + documentType;
    }

    private String generator() {
        String ref = "";
        if (reference < 10) {
            ref =  "000" + reference;
        } else if (reference < 100) {
            ref = "00" + reference;
        } else if (reference < 1000) {
            ref = "0" + reference;
        } else if (reference == 1000) {
            ref = "" + 1000;
            reference = 0;
        }

        return ref;
    }

}
