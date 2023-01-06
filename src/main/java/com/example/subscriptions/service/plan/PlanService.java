package com.example.subscriptions.service.plan;

import java.sql.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.subscriptions.LoggerImpl.MyLogger;
import com.example.subscriptions.entities.subscriber.plan.Plan;
import com.example.subscriptions.exceptionhandler.ExceptionHandler;
import com.example.subscriptions.repository.plan.PlanRepository;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planrepo;


    public void addPlan(Plan plan){
            ExceptionHandler.handleEvent(plan);     // -> exception handler
            planrepo.save(plan);
            MyLogger.trace("in plan service- in add plan---------------------------------");
    }

    public void deletePlan(int plan_id){
            planrepo.deleteById(plan_id);
            MyLogger.trace("in plan service - in delete plan-------------------------------");
    }

    public void updateAPlan(Plan plan,int id){

            ExceptionHandler.handleEvent(plan);        // -> exception handler
            
            Plan b=planrepo.findById(id).get();
            b.setName(plan.getName());
            b.setValidity(plan.getValidity());
            planrepo.save(b);

           MyLogger.trace("in plan service - in update plan------------------------------------");
        
    }

    public Collection<Plan> retrieveAllPlans(int noOfEntries){
        Pageable allPagedData=PageRequest.of(0, noOfEntries);
        return planrepo.findAll(allPagedData).getContent();
    }

    public Plan retrieveOnePlan(int id){
        return planrepo.findById(id).get();
    }

    public Collection<Plan> retrievePlansByValidity(Date validity,int noOfEntries){
        Pageable allPagedPlansByValidity=PageRequest.of(0, noOfEntries);
        return planrepo.findByValidity(validity,allPagedPlansByValidity);
    }

    public Collection<Plan> retrievePlansByCreationDate(Date createDate,int noOfEntries){
        Pageable pagedPlansByCreationDate=PageRequest.of(0, noOfEntries);
        return planrepo.findByCreationDate(createDate,pagedPlansByCreationDate);
    }

    public Collection<Plan> retrievePlansByUpdationDate(Date updateDate,int noOfEntries){
        Pageable pagedPlansByUpdationDate=PageRequest.of(0, noOfEntries);
        return planrepo.findByUpdationDate(updateDate,pagedPlansByUpdationDate);      
    }
    
    public Collection<Plan> retrievePlansByCreationYear(int createDate,int noOfEntries){
        Pageable pagedPlansByCreatedDate=PageRequest.of(0, noOfEntries);
        return planrepo.findByCreationYear(createDate, pagedPlansByCreatedDate);
    }

    public Collection<Plan> retrievePlansByUpdationYear(int updateYear,int noOfEntries){
        Pageable pagedPlansByUpdationDate=PageRequest.of(0, noOfEntries);
        return planrepo.findByUpdationYear(updateYear, pagedPlansByUpdationDate);
    }

    public Collection<Plan> retrievePlansByValidityYear(int validity,int noOfEntries){
        Pageable pagedPlansByValidityDate=PageRequest.of(0, noOfEntries);
        return planrepo.findByValidityYear(validity, pagedPlansByValidityDate);
    }

    public Collection<Plan> retrievePlansByCreationMonth(int createDate,int noOfEntries){
        Pageable pagedPlansByCreatedDate=PageRequest.of(0, noOfEntries);
        return planrepo.findByCreationMonth(createDate, pagedPlansByCreatedDate);
    }

    public Collection<Plan> retrievePlansByUpdationMonth(int updateMonth,int noOfEntries){
        Pageable pagedPlansByUpdationDate=PageRequest.of(0, noOfEntries);
        return planrepo.findByUpdationMonth(updateMonth, pagedPlansByUpdationDate);
    }

    public Collection<Plan> retrievePlansByValidityMonth(int validity,int noOfEntries){
        Pageable pagedPlansByValidityDate=PageRequest.of(0, noOfEntries);
        return planrepo.findByValidityMonth(validity, pagedPlansByValidityDate);
    }
}
