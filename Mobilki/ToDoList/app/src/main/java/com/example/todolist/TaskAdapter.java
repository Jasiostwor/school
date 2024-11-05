package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Task task, int position);
    }

    public TaskAdapter(List<Task> taskList, OnItemClickListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(task, position));
    }

    @Override
    public int getItemCount() { return taskList.size(); }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public TaskViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.task_title);
        }
    }

    public void setTasks(List<Task> tasks) {
        this.taskList = tasks;
    }
}


