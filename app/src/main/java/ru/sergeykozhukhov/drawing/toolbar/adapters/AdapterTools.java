package ru.sergeykozhukhov.drawing.toolbar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.sergeykozhukhov.drawing.R;
import ru.sergeykozhukhov.drawing.toolbar.models.Tool;

/*
* Адаптер для ToolBar
* */

public class AdapterTools extends RecyclerView.Adapter<AdapterTools.ViewHolder> {

    /*
    * onToolBarClickListener - обработчик нажатий на элементы ToolBar
    * tools - набор элементов ToolBar
    * */
    private OnToolBarClickListener onToolBarClickListener;
    private List<Tool> tools;

    public AdapterTools(List<Tool> tools, OnToolBarClickListener onToolBarClickListener) {
        this.tools = tools;
        this.onToolBarClickListener = onToolBarClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tool, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Tool tool = tools.get(position);
        holder.bindView(tool, onToolBarClickListener);

    }

    @Override
    public int getItemCount() {
        return tools.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imv_tool);

        }

        private void bindView(final Tool tool, final OnToolBarClickListener listener) {

            imageView.setImageResource(tool.getIdImage());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(tool);
                }
            });
        }

    }
}
