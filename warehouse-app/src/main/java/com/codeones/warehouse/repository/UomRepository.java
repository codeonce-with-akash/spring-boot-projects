package com.codeones.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeones.warehouse.entity.Uom;

public interface UomRepository extends JpaRepository<Uom, Long> {

}
