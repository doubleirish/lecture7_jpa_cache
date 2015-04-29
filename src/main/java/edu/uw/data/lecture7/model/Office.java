package edu.uw.data.lecture7.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by credmond on 26/03/15.
 */
@Entity
@Table(name = "OFFICES")
public class Office {
  private String officecode;
  private String city;
  private String phone;
  private String addressline1;
  private String addressline2;
  private String state;
  private String country;
  private String postalcode;
  private String territory;

  private Set<Employee> employees =new HashSet<>();

  @Id
  @Column(name = "OFFICECODE",  length = 10, nullable = false, insertable = true, updatable = true)
  public String getOfficecode() {
    return officecode;
  }

  public void setOfficecode(String officecode) {
    this.officecode = officecode;
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
  @Column(name = "PHONE", nullable = true, insertable = true, updatable = true, length = 50)
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Basic
  @Column(name = "ADDRESSLINE1", nullable = true, insertable = true, updatable = true, length = 50)
  public String getAddressline1() {
    return addressline1;
  }

  public void setAddressline1(String addressline1) {
    this.addressline1 = addressline1;
  }

  @Basic
  @Column(name = "ADDRESSLINE2", nullable = true, insertable = true, updatable = true, length = 50)
  public String getAddressline2() {
    return addressline2;
  }

  public void setAddressline2(String addressline2) {
    this.addressline2 = addressline2;
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
  @Column(name = "COUNTRY", nullable = true, insertable = true, updatable = true, length = 50)
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Basic
  @Column(name = "POSTALCODE", nullable = true, insertable = true, updatable = true, length = 15)
  public String getPostalcode() {
    return postalcode;
  }

  public void setPostalcode(String postalcode) {
    this.postalcode = postalcode;
  }

  @Basic
  @Column(name = "TERRITORY", nullable = true, insertable = true, updatable = true, length = 10)
  public String getTerritory() {
    return territory;
  }

  public void setTerritory(String territory) {
    this.territory = territory;
  }


  @OneToMany(mappedBy = "office")
  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  @Override
  public String toString() {
    return "Office{" +
        "officecode='" + officecode + '\'' +
        ", city='" + city + '\'' +
        ", phone='" + phone + '\'' +
        ", addressline1='" + addressline1 + '\'' +
        ", addressline2='" + addressline2 + '\'' +
        ", state='" + state + '\'' +
        ", country='" + country + '\'' +
        ", postalcode='" + postalcode + '\'' +
        ", territory='" + territory + '\'' +
        '}';
  }
}
