package com.codeones.warehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "doc_tab")
public class Document {
	@Id
	//@GeneratedValue(generator = "doc_gen")
	//@SequenceGenerator(name = "doc_gen", sequenceName = "doc_seq")
	@Column(name = "doc_id_col")
	private Long docId;
	
	@Column(name = "doc_name_col")
	private String docName;
	
	@Lob
	@Column(name = "doc_data_col", length = 2000)
	private byte[] docData;
}
