package com.oliver.addresslist.repository;

import com.oliver.addresslist.entity.Contactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AddNewContactorRepository extends JpaRepository<Contactor,Integer> {

    @Query(value = "insert into Contactor(name, mobilePhone, officePhone, familyPhone, position, company, address, zipcode, email, remark, num) value (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11)" ,nativeQuery = true)
    @Modifying
    public void insertContactor(String name,String mobilePhone,String officePhone,String familyPhone,String position,String  company,String address,String zipcode,String email,String remark,String num);
}
