package com.codeones.warehouse.entity;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "order_method_tab")
@Builder
public class OrderMethod {
	@Id
	@GeneratedValue(generator = "order_method_gen")
	@SequenceGenerator(name = "order_method_gen", sequenceName = "order_method_seq")
	@Column(name = "om_id_col")
	private Long id;

	@Column(name = "om_mode_col")
	private String orderMode;

	@Column(name = "om_code_col")
	private String orderCode;

	@Column(name = "om_type_col")
	private String orderType;

	@ElementCollection
	@CollectionTable(name = "om_accept_tab", // child table name
			joinColumns = @JoinColumn(name = "om_id_col")) // fk column name
	@Column(name = "om_acpt_col") // data column name
	private Set<String> orderAccept;

	@Column(name = "order_desc_col")
	private String description;
}
