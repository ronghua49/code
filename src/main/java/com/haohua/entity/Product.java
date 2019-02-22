package com.haohua.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created By MBG-GUI-EXTENSION https://github.com/spawpaw/mybatis-generator-gui-extension
 * Description:
 * 
 *
 * @author 
 */
public class Product implements Serializable {
    /**
     *
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    private Integer id;

    /**
     *
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    private String pro_name;

    /**
     *
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    private BigDecimal pro_price;

    /**
     *
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    private Integer inventory;

    /**
     *
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    private Long version;

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column demo..product.id
     *
     * @return the value of demo..product.id
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public Product withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method sets the value of the database column demo..product.id
     *
     * @param id the value for demo..product.id
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column demo..product.pro_name
     *
     * @return the value of demo..product.pro_name
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public String getPro_name() {
        return pro_name;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public Product withPro_name(String pro_name) {
        this.setPro_name(pro_name);
        return this;
    }

    /**
     * This method sets the value of the database column demo..product.pro_name
     *
     * @param pro_name the value for demo..product.pro_name
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    /**
     * This method returns the value of the database column demo..product.pro_price
     *
     * @return the value of demo..product.pro_price
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public BigDecimal getPro_price() {
        return pro_price;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public Product withPro_price(BigDecimal pro_price) {
        this.setPro_price(pro_price);
        return this;
    }

    /**
     * This method sets the value of the database column demo..product.pro_price
     *
     * @param pro_price the value for demo..product.pro_price
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public void setPro_price(BigDecimal pro_price) {
        this.pro_price = pro_price;
    }

    /**
     * This method returns the value of the database column demo..product.inventory
     *
     * @return the value of demo..product.inventory
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public Integer getInventory() {
        return inventory;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public Product withInventory(Integer inventory) {
        this.setInventory(inventory);
        return this;
    }

    /**
     * This method sets the value of the database column demo..product.inventory
     *
     * @param inventory the value for demo..product.inventory
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    /**
     * This method returns the value of the database column demo..product.version
     *
     * @return the value of demo..product.version
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public Long getVersion() {
        return version;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public Product withVersion(Long version) {
        this.setVersion(version);
        return this;
    }

    /**
     * This method sets the value of the database column demo..product.version
     *
     * @param version the value for demo..product.version
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pro_name=").append(pro_name);
        sb.append(", pro_price=").append(pro_price);
        sb.append(", inventory=").append(inventory);
        sb.append(", version=").append(version);
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
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
        Product other = (Product) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPro_name() == null ? other.getPro_name() == null : this.getPro_name().equals(other.getPro_name()))
            && (this.getPro_price() == null ? other.getPro_price() == null : this.getPro_price().equals(other.getPro_price()))
            && (this.getInventory() == null ? other.getInventory() == null : this.getInventory().equals(other.getInventory()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPro_name() == null) ? 0 : getPro_name().hashCode());
        result = prime * result + ((getPro_price() == null) ? 0 : getPro_price().hashCode());
        result = prime * result + ((getInventory() == null) ? 0 : getInventory().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}