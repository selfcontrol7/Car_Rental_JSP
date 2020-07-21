/**
 * <h>Reservation Class</h>
 * <p>This class is designed to represent Reservation</p>
 *<pre>
 * @author Woo Chan Park
 * @version 1.0
 * @since 2019-05-27
 *</pre>
 */

package com.rentalsystem.beans;

import java.time.LocalDate;
import java.util.Vector;

public class Reservation {
    /**
     * Reservation Class
     */
    private static Vector<Reservation> reservations = new Vector<Reservation>();
    private static int createReserveID = 0;      //to assign unique ID for each instances
    private String reserveID;                  //unique reservation ID for each instances
    private LocalDate dateReserved;
    private LocalDate datePickUP;
    private LocalDate dateExpectedReturn;
    private String pickUpPlace;
    private String residentID;            // a reference to Customer instances
    private String modelCode;              // a reference to CarModel instances

    public Reservation(){
        /**
         * This constructor is for use CRUD methods.
         */
    }

    public Reservation(LocalDate dateReserved, LocalDate datePickUP, LocalDate dateExpectedReturn , String pickUpPlace, String residentID, String modelCode) {

        /**
         * Constructor
         */

        this.reserveID = Integer.toString(createReserveID++);
        this.dateReserved = dateReserved;
        this.datePickUP = datePickUP;
        this.dateExpectedReturn = dateExpectedReturn;
        this.pickUpPlace = pickUpPlace;
        this.residentID = residentID;
        this.modelCode = modelCode;

        reservations.add(this);                    //transient object
    }

    public static boolean modifyReservation(String reserveID, LocalDate datePickUP, LocalDate dateExpectedReturn, String pickUpPlace) {
        /**
         * To modify Reservation
         * @param LocalDate first  parameter : date when customer pickup
         * @param LocalDate second parameter : date when customer is expected to return CarItem
         * @param String    third  parameter : place where customer is going to pick his/her CarItem
         * @return boolean
         */
            for(Reservation r : reservations) {
                if(r.residentID == reserveID) {
                    r.datePickUP = datePickUP;
                    r.dateExpectedReturn = dateExpectedReturn;
                    r.pickUpPlace = pickUpPlace;
                    System.out.println("Reservation has changed completely.");
                    return true;
                }
            }
            return false;
    }

    protected void finalize()throws Throwable {
        /**
         * Destructor
         */
        this.dateReserved = null;
        this.datePickUP = null;
        this.dateExpectedReturn = null;
        this.pickUpPlace = null;
        this.residentID = null;
        this.modelCode = null;

        super.finalize();

        System.out.println("Reservation has been deleted completely.");
    }

    public static void displayOneReservationInfo(String reserveID)
    {
        /**
         * To display all attributes of one reservation which meet following condition : reserveID
         * @param String reserveID
         * @return Nothing
         */

        for(Reservation r : reservations) {
                if(r.reserveID.equals(reserveID)){
                    for(Customer c : Customer.customers)
                        if (c.residentID == r.residentID) {
                            System.out.println("Customer Name  : " + c.customerName);
                            System.out.println("Phone Number  : " + c.phoneNumber);
                        }
                System.out.println("Resident ID : " + r.residentID);
                System.out.println("DateReserved : " + r.dateReserved);
                System.out.println("DatePickUP : " + r.datePickUP);
                System.out.println("DateExpectedReturn :" + r.dateExpectedReturn);
                System.out.println("PickUpPlace : " + r.pickUpPlace);
            }
        }
    }

    public static void displayAllReservationInfo() {
        /**
         * To list all reservation informations
         */
        System.out.println("------------------Display All Reservation Information------------------");
        for(Reservation r : reservations) {
                for(Customer c : Customer.customers) {
                    if(c.residentID == r.residentID) {
                        System.out.println("Customer Name  : " + c.customerName);
                        System.out.println("Phone Number  : " + c.phoneNumber);
                    }
                }
                System.out.println("Reserve ID :" + r.reserveID);
                System.out.println("DateReserved : " + r.dateReserved);
                System.out.println("DatePickUP : " + r.datePickUP);
                System.out.println("DateExpectedReturn :" + r.dateExpectedReturn);
                System.out.println("PickUpPlace : " + r.pickUpPlace);
            System.out.println("--------------------------------------------------------");
            }
    }


    public static String getReserveID(String residentID, String modelCode) {
        for( Reservation r : reservations) {
            if(r.residentID == residentID && r.modelCode == modelCode)
            {
                return r.reserveID;
            }
        }
        return "There is no reservation meeting conditions.";
    }
}
