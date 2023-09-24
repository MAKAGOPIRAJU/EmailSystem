package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private  ArrayList<Meeting> calendar; // Stores all the meetings


    public Workspace(String emailId) {
        super(emailId);
        // The inboxCapacity is equal to the maximum value an integer can store.
        this.calendar = new ArrayList<>();
         return;
    }

    public void addMeeting(Meeting meeting){

        //add the meeting to calendar
        LocalTime start = meeting.getStartTime();
        LocalTime end   = meeting.getEndTime();

        Meeting meet = new Meeting(start , end);

        calendar.add(meet);

    }

    public int findMaxMeetings(){

        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

    Collections.sort(calendar , (a,b)->{

        if(a.getStartTime() == b.getStartTime()) return a.getEndTime().compareTo(b.getEndTime());//if start time is equal store them basing on end time

        return a.getStartTime().compareTo(b.getStartTime());//store them basing on the start time in assending order
    });

    LocalTime previousMeetStartTime = calendar.get(0).getStartTime();
    LocalTime previousMeetEndTime   = calendar.get(0).getEndTime();

    int maxNoOfMeetings = 1;

    for(int i = 1 ;i < calendar.size(); i++){

        Meeting currMeet = calendar.get(i);
        LocalTime currMeetStartTime = currMeet.getStartTime();
        LocalTime currMeetEndTime   = currMeet.getEndTime();

        if(currMeetStartTime.compareTo(previousMeetEndTime) <= 0){

            //the currentMeeting start time is less than or equal previous meet end time the both the meetings are overlapped

            if(previousMeetEndTime.compareTo(currMeetEndTime) > 0){

                //if the previousMeetEnd time is greater than currMeetEnd time
                //that means the duration period of previous meeting is greater than current meeting so skip that previous meeting

                previousMeetStartTime = currMeetStartTime;
                previousMeetEndTime   = currMeetEndTime;

            }
        }

        else{

            //meetings are not overLapped

            maxNoOfMeetings++;
            previousMeetStartTime = currMeetStartTime;
            previousMeetEndTime   = currMeetEndTime;
        }
    }
    return maxNoOfMeetings;
    }
}
