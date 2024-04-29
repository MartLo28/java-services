package com.contactservice;

public class Contact {
    private final String contactID;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactID, String firstName, String lastName, String phone, String address) {
        if (contactID == null || contactID.length() > 10)
            throw new IllegalArgumentException("Contact ID must not be null and must be less than or equal to 10 characters.");
        if (firstName == null || firstName.length() > 10)
            throw new IllegalArgumentException("First name must not be null and must be less than or equal to 10 characters.");
        if (lastName == null || lastName.length() > 10)
            throw new IllegalArgumentException("Last name must not be null and must be less than or equal to 10 characters.");
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone must be exactly 10 digits and contain only numbers.");
        }
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address must be less than or equal to 30 characters");
        }
            
        
        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getContactID() { return contactID; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10)
            throw new IllegalArgumentException("First name must not be null and must be less than or equal to 10 characters.");
        this.firstName = firstName;
    }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10)
            throw new IllegalArgumentException("Last name must be less than or equal to 10 characters and must not be null.");
        this.lastName = lastName;
    }
    public String getPhone() { return phone; }
    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+"))
            throw new IllegalArgumentException("Phone must be exactly 10 digits and contain only numbers.");
        this.phone = phone;
    }
    public String getAddress() { return address; }
    public void setAddress(String address) {
        if (address == null || address.length() > 30)
            throw new IllegalArgumentException("Address must be less than or equal to 30 characters and must not be null.");
        this.address = address;
    }
}
