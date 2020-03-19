package com.bb.mvproomapp.presenter;

import com.bb.mvproomapp.database.UserEntity;

public interface Contract {

    public interface RoomPresenter{

        void logInUser(String userName, String userPassword);

        void signOutUser();

        UserEntity getUserInstance();

        void getGitResults();
    }

    interface View{

        void userLoginSuccess();

        void userLoginFailed();

        void userLoggedOut();

        void userNotLoggedInMessage();

        void displayResults();
    }
}
