package com.example.Traceability.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Traceability.Entity.MaterialEntity;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {

}
