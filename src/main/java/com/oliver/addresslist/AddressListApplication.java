package com.oliver.addresslist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.oliver.addresslist.encrypt.GeneKeyPair;

import java.util.Map;

@SpringBootApplication
public class AddressListApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressListApplication.class, args);
        GeneKeyPair.generateKeyPair();
        if (GeneKeyPair.keyCount()==0){
            GeneKeyPair.generateKeyPair();
        }
        System.out.println(GeneKeyPair.readKey("PUBLIC_KEY_IDEA"));
        System.out.println(GeneKeyPair.readKey("PRIVATE_KEY"));
    }

}
