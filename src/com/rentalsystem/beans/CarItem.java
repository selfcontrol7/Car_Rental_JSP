/**
 * <h>This class describes Car Item</h>
 * <p>
 * <pre>
 * @author Woochan Park
 * @version 1.1
 * @since 2019-5-27
 * </pre>
 */

//Usage : CarItem carItem1 = new CarItem("2549","WHITE", StatusType.GOOD,3000, yesterday,LocalDate.of(9999,12,31),3000, camry);

package com.rentalsystem.beans;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

import com.rentalsystem.beans.Type.StatusType;
import com.rentalsystem.conn.ConnectorJ;

public class CarItem {
    /**
     * CarItem Class
     */
    public static Vector<CarItem> carItems = new Vector<CarItem>();
    private String VIN;
    private String color;
    private static StatusType carItemStatus;
    private String purchasedPrice;
    private LocalDate datePurchased;
    private LocalDate dateDiscarded;
    private String feePerDay;
    private String modelCode; // a reference to carModel instances

    public CarItem(){
        /**
         * This constructor is for use CRUD methods.
         */
    };
    public CarItem(String VIN, String color, StatusType  carItemStatus, String purchasedPrice,LocalDate datePurchased,LocalDate dateDiscarded, String feePerDay, String modelCode){
        /**
         * Constructor
         */
        /* initiate member variables */
        this.VIN = VIN;
        this.color = color;
        this.carItemStatus = carItemStatus;
        this.purchasedPrice = purchasedPrice;
        this.datePurchased = datePurchased;
        this.dateDiscarded = dateDiscarded;
        this.feePerDay = feePerDay;
        this.modelCode = modelCode;

        carItems.add(this);
        this.dbregisterCarItem();
    }

    @Override
    protected void finalize() throws Throwable{
        /**
         * Destructor
         */
        this.VIN = null;
        this.color = null;
        this.carItemStatus = null;
        this.purchasedPrice = null;
        this.datePurchased = null;
        this.dateDiscarded = null;
        this.feePerDay = null;
        this.modelCode = null;

        super.finalize();
    }

    public void dispplayAllCarItemInfo(){
        /**
         * To display all attributes of all Car Item
         * @param String model Code
         * @return Nothing
         */
        this.dbRetrieveAllCarItem();

    }
    public void displayOneCarItemInfo(String modelCode) {

        /**
         * To display all attributes
         * @param String model Code
         * @return Nothing
         */

        this.dbRetrieveCarItemByModelCode(modelCode);
    }

    public void modifyCarItem(String VIN, StatusType statusType, String feePerDay){

        /**
         * To modify Car Item's status, fee per day Information
         * @param   String VIN
         * @param   StatusType Car Item's status : enum
         * @param   String feePerDay
         * @return Nothing
         */
        this.dbModifiyCarItem(VIN, statusType, feePerDay);
    }

    public boolean deleteCarItem(String VIN){
        /**
         * To delete Car Item both instance and db record by VIN
         * @param   String VIN
         * @return Nothing
         */
        for(CarItem c : carItems) {
            if(c.VIN == VIN){
                try{c.finalize();}catch(Throwable e){};
                this.dbDiscardCarItem(VIN);
                return true;
            }
        }
        return false;
    }


//    public String getVIN(){
//        /**
//         * To get VIN of carItem
//         * @param Nothing
//         * @return Nothing
//         */
//        return this.VIN;
//    }
//
//
//    public String getColor() {
//        /**
//         * To get Color of carItem
//         * @param Nothing
//         * @return Nothing
//         */
//        return color;
//    }
//
//    public static StatusType getCarItemStatus() {
//        /**
//         * To get CarItem's status
//         * @param Nothing
//         * @return StatusType This parameter is enum type
//         */
//        return carItemStatus;
//    }
//
//    public int getPurchasedPrice() {
//        /**
//         * To get purchased price of carItem
//         * @param Nothing
//         * @return int
//         */
//        return purchasedPrice;
//    }
//
//    public LocalDate getDatePurchased(){
//        /**
//         * To get the date when carItem was purchased
//         * @param Nothing
//         * @return Localdate
//         */
//        return this.datePurchased;
//    }
//    public LocalDate getDateDiscarded() {
//        /**
//         * To get the date when carItem was discarded
//         * @param Nothing
//         * @return Localdate
//         */
//        return dateDiscarded;
//    }
//
//    public int getFeePerDay() {
//        /**
//         * To get fee per day
//         * @param Nothing
//         * @return int This returns Fee for one day of Car Item
//         */
//        return feePerDay;
//    }
//
//    public CarModel getCarModel(){
//        /**
//         * To get Car Model of carItem
//         * @param Nothing
//         * @return Nothing
//         */
//        return carModel;
//    }
//
//    public void setFeePerDay(int feePerDay) {
//        /**
//         * To make fee per day modified
//         * @param int This parameters sets feePerDay of Car Item
//         * @return Nothing
//         */
//        this.feePerDay = feePerDay;
//    }

