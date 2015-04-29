package edu.uw.data.lecture7.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Created by credmond on 26/03/15.
 */
@Entity
public class PaymentPK implements Serializable {
  private Integer customernumber;
  private String checknumber;

  @Column(name = "CUSTOMERNUMBER", nullable = false, insertable = true, updatable = true)
  @Id
  public Integer getCustomernumber() {
    return customernumber;
  }

  public void setCustomernumber(Integer customernumber) {
    this.customernumber = customernumber;
  }

  @Column(name = "CHECKNUMBER", nullable = false, insertable = true, updatable = true, length = 50)
  @Basic
  @Id
  public String getChecknumber() {
    return checknumber;
  }

  public void setChecknumber(String checknumber) {
    this.checknumber = checknumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PaymentPK paymentPK = (PaymentPK) o;

    if (checknumber != null ? !checknumber.equals(paymentPK.checknumber) : paymentPK.checknumber != null) return false;
    if (customernumber != null ? !customernumber.equals(paymentPK.customernumber) : paymentPK.customernumber != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = customernumber != null ? customernumber.hashCode() : 0;
    result = 31 * result + (checknumber != null ? checknumber.hashCode() : 0);
    return result;
  }
}
