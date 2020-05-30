package com.example.ashton_morgan_student_scheduler_c196;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Term.class, Course.class}, version = 1)
public abstract class SchedulerDatabase extends RoomDatabase {


    private static SchedulerDatabase instance;

    public abstract CourseDao courseDao();
    public abstract TermDao termDao();

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
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TermDao termDao;
        private CourseDao courseDao;

        private PopulateDbAsyncTask(SchedulerDatabase database) {
            termDao = database.termDao();
            courseDao = database.courseDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            termDao.insert(new Term("Term 1", "10/01/2020", "04/01/2021"));
            termDao.insert(new Term("Term 2", "05/01/2021", "11/01/2021"));
            termDao.insert(new Term("Term 1", "11/01/2021", "04/01/2022"));

            courseDao.insert(new Course("C495 Software Development","10/01/2020", "11/15/2020",
                    "In Progress", "Tem Mohamed tem233@Wgu.edu 868-847-1444", "Performance assessment", "none", 1));



            return null;
        }
    }
}
