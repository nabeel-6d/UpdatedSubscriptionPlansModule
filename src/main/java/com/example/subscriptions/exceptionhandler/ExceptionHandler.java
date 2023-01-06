package com.example.subscriptions.exceptionhandler;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.example.subscriptions.LoggerImpl.MyLogger;
import com.example.subscriptions.entities.subscriber.Address;
import com.example.subscriptions.entities.subscriber.Subscriber;
import com.example.subscriptions.entities.subscriber.plan.Plan;

@SuppressWarnings("unused")
public class ExceptionHandler {
    public static Object handleEvent(Object obj) {
      try {
        if(obj instanceof String ){
            String subject = (String)obj;
            if(subject == null || subject.isEmpty() || subject.isBlank()){
                throw new IllegalArgumentException("Object is not valid --either null or empty or blank");
            }
            return subject;
       }
       if(obj instanceof Subscriber){
            Subscriber subject = (Subscriber)obj;
            if(subject == null)
                throw new IllegalArgumentException("Object is not valid --maybe null ");
            
            return subject;
       }
       if(obj instanceof Plan){
            Plan subject = (Plan)obj;
            if(subject == null)
                throw new IllegalArgumentException("Object is not valid --maybe null ");

            return subject;
       }
       if(obj instanceof Date){
            Date subject = (Date)obj;
            if(subject == null)
                throw new IllegalArgumentException("Object is not valid --maybe null");

            return subject;
       }
       if(obj instanceof Address){
            Address subject = (Address)obj;
            if(subject == null)
                throw new IllegalArgumentException("Object is not valid --maybe null");
            return subject;
       }
       if(obj instanceof Integer){
            Integer subject = (Integer) obj;
            if(subject < 0)
                throw new IllegalArgumentException("Object is not valid --maybe negative");
            return subject;
       }
      } catch (IllegalArgumentException | NullPointerException e) {
        MyLogger.trace("handler catch");
        e.printStackTrace();
        MyLogger.error("\n\n*********************exactly at line num :  "+Thread.currentThread().getStackTrace()[1].getLineNumber());
      }
      return null;
    }

    public static Date handleEventForParse(SimpleDateFormat sdf,String date){
       try {
        Date parsedDate = new Date(sdf.parse(date).getTime());
        return parsedDate;
       } catch (ParseException e) {
         e.printStackTrace();
         MyLogger.error("\n\n*********************exactly at line num :  "+Thread.currentThread().getStackTrace()[1].getLineNumber());
       }
       return null;
    }
}
