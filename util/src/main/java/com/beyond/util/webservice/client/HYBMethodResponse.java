
package com.beyond.util.webservice.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>HYBMethodResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="HYBMethodResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HYBResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HYBMethodResponse", propOrder = {
    "hybResponse"
})
public class HYBMethodResponse {

    @XmlElement(name = "HYBResponse")
    protected String hybResponse;

    /**
     * 获取hybResponse属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHYBResponse() {
        return hybResponse;
    }

    /**
     * 设置hybResponse属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHYBResponse(String value) {
        this.hybResponse = value;
    }

}
