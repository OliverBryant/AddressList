package com.oliver.addresslist.repository;


import com.oliver.addresslist.entity.Contactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ModifyContactorRepository extends JpaRepository<Contactor,String> {

    @Query(value = "update Contactor set name=?1, mobilePhone=?2, officePhone=?3, familyPhone=?4, position=?5, company=?6, address=?7, zipcode=?8, email=?9, remark=?10 where num = ?11 and id=?12" ,nativeQuery = true)
    @Modifying
    public void modifyContactor(String name,String mobilePhone,String officePhone,String familyPhone,String position,String  company,String address,String zipcode,String email,String remark,String num,Integer id);
}
