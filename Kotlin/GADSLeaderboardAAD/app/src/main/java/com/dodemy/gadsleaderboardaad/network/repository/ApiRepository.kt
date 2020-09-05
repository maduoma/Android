package com.dodemy.gadsleaderboardaad.network.repository

import android.util.Log
import com.dodemy.gadsleaderboardaad.model.Hours
import com.dodemy.gadsleaderboardaad.model.Skills
import com.dodemy.gadsleaderboardaad.network.ApiService
import com.dodemy.gadsleaderboardaad.network.Result
import com.dodemy.gadsleaderboardaad.network.SubmissionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val service: ApiService,
    private val submissionService: SubmissionService
) {

    suspend fun getHours(): Result<List<Hours>> {
        return try {
            Result.Success(withContext(Dispatchers.IO) {
                service.getHoursAsync().await()
            })
        } catch (ex: Exception) {
            return Result.Error(ex.message!!)
        }
    }

    suspend fun getSkills(): Result<List<Skills>> {
        return try {
            Result.Success(withContext(Dispatchers.IO) {
                service.getSkillsAsync().await()
            })
        } catch (ex: Exception) {
            return Result.Error(ex.message!!)
        }
    }

    suspend fun makeSubmission(
        emailAddress: String,
        name: String,
        lastName: String,
        linkToProject: String
    ): Result<Unit> {
        return try {
            Result.Success(withContext(Dispatchers.IO) {
                submissionService.makeSubmissionAsync(emailAddress, name, lastName, linkToProject)
                    .await()
            })
        } catch (ex: Exception) {
            Log.e("mistake", ex.message!!)
            return Result.Error(ex.message!!)
        }
    }
}