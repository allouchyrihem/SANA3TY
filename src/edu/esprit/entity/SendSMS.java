/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author user
 */
public class SendSMS {
    public static final String ACCOUNT_SID = "AC30c878c692fc87e203852b7ca4ae7951";     ///
    public static final String AUTH_TOKEN = "72d024e54b3979c01ef4f89cbcd2ea2a"; ///
    public static final String TWILIO_NUMBER = "+16202981697";

    public static void sendSms() {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String phoneNumber = "+216 22 367 874";
        String code="Vous avez reçu une nouvelle réclamation";
        Message message = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(TWILIO_NUMBER), code).create();
        System.out.println(message.getSid());
    }

}