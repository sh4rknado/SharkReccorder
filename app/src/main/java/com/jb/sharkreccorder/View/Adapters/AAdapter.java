package com.jb.sharkreccorder.View.Adapters;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.View.Holders.AHolder;
import java.util.ArrayList;
import java.util.List;

public abstract class AAdapter<AModel, ViewHolder extends AHolder> extends RecyclerView.Adapter<ViewHolder>  implements IObserver {

    public List<ViewHolder> modelHolders;
    public List<AModel> models;
    public ViewGroup parent;

    public AAdapter() {
        this.modelHolders = new ArrayList<>();
        this.models = new ArrayList<>();
    }

    public List<AModel> getSelectedItem() {
        List<AModel> selectedModel = new ArrayList<>();

        for (AModel m : models)
        {
            com.jb.sharkreccorder.Model.AModel _m = (com.jb.sharkreccorder.Model.AModel) m;
            if(_m.isSelected()) selectedModel.add(m);
        }

        return selectedModel;
    }

    public void resetSelectedItem() {

        for (AModel m : models)
            ((com.jb.sharkreccorder.Model.AModel) m).setSelected(false);

        // for (ViewHolder vw : modelHolders)
            // vw.imageButton.setBackground(ContextCompat.getDrawable(this.parent.getContext(), R.drawable.ic_ansible_red_rounded));

        // this.update(0);
    }

    public void selectAllItems() {
        for (AModel m : models)
            ((com.jb.sharkreccorder.Model.AModel) m).setSelected(true);

        // for (ViewHolder vw : modelHolders)
        //    vw.imageButton.setBackground(ContextCompat.getDrawable(this.parent.getContext(), R.drawable.ic_selected_foreground));

        // this.update(2);
    }

    @Override
    public int getItemCount() { return models.size(); }

    public void setModels(List<AModel> _models) { this.models = _models; notifyDataSetChanged(); }

}

