package com.example.ashton_morgan_student_scheduler_c196;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 8)
public abstract class SchedulerDatabase extends RoomDatabase {


    private static SchedulerDatabase instance;

    public abstract CourseDao courseDao();
    public abstract TermDao termDao();
    public abstract AssessmentDao assessmentDao();

    public static synchronized SchedulerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SchedulerDatabase.class, "schedule_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            //new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TermDao termDao;
        private CourseDao courseDao;
        private AssessmentDao assessmentDao;

        private PopulateDbAsyncTask(SchedulerDatabase database) {
            termDao = database.termDao();
            courseDao = database.courseDao();
            assessmentDao = database.assessmentDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            String notesSample = "You will develop a multiple-screen mobile application for WGU students to track their terms, " +
                    "courses associated with each term, and assessment(s) associated with each course. " +
                    "The application will allow students to enter, edit, and delete term, co";

            termDao.insert(new Term("Term 1", "10/01/2020", "04/01/2021"));
            termDao.insert(new Term("Term 2", "05/01/2021", "11/01/2021"));
            termDao.insert(new Term("Term 3", "11/01/2021", "04/01/2022"));

            courseDao.insert(new Course("C495 Software Development","10/01/2020", "11/15/2020",
                    "In Progress", "Mickey mouse mickey@wgu.edu 373-222-2105", notesSample, 1));
            courseDao.insert(new Course("C176 Data Structures","10/01/2021", "11/15/2021",
                    "Planned", "Mickey mouse mickey@wgu.edu 373-222-2105", "none", 1));


            assessmentDao.insert(new Assessment("06/26/2020", "Android Software Scheduler", "Performance Assessment", 1));

            return null;
        }
    }
}
