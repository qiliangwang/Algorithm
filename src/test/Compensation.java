package test;

import java.math.BigDecimal;
import java.util.Date;

public class Compensation {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.id
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.order_no
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.uid
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private Long uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.type
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.in_out_type
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private Integer inOutType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.status
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private Boolean status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.item_id
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private Integer itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.amount
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.description
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.created_at
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_compensation_record.updated_at
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.id
     *
     * @return the value of tb_compensation_record.id
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withId(Long id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.id
     *
     * @param id the value for tb_compensation_record.id
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.order_no
     *
     * @return the value of tb_compensation_record.order_no
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withOrderNo(String orderNo) {
        this.setOrderNo(orderNo);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.order_no
     *
     * @param orderNo the value for tb_compensation_record.order_no
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.uid
     *
     * @return the value of tb_compensation_record.uid
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withUid(Long uid) {
        this.setUid(uid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.uid
     *
     * @param uid the value for tb_compensation_record.uid
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.type
     *
     * @return the value of tb_compensation_record.type
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withType(Integer type) {
        this.setType(type);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.type
     *
     * @param type the value for tb_compensation_record.type
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.in_out_type
     *
     * @return the value of tb_compensation_record.in_out_type
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Integer getInOutType() {
        return inOutType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withInOutType(Integer inOutType) {
        this.setInOutType(inOutType);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.in_out_type
     *
     * @param inOutType the value for tb_compensation_record.in_out_type
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setInOutType(Integer inOutType) {
        this.inOutType = inOutType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.status
     *
     * @return the value of tb_compensation_record.status
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withStatus(Boolean status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.status
     *
     * @param status the value for tb_compensation_record.status
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.item_id
     *
     * @return the value of tb_compensation_record.item_id
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withItemId(Integer itemId) {
        this.setItemId(itemId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.item_id
     *
     * @param itemId the value for tb_compensation_record.item_id
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.amount
     *
     * @return the value of tb_compensation_record.amount
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withAmount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.amount
     *
     * @param amount the value for tb_compensation_record.amount
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.description
     *
     * @return the value of tb_compensation_record.description
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.description
     *
     * @param description the value for tb_compensation_record.description
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.created_at
     *
     * @return the value of tb_compensation_record.created_at
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withCreatedAt(Date createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.created_at
     *
     * @param createdAt the value for tb_compensation_record.created_at
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_compensation_record.updated_at
     *
     * @return the value of tb_compensation_record.updated_at
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public Compensation withUpdatedAt(Date updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_compensation_record.updated_at
     *
     * @param updatedAt the value for tb_compensation_record.updated_at
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Compensation other = (Compensation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getInOutType() == null ? other.getInOutType() == null : this.getInOutType().equals(other.getInOutType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getInOutType() == null) ? 0 : getInOutType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_compensation_record
     *
     * @mbg.generated Thu Jun 27 16:39:23 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", uid=").append(uid);
        sb.append(", type=").append(type);
        sb.append(", inOutType=").append(inOutType);
        sb.append(", status=").append(status);
        sb.append(", itemId=").append(itemId);
        sb.append(", amount=").append(amount);
        sb.append(", description=").append(description);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }
}