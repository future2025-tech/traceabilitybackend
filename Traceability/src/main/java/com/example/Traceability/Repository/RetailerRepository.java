package com.example.Traceability.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Traceability.Entity.RetailerEntity;

@Repository
public interface RetailerRepository extends JpaRepository<RetailerEntity, Long> {

}
