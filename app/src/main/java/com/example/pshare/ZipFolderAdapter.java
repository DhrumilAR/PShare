package com.example.pshare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ZipFolderAdapter extends RecyclerView.Adapter<ZipFolderAdapter.ZipFolderViewHolder> {

    private List<ZipFolder> zipFolders;

    public ZipFolderAdapter(List<ZipFolder> zipFolders) {
        this.zipFolders = zipFolders;

    }

    @NonNull
    @Override
    public ZipFolderAdapter.ZipFolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_homefragment, parent, false);
        return new ZipFolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZipFolderAdapter.ZipFolderViewHolder holder, int position) {
        ZipFolder zipFolder = zipFolders.get(position);
    }

    @Override
    public int getItemCount() {
        return zipFolders.size();
    }

    public static class ZipFolderViewHolder extends RecyclerView.ViewHolder {
        private TextView folderNameTextView;

        public ZipFolderViewHolder(View itemView) {
            super(itemView);
            folderNameTextView = itemView.findViewById(R.id.textfolder);
        }
    }
}
