package com.jb.sharkreccorder.View.Holders;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jb.sharkreccorder.Utils.Observer.IObserver;

public abstract class AHolder extends RecyclerView.ViewHolder implements IObserver {

    public AHolder(@NonNull View itemView) {
        super(itemView);
    }

}