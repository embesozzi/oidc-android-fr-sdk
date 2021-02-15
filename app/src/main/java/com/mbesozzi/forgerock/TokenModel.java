package com.mbesozzi.forgerock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TokenModel extends ViewModel {
    private String jsonTokens;
    public MutableLiveData<String> item = new MutableLiveData<>();

    LiveData<String> getItem() {
        return item;
    }

    public void setJsonTokens(String jsonTokens){
        this.jsonTokens = jsonTokens;
    }

    public void postItem(String item){
        this.item.postValue(item);
    }
    public void setItem(String item){
        this.item.setValue(item);
    }

    public String getJsonTokens(){
        return jsonTokens;
    }

}
