package edu.uw.data.lecture7.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by credmond on 26/03/15.
 */
@NamedQueries({
        @NamedQuery(name = "Customer.findAll",    query = "SELECT c from Customer c")

        , @NamedQuery(name = "Customer.findByCustomerFirstAndLast",
        query = "SELECT c FROM Customer c  WHERE c.contactFirstname = :firstName   AND   c.contactLastname = :lastName ")

//     TODO Lab query for "Customer.findByCity" @NamedQuery  goes Here")

        })
@Entity
@Table(name = "CUSTOMERS" )

@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class Customer {
  private Integer customerNumber;
  private String customerName;
  private String contactLastname;
  private String contactFirstname;
  private String phone;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String state;
  private String postalCode;
  private String country;
  //private Integer salesRepEmployeeNumber;
  private Double creditLimit;

  private Set<Order> orders = new HashSet<>();
  private Employee salesRep ;

  @Id
  @Column(name = "CUSTOMERNUMBER")
  public Integer getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(Integer customernumber) {
    this.customerNumber = customernumber;
  }

  @Basic
  @Column(name = "CUSTOMERNAME", nullable = true, insertable = true, updatable = true, length = 50)
  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customername) {
    this.customerName = customername;
  }

  @Basic
  @Column(name = "CONTACTLASTNAME", nullable = true, insertable = true, updatable = true, length = 50)
  public String getContactLastname() {
    return contactLastname;
  }

  public void setContactLastname(String contactlastname) {
    this.contactLastname = contactlastname;
  }

  @Basic
  @Column(name = "CONTACTFIRSTNAME", nullable = true, insertable = true, updatable = true, length = 50)
  public String getContactFirstname() {
    return contactFirstname;
  }

  public void setContactFirstname(String contactfirstname) {
    this.contactFirstname = contactfirstname;
  }

  @Basic
  @Column(name = "PHONE", nullable = true, insertable = true, updatable = true, length = 50)
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Basic
  @Column(name = "ADDRESSLINE1", nullable = true, insertable = true, updatable = true, length = 50)
  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressline1) {
    this.addressLine1 = addressline1;
  }

  @Basic
  @Column(name = "ADDRESSLINE2", nullable = true, insertable = true, updatable = true, length = 50)
  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressline2) {
    this.addressLine2 = addressline2;
  }

  @Basic
  @Column(name = "CITY", nullable = true, insertable = true, updatable = true, length = 50)
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Basic
  @Column(name = "STATE", nullable = true, insertable = true, updatable = true, length = 50)
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Basic
  @Column(name = "POSTALCODE", nullable = true, insertable = true, updatable = true, length = 15)
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalcode) {
    this.postalCode = postalcode;
  }

  @Basic
  @Column(name = "COUNTRY", nullable = true, insertable = true, updatable = true, length = 50)
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

//  @Basic
//  @Column(name = "SALESREPEMPLOYEENUMBER", nullable = true, insertable = true, updatable = true)
//  public Integer getSalesRepEmployeeNumber() {
//    return salesRepEmployeeNumber;
//  }
//
//  public void setSalesRepEmployeeNumber(Integer salesrepemployeenumber) {
//    this.salesRepEmployeeNumber = salesrepemployeenumber;
//  }

  @Basic
  @Column(name = "CREDITLIMIT", nullable = true, insertable = true, updatable = true, precision = 0)
  public Double getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(Double creditlimit) {
    this.creditLimit = creditlimit;
  }


  @OneToMany(mappedBy = "customer" , fetch = FetchType.LAZY)
  public Set<Order> getOrders() {
    return orders;
  }

  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  }


  @ManyToOne( fetch = FetchType.LAZY)
  @JoinColumn(name = "SALESREPEMPLOYEENUMBER", referencedColumnName = "EMPLOYEENUMBER")
  public Employee getSalesRep() {
    return salesRep;
  }

  public void setSalesRep(Employee salesRep) {
    this.salesRep = salesRep;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Customer)) return false;

    Customer customer = (Customer) o;

    if (!customerNumber.equals(customer.customerNumber)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return customerNumber.hashCode();
  }

  @Override
  public String toString() {
    return "Customer{" +
        "customernumber=" + customerNumber +
        ", customername='" + customerName + '\'' +
        ", contactlastname='" + contactLastname + '\'' +
        ", contactfirstname='" + contactFirstname + '\'' +
        ", phone='" + phone + '\'' +
        ", addressline1='" + addressLine1 + '\'' +
        ", addressline2='" + addressLine2 + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", postalcode='" + postalCode + '\'' +
        ", country='" + country + '\'' +
        ", creditlimit=" + creditLimit +
        '}';
  }
}
