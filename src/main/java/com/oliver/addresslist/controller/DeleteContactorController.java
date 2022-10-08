package com.oliver.addresslist.controller;


import com.oliver.addresslist.service.DeleteContactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteContactorController {
    @Autowired
    private DeleteContactorService deleteContactorService;

    @GetMapping(value = "delete")
    public void deleteContactor(@RequestParam("id") String id,@RequestParam("num")String num){deleteContactorService.deleteContactor(id,num);}
}
