package com.oliver.addresslist.controller;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oliver.addresslist.encrypt.*;
import com.oliver.addresslist.entity.Contactor;
import com.oliver.addresslist.service.AddNewContactorService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Map;


@RestController
public class AddNewContactorController {
    @Autowired
    private AddNewContactorService addNewContactorService;

    @PostMapping(value = "/addnew")
    @Transactional
    public void addNewContactor(@RequestParam("encryptKey")String encrypteKey,@RequestParam("busData")String busData,@RequestParam("num")String num) throws EncrypException {

        String decryptKey = RsaUtil.decrypt(encrypteKey, GeneKeyPair.readKey("PRIVATE_KEY"));
        System.out.println("解密后的密钥：" + decryptKey);

        String decryptBusData = AesUtil.decrypt(busData,decryptKey);
        JsonObject respDataJsonObject = JsonParser.parseString(decryptBusData).getAsJsonObject();
        System.out.println("解密后的数据：" + respDataJsonObject.toString());

        String decryptPlainText = respDataJsonObject.get("plainText").getAsString();
        String decryptSignInfo = respDataJsonObject.get("signInfo").getAsString();
        String s = "PUBLIC_KEY_AS_"+num;
        boolean verifySign = SHA256withRSA.verifySign(GeneKeyPair.readKey(s),decryptPlainText,decryptSignInfo);
        System.out.println("是否验签通过：" + verifySign);

        Contactor contactor;
        if (verifySign == true){
            JSONObject jsonObject = JSONObject.fromObject(decryptPlainText);
            contactor = (Contactor) JSONObject.toBean(jsonObject,Contactor.class);

            String name = contactor.getName();
            String mobilePhone = contactor.getMobilePhone();
            String officePhone = contactor.getOfficePhone();
            String familyPhone = contactor.getFamilyPhone();
            String position = contactor.getPosition();
            String company = contactor.getCompany();
            String address = contactor.getAddress();
            String zipcode = contactor.getZipcode();
            String email = contactor .getEmail();
            String remark = contactor.getRemark();

            addNewContactorService.save(name, mobilePhone, officePhone, familyPhone, position, company, address, zipcode, email, remark, num);
        }else {
            System.out.println("签名验证失败，请确认发送方信息!!!!");
        }
    }

}
