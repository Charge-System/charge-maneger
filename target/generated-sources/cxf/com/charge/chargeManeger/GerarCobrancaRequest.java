
package com.charge.chargeManeger;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customer" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="billingType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="dueDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customer",
    "billingType",
    "value",
    "dueDate"
})
@XmlRootElement(name = "gerarCobrancaRequest")
public class GerarCobrancaRequest {

    @XmlElement(required = true)
    protected String customer;
    @XmlElement(required = true)
    protected String billingType;
    protected double value;
    @XmlElement(required = true)
    protected String dueDate;

    /**
     * Obtém o valor da propriedade customer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Define o valor da propriedade customer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomer(String value) {
        this.customer = value;
    }

    /**
     * Obtém o valor da propriedade billingType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingType() {
        return billingType;
    }

    /**
     * Define o valor da propriedade billingType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingType(String value) {
        this.billingType = value;
    }

    /**
     * Obtém o valor da propriedade value.
     * 
     */
    public double getValue() {
        return value;
    }

    /**
     * Define o valor da propriedade value.
     * 
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Obtém o valor da propriedade dueDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Define o valor da propriedade dueDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDueDate(String value) {
        this.dueDate = value;
    }

}
