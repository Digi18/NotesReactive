package Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Models.User;

@Database(entities = {User.class},version = 1)
public abstract class UserDb extends RoomDatabase {

    public abstract UserDao userDao();

    private static UserDb instance;

    public static synchronized UserDb getInstance(Context context){

        if(instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext(),UserDb.class,
                                         "User_database").fallbackToDestructiveMigration().build();

        }

        return instance;
    }


}
