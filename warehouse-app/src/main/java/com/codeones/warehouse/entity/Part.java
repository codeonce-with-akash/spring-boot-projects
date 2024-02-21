package com.codeones.warehouse.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "part_tab")
@Builder
public class Part {
	@Id
	@GeneratedValue(generator = "part_gen")
	@SequenceGenerator(name = "part_gen", sequenceName = "part_seq")
	@Column(name = "part_id_col")
	private Long id;

	@Column(name = "part_code_col")
	private String partCode;
	
	@Column(name = "part_wid_col")
	private Double wid;
	
	@Column(name = "part_len_col")
	private Double len;
	
	@Column(name = "part_ht_col")
	private Double ht;
	
	@Column(name = "part_base_cost_col")
	private Double baseCost;
	
	@Column(name = "part_currency_col")
	private String baseCurr;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "uom_id_fk_col")
	private Uom uom;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "om_id_fk_col")
	private OrderMethod orderMethod;
	
	@Column(name = "part_desc_col")
	private String partDesc;
}
