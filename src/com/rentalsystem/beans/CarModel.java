/**
 * <h>Car Model Class</h>
 *  This class describes Car Model.
 * <br>
 * <pre>
 * @author WooChan Park
 * @version 1.0
 * @since 2019-05-26
 * </pre>
 */
package com.rentalsystem.beans;
import com.rentalsystem.conn.ConnectorJ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarModel {

    private String modelCode;
    private String modelName;
    private String carType;
    private int modelYear;
    private String manufacturer;

    public CarModel(){
        /**
         * This constructor is for use CRUD methods.
         */
    }

    public CarModel(String modelCode, String name, String type, int modelYear, String manufacturer) {

        /**
         * Constructor
         */
        this.modelCode = modelCode;
        this.modelName = name;
        this.carType = type;
        this.modelYear = modelYear;
        this.manufacturer = manufacturer;

        dbRegisterCarModel();

    }

    public void deleteCarModels(String modelCode){

        /**
         * Destructor
         */

        dbDeleteCarModel(modelCode);

    }

    public void displayModelInfo(String modelCode) {

        /**
         * To display all information of Car Model from DB
         * @param String modelCode
         * @return Nothing
         */

        dbRetrieveOneCarModel(modelCode);
    }

    public void displayAllModels()
    {
        /**
         * To display all information of entire Car Models from DB
         * @param nothing
         * @return Nothing
         */

        dbRetrieveALLCarModel();
    }


    public void searchCarModelByCarType(String carType) {

        /**
         * To search Car Models which meets following condition : Car Type
         * Using Database Operation
         * @param String carType
         * @return Nothing
         */

        dbRetrieveCarIModelByCarType(carType);
    }


    public String getModelCode() {
        return this.modelCode;
    }

    public String getName() {
        return this.modelName;
    }

    public String getCarType() {
        return this.carType;
    }

    private boolean dbRegisterCarModel() {

        /**
         * To register a Car Model into DB
         * @param Nothing
         * @return boolean This parameter is to check whether registering has been made completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try {
            Statement stmt = ConnectorJ.con.createStatement();

            /**
             * To make query to insert a new Car Model Record
             */

            StringBuilder sb = new StringBuilder();
            String sql = sb.append("INSERT INTO CarModel VALUES(")
                    .append("'" + this.modelCode + "'" + ",")
                    .append("'" + this.modelName + "'" + ",")
                    .append("'" + this.carType + "'" + ",")
                    .append("'" + this.modelYear + "'" + ",")
                    .append("'" + this.manufacturer + "'")
                    .append(");").toString();

            System.out.println(sql);

            stmt.execute("use car_rental_system");
            stmt.execute(sql);
            ConnectorJ.disconnectFromServer();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            ConnectorJ.disconnectFromServer();
            return false;
        }
    }

    private boolean dbRetrieveOneCarModel(String modelCode){

        /**
         * To retrieve One Car Model when this.displayInfo() is called.
         * @param String modelCode
         * @return boolean This parameter is to check whether retrieving has done completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append("SELECT * FROM CarModel where modelCode = ")
                    .append("'"+ modelCode +"'"+";").toString();

            System.out.println(sql);

            try{

                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("------------------Display Model's Information------------------");

                /**
                 * To print a Car Model's Information
                 */

                while(rs.next()){
                    System.out.print("Model Code : " + rs.getString("modelCode"));
                    System.out.println();
                    System.out.print("Model Name: " + rs.getString("modelName"));
                    System.out.println();
                    System.out.print("Type of Car : " + rs.getString("carType"));
                    System.out.println();
                    System.out.print("Model Year : " + rs.getString("modelYear"));
                    System.out.println();
                    System.out.print("Manufacturer : " + rs.getString("manufacturer"));
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

    private boolean dbRetrieveALLCarModel(){

        /**
         * To retrieve All Car Models
         * @param Nothing
         * @return boolean This parameter is to check whether retrieving has done completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append("SELECT * FROM CarModel;").toString();

            System.out.println(sql);

            try{

                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("------------------Display All Model's Information------------------");

                /**
                 * To print a Car Model's Information
                 */

                while(rs.next()){
                    System.out.print("Model Code : " + rs.getString("modelCode"));
                    System.out.println();
                    System.out.print("Model Name: " + rs.getString("modelName"));
                    System.out.println();
                    System.out.print("Type of Car : " + rs.getString("carType"));
                    System.out.println();
                    System.out.print("Model Year : " + rs.getString("modelYear"));
                    System.out.println();
                    System.out.print("Manufacturer : " + rs.getString("manufacturer"));
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

    private boolean dbRetrieveCarIModelByCarType(String carType){

        /**
         * To retrieve CarModels which meets following condition : Car Type
         * @param Nothing
         * @return boolean This parameter is to check whether retrieving has done completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append("SELECT * FROM CarModel WHERE carType =")
                    .append("'"+carType +"'"+";").toString();

            System.out.println(sql);

            try{
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("------------------ Display "+carType+" Type Car Model ------------------");

                /**
                 * To print Car Model Information
                 */

                while(rs.next()){
                    System.out.print("Model Code : " + rs.getString("modelCode"));
                    System.out.println();
                    System.out.print("Model Name: " + rs.getString("modelName"));
                    System.out.println();
                    System.out.print("Type of Car : " + rs.getString("carType"));
                    System.out.println();
                    System.out.print("Model Year : " + rs.getString("modelYear"));
                    System.out.println();
                    System.out.print("Manufacturer : " + rs.getString("manufacturer"));
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

    private boolean dbDeleteCarModel(String modelCode){

        /**
         * To delete Car Model record from Database
         * @param   String modelCode
         * @return boolean
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            /*To make delete query*/

            String sql = sb.append("DELETE FROM CarModel where modelCode = ")
                    .append("'"+ modelCode +"';").toString();

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
