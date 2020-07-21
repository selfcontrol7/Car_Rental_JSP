/**
 * <h>Rental Class</h>
 * This class is designed to manage membership.
 * Persistent Type
 * <br>
 * <pre>
 * @author Woochan Park
 * @version 1.0
 * @since 2019-05-27
 * </pre>
 */
package com.rentalsystem.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.Vector;

import com.rentalsystem.beans.Type.InsuranceType;
import com.rentalsystem.conn.ConnectorJ;

public class Rental {
    /**
     * Rental Class
     */
    Vector<Rental> rentals = new Vector<Rental>();
    private static int createRentalID = 0;      //unique rental ID for each instances
    private String rentalID;
    private LocalDate dateDue;
    private LocalDate dateCheckedOut;
    private LocalDate dateExpectedReturn;
    private InsuranceType insuranceOption;
    private String rentalFee;                       //total rental fee
    private int paymentStatus;
    private String residentID;                  // a reference to Customer instances
    private String VIN;                         // a reference to CarItem  instances
    private String reserveID;                   // a reference to Reservation instances

    public Rental() {

        /**
         * This constructor is for use CRUD methods.
         */
    }

    public Rental(LocalDate dateDue, LocalDate dateChekedOut, LocalDate dateExpectedReturn, InsuranceType insuranceOption, int paymentStatus, String residentID, String VIN) {

        /**
         * Constructor without reservation
         */

        this.rentalID = Integer.toString(createRentalID);
        this.dateDue = dateDue;
        this.dateCheckedOut = dateChekedOut;
        this.dateExpectedReturn = dateExpectedReturn;
        this.insuranceOption = insuranceOption;
        this.paymentStatus = paymentStatus;
        this.residentID = residentID;
        this.VIN = VIN;
        this.reserveID = null;                    //there is no constraints 'not null' in DB for this column
//        this.calculateRentalFee();

        createRentalID++;

        this.dbRegisterRental();

    }

    public Rental(LocalDate dateDue, LocalDate dateChekedOut, LocalDate dateExpectedReturn, InsuranceType insuranceOption, int paymentStatus, String residentID, String VIN, String reserveID) {

        /**
         * Constructor with reservation
         */

        this.rentalID = Integer.toString(createRentalID);
        this.dateDue = dateDue;
        this.dateCheckedOut = dateChekedOut;
        this.dateExpectedReturn = dateExpectedReturn;
        this.insuranceOption = insuranceOption;
        this.paymentStatus = paymentStatus;
        this.residentID = residentID;
        this.VIN = VIN;
        this.reserveID = reserveID;
//        this.calculateRentalFee();

        createRentalID++;

        this.dbRegisterRental();
    }

    public boolean deleteRental(String rentalID){
        for(Rental r : rentals) {
            if(r.rentalID == rentalID){
                try{r.finalize();}catch(Throwable e){};
                this.dbDeleteRental(rentalID);
                return true;
            }
        }
        return false;
    }
    protected void finalize() throws Throwable {
        /**
         * Destructor
         */
        this.dateDue = null;
        this.dateCheckedOut = null;
        this.dateExpectedReturn = null;
        this.insuranceOption = null;
//        this.paymentStatus = null;
        this.rentalFee = null;
        this.residentID = null;
        this.VIN = null;
        this.reserveID = null;

        super.finalize();
    }

//    public int calculateRentalFee(){
//        /**
//         * To calculate total rental fee
//         * @local int
//         */
//        this.rentalFee = this.VIN * Period.between(dateCheckedOut,dateExpectedReturn).getDays();
//        return this.rentalFee;
//    }

    public void displayAllRental(){
        /**
         * To display All Rentals
         * @param Nothing
         * @return Nothing
         */
        this.dbRetrieveAllRental();
    }

//    public LocalDate getDateCheckedOut() {
//        /**
//         * To get Date of CheckedOut
//         * @return LocalDate
//         */
//
//        return dateCheckedOut;
//    }
//    public LocalDate getDateExpectedReturn() {
//        /**
//         * To get Date of ExpectedReturn
//         * @return LocalDate
//         */
//
//        return dateExpectedReturn;
//    }
//
//    public void setRentalFee(int adjustAmount) {
//        /**
//         * To make Rental Fee modified by adjustAmount
//         * input : int
//         */
//
//        this.rentalFee += adjustAmount;
//    }

