
package com.beyond.util.webservice.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>HYBMethod complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="HYBMethod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HYBRequest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HYBMethod", propOrder = {
    "hybRequest"
})
public class HYBMethod {

    @XmlElement(name = "HYBRequest")
    protected String hybRequest;

    /**
     * 获取hybRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHYBRequest() {
        return hybRequest;
    }

    /**
     * 设置hybRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHYBRequest(String value) {
        this.hybRequest = value;
    }

}
