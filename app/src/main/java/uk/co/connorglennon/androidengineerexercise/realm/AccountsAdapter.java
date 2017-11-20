package uk.co.connorglennon.androidengineerexercise.realm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import uk.co.connorglennon.androidengineerexercise.R;

/**
 * Created by Connor Glennon on 20/11/2017.
 */

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.MyViewHolder>  {

    private ArrayList<RealmAccount> accounts;
    private int rv_row_account_details;
    private Context applicationContext;

    public AccountsAdapter(ArrayList<RealmAccount> accounts, int rv_row_account_details, Context applicationContext) {
        this.accounts = accounts;
        this.rv_row_account_details = rv_row_account_details;
        this.applicationContext = applicationContext;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(applicationContext).inflate(rv_row_account_details, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RealmAccount account = accounts.get(position);
        RealmAccountDetails accountDetails = account.getAccountDetails();

        try {
            //File f=new File(accountDetails.getProfilePhoto(), "profile.jpg");
            File f=new File(accountDetails.getProfilePhoto());
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
            holder.ivProfilePicture.setImageBitmap(bitmap);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        holder.tvAddress.setText(accountDetails.getAddress());
        holder.tvAge.setText(accountDetails.getAge());
        holder.tvCountry.setText(accountDetails.getCountry());
        holder.tvDOB.setText(accountDetails.getDob());
        holder.tvForename.setText(accountDetails.getForename());
        holder.tvSurname.setText(accountDetails.getSurname());
        holder.tvUsername.setText(accountDetails.getUsername());
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvForename, tvSurname, tvUsername, tvAge, tvDOB,
        tvGender, tvCountry, tvAddress;

        ImageView ivProfilePicture;


        public MyViewHolder(View itemView) {

            super(itemView);
            this.tvForename = (TextView) itemView.findViewById(R.id.forename);
            this.tvSurname = (TextView) itemView.findViewById(R.id.surname);
            this.tvUsername = (TextView) itemView.findViewById(R.id.username);
            this.tvAge = (TextView) itemView.findViewById(R.id.age);
            this.tvDOB = (TextView) itemView.findViewById(R.id.dob);
            this.tvGender = (TextView) itemView.findViewById(R.id.gender);
            this.tvCountry = (TextView) itemView.findViewById(R.id.country);
            this.tvAddress = (TextView) itemView.findViewById(R.id.address);
            this.ivProfilePicture = (ImageView) itemView.findViewById(R.id.profilePicture);
        }
    }
}
