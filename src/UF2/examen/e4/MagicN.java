package UF2.examen.e4;

import java.time.LocalDate;
import java.util.concurrent.Callable;

public class MagicN implements Callable<Integer> {
    private LocalDate date;

    MagicN(LocalDate date) {
        this.date = date;
    }

//    public int getMagicNumber() {
//
//        String dateFull = String.valueOf(date.getYear()) +
//                String.valueOf(date.getMonthValue()) +
//                String.valueOf(date.getDayOfMonth());
//
//        int sum = 0;
//        int num = Integer.parseInt(dateFull);
//        while(num > 9) {
//            while (num > 0) {
//                sum = sum + num % 10;
//                num = num / 10;
//            }
//            num = sum;
//            sum = 0;
//        }
//        return num;
//    }


    @Override
    public Integer call() throws Exception {
        String dateFull = String.valueOf(date.getYear()) +
                String.valueOf(date.getMonthValue()) +
                String.valueOf(date.getDayOfMonth());

        int sum = 0;
        int num = Integer.parseInt(dateFull);
        while(num > 9) {
            while (num > 0) {
                sum = sum + num % 10;
                num = num / 10;
            }
            num = sum;
            sum = 0;
        }
        return num;
    }
}
