/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author rouao
 */
public class SmsService {
    // Your Twilio account SID and authentication token
    public static final String ACCOUNT_SID = "AC2f24f5ca89216b1001f9b94fd1912950";
    public static final String AUTH_TOKEN = "cfdd0dab57e7690a92f39e4c0b2f719b";

    // Your Twilio phone number
    public static final String TWILIO_NUMBER = "+16813813812";

    public void sendSms(String to, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message sms = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(TWILIO_NUMBER),
                message)
                .create();

        System.out.println("SMS sent: " + sms.getSid());
    }
    
}