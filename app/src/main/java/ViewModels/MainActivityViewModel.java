package ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import Models.User;
import Room.UserRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private UserRepository repos;
    private LiveData<List<User>> allUsers;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repos = new UserRepository(application);
        allUsers = repos.getAllUsers();

    }

    public LiveData<List<User>> getAllUsers(){

        return allUsers;
    }
}
