/**
 * <h>Member Class/h>
 * <p>This class is to describe subclass of Customer : membership</p>
 * <pre>
 * @author Woochan Park
 * @version 1.0
 * @since 2019-05-26
 * </pre>
 */
package com.rentalsystem.beans;

import com.rentalsystem.beans.Type.CreditCard;
import com.rentalsystem.beans.Type.MemberType;
import com.rentalsystem.conn.ConnectorJ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

public class Member extends Customer {
    /**
     * Member Class
     */
    private static Vector<Member> members = new Vector<Member>();
    private String memberID;
    private MemberType memberType;
    private int creditRate;
    private CreditCard creditCard;
    LocalDate joiningDate;
    LocalDate expirationDate;

    public Member(){
        /**
         * This constructor is for use CRUD methods.
         */
    };

    public Member(String residentID,String name, String address, String phoneNumber, String affiliation, String email, String driverLicense, String memberID,  MemberType memberType, int creditRate, CreditCard creditCard, LocalDate joiningDate,LocalDate expirationDate){
        /**
         * Constructor
         */
        /* initialize SuperClass : Customer */
        super(residentID,name,address,phoneNumber,affiliation, email,driverLicense);

        /* initialize SubClass : Member */
        this.memberID = memberID;
        this.memberType = memberType;
        this.creditRate = creditRate;
        this.creditCard = creditCard;
        this.joiningDate = joiningDate;
        this.expirationDate = expirationDate;

        members.add(this);
        this.dbRegisterMembership();
    }

    @Override
    public void finalize()throws Throwable{
        /* SuperClass : Customer */
        super.driverLicense = null;
        super.residentID = null;
        super.customerName = null;
        super.address = null;
        super.phoneNumber = null;
        super.affiliation = null;
        super.email = null;

        /*SubClass : Member */
        this.memberID = null;
        this.creditRate = Integer.parseInt(null);
        this.memberType = null;
        this.creditCard = null;
        this.joiningDate = null;
        this.expirationDate = null;
        super.finalize();
    }

    public void displayAllMemberInfo(){
        this.dbRetrieveAllMembers();
    }

    public void displayOneMemberInfo(String memberID){
        this.dbRetrieveOneMember(memberID);
    }

    public void modifyMembeInfo(String memberID, int creditrate, CreditCard creditCard){
        this.dbModifyMembership(memberID,creditRate,creditCard);
    }

    /* to inactivate membership */
    public void inactiveMembership(String memberID){
        /**
         * To inactivate a membership which meets following condition : member ID
         * @param String member ID
         */
        for(Member m : members)
            if(m.memberID == memberID){
                try{m.finalize();}catch (Throwable e){};
            }
        dbInactiveMembership(memberID);
    }

    public boolean dbRegisterMembership(){

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
                    .append("'"+ this.residentID+"'"+",")
                    .append("'"+ this.customerName+"'"+",")
                    .append("'"+ this.address+ "'" + ",")
                    .append("'"+ this.phoneNumber+ "'" + ",")
                    .append("'"+ this.affiliation+"'" + ",")
                    .append("'"+ this.email+"'" + ",")
                    .append("'"+ this.driverLicense+"'")
                    .append(");").toString();

            System.out.println(sql);


            stmt.execute("use car_rental_system");
            stmt.execute(sql);

            /**
             * To make second query to input record in Sub Class table  : Member
             */

            sb = new StringBuilder();
            sql = sb.append("INSERT INTO member VALUES(")
                    .append("'"+ this.memberID+"'"+",")
                    .append("'"+ this.memberType+"'"+",")
                    .append("'"+ this.creditRate+ "'" + ",")
                    .append("'"+ this.creditCard+ "'" + ",")
                    .append("'"+ this.joiningDate+"'" + ",")
                    .append("'"+ this.expirationDate+"'" + ",")
                    .append("'"+ this.residentID+"'")
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

    private boolean dbRetrieveAllMembers(){

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

    private boolean dbRetrieveOneMember(String memberID){

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

            /*
            Give query a condition to find one record : memberID is unique
             */

            String sql = sb.append("select * from member where memberId = ")
                    .append("'"+ memberID + "';" ).toString();

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

    private boolean dbModifyMembership(String memberID ,int creditRate, CreditCard creditCard){
        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

        /**
         *To modify credit rate and type of credit card from membership information
         * @param String member ID
         * @param int   credit Rate
         * @param CreditCard
         * @return boolean
         */

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append(" UPDATE member set ")
                    .append("creditRate ="+ creditRate +",")
                    .append("creditCard ="+ "'"+ creditCard.toString() +"'")
                    .append(" where memberId = "+ "'"+ memberID+ "'" + ";")
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

    private boolean dbInactiveMembership(String memberID){
        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();
        /**
         * To inactive Membership
         * @param String member ID
         * @return boolean
         */

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            /**
             * To make query to delete a record by parameter member's member ID
             */
            String sql = sb.append("delete from member where member.memberID =")
                    .append("'"+ memberID +"'")
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
