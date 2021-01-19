package com.dodemy.notetakingwithroom.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dodemy.notetakingwithroom.dao.DaoAccess;
import com.dodemy.notetakingwithroom.model.Note;


@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
