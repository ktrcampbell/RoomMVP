package com.bb.mvproomapp.presenter;

import androidx.room.Room;

import com.bb.mvproomapp.database.UserEntity;
import com.bb.mvproomapp.database.UsersDB;
import com.bb.mvproomapp.view.HomeActivity;

public class RoomPresenter implements Contract.RoomPresenter {

    private Contract.View mainView;
    private UsersDB usersDB;

    private UserEntity currentUser = null;

    public RoomPresenter(Contract.View mainView) {
        this.mainView = mainView;
        usersDB = Room
                .databaseBuilder(((HomeActivity)mainView).getApplicationContext(),
                UsersDB.class,
                "users.db"
        )
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public void logInUser(String userName, String userPassword) {

        currentUser = usersDB.getUserDAO().loginSelect(userName, userPassword);
        if(currentUser == null){
            mainView.userLoginFailed();
        }else{
            mainView.userLoginSuccess();
        }
    }

    @Override
    public void signOutUser() {
        currentUser = null;
        mainView.userLoggedOut();
    }

    @Override
    public UserEntity getUserInstance() {
        return currentUser;
    }

    @Override
    public void getGitResults() {
//TODO: make API call to Git
    }
}
