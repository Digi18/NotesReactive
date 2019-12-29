package Room;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.notesreactive.MainActivity;

import java.util.List;
import java.util.logging.LogRecord;

import Models.User;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class UserRepository {

    private UserDb userDb;
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private Context ctx;

    public UserRepository(Application application) {

        userDb = UserDb.getInstance(application);
        userDao = userDb.userDao();
        allUsers = userDao.getAllUsers();
        ctx = application.getApplicationContext();
    }

    public void insert(final User user){

       Completable.fromAction(() -> userDb.userDao().insert(user))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new CompletableObserver() {

                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onComplete() {

                                            Intent i = new Intent(ctx,MainActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            ctx.startActivity(i);

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                           Toast.makeText(ctx,e.getMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    });

          }

          public LiveData<List<User>> getAllUsers(){

               return allUsers;
          }

 }
