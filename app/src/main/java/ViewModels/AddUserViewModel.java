package ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import Models.User;
import Room.UserRepository;

public class AddUserViewModel extends AndroidViewModel {

    private UserRepository repo;

    public AddUserViewModel(@NonNull Application application) {
        super(application);

        repo = new UserRepository(application);
    }

    public void insert(User user){

        repo.insert(user);
    }
}
