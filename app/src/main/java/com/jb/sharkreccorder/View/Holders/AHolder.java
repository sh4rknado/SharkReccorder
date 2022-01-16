package com.jb.sharkreccorder.View.Holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.Utils.Observer.ISujet;

import java.util.ArrayList;
import java.util.List;


public abstract class AHolder extends RecyclerView.ViewHolder implements ISujet {

    private List<IObserver> observers;

    public AHolder(@NonNull View itemView) {
        super(itemView);
        observers = new ArrayList<>();
    }

    public AHolder(@NonNull View itemView, IObserver o) {
        super(itemView);
        observers = new ArrayList<>();
        this.register(o);
    }

    //region OBSERVER

    public void register(IObserver o) { this.observers.add(o); }

    public void unregister(IObserver o) { this.observers.remove(o); }

    public void update(String key, Object value) {
        for (IObserver o: observers)
            o.update(key, value);
    }

    //endregion

}