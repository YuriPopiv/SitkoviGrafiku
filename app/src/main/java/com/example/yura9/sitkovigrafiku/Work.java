package com.example.yura9.sitkovigrafiku;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by yura9 on 1/30/2017.
 */

public class Work {
    private UUID mID;
    private int mNumber;
    private int mTimeCount;
    private boolean mFictive;

    private Date mDate;
    private Date mEndDate;

    public String getDescription() {
        return description;
    }

    public void setDesctription(String description) {
        this.description = description;
    }

    private String description;


    private Set<Integer> previousWorks;

    public int mTrp;
    public int mTrk;
    public int mTpp;
    public int mTpk;
    public int mRp;
    public String previous;

    public Work(){
        mID = UUID.randomUUID();
    }

    public Work(int id,String desc, int timeCount, String prev){
        mID = UUID.randomUUID();
        mNumber = id;
        mTimeCount = timeCount;
        mTpp = 999;
        mDate = new Date();
        description = desc;

        previous = prev;

        previousWorks = new HashSet<>();
        String[] s1 = prev.split(";");
        for (String i : s1){
            if (i!=null && i!=""){
                previousWorks.add(Integer.parseInt(i));
            }
        }

    }

    public int getmNumber() {
        return mNumber;
    }

    public void setmNumber(int name) {
        mNumber = name;
    }

    public int getTimeCount() {
        return mTimeCount;
    }

    public void setTimeCount(int number) {
        mTimeCount = number;
    }

    public boolean isFictive() {
        return mFictive;
    }

    public void setFictive(boolean fictive) {
        mFictive = fictive;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
    public Date getEndDate() {
        return mEndDate;
    }

    public void setEndDate(Date endDate) {
        mEndDate = endDate;
    }

    public UUID getID() {
        return mID;
    }

    public Set<Integer> getPreviousWorks() {
        return previousWorks;
    }

    public void setPreviousWorks(Set<Integer> previousWorks) {
        this.previousWorks = previousWorks;
    }

    public String toString(){
        return mNumber + " " + mTimeCount + " " + mTrp + " " + mTpp + " " + mRp + " " +getDateString(mDate) +" " + getDateString(mEndDate);
    }

    public String  getDateString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy ");
        return simpleDateFormat.format(date);
    }


}
