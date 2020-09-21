/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.dodemy.questionsandanswers.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dodemy.questionsandanswers.data.local.db.dao.OptionDao;
import com.dodemy.questionsandanswers.data.local.db.dao.QuestionDao;
import com.dodemy.questionsandanswers.data.local.db.dao.UserDao;
import com.dodemy.questionsandanswers.data.model.db.Option;
import com.dodemy.questionsandanswers.data.model.db.Question;
import com.dodemy.questionsandanswers.data.model.db.User;


@Database(entities = {User.class, Question.class, Option.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OptionDao optionDao();

    public abstract QuestionDao questionDao();

    public abstract UserDao userDao();
}
