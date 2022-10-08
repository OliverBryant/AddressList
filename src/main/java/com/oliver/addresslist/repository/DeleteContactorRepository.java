package com.oliver.addresslist.repository;


import com.oliver.addresslist.entity.Contactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DeleteContactorRepository extends JpaRepository<Contactor,Integer> {

    @Query(value = "delete from Contactor where id=?1 and num = ?2" , nativeQuery = true)
    @Modifying
    public void deleteContator(String id,String num);

}
