package com.codeones.warehouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeones.warehouse.constants.WhUserTypeConstant;
import com.codeones.warehouse.entity.WhUserType;
import com.codeones.warehouse.exception.WhUserContactIsAlreadyExistException;
import com.codeones.warehouse.exception.WhUserIdNumberAlreadyExistException;
import com.codeones.warehouse.exception.WhUserTypeNotFoundException;
import com.codeones.warehouse.payloads.WhUserTypeRequest;
import com.codeones.warehouse.repository.WhUserTypeRepository;
import com.codeones.warehouse.service.IWhUserTypeService;
import com.codeones.warehouse.util.MailUtil;

import jakarta.transaction.Transactional;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {

	@Autowired
	private WhUserTypeRepository whUserTypeRepository;
	
	@Autowired
	private MailUtil mailUtil;
	
	@Override
	@Transactional
	public String createWhUserType(WhUserTypeRequest whUserTypeRequest) {
		WhUserType whUserType = new WhUserType();
		whUserType.setUserType(whUserTypeRequest.getUserType());
		whUserType.setUserCode(whUserTypeRequest.getUserCode());
		if(whUserTypeRequest.getUserType().equalsIgnoreCase(WhUserTypeConstant.WH_USR_TYPE_VENDOR))
			whUserType.setUserFor(WhUserTypeConstant.WH_USR_FOR_PURCHASE);
		else if(whUserTypeRequest.getUserType().equalsIgnoreCase(WhUserTypeConstant.WH_USR_TYPE_CUSTOMER))
			whUserType.setUserFor(WhUserTypeConstant.WH_USR_FOR_SALE);
		if(getWhUserEmailCount(whUserTypeRequest.getUserEmail())>0)
			throw new WhUserTypeNotFoundException(whUserTypeRequest.getUserEmail() + WhUserTypeConstant.WH_USR_ALREADY_EXIST);
		else
			whUserType.setUserEmail(whUserTypeRequest.getUserEmail());
		
		if(whUserTypeRepository.getWhUserContactCount(whUserTypeRequest.getUserContact())>0)
			throw new WhUserContactIsAlreadyExistException(whUserTypeRequest.getUserContact()+WhUserTypeConstant.WH_USR_ALREADY_EXIST);
		else
			whUserType.setUserContact(whUserTypeRequest.getUserContact());
		
		whUserType.setUserIdType(whUserTypeRequest.getUserIdType());
		
		if(whUserType.getUserIdType().equalsIgnoreCase(WhUserTypeConstant.WH_USR_ID_TYPE_OTHER))	
			whUserType.setIfOther(whUserTypeRequest.getIfOther());
		
		if (whUserTypeRepository.getWhUserIdNumCount(whUserTypeRequest.getUserIdNum())>0) 
			throw new WhUserIdNumberAlreadyExistException(whUserTypeRequest.getUserIdNum()+WhUserTypeConstant.WH_USR_ALREADY_EXIST);
		else
			whUserType.setUserIdNum(whUserTypeRequest.getUserIdNum());
		
		Long id = whUserTypeRepository.save(whUserType).getId();
		
		// On saved successful Sending email to the whUser. 
		if(id > 0) {
			new Thread(()-> {
					mailUtil.sendEmail(
							whUserTypeRequest.getUserEmail(), 
							"Warehouse User Registration", 
							new StringBuffer("Congratulations! ").append(whUserTypeRequest.getUserCode()).append(" your registration is successful for Warehouse application!").toString());
							}
					).start();
		}
		
		return new StringBuilder(WhUserTypeConstant.WH_USR_TYPE_MSG).append(id).append(WhUserTypeConstant.WH_USR_CREATED_MSG).toString();
	}
	
	@Override
	public Integer getWhUserEmailCount(String whUserEmail) {
		return whUserTypeRepository.getWhUserEmailCount(whUserEmail);
	}
	
	@Override
	public WhUserType getWhUserTypeById(Long id) {
		return whUserTypeRepository.findById(id).orElseThrow(() -> new WhUserTypeNotFoundException(new StringBuilder(WhUserTypeConstant.WH_USR_TYPE_MSG).append(id).append(WhUserTypeConstant.WH_USR_NOT_FOUND_MSG).toString()));
	}
	
	@Override
	public List<WhUserType> getAllWhUserType() {
		return whUserTypeRepository.findAll();
	}
	
	@Override
	public WhUserType updateWhUserType(Long id, WhUserTypeRequest whUserTypeRequest) {
		WhUserType dbWhUserType = getWhUserTypeById(id);
		if(whUserTypeRequest.getUserType()!=null)
			dbWhUserType.setUserType(whUserTypeRequest.getUserType());
		if(whUserTypeRequest.getUserCode()!=null)
			dbWhUserType.setUserCode(whUserTypeRequest.getUserCode());
		if(whUserTypeRequest.getUserEmail()!=null) {
			if(whUserTypeRepository.getWhUserEmailCountForUpdate(whUserTypeRequest.getUserEmail(), id)>0) 
				throw new WhUserTypeNotFoundException(new StringBuilder(whUserTypeRequest.getUserEmail()).append(WhUserTypeConstant.WH_USR_ALREADY_EXIST).toString());
			dbWhUserType.setUserEmail(whUserTypeRequest.getUserEmail());
		}
		
		if(whUserTypeRequest.getUserContact()!=null) {
			if(whUserTypeRepository.getWhUserContactCountForUpdate(whUserTypeRequest.getUserContact(), id)>0)
				throw new WhUserContactIsAlreadyExistException(whUserTypeRequest.getUserContact()+WhUserTypeConstant.WH_USR_ALREADY_EXIST);
			dbWhUserType.setUserContact(whUserTypeRequest.getUserContact());
		}
		if(whUserTypeRequest.getUserIdType()!=null)
			dbWhUserType.setUserIdType(whUserTypeRequest.getUserIdType());
		
		if(whUserTypeRequest.getUserIdNum()!=null) {
			if(whUserTypeRepository.getWhUserIdNumCountForUpdate(whUserTypeRequest.getUserIdNum(), id)>0)
				throw new WhUserIdNumberAlreadyExistException(whUserTypeRequest.getUserIdNum()+WhUserTypeConstant.WH_USR_ALREADY_EXIST);
			dbWhUserType.setUserIdNum(whUserTypeRequest.getUserIdNum());
		}
		if(whUserTypeRequest.getIfOther()!=null)
			dbWhUserType.setIfOther(whUserTypeRequest.getIfOther());
		return whUserTypeRepository.save(dbWhUserType);
	}
	
	@Override
	public void deleteWhUserType(Long id) {
		WhUserType dbWhUserType = getWhUserTypeById(id);
		whUserTypeRepository.delete(dbWhUserType);
	}

}
