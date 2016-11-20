package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.ucsy.xolution.DetailActivity;
import com.ucsy.xolution.R;

import java.util.List;

import model.Lawyer;

/**
 * Created by Ye Wint  Naing on 11/19/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<Lawyer> lawyarList;
    Activity context;
    int last = -1;
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView name, skill, rating;

        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            skill = (TextView) view.findViewById(R.id.skill);
            rating = (TextView) view.findViewById(R.id.rating);


            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Lawyer l = lawyarList.get(getPosition());
            String name = l.name;
            String[] area = l.area_law;
            String about = l.about;
            int image = l.image;

           // Toast.makeText(context, area[0], Toast.LENGTH_SHORT).show();


           Intent i = new Intent(context, DetailActivity.class);
            i.putExtra("name",name);
            i.putExtra("area",area);
            i.putExtra("about",about);
            i.putExtra("image",image);

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(context, v.findViewById(R.id.image), "profile");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                context.startActivity(i,options.toBundle());
            }else{
                context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                context.startActivity(i);


            }
        }
    }


    public HomeAdapter(Activity context, List<Lawyer> lawyarList) {
        this.lawyarList = lawyarList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lawyer_show_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Lawyer data = lawyarList.get(position);
        Picasso.with(context).load(data.image).transform(new CircleTransform()).into(holder.image);
        holder.name.setText(data.name);
     //   holder.skill.setText(data.skill);
       // holder.rating.setText(data.rating);

        if(position > last){
            last = position;

            Animation ani = AnimationUtils.loadAnimation(context,R.anim.move);
            holder.itemView.setAnimation(ani);
        }

    }

    @Override
    public int getItemCount() {
        return lawyarList.size();
    }
}