    private boolean dbregisterCarItem(){
        /**
         * To register a Car Item into DB
         * @param Nothing
         * @return boolean This parameter is to check whether registering has been made completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try {
            Statement stmt = ConnectorJ.con.createStatement();

            /**
             * To make query to insert a new Car Item Record
             */

            StringBuilder sb = new StringBuilder();
            String sql = sb.append("INSERT INTO CarItem VALUES(")
                    .append("'" + this.VIN + "'" + ",")
                    .append("'" + this.color + "'" + ",")
                    .append("'" + this.carItemStatus + "'" + ",")
                    .append("'" + this.purchasedPrice + "'" + ",")
                    .append("'" + this.datePurchased + "'" + ",")
                    .append("'" + this.dateDiscarded + "'" + ",")
                    .append("'" + this.feePerDay+ "'" + ",")
                    .append("'" + this.modelCode + "'")
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

    private boolean dbRetrieveAllCarItem(){

        /**
         * To retrieve All Car Items
         * @param Nothing
         * @return boolean This parameter is to check whether retrieving has done completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append("select * from car_rental_system.carItem").toString();

            System.out.println(sql);

            try{
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("------------------Retrieving Car Items Data------------------");
                /**
                 * To print Car Item Information from Car Item table.
                 */
                
                while(rs.next()){
                    System.out.print("VIN : " + rs.getString("VIN"));
                    System.out.println();
                    System.out.print("Car Color: " + rs.getString("color"));
                    System.out.println();
                    System.out.print("Car Status : " + rs.getString("CarItemStatus"));
                    System.out.println();
                    System.out.print("Purchased Price : " + rs.getString("purchasedPrice"));
                    System.out.println();
                    System.out.print("Purchased Date : " + rs.getDate("datePurchased"));
                    System.out.println();
                    System.out.print("Purchased Discarded : " + rs.getDate("dateDiscarded"));
                    System.out.println();
                    System.out.print("Fee For One Day : " + rs.getString("feePerDay"));
                    System.out.println();
                    System.out.print("Car Model Code : " + rs.getString("modelCode"));
                    System.out.println();
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

    private boolean dbRetrieveCarItemByModelCode(String modelCode){

        /**
         * To retrieve a Car Item in certain condition : Model Code
         * @param String model Code
         * @return boolean This parameter is to check whether retrieving has done completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append("select * from carItem where ")
                    .append("modelCode = "+ "'"+ modelCode +"'"+";").toString();

            System.out.println(sql);

            try{
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("------------------Retrieving A Car Items Data------------------");
                /**
                 * To print Car Item Information from Car Item table.
                 */
                while(rs.next()){
                    System.out.print("VIN : " + rs.getString("VIN"));
                    System.out.println();
                    System.out.print("Car Color: " + rs.getString("color"));
                    System.out.println();
                    System.out.print("Car Status : " + rs.getString("CarItemStatus"));
                    System.out.println();
                    System.out.print("Purchased Price : " + rs.getString("purchasedPrice"));
                    System.out.println();
                    System.out.print("Purchased Date : " + rs.getDate("datePurchased"));
                    System.out.println();
                    System.out.print("Purchased Discarded : " + rs.getDate("dateDiscarded"));
                    System.out.println();
                    System.out.print("Fee For One Day : " + rs.getString("feePerDay"));
                    System.out.println();
                    System.out.print("Car Model Code : " + rs.getString("modelCode"));
                    System.out.println();
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

    private boolean dbModifiyCarItem(String VIN, StatusType statusType, String feePerDay){
        /**
         *To modify car item's status and fee
         * @param String VIN
         * @param StatusType
         * @param String  fee for one day of this car item
         * @return boolean This parameter is to check whether retrieving has done completely or not.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append(" UPDATE carItem set ")
                    .append("CarItemStatus ="+ "'"+ statusType.toString() + "'"+",")
                    .append("feePerDay ="+ feePerDay +"'")
                    .append("where VIN = "+ "'"+ VIN + "'" + ";")
                    .toString();

            System.out.println(sql);
            stmt.executeUpdate(sql);

            return true;

        }catch(SQLException e){
            e.printStackTrace();
            ConnectorJ.disconnectFromServer();
            return false;
        }
    }

    private boolean dbDiscardCarItem(String VIN){

        /**
         * To discard Car Item
         * @param String Car Model
         * @return boolean
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            /**
             * To make query to delete a record by parameter : carItem's VIN
             */

            String sql = sb.append("delete from carItem where VIN = ")
                    .append("'"+ VIN +"'")
                    .append(";").toString();

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

