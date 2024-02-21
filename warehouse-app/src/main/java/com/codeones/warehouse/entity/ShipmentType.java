package com.codeones.warehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shipment_type_tab")
public class ShipmentType {
	@Id
	@GeneratedValue(generator = "shipment_type_gen")
	@SequenceGenerator(name = "shipment_type_gen", sequenceName = "shipment_type_seq")
	@Column(name = "sh_id_col")
	private Long id;
	@Column(name = "sh_mode_col")
	private String shipMode;
	@Column(name = "sh_code_col")
	private String shipCode;
	@Column(name = "sh_enbl_col")
	private String enblShip;
	@Column(name = "sh_grade_col")
	private String shipGrade;
	@Column(name = "sh_desc_col")
	private String shipDesc;
}
