package com.example.ashton_morgan_student_scheduler_c196;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Term.class, Course.class}, version = 1)
public abstract class ScheduleDatabase extends RoomDatabase {


    private static ScheduleDatabase instance;

    public abstract CourseDao courseDao();
    public abstract TermDao termDao();

    public static synchronized ScheduleDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ScheduleDatabase.class, "schedule_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
