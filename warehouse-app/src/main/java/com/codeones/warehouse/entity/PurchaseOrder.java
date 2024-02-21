package com.codeones.warehouse.entity;

import com.codeones.warehouse.enums.PurchaseOrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "po_tab")
public class PurchaseOrder {
	@Id
	@GeneratedValue(generator = "po_gen")
	@SequenceGenerator(name = "po_gen", sequenceName = "po_seq")
	@Column(name = "po_id_col")
	private Long poId;
	
	@Column(name = "po_code_col")
	private String poCode;
	@Column(name = "po_ref_num_col")
	private String poRefNum;
	@Column(name = "po_qlty_check_col")
	private String poQltyCheck;
	
	@Enumerated
	@Column(name = "po_status_col")
	private PurchaseOrderStatus defalultStatus = PurchaseOrderStatus.OPEN;

	@Column(name = "po_desc_col")
	private String poDesc;

	//Integration
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "po_st_fk_col")
	private ShipmentType st;
	
	//Integration
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "po_wh_fk_col")
	private WhUserType wh;
}
