package com.oliver.addresslist.service;

import com.oliver.addresslist.repository.ModifyContactorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ModifyContactorService {
    @Autowired
    private ModifyContactorRepository modifyContactorRepository;

    @Transactional
    public void modifyContactor(String name,String mobilePhone,String officePhone,String familyPhone,String position,String  company,String address,String zipcode,String email,String remark,String num,Integer id){
        modifyContactorRepository.modifyContactor(name, mobilePhone, officePhone, familyPhone, position, company, address, zipcode, email, remark, num,id);
    }
}
