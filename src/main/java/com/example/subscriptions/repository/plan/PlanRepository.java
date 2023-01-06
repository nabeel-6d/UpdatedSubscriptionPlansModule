package com.example.subscriptions.repository.plan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.subscriptions.entities.subscriber.plan.Plan;

public interface PlanRepository extends JpaRepository<Plan,Integer>{
    List<Plan> findByValidity(Date validity,Pageable pageable);
    List<Plan> findByCreationDate(Date creationDate,Pageable pageable);
    List<Plan> findByUpdationDate(Date updationDate,Pageable pageable);

    /* ---------------------------- yearly retrieval ---------------------------- */
    @Query(value = "select * from plan as p WHERE (select Extract(YEAR FROM p.creationDate))=:creationYear",nativeQuery = true)
    List<Plan> findByCreationYear(int creationYear,Pageable pageable);
    @Query(value = "select * from plan as p WHERE (select Extract(YEAR FROM p.validity))=:validity",nativeQuery = true)
    List<Plan> findByValidityYear(int validity,Pageable pageable);
    @Query(value = "select * from plan as p WHERE (select Extract(YEAR FROM p.updationDate))=:updationYear",nativeQuery = true)
    List<Plan> findByUpdationYear(int updationYear,Pageable pageable);

    /* ---------------------------- monthly retrieval --------------------------- */
    @Query(value = "select * from plan as p WHERE (select Extract(MONTH FROM p.creationDate))=:creationMonth",nativeQuery = true)
    List<Plan> findByCreationMonth(int creationMonth,Pageable pageable);
    @Query(value = "select * from plan as p WHERE (select Extract(MONTH FROM p.validity))=:validityInMonth",nativeQuery = true)
    List<Plan> findByValidityMonth(int validityInMonth,Pageable pageable);
    @Query(value = "select * from plan as p WHERE (select Extract(MONTH FROM p.updationDate))=:updationMonth",nativeQuery = true)
    List<Plan> findByUpdationMonth(int updationMonth,Pageable pageable);
}


/*
 * select e EXTRACT(MONTH FROM e.creationDate)
 */