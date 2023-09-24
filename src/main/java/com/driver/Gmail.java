package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Gmail extends Email {
private HashMap<String , Date> Inbox    = new HashMap<>();
private  ArrayList<String> seqMessages  = new ArrayList<>();

private  ArrayList<String> trashBin = new ArrayList<>();

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
          super(emailId);
          this.inboxCapacity = inboxCapacity;
    }


    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.


        if(Inbox.size() == inboxCapacity) {

            //   the inbox is full remove the oldest mail

            String oldMessage = seqMessages.get(0);

            trashBin.add(oldMessage);//add the message and date to the thrashBin

            Inbox.remove(oldMessage);//remove that message from the inbox

            seqMessages.remove(0);//remove the message from sequence
        }

        // add normally

        seqMessages.add(message);//assign in the sequence

        Inbox.put(message , date);//assign in the inbox

    }

    public void deleteMail(String message){

        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        if(Inbox.containsKey(message)) {
            Date date = Inbox.get(message);
            Inbox.remove(message);//deleted from the Inbox

            for(int i = 0 ;i < seqMessages.size();i++) {
                if(seqMessages.get(i).equals(message)) seqMessages.remove(i);//remove that message from seqMessages also
            }

            trashBin.add(message);
        }


    }

    public String findLatestMessage(){

        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(seqMessages.size() == 0) return null;

        String latestMessage = seqMessages.get(seqMessages.size()-1);

        return latestMessage;
    }

    public String findOldestMessage(){

        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

        if(seqMessages.size() == 0) return null;

        String oldestMessage = seqMessages.get(0);

        return oldestMessage;

    }

    public int findMailsBetweenDates(Date start, Date end){

        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date (includes start and end date both)

        int noOfMails = 0;

        for(Date date : Inbox.values()) {

           if(date.equals(start) || date.equals(end) || (date.compareTo(start) >= 0 && date.compareTo(end) <= 0)) noOfMails++;
        }

        return  noOfMails;
    }

    public int getInboxSize(){

        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){

        // Return number of mails in Trash
       System.out.println(trashBin);
        return trashBin.size();

    }

    public void emptyTrash(){

        // clear all mails in the trash

        for(int i = 0 ;i < trashBin.size(); i++){
            trashBin.remove(i);
            i--;
        }
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox

        return inboxCapacity;
    }
}
