package com.example.ashton_morgan_student_scheduler_c196;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SchedulerRepository {

    private CourseDao courseDao;
    private LiveData<List<Course>> allCourses;

    private TermDao termDao;
    private LiveData<List<Term>> allTerms;

    private AssessmentDao assessmentDao;
    private LiveData<List<Assessment>> allAssessments;

    public SchedulerRepository(Application application) {
        SchedulerDatabase database = SchedulerDatabase.getInstance(application);

        courseDao = database.courseDao();
        allCourses = courseDao.getAllCourses();

        termDao = database.termDao();
        allTerms = termDao.getAllTerms();

        assessmentDao = database.assessmentDao();
        allAssessments = assessmentDao.getAllAssessments();


    }


    public void insert(Term term) {

        new InsertTermAsyncTask(termDao).execute(term);
    }

    public void update(Term term) {
        new UpdateTermAsyncTask(termDao).execute(term);

    }

    public void delete(Term term) {

        new DeleteTermAsyncTask(termDao).execute(term);
    }

    public void deleteAllTerms() {
        new DeleteAllTermsAsyncTask(termDao).execute();
    }

    public LiveData<List<Term>> getAllTerms() {
        return allTerms;
    }

    private static class InsertTermAsyncTask extends AsyncTask<Term, Void, Void> {
        private TermDao termDao;

        private InsertTermAsyncTask(TermDao termDao) {
            this.termDao = termDao;
        }

        @Override
        protected Void doInBackground(Term... terms) {

            termDao.insert(terms[0]);
            return null;
        }
    }

    private static class UpdateTermAsyncTask extends AsyncTask<Term, Void, Void> {
        private TermDao termDao;

        private UpdateTermAsyncTask(TermDao termDao) {
            this.termDao = termDao;
        }

        @Override
        protected Void doInBackground(Term... terms) {

            termDao.update(terms[0]);
            return null;
        }
    }

    private static class DeleteTermAsyncTask extends AsyncTask<Term, Void, Void> {
        private TermDao termDao;

        private DeleteTermAsyncTask(TermDao termDao) {
            this.termDao = termDao;
        }

        @Override
        protected Void doInBackground(Term... terms) {

            termDao.delete(terms[0]);
            return null;
        }
    }

    private static class DeleteAllTermsAsyncTask extends AsyncTask<Void, Void, Void> {
        private TermDao termDao;

        private DeleteAllTermsAsyncTask(TermDao termDao) {
            this.termDao = termDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            termDao.deleteAllNotes();
            return null;
        }
    }


    public void insert(Course course) {
        new InsertCourseAsyncTask(courseDao).execute(course);
    }

    public void update(Course course) {
        new UpdateCourseAsyncTask(courseDao).execute(course);
    }

    public void delete(Course course) {
        new DeleteCourseAsyncTask(courseDao).execute(course);

    }

    public void deleteAllCourses() {
        new DeleteAllCoursesAsyncTask(courseDao).execute();

    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    private static class InsertCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        private InsertCourseAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {

            courseDao.insert(courses[0]);
            return null;
        }
    }

    private static class UpdateCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        private UpdateCourseAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {

            courseDao.update(courses[0]);
            return null;
        }
    }

    private static class DeleteCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        private DeleteCourseAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {

            courseDao.delete(courses[0]);
            return null;
        }
    }

    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void> {
        private CourseDao courseDao;

        private DeleteAllCoursesAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            courseDao.deleteAllCourses();
            return null;
        }
    }


    public void insert(Assessment assessment) {
        new InsertAssessmentAsyncTask(assessmentDao).execute(assessment);
    }

    public void update(Assessment assessment) {
        new UpdateAssessmentAsyncTask(assessmentDao).execute(assessment);
    }

    public void delete(Assessment assessment) {
        new DeleteAssessmentAsyncTask(assessmentDao).execute(assessment);

    }

    public void deleteAllAssessments() {
        new DeleteAllAssessmentsAsyncTask(assessmentDao).execute();

    }

    public LiveData<List<Assessment>> getAllAssessments() {
        return allAssessments;
    }

    private static class InsertAssessmentAsyncTask extends AsyncTask<Assessment, Void, Void> {
        private AssessmentDao assessmentDao;

        private InsertAssessmentAsyncTask(AssessmentDao assessmentDao) {
            this.assessmentDao = assessmentDao;
        }

        @Override
        protected Void doInBackground(Assessment... assessments) {

            assessmentDao.insert(assessments[0]);
            return null;
        }
    }

    private static class UpdateAssessmentAsyncTask extends AsyncTask<Assessment, Void, Void> {
        private AssessmentDao assessmentDao;

        private UpdateAssessmentAsyncTask(AssessmentDao assessmentDao) {
            this.assessmentDao = assessmentDao;
        }

        @Override
        protected Void doInBackground(Assessment... assessments) {

            assessmentDao.update(assessments[0]);
            return null;
        }
    }

    private static class DeleteAssessmentAsyncTask extends AsyncTask<Assessment, Void, Void> {
        private AssessmentDao assessmentDao;

        private DeleteAssessmentAsyncTask(AssessmentDao assessmentDao) {
            this.assessmentDao = assessmentDao;
        }

        @Override
        protected Void doInBackground(Assessment... assessments) {

            assessmentDao.delete(assessments[0]);
            return null;
        }
    }

    private static class DeleteAllAssessmentsAsyncTask extends AsyncTask<Void, Void, Void> {
        private AssessmentDao assessmentDao;

        private DeleteAllAssessmentsAsyncTask(AssessmentDao assessmentDao) {
            this.assessmentDao = assessmentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            assessmentDao.deleteAllAssessments();
            return null;
        }
    }

}































