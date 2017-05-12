package com.example.yura9.sitkovigrafiku;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

/**
 * Created by yura9 on 1/30/2017.
 */

public class WorkList{

    private List<Work> mWorks;

    private List<Work> mCritical;
    private static WorkList sWorkList;

    private int mMaxNumber;

    public String getDeadLine() {
        return mDeadLine;
    }

    private String mDeadLine;
    private Calendar calendar;

    public int getTotalDaysAmount() {
        return totalDaysAmount;
    }

    private int totalDaysAmount;

    public static WorkList get(){
        if(sWorkList == null) {
            sWorkList = new WorkList();
        }
        return sWorkList;
    }

    private WorkList(){
        mWorks = new ArrayList<>();
        mCritical = new ArrayList<>();
        calendar = Calendar.getInstance();
        Work w = new Work(1,"Початок проекту", 0, "0");
        Work w2 = new Work(2,"Вибір системи", 15, "1");
        Work w3 = new Work(3,"Придбання програмного забезпечення", 7, "2");
        Work w4 = new Work(4,"Планування проекту мережі", 7, "2");
        Work w5 = new Work(5,"Придбання комп’ютерів та мережевого обладнання", 15, "2");
        Work w6 = new Work(6,"Навчання адміністратора та програміста", 30, "4");
        Work w7 = new Work(7,"Монтаж локальної мережі", 20, "4;5");
        Work w8 = new Work(8,"Встановлення ПЗ на комп’ютери", 5, "3;5");
        Work w9 = new Work(9,"Вставлення мережевого ПЗ, налаштування мережі", 25, "6;7;8");
        Work w10 = new Work(10,"Введення початкових данних в інформаційну базу", 40, "9");
        Work w11 = new Work(11,"Навчання персоналу", 30, "9");
        Work w12 = new Work(12,"Поредання в експлуатацію", 5, "10;11");
        Work w13 = new Work(13,"Кінець проекту", 0, "12");
        mWorks.add(w);
        mWorks.add(w2);
        mWorks.add(w3);
        mWorks.add(w4);
        mWorks.add(w5);
        mWorks.add(w6);
        mWorks.add(w7);
        mWorks.add(w8);
        mWorks.add(w9);
        mWorks.add(w10);
        mWorks.add(w11);
        mWorks.add(w12);
        mWorks.add(w13);


        setMaxNumber();
        setTr();
        setTp();
        setDate();
        criticalWay();

    }



    public List<Work> getWorks() {
        return mWorks;
    }

    public void setTr(){
        for (Work chWork : mWorks){
            if (chWork.getmNumber() == 1){
                chWork.mTrp = 0;
            }
            for (Work w : mWorks){
                if (w.getPreviousWorks().contains(chWork.getmNumber())){
                    if (w.mTrp < chWork.mTrp + chWork.getTimeCount())
                        w.mTrp = chWork.mTrp + chWork.getTimeCount();
                }

            }
        }
    }

    public void setTp(){
        ListIterator<Work> li = mWorks.listIterator(mWorks.size());
        Work current;
        Work previous;

        while (li.hasPrevious()){
            current = li.previous();
            if (current.getmNumber() == mMaxNumber) {
                current.mTpp = current.mTrp;
                continue;
                //частковий резерв
                //current.mRv = current.mTpk - current.mTrk;
            }
            ListIterator<Work> liS = mWorks.listIterator(mWorks.size());
            while (liS.hasPrevious()){
                previous = liS.previous();
                if (previous.getPreviousWorks().contains(current.getmNumber())) {

                    current.mTpp = Math.min(current.mTpp, previous.mTpp - current.getTimeCount());

                }
            }
            //Повний резер
            current.mRp = current.mTpp - current.mTrp;

            current.mTrk = current.mTrp + current.getTimeCount();
            current.mTpk = current.mTpp + current.getTimeCount();
        }
    }




    public void setMaxNumber(){
        mMaxNumber = 0;
        for (Work w : mWorks){
            if (w.getmNumber() > mMaxNumber) mMaxNumber = w.getmNumber();
        }
    }


    public Work getWork(UUID uuid){
        for (Work work : mWorks){
            if (work.getID().equals(uuid)){
                return work;
            }
        }
        return null;
    }

    public void setDate(){
        for (Work work : mWorks){
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, work.mTrp);
            work.setDate(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, work.getTimeCount());
            work.setEndDate(calendar.getTime());
        }
    }

    public void addWork(Work work){
        mWorks.add(work);
    }



    public void setWork(Work work) {
        mWorks.add(work);
    }

    public int getWorksCount(){
        return mWorks.size();
    }

    public List<Work> getCritical() {
        return mCritical;
    }

    public void setCritical(List<Work> critical) {
        mCritical = critical;
    }


    public void criticalWay(){
        for (Work work : mWorks){
            if (work.mRp == 0){
                mCritical.add(work);
                totalDaysAmount+=work.getTimeCount();
                if (work.getmNumber()==mMaxNumber){
                    mDeadLine = work.getDateString(work.getEndDate());
                }
            }
        }
    }

}
