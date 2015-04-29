package edu.uw.data.lecture7.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by credmond on 26/03/15.
 */
@Entity
@Table(name = "PAYMENTS", schema = "APP", catalog = "")
@IdClass(PaymentPK.class)
public class Payment {
  private Integer customernumber;
  private String checknumber;
  private Date paymentdate;
  private Double amount;

  @Id
  @Column(name = "CUSTOMERNUMBER", nullable = false, insertable = true, updatable = true)
  public Integer getCustomernumber() {
    return customernumber;
  }

  public void setCustomernumber(Integer customernumber) {
    this.customernumber = customernumber;
  }

  @Id
  @Basic
  @Column(name = "CHECKNUMBER", nullable = false, insertable = true, updatable = true, length = 50)
  public String getChecknumber() {
    return checknumber;
  }

  public void setChecknumber(String checknumber) {
    this.checknumber = checknumber;
  }

  @Basic
  @Column(name = "PAYMENTDATE", nullable = true, insertable = true, updatable = true)
  public Date getPaymentdate() {
    return paymentdate;
  }

  public void setPaymentdate(Date paymentdate) {
    this.paymentdate = paymentdate;
  }

  @Basic
  @Column(name = "AMOUNT", nullable = true, insertable = true, updatable = true, precision = 0)
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Payment payment = (Payment) o;

    if (amount != null ? !amount.equals(payment.amount) : payment.amount != null) return false;
    if (checknumber != null ? !checknumber.equals(payment.checknumber) : payment.checknumber != null) return false;
    if (customernumber != null ? !customernumber.equals(payment.customernumber) : payment.customernumber != null)
      return false;
    if (paymentdate != null ? !paymentdate.equals(payment.paymentdate) : payment.paymentdate != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = customernumber != null ? customernumber.hashCode() : 0;
    result = 31 * result + (checknumber != null ? checknumber.hashCode() : 0);
    result = 31 * result + (paymentdate != null ? paymentdate.hashCode() : 0);
    result = 31 * result + (amount != null ? amount.hashCode() : 0);
    return result;
  }
}
