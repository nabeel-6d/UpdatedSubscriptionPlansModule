package com.example.subscriptions.entities.subscriber.plan;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.subscriptions.LoggerImpl.MyLogger;
import com.example.subscriptions.exceptionhandler.ExceptionHandler;

@Entity
@Table(name = "Plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int plan_id;//{primary key} auto generated so no setter / in-param cnstr
    private String name;
    private Date validity;
    private Date creationDate;
    private Date updationDate;
    
    {
        if(creationDate == null)
            creationDate = new Date(System.currentTimeMillis());    
    }

    public Plan() {
        MyLogger.trace("in plan no-arg entity cnstr");
    }
    
    public Plan(String name, Date validity) {
        this.name = (String) ExceptionHandler.handleEvent(name);
        this.validity = (Date) ExceptionHandler.handleEvent(validity);
        MyLogger.trace("in plan param cnstr");
    }

    public int getPlan_id() {
        MyLogger.trace("in plan plan_id getter");
        return plan_id;
    }
   
    public String getName() {
        MyLogger.trace("in plan name getter");
        return name;
    }
    public void setName(String name) {
        this.name = (String) ExceptionHandler.handleEvent(name);
        this.updationDate=new Date(System.currentTimeMillis());
        MyLogger.trace("in plan name setter");
    }
    public Date getValidity() {
        MyLogger.trace("in plan validity getter");
        return validity;
    }
    public void setValidity(Date validity) {
        MyLogger.trace("in plan validity setter");
        this.validity = (Date) ExceptionHandler.handleEvent(validity);
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
        this.updationDate=new Date(System.currentTimeMillis());
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getUpdationDate() {
        return updationDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + plan_id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((validity == null) ? 0 : validity.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((updationDate == null) ? 0 : updationDate.hashCode());
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
        Plan other = (Plan) obj;
        if (plan_id != other.plan_id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (validity == null) {
            if (other.validity != null)
                return false;
        } else if (!validity.equals(other.validity))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (updationDate == null) {
            if (other.updationDate != null)
                return false;
        } else if (!updationDate.equals(other.updationDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Plan [plan_id=" + plan_id + ", name=" + name + ", validity=" + validity + ", creationDate="
                + creationDate + ", updationDate=" + updationDate + "]";
    }
    

    
}
