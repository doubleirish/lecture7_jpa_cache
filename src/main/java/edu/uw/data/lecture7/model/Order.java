package edu.uw.data.lecture7.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by credmond on 26/03/15.
 */
@NamedQueries({
        @NamedQuery(name = "Order.findAll",
                query = "SELECT o from Order o")

})
@Entity
@Table(name = "ORDERS" )
public class Order {
  private Integer orderNumber;
  private Date orderDate;
  private Date requiredDate;
  private Date shippedDate;
  private String status;
  private Customer customer;


  private OrderDetail orderDetail;
  @Id
  @Column(name = "ORDERNUMBER" )
  public Integer getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(Integer ordernumber) {
    this.orderNumber = ordernumber;
  }

  @Basic
  @Column(name = "ORDERDATE", nullable = true, insertable = true, updatable = true)
  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderdate) {
    this.orderDate = orderdate;
  }

  @Basic
  @Column(name = "REQUIREDDATE", nullable = true, insertable = true, updatable = true)
  public Date getRequiredDate() {
    return requiredDate;
  }

  public void setRequiredDate(Date requireddate) {
    this.requiredDate = requireddate;
  }

  @Basic
  @Column(name = "SHIPPEDDATE", nullable = true, insertable = true, updatable = true)
  public Date getShippedDate() {
    return shippedDate;
  }

  public void setShippedDate(Date shippeddate) {
    this.shippedDate = shippeddate;
  }

  @Basic
  @Column(name = "STATUS", nullable = true, insertable = true, updatable = true, length = 15)
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }



  @ManyToOne
  @JoinColumn(name = "CUSTOMERNUMBER", // col name in ORDER table
      referencedColumnName = "CUSTOMERNUMBER") // col name in CUSTOMER table
  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customersByCustomernumber) {
    this.customer = customersByCustomernumber;
  }

  @OneToOne //shared primary key
  @PrimaryKeyJoinColumn
  public OrderDetail getOrderDetail() {
    return orderDetail;
  }

  public void setOrderDetail(OrderDetail orderDetail) {
    this.orderDetail = orderDetail;
  }

  @Override
  public String toString() {
    return "Order{" +
        "orderNumber=" + orderNumber +
        ", orderDate=" + orderDate +
        ", requiredDate=" + requiredDate +
        ", shippedDate=" + shippedDate +
        ", status='" + status + '\'' +
        '}';
  }
}
