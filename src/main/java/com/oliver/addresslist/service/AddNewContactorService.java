package com.oliver.addresslist.service;

import com.oliver.addresslist.repository.AddNewContactorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddNewContactorService {

    @Autowired
    private AddNewContactorRepository addNewContactorRepository;

    public void save(String name,String mobilePhone,String officePhone,String familyPhone,String position,String  company,String address,String zipcode,String email,String remark,String num){addNewContactorRepository.insertContactor(name, mobilePhone, officePhone, familyPhone, position, company, address, zipcode, email, remark, num);}

}
