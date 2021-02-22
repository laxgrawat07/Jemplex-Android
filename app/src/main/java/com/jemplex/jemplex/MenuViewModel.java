package com.jemplex.jemplex;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Stack;

public class MenuViewModel extends ViewModel {
    private final MutableLiveData<Boolean> mNewMenuStackNeeded = new MutableLiveData<>();

    private final Stack<String> MenuLevel = new Stack<>();

    private int mCounter = 0;

    public MenuViewModel(){
        MenuLevel.push("Login");
        Log.d("MainActivityViewModel", "        MainActivityViewModel NEW INSTANCE CREATED");
    }
    public void NavigationComponentClicked(String CurrentNavigation){
        //Here instead of this counter we can have the actual Menu Hierarchy saved into a list/array
        //And using the MenuID/MenuName got clicked we will find the next Menu to open or the activity to open
        //Based on if next is menu or if activity we will change the related Observable data

        mCounter++;

        MenuLevel.push(CurrentNavigation + mCounter);

        mNewMenuStackNeeded.setValue(true);
        mNewMenuStackNeeded.setValue(false);
    }


    public void NavigationBackClicked()
    {
        //May be in actual flow, we should not go back to the login screen, Instead user has to use the Logout Options Menu
        //We can handle it here
        if(!MenuLevel.empty())
        {
            mCounter--;

            MenuLevel.pop();
        }
    }

    /*
        We should not expose mutable live data, we should always expose only the LiveData
     */
    public String getCurrentFragmentData() {
        return MenuLevel.peek();
    }

    public LiveData<Boolean> getNewMenuStackNeeded() {
        return mNewMenuStackNeeded;
    }
}