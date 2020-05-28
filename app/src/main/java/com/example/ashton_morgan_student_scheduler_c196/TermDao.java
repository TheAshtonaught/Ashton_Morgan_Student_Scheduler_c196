package com.example.ashton_morgan_student_scheduler_c196;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TermDao {

    @Insert
    void insert(Term term);

    @Update
    void update(Term term);

    @Delete
    void delete(Term term);

    @Query("DELETE FROM term_table")
    void deleteAllNotes();

    @Query("SELECT * FROM term_table")
    LiveData<List<Term>> getAllTerms();

}
