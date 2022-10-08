package com.oliver.addresslist.controller;


import com.google.gson.JsonObject;
import com.oliver.addresslist.encrypt.*;
import com.oliver.addresslist.entity.Contactor;
import com.oliver.addresslist.entity.Contector;
import com.oliver.addresslist.entity.SimplyContector;
import com.oliver.addresslist.service.ShowAllContactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ShowAllContactorController {

    @Autowired
    private ShowAllContactorService showAllContactorService;


    @GetMapping(value = "showAll")
    public List<SimplyContector> showAllContactors(@RequestParam("num") String num){

        return showAllContactorService.selectAllContactors(num);

    }

    @GetMapping(value = "research")
    public List<String> research(@RequestParam("id") String id,@RequestParam("num")String num) throws EncrypException {

        List<Contector> list = showAllContactorService.research(id,num);
        Contactor contactor = new Contactor();

        for (Contector contector : list) {
            contactor.setId(contector.getId());
            contactor.setName(contector.getName());
            contactor.setMobilePhone(contector.getMobilePhone());
            contactor.setOfficePhone(contector.getOfficePhone());
            contactor.setFamilyPhone(contector.getFamilyPhone());
            contactor.setPosition(contector.getPosition());
            contactor.setCompany(contector.getCompany());
            contactor.setAddress(contector.getAddress());
            contactor.setZipcode(contector.getZipcode());
            contactor.setRemark(contector.getRemark());
            contactor.setEmail(contector.getEmail());
            contactor.setNum(contector.getNum());
        }

        //签名
        JsonObject busDataJson = new JsonObject();
        busDataJson.addProperty("plainText",contactor.toString());
        String signInfo = null;
        try {
            signInfo = SHA256withRSA.sign(GeneKeyPair.readKey("PRIVATE_KEY"),contactor.toString());
        } catch (EncrypException e) {
            e.printStackTrace();
        }
        busDataJson.addProperty("signInfo",signInfo);
        System.out.println("签名信息：" + signInfo);

        //AES密钥生成
        String aesKey = AesUtil.getAESSecureKey();
        System.out.println("密钥明文：" + aesKey);

        //加密随机密钥
        String encryptedAesKey = null;
        try {

            String s = "PUBLIC_KEY_AS_"+num;
            System.out.println(num);
            System.out.println(s);

            encryptedAesKey = RsaUtil.encrypt(aesKey,GeneKeyPair.readKey(s));
        } catch (EncrypException e) {
            e.printStackTrace();
        }
        System.out.println("密钥密文：" + encryptedAesKey);

        //数据加密
        String encrypt = null;
        try {
            encrypt = AesUtil.encrypt(busDataJson.toString(),aesKey);
        } catch (EncrypException e) {
            e.printStackTrace();
        }
        System.out.println("加密数据：" + encrypt);

        List<String> root = new ArrayList<>();
        root.add(encryptedAesKey);
        root.add(encrypt);

        return  root;
    }

    @GetMapping(value = "showSome")
    public List<SimplyContector> showSomeContactors(@RequestParam("part")String part,@RequestParam("num")String num){
        return showAllContactorService.selectSomeContactors(part,num);
    }

}
