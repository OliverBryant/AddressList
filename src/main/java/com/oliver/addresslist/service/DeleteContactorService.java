package com.oliver.addresslist.service;

import com.oliver.addresslist.repository.DeleteContactorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteContactorService {
    @Autowired
    private DeleteContactorRepository deleteContactorRepository;

    @Transactional
    public void deleteContactor(String id,String num){deleteContactorRepository.deleteContator(id,num);}
}
