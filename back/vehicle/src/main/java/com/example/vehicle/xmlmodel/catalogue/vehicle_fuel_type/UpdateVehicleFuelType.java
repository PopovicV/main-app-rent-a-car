//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.20 at 10:55:26 PM CEST 
//


package com.example.vehicle.xmlmodel.catalogue.vehicle_fuel_type;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://rentacar.com/vehicle-fuel-type}vehicleFuelType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "vehicleFuelType"
})
@XmlRootElement(name = "updateVehicleFuelType")
public class UpdateVehicleFuelType {

    @XmlElement(required = true)
    protected VehicleFuelType vehicleFuelType;

    /**
     * Gets the value of the vehicleFuelType property.
     * 
     * @return
     *     possible object is
     *     {@link VehicleFuelType }
     *     
     */
    public VehicleFuelType getVehicleFuelType() {
        return vehicleFuelType;
    }

    /**
     * Sets the value of the vehicleFuelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link VehicleFuelType }
     *     
     */
    public void setVehicleFuelType(VehicleFuelType value) {
        this.vehicleFuelType = value;
    }

}
