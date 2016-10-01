package com.codepath.simpletodo;

import android.support.annotation.IntDef;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

/**
 * Created by Punam on 9/27/2016.
 */

@Table(database = MyDatabase.class)
public class Item extends BaseModel implements Serializable {
    @Column
    @PrimaryKey(autoincrement=true)
    long _id;

    @Column
    String itemName;

    @Column
    Date dueDate;

    @Column
    @Priority int priority;

    @IntDef({Priority.HIGH, Priority.MEDIUM, Priority.LOW, Priority.NONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority{
        int NONE = 0;
        int LOW = 1;
        int MEDIUM = 2;
        int HIGH = 3;
    }

    public void setName(String name){
        itemName = name;
    }

    public void setDueDate(Date date){
        dueDate = date;
    }

    public void setPriority(@Priority int p){
        priority = p;
    }

    public String getItemName(){
        return itemName;
    }

    public Date getDueDate(){
        return dueDate;
    }

    public @Item.Priority int getPriority(){
        return priority;
    }
}
