package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.notesreactive.R;

import java.util.ArrayList;
import java.util.List;

import Models.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> mUsers = new ArrayList<>();
    private Context context;

    public UserAdapter(List<User> mUsers, Context context) {
        this.mUsers = mUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_row_layout,parent,false);

       ViewHolder viewHolder = new ViewHolder(v);

       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        User user = mUsers.get(position);

        holder.row_name.setText(user.getName());
        holder.row_age.setText(user.getAge());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setmUsers(List<User> mUsers) {
        this.mUsers = mUsers;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView row_name,row_age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            row_name = itemView.findViewById(R.id.row_name);
            row_age = itemView.findViewById(R.id.row_age);
        }
    }

}
