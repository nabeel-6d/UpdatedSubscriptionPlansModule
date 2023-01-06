package com.example.subscriptions.controllers.plan;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.subscriptions.entities.subscriber.plan.Plan;
import com.example.subscriptions.exceptionhandler.ExceptionHandler;
import com.example.subscriptions.service.plan.PlanService;

@RestController
@RequestMapping("plans")
public class PlanController {

    @Autowired
    private PlanService service;

    @PostMapping("/add")
    public void add(@RequestBody Plan plan){
        service.addPlan(plan);
    }

    @DeleteMapping("/delete/{plan_id}")
    public void delete(@PathVariable(name ="plan_id") int id){
        service.deletePlan(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Plan plan,@PathVariable int id){
        service.updateAPlan(plan,id);
    }

    @GetMapping("/giveplans/no-of-entries/{noOfEntries}")
    public Collection<Plan> retrieve(@PathVariable int noOfEntries){
       return service.retrieveAllPlans(noOfEntries);
    }

    @GetMapping("/giveplans/{plan_id}")
    public Plan retrieveOne(@PathVariable int plan_id){
        return service.retrieveOnePlan(plan_id);
    }


    /* -------------------------- exact by date retrieval -------------------------- */

    @GetMapping("/giveplans/byvalidity/{validity}/no-of-entries/{noOfEntries}")
    public Collection<Plan> allPlansByValidity(@PathVariable String validity,@PathVariable int noOfEntries){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date sqlFormattedDate = ExceptionHandler.handleEventForParse(sdf, validity);
        return service.retrievePlansByValidity(sqlFormattedDate,noOfEntries);
    }

    @GetMapping("/giveplans/bydatecreated/{createdate}/no-of-entries/{noOfEntries}")
    public Collection<Plan> allPlansByCreationDate(@PathVariable String createdate,@PathVariable int noOfEntries){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlFormattedDate =  ExceptionHandler.handleEventForParse(sdf, createdate);
        return service.retrievePlansByCreationDate(sqlFormattedDate,noOfEntries);
    }

    @GetMapping("/giveplans/bydatemodified/{modifieddate}/no-of-entries/{noOfEntries}")
    public Collection<Plan> allPlansByUpdationDate(@PathVariable String modifieddate,@PathVariable int noOfEntries){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date sqlFormattedDate = ExceptionHandler.handleEventForParse(sdf, modifieddate);
        return service.retrievePlansByUpdationDate(sqlFormattedDate,noOfEntries);
    }

    /* ----------------------------------- Yearly and monthly retrieval----------------------------------- */


    @GetMapping("giveplans/yearcreated/{year}/no-of-entries/{noOfEntries}")
    public Collection<Plan> allPlansByCreationYear(@PathVariable int year,@PathVariable int noOfEntries){
        return service.retrievePlansByCreationYear(year,noOfEntries);
    }
    
    @GetMapping("giveplans/monthcreated/{month}/no-of-entries/{noOfEntries}")
    public Collection<Plan> allPlansByCreationMonth(@PathVariable int month,@PathVariable int noOfEntries){
        return service.retrievePlansByCreationMonth(month,noOfEntries);
    }

    
    @GetMapping("giveplans/yearupdated/{year}/no-of-entries/{noOfEntries}")
    public Collection<Plan> allPlansByUpdationYear(@PathVariable int year,@PathVariable int noOfEntries){
        return service.retrievePlansByUpdationYear(year,noOfEntries);
    }
    
    @GetMapping("giveplans/monthupdated/{month}/no-of-entries/{noOfEntries}")
    public Collection<Plan> allPlansByUpdationMonth(@PathVariable int month,@PathVariable int noOfEntries){
        return service.retrievePlansByUpdationMonth(month,noOfEntries);
    }
    
    @GetMapping("giveplans/validityinyear/{year}/no-of-entries/{noOfEntries}")
    public Collection<Plan> allPlansByValidityYear(@PathVariable int year,@PathVariable int noOfEntries){
        return service.retrievePlansByValidityYear(year,noOfEntries);
    }
    @GetMapping("giveplans/validityinmonth/{month}/no-of-entries/{noOfEntries}")
    public Collection<Plan> allPlansByValidityMonth(@PathVariable int month,@PathVariable int noOfEntries){
        return service.retrievePlansByValidityMonth(month,noOfEntries);
    }

}
