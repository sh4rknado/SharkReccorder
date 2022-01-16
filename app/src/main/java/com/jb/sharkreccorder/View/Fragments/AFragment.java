package com.jb.sharkreccorder.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.jb.sharkreccorder.Model.AModel;
import com.jb.sharkreccorder.Persistence.BundleVars;

public abstract class AFragment extends Fragment {

    public Menu menu;
    public View root;

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        this.menu = menu;
    }

    public void showMessage(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void Add(AModel model, Class<?> cls, String EXTRA) {
        Intent intent = new Intent(this.root.getContext(), cls);

        if(model != null) {
            Bundle b = new Bundle();
            b.putString(EXTRA, AModel.ConvertToJson(model));
            intent.putExtras(b);
        }

        startActivityForResult(intent, BundleVars.ADD_REQUEST);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        root = null;
    }

}
