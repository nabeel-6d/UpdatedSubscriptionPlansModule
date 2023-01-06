package com.example.subscriptions.entities.subscriber;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.subscriptions.LoggerImpl.MyLogger;
import com.example.subscriptions.exceptionhandler.ExceptionHandler;

@Entity
public class Address {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int sl_no;
    private String houseNo;
    private String streetname;
    private String city;
    private String zipcode;
    private String country;  
  
    
    public Address() {
       MyLogger.trace("in address no-arg cnstr");
    }

    public Address(String houseNo, String streetname, String city, String zipcode, String country) {
             MyLogger.trace("in address param cnstr");

            this.houseNo = (String) ExceptionHandler.handleEvent(houseNo);
            this.streetname = (String) ExceptionHandler.handleEvent(streetname);
            this.city = (String) ExceptionHandler.handleEvent(city);
            this.zipcode = (String) ExceptionHandler.handleEvent(zipcode);
            this.country = (String) ExceptionHandler.handleEvent(country);

    }

    public int getSl_no() {
        return sl_no;
    }
    
    public String getHouseNo() {
        MyLogger.trace("in address gethouseNo");
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        MyLogger.trace("in address sethouseNo");
       
        this.houseNo =  (String) ExceptionHandler.handleEvent(houseNo);;
    }

    public String getStreetname() {
        MyLogger.trace("in address getstreetname");
        return streetname;
    }

    public void setStreetname(String streetname) {
       MyLogger.trace("in address setstreetname");
       this.streetname = (String) ExceptionHandler.handleEvent(streetname);
    }

    public String getCity() {
       MyLogger.trace("in address getcity");
        return city;
    }

    public void setCity(String city) {
       MyLogger.trace("in address sethouseNo");
       this.city = (String) ExceptionHandler.handleEvent(city);
     
    }
    
    public String getZipcode() {
        MyLogger.trace("in address getzipcode");
        return zipcode;
    }

    public void setZipcode(String zipcode) {
       MyLogger.trace("in address sethouseNo");
       this.country = (String) ExceptionHandler.handleEvent(zipcode);
    }

    public String getCountry() {
        MyLogger.trace("in address getcountry");
        return country;
    }

    public void setCountry(String country) {
       MyLogger.trace("in address setcountry");
       this.country = (String) ExceptionHandler.handleEvent(country);
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((houseNo == null) ? 0 : houseNo.hashCode());
        result = prime * result + ((streetname == null) ? 0 : streetname.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (houseNo == null) {
            if (other.houseNo != null)
                return false;
        } else if (!houseNo.equals(other.houseNo))
            return false;
        if (streetname == null) {
            if (other.streetname != null)
                return false;
        } else if (!streetname.equals(other.streetname))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (zipcode == null) {
            if (other.zipcode != null)
                return false;
        } else if (!zipcode.equals(other.zipcode))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Address [houseNo=" + houseNo + ", streetname=" + streetname + ", city=" + city + ", zipcode=" + zipcode
                + ", country=" + country + "]";
    }

    
    
}
