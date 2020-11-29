package com.dodemy.room_bakingapp.data.db;



import com.dodemy.room_bakingapp.data.db.dao.RecipeDao;
import com.dodemy.room_bakingapp.utils.AppExecutors;


public class LocalDataSource implements LocalDbHelper {

    private final RecipeDao recipeDao;
    private final AppExecutors appExecutors;
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static LocalDataSource sInstance;

    private LocalDataSource(RecipeDao dao,
                            AppExecutors executors) {
        recipeDao = dao;
        appExecutors = executors;
    }

    public synchronized static LocalDataSource getInstance(
            RecipeDao recipeDao,
            AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new LocalDataSource(recipeDao,
                        executors);
            }
        }
        return sInstance;
    }

//
//    @Override
//    public void insertRecipeList(final List<RecipeWithIngredientStep> recipeResponses) {
//        Runnable insertRunnable = new Runnable() {
//            @Override
//            public void run() {
//                recipeDao.insertRecipeList(recipeResponses);
//            }
//        };
//        appExecutors.diskIO().execute(insertRunnable);
//    }

//    @Override
//    public LiveData<List<RecipeWithIngredientStep>> getRecipeList() {
//        return recipeDao.getRecipeList();
//    }
}
