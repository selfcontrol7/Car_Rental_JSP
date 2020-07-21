/**
 * <h>Customer Class/h>
 * <pr>This class is designed to describe Customer</pr>
 * <pre>
 * @author  WooChan Park
 * @ver 1.0
 * @since 2019-05-27
 * </pre>
 */
package com.rentalsystem.beans;

import java.util.Vector;

public class Customer{
    /**
     * Customer Class
     */
    protected static Vector<Customer> customers = new  Vector<Customer>();
    protected String residentID;
    protected String customerName;
    protected String address;
    protected String phoneNumber;
    protected String affiliation; //
    protected String email;
    protected String driverLicense;

    public Customer() {};

    public Customer(String residentID ,String name ,String address ,String phoneNumber , String affiliation, String email, String driverLicense)
    {
        /**
         * Constructor
         */
        /* To check Validity */
        if (driverLicense == null) {
            System.out.println("Invalid License");
            System.exit(0);
        }
        else{
            this.driverLicense = driverLicense;
        }

        this.residentID = residentID;
        this.customerName = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.affiliation = affiliation;
        this.email = email;

        customers.add(this);
    }

    public void displayInfo()
    {
        /**
         * To display Customer Information
         */
        System.out.println("Resident ID :" + this.residentID);
        System.out.println("Customer Name : "+ this.customerName);
        System.out.println("Address : " + this.address);
        System.out.println("PhoneNumber : " + this.phoneNumber);
        System.out.println("Affiliation : " + this.affiliation);
        System.out.println("Email : " + this.email);
        System.out.println("DriverLicense : " + this.driverLicense);
    }

    public String getResidentID() {
        /**
         * To get ResidentID
         */
        return residentID;
    }

    public String getCustomerName() {
        /**
         * To get Name
         */
        return this.customerName;
    }

    public String getAddress(){
        /**
         * To get Address
         */
        return this.address;
    }

    public String getPhoneNumber() {
        /**
         * To get PhoneNumber
         */
        return phoneNumber;
    }

    public String getAffiliation() {
        /**
         * To get Affiliation
         */
        return affiliation;
    }

    public String getEmail() {
        /**
         * To get Email Address
         */
        return email;
    }

    public String getDriverLicense() {
        /**
         * TO get DriversLicense
         */
        return driverLicense;
    }

    public void setName(String name) {
        /**
         * To make Name modified
         */
        this.customerName = name;
    }

    public void setAddress(String address) {
        /**
         * To make Address modified
         */
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        /**
         * To make PhoneNumber modified
         */
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        /**
         * TO make Email modified
         */
        this.email = email;
    }
}
