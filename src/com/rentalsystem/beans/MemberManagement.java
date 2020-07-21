/**
 * <h>Membership Management</h>
 * This class is designed to manage membership.
 * <br>
 * <pre>
 * @author Woochan Park
 * @version 1.0
 * @since 2019-05-27
 * </pre>
 */
package com.rentalsystem.beans;
import com.rentalsystem.conn.ConnectorJ;

import java.sql.*;

public class MemberManagement{

    public static boolean registerMembership(Member member){

        /**
         * To register Membership into DB
         * @param Member
         * @return boolean This parameter is to check whether registering has been made completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        try {
            Statement stmt = ConnectorJ.con.createStatement();


            /**
             * To make first query to input record in SuperClass table  : Customer
             */

            StringBuilder sb = new StringBuilder();
            String sql = sb.append("INSERT INTO customer VALUES(")
                    .append("'"+member.getResidentID()+"'"+",")
                    .append("'"+member.getCustomerName()+"'"+",")
                    .append("'"+member.getAddress()+ "'" + ",")
                    .append("'"+member.getPhoneNumber()+ "'" + ",")
                    .append("'"+member.getAffiliation()+"'" + ",")
                    .append("'"+member.getEmail()+"'" + ",")
                    .append("'"+member.getDriverLicense()+"'")
                    .append(");").toString();

            System.out.println(sql);


            stmt.execute("use car_rental_system");
            stmt.execute(sql);

            /**
             * To make second query to input record in Sub Class table  : Member
             */

            sb = new StringBuilder();
            sql = sb.append("INSERT INTO member VALUES(")
                    .append("'"+ member.getMemberID()+"'"+",")
                    .append("'"+ member.getMemberType().toString()+"'"+",")
                    .append("'"+ member.getCreditRate()+ "'" + ",")
                    .append("'"+ member.getCreditCard()+ "'" + ",")
                    .append("'"+ member.getJoiningDate()+"'" + ",")
                    .append("'"+ member.getExpirationDate()+"'" + ",")
                    .append("'"+ member.getResidentID()+"'")
                    .append(")").toString();


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

    public static boolean retrieveMembership(){
        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        /**
         * To retrieve membership information  in DB
         * @param Nothing
         * @return boolean This parameter is to check whether retrieving has done completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append("select * from member;").toString();

            try{
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("------------------Retrieving Membership Data------------------");
                /**
                 * To print Membership Information one by one from Member table.
                 */
                while(rs.next()){
                    System.out.print("Member ID : " + rs.getString("memberID"));
                    System.out.println();
                    System.out.print("Member Type : " + rs.getString("memberType"));
                    System.out.println();
                    System.out.print("Credit Rate : " + rs.getInt("creditRate"));
                    System.out.println();
                    System.out.print("Joining Date : " + rs.getDate("joiningDate"));
                    System.out.println();
                    System.out.print("Expiration Date : " + rs.getDate("expirationDate"));
                    System.out.println();
                    System.out.print("Resident ID : " + rs.getString("residentID"));
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

    public static boolean modifyMembership(Member member,int creditRate, CreditCard creditCard){
        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        /**
         *To modify credit rate and type of credit card from membership information
         * @param Member
         * @param int
         * @param CreditCard
         * @return boolean
         */

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append(" UPDATE member set ")
                            .append("creditRate ="+ creditRate +",")
                            .append("creditCard ="+ "'"+ creditCard.toString() +"'")
                            .append("where memberId = "+ "'"+ member.getMemberID()+ "'" + ";")
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

    public static boolean inactiveMembership(Member member){
        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();
        /**
         * To inactive Membership
         * @param Member
         * @return boolean
         */
        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            /**
             * To make query to delete a record by parameter member's member ID
             */
            String sql = sb.append("delete from member where member.memberID =")
                            .append("'"+ member.getMemberID() +"'")
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
