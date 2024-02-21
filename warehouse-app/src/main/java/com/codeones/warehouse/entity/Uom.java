package com.codeones.warehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "uom_tab")
@AllArgsConstructor
@NoArgsConstructor
public class Uom {
	@Id
	@GeneratedValue(generator = "uom_gen")
	@SequenceGenerator(name = "uom_gen", sequenceName = "uom_seq")
	@Column(name = "uom_id_col")
	private Long id;

	@Column(name = "uom_type_col")
	private String uomType;

	@Column(name = "uom_model_col")
	private String uomModel;

	@Column(name = "uom_desc_col")
	private String desc;
}
