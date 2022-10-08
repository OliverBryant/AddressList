package com.oliver.addresslist.repository;

import com.oliver.addresslist.entity.Contactor;
import com.oliver.addresslist.entity.Contector;
import com.oliver.addresslist.entity.SimplyContector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowAllContactorRepository extends JpaRepository<Contactor,Integer> {

    @Query(value = "select c.id as id, c.name as name,c.num as num from Contactor c where num = ?1",countQuery = "select count (name) from Contactor where num = ?1",nativeQuery = true)
    @Modifying
    public List<SimplyContector> selectAllContactors(String num);

    @Query(value = "select c.id as id, c.name as name,c.num as num from Contactor c where name like %?1% and c.num = ?2" , nativeQuery = true)
    @Modifying
    public List<SimplyContector> selectSomeContactors(String part,String num);

    @Query(value = "select * from Contactor c where c.id = ?1 and c.num = ?2",nativeQuery = true)
    @Modifying
    public List<Contector> research(String id,String num);



}
