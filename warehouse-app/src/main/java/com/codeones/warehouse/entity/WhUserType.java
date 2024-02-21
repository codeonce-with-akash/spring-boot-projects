package com.codeones.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@Table(name = "wh_user_type_tab")
public class WhUserType {
	@Id
	@GeneratedValue(generator = "whusr_gen")
	@SequenceGenerator(name = "whusr_gen", sequenceName = "whusr_seq")
	@Column(name = "wh_usr_id_col")
	private Long id;

	@Column(name = "wh_usr_type_col")
	private String userType;

	@Column(name = "wh_usr_code_col")
	private String userCode;

	@Column(name = "wh_usr_for_col")
	private String userFor;

	@Column(name = "wh_usr_email_col")
	private String userEmail;

	@Column(name = "wh_usr_contact_col")
	private String userContact;

	@Column(name = "wh_usr_id_type_col")
	private String userIdType;

	@Column(name = "wh_usr_other_col")
	@JsonInclude(value = Include.NON_NULL)
	private String ifOther;

	@Column(name = "wh_usr_id_num_col")
	private String userIdNum;
}
