/*
 * Copyright (c) 2018 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish, 
 * distribute, sublicense, create a derivative work, and/or sell copies of the 
 * Software in any work that is designed, intended, or marketed for pedagogical or 
 * instructional purposes related to programming, coding, application development, 
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works, 
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.dodemy.mvvm_creaturemon.model.room
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.dodemy.mvvm_creaturemon.app.CreaturemonApplication
import com.dodemy.mvvm_creaturemon.model.Creature
import com.dodemy.mvvm_creaturemon.model.CreatureRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RoomRepository : CreatureRepository {

  private val mDiskIO: ExecutorService = Executors.newFixedThreadPool(2)

  private val creatureDao: CreatureDao = CreaturemonApplication.database.creatureDao()

  private val allCreatures: LiveData<List<Creature>>

  init {
    allCreatures = creatureDao.getAllCreatures()
  }

  override fun saveCreature(creature: Creature) {
    InsertAsyncTask(creatureDao).execute(creature)
  }

  override fun getAllCreatures() = allCreatures

  override fun clearAllCreatures() {
    val creatureArray = allCreatures.value?.toTypedArray()
    if(creatureArray != null){
      DeleteAsyncTask(creatureDao).execute(*creatureArray)
    }
  }

  private class InsertAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      dao.insert(params[0])
      return null
    }
  }

  private class DeleteAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      dao.clearCreatures(*params)
      return null
    }
  }

}

/*
private AsyncTask mAsyncTask;
@Override
public void onStart() {
    super.onStart();
    mAsyncTask = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... voids) {
            while (!isCancelled()) {
                Log.d("AsyncTask", "count: " + counter.get());
                counter.incrementAndGet();
            }
            return null;
        }
    }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
}
@Override
public void onStop() {
    super.onStop();
    mAsyncTask.cancel(true);
}
 */