import java.net.ConnectException;
import java.sql.*;
import java.time.LocalDate;

public class Main {

    public static LocalDate yesterday = LocalDate.now().minusDays(1);
    public static LocalDate today = LocalDate.now();
    public static LocalDate tomorrow = today.plusDays(1);
    public static LocalDate dayAfterTomorrow = today.plusDays(2);

    public static void main(String[] args)
    {

        MemberManageControl.controlManage();

        /**
         * To create Carmodel Instances
         */
        CarModel carmodel = new CarModel();
        CarModel camry = new CarModel("1001","CAMRY","SEDAN", 2019,"TOYOTA");
        CarModel rav4 = new CarModel("1002","RAV4","SUV", 2019,"TOYOTA");
        CarModel m3 = new CarModel("2001","M3","SEDAN", 2017,"BMW");
        CarModel x5 = new CarModel("2002","X5","SUV", 2018,"BMW");
        CarModel s350 = new CarModel("3001","S350","SEDAN", 2017,"BENZ");
        CarModel gla = new CarModel("3002","GLA","SUV", 2018,"BENZ");
        CarModel palisade = new CarModel("4001","PALISADE","SUV", 2019,"HYUNDAI");
        CarModel g70 = new CarModel("4002","G70","SEDAN", 2018,"HYUNDAI");


        CarItem carItem1 = new CarItem("2549","WHITE", StatusType.GOOD,"3000", yesterday,LocalDate.of(9999,12,31),"3000", "1001");
        CarItem carItem2 = new CarItem("4887","BLACK", StatusType.FINE,"5000",yesterday,LocalDate.of(9999,12,31),"1500", "1002");
        CarItem carItem3 = new CarItem("9625","RED", StatusType.BAD,"7000",today,LocalDate.of(9999,12,31),"2000","2001");

        ReservationManageContorl.controlReserve();


//        System.out.println("----------------Display Reservations---------------");
//        res1.displayInfo();
//        System.out.println("\n");
//        res2.displayInfo();
//        System.out.println("\n");
//        res3.displayInfo();
//        System.out.println("\n");
//
//        /**
//         * To create Rental Instances
//         */

        Rental rental1 = new Rental(tomorrow, today, tomorrow, InsuranceType.COMPREHENSIVE, 1, "11111", "2549");
        Rental rental2 = new Rental(tomorrow, today, tomorrow, InsuranceType.COLLISION,0,"22222", "4887");
        Rental rental3 = new Rental(tomorrow, today, tomorrow, InsuranceType.LIABILITY,0 ,"33333", "9625");
//
//        System.out.println("----------------Display CarItems---------------");
//        rental1.display();
//        System.out.println("\n");
//        rental2.display();
//        System.out.println("\n");
//        rental3.display();
//        System.out.println("\n");
//
//        res1.modifyReservation(dayAfterTomorrow,tomorrow,"ITAEWON");
//
    }

}
