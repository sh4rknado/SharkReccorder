package com.jb.sharkreccorder.View.Holders;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class AHolder extends RecyclerView.ViewHolder {

    public ImageButton imageButton;

    public AHolder(@NonNull View itemView) {
        super(itemView);
        // imageButton = itemView.findViewById(R.id.btn_server);
        // itemView.setLongClickable(true);
    }

}