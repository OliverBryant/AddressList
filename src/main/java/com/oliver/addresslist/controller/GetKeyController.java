package com.oliver.addresslist.controller;

import com.oliver.addresslist.encrypt.GeneKeyPair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GetKeyController {

    @GetMapping(value = "getKey")
    public List<String> getKey(@RequestParam ("PUBLIC_KEY_AS") String PUBLIC_KEY_AS){
        String publicKey = GeneKeyPair.readKey("PUBLIC_KEY_IDEA");
        Integer len = GeneKeyPair.keyCount()-2;
        String s = "PUBLIC_KEY_AS_"+len;
        GeneKeyPair.writeKey(s,PUBLIC_KEY_AS);
        List<String> list = new ArrayList<>();
        list.add(publicKey);
        list.add(len.toString());
        return list;
    }
}
