package edu.uw.data.lecture7.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created by credmond on 26/03/15.
 */
@Entity
@Table(name = "PRODUCTS", schema = "APP", catalog = "")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Product {
  private String productcode;
  private String productname;
  private String productline;
  private String productscale;
  private String productvendor;
  private String productdescription;
  private Integer quantityinstock;
  private Double buyprice;
  private Double msrp;
  private Productline productlinesByProductline;

  @Id
  @Column(name = "PRODUCTCODE", nullable = false, insertable = true, updatable = true, length = 15)
  public String getProductcode() {
    return productcode;
  }

  public void setProductcode(String productcode) {
    this.productcode = productcode;
  }

  @Basic
  @Column(name = "PRODUCTNAME", nullable = true, insertable = true, updatable = true, length = 70)
  public String getProductname() {
    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }

//  @Basic
//  @Column(name = "PRODUCTLINE", nullable = true, insertable = true, updatable = true, length = 50)
//  public String getProductline() {
//    return productline;
//  }
//
//  public void setProductline(String productline) {
//    this.productline = productline;
//  }

  @Basic
  @Column(name = "PRODUCTSCALE", nullable = true, insertable = true, updatable = true, length = 10)
  public String getProductscale() {
    return productscale;
  }

  public void setProductscale(String productscale) {
    this.productscale = productscale;
  }

  @Basic
  @Column(name = "PRODUCTVENDOR", nullable = true, insertable = true, updatable = true, length = 50)
  public String getProductvendor() {
    return productvendor;
  }

  public void setProductvendor(String productvendor) {
    this.productvendor = productvendor;
  }

//  @Basic
//  @Column(name = "PRODUCTDESCRIPTION", nullable = true, insertable = true, updatable = true, length = 32700)
//  public String getProductdescription() {
//    return productdescription;
//  }
//
//  public void setProductdescription(String productdescription) {
//    this.productdescription = productdescription;
//  }

  @Basic
  @Column(name = "QUANTITYINSTOCK", nullable = true, insertable = true, updatable = true)
  public Integer getQuantityinstock() {
    return quantityinstock;
  }

  public void setQuantityinstock(Integer quantityinstock) {
    this.quantityinstock = quantityinstock;
  }

  @Basic
  @Column(name = "BUYPRICE", nullable = true, insertable = true, updatable = true, precision = 0)
  public Double getBuyprice() {
    return buyprice;
  }

  public void setBuyprice(Double buyprice) {
    this.buyprice = buyprice;
  }

  @Basic
  @Column(name = "MSRP", nullable = true, insertable = true, updatable = true, precision = 0)
  public Double getMsrp() {
    return msrp;
  }

  public void setMsrp(Double msrp) {
    this.msrp = msrp;
  }



  @ManyToOne
  @JoinColumn(name = "PRODUCTLINE", referencedColumnName = "PRODUCTLINE")
  public Productline getProductlinesByProductline() {
    return productlinesByProductline;
  }

  public void setProductlinesByProductline(Productline productlinesByProductline) {
    this.productlinesByProductline = productlinesByProductline;
  }
}
