package com.oliver.addresslist.service;

import com.oliver.addresslist.entity.Contector;
import com.oliver.addresslist.entity.SimplyContector;
import com.oliver.addresslist.repository.ShowAllContactorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShowAllContactorService {

    @Autowired
    private ShowAllContactorRepository showAllContactorRepository;

    public List<SimplyContector> selectAllContactors(String num){
        return showAllContactorRepository.selectAllContactors(num);
    }

    public List<Contector> research(String id,String num){
        return showAllContactorRepository.research(id,num);
    }


    @Transactional
    public List<SimplyContector> selectSomeContactors(String part,String num){
        return showAllContactorRepository.selectSomeContactors(part,num);
    }
}
