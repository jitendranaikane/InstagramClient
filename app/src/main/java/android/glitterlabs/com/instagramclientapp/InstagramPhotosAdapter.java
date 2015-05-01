package android.glitterlabs.com.instagramclientapp;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto>
{

    //what data do we need from activity.
    //Context Datasource.
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects)
    {
        super(context,android.R.layout.simple_list_item_1,objects);
    }

     @Override

     public  View getView(int position,View convertView,ViewGroup parent )
     {
           final InstagramPhoto photo=getItem(position);

         if(convertView==null)
         {
             //create the new view for templete.
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.item_photo,parent,false);
         }
       //set the ID for each vaiew item.
         TextView tvCaption=(TextView)convertView.findViewById(R.id.tvCaption);
         ImageView ivPhoto=(ImageView)convertView.findViewById(R.id.ivPhoto);
         TextView tvUsername=(TextView)convertView.findViewById(R.id.tvUsername);
         TextView tvLike=(TextView)convertView.findViewById(R.id.tvLikes);
         ImageView ivProfile=(ImageView)convertView.findViewById(R.id.ivUserProfile);
         final TextView tvComment=(TextView)convertView.findViewById(R.id.tvComment);

         tvComment.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View v)
             {

                 final Dialog dialog=new Dialog(getContext());
                 dialog.setTitle("User Comments");
                 dialog.setContentView(R.layout.comment_dialog);

                 dialog.show();
                 Button btnOk=(Button)dialog.findViewById(R.id.btnOK);
                 TextView tvComment1=(TextView)dialog.findViewById(R.id.tvComment1);
                 tvComment1.setText(photo.comments);
                 btnOk.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v)
                     {
                         dialog.dismiss();


                     }
                 });

             }
         });

         //insert the model data into each of the view.
         tvCaption.setText(photo.caption);
         tvUsername.setText(photo.username);
         tvLike.setText(String.valueOf(photo.likesCounts));
         //clear the image view
         ivPhoto.setImageResource(0);
         ivProfile.setImageResource(0);


         // insert the image using Picasso.
         Picasso.with(getContext()).load(photo.imageUrl).placeholder(R.drawable.ic_launcher).into(ivPhoto);



         Picasso.with(getContext()).load(photo.imageProfile).placeholder(R.drawable.ic_launcher).into(ivProfile);


         //Return  created item as a view
         return  convertView;
     }

}