    private boolean dbRegisterRental(){
        /**
         * To register Rental information into DB
         * @param Nothing
         * @return boolean This parameter is to check whether registering has been made completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try {
            Statement stmt = ConnectorJ.con.createStatement();

            /**
             * To make query to insert a new record into Rental table
             */

            StringBuilder sb = new StringBuilder();
            String sql = sb.append("INSERT INTO Rental VALUES(")
                    .append("'" + this.rentalID + "'" + ",")
                    .append("'" + this.dateDue + "'" + ",")
                    .append("'" + this.dateCheckedOut + "'" + ",")
                    .append("'" + this.dateExpectedReturn + "'" + ",")
                    .append("'" + this.insuranceOption + "'" + ",")
                    .append("'" + this.rentalFee + "'" + ",")
                    .append("'" + this.paymentStatus + "'" + ",")
                    .append("'" + this.residentID + "'" + ",")
                    .append("'" + this.VIN+ "'" +",")
                    .append("'" + this.reserveID + "'")
                    .append(");").toString();

            System.out.println(sql);

            stmt.execute("use car_rental_system");
            stmt.execute(sql);
            ConnectorJ.disconnectFromServer();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            ConnectorJ.disconnectFromServer();
            return false;
        }
    }

    private boolean dbRetrieveAllRental(){

        /**
         * To retrieve All Rentals
         * * @param Nothing
         * @return boolean This parameter is to check whether retrieving has done completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append("select * from rental").toString();

            System.out.println(sql);

            try{
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("------------------Retrieving Car Items Data------------------");
                /**
                 * To print Car Item Information from Car Item table.
                 */
                while(rs.next()){
                    System.out.print("Rental ID : " + rs.getString("rentalID"));
                    System.out.println();
                    System.out.print("Due Date: " + rs.getDate("dateDue"));
                    System.out.println();
                    System.out.print("Checked-out Date : " + rs.getDate("dateCheckedOut"));
                    System.out.println();
                    System.out.print("Expected Return Date : " + rs.getDate("dateExpectedReturn"));
                    System.out.println();
                    System.out.print("Insurance Option : " + rs.getString("insuranceOption"));
                    System.out.println();
                    if(rs.getInt("paymentStatus") == 0){
                        System.out.print("Payment : Not Yet Paid");
                        System.out.println();
                    }
                    else if(rs.getInt("paymentStatus") == 0) {
                        System.out.print("Payment : Already Paid");
                        System.out.println();
                    }
                    System.out.print("Resident ID : " + rs.getString("residentID"));
                    System.out.println();
                    System.out.print("VIN : " + rs.getString("VIN"));
                    System.out.println();
                    if(rs.getString("reserveID").equals("null")){
                        System.out.println("'' DIRECT RENTAL WITHOUT RESERVATION''");
                        System.out.println();
                    }
                    else {
                        System.out.print("ReserveID : " + rs.getString("reserveID"));
                        System.out.println();
                    }
                    System.out.println("������������������������������������������������---------------");
                }

                ConnectorJ.disconnectFromServer();
                return true;

            }catch(SQLException e){
                e.printStackTrace();
                ConnectorJ.disconnectFromServer();
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            ConnectorJ.disconnectFromServer();
            return false;
        }

    }

    private boolean dbDeleteRental(String rentalID){

        /**
         * To delete a Rental Record from Database by following condition : rental ID
         * @param   String Rental ID
         * @return boolean
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            /*To make delete query*/

            String sql = sb.append("DELETE FROM Rental where modelCode = ")
                    .append("'"+ rentalID +"';").toString();

            System.out.println(sql);
            stmt.executeUpdate(sql);
            ConnectorJ.disconnectFromServer();

            return true;
        }catch(SQLException e){
            e.printStackTrace();
            ConnectorJ.disconnectFromServer();
            return false;
        }
    }
}
