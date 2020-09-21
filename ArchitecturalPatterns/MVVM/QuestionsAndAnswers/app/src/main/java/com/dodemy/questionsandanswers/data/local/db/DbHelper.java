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


import com.dodemy.questionsandanswers.data.model.db.Option;
import com.dodemy.questionsandanswers.data.model.db.Question;
import com.dodemy.questionsandanswers.data.model.db.User;

import io.reactivex.Observable;
import java.util.List;



public interface DbHelper {

    Observable<List<Question>> getAllQuestions();

    Observable<List<User>> getAllUsers();

    Observable<List<Option>> getOptionsForQuestionId(Long questionId);

    Observable<Boolean> insertUser(final User user);

    Observable<Boolean> isOptionEmpty();

    Observable<Boolean> isQuestionEmpty();

    Observable<Boolean> saveOption(Option option);

    Observable<Boolean> saveOptionList(List<Option> optionList);

    Observable<Boolean> saveQuestion(Question question);

    Observable<Boolean> saveQuestionList(List<Question> questionList);
}
