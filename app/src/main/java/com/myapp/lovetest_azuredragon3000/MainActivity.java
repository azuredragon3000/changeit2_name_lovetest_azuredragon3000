package com.myapp.lovetest_azuredragon3000;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myapp.lovetest_azuredragon3000.billing.BillingClientLifecycle;
import com.myapp.lovetest_azuredragon3000.billing.BillingUltiti;
import com.myapp.lovetest_azuredragon3000.billing.ViewModelBilling;
import com.myapp.lovetest_azuredragon3000.boiphuongdong.ActivityPhuongDong;
import com.myapp.lovetest_azuredragon3000.boiphuongtay.ActivityPhuongTay;
import com.myapp.lovetest_azuredragon3000.boitinhyeu.ActivityBTYMain;
import com.myapp.lovetest_azuredragon3000.boivochong.ActivityBoiChongVo;
import com.myapp.lovetest_azuredragon3000.cauduyen.ActivityCauDuyen;
import com.myapp.lovetest_azuredragon3000.congiap12.ActivityCungMang;
import com.myapp.lovetest_azuredragon3000.congiap12.ActivityNamSinhMain;
import com.myapp.lovetest_azuredragon3000.login.LoginActivity;
import com.myapp.lovetest_azuredragon3000.luckydraw.ActivityLuckyCirle;
import com.myapp.lovetest_azuredragon3000.ngontinh.ActivityNgonTinh;
import com.myapp.lovetest_azuredragon3000.reatimeDB.FireBaseReadDB;
import com.myapp.lovetest_azuredragon3000.reatimeDB.FireBaseWriteDB;
import com.myapp.mylibrary.DB.ItemTruyen;
import com.myapp.mylibrary.ads.AdsInterstitial;
import com.myapp.mylibrary.recycleview.GridSpacingItemDecoration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    public final String PATHDB = "https://navigation-ce3f9-default-rtdb.asia-southeast1.firebasedatabase.app/";
    RecyclerView rc;
    int spanCount;
    int value;
    boolean login;
    AdsInterstitial adsInterstitial;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_main);
        value = this.getResources().getConfiguration().orientation;
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance(PATHDB);
        this.mDatabase = database.getReference();

        adsInterstitial = new AdsInterstitial(
                "07CC7E40850ABA2DF210A2D2564CAD76",
                "ca-app-pub-8404443559572571/1888597557",
                this);
        Intent intent = getIntent();
        login = intent.getBooleanExtra("KEY",false);
        BillingClientLifecycle billingClientLifecycle = ((SubApp) getApplication()).getBillingClientLifecycle();
        ViewModelBilling viewModelBilling;
        viewModelBilling = new ViewModelProvider(this).get(ViewModelBilling.class);
        BillingUltiti billingUltiti = new BillingUltiti(viewModelBilling,
                this,
                billingClientLifecycle,this);

        //RecyclerView recyclerView = findViewById(R.id.rc);
        ListenerItem listener = new ListenerItem() {
            @Override
            public void click(int index, Activity activity) {

                //Toast.makeText(getApplicationContext(),index+"",Toast.LENGTH_SHORT).show();
                switch (index){
                    case 0:
                        startActivity(new Intent(getApplicationContext(), ActivityBTYMain.class));
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(), ActivityNamSinhMain.class));
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(), ActivityPhuongDong.class));
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(), ActivityPhuongTay.class));
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(), ActivityBoiChongVo.class));
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(), ActivityCauDuyen.class));
                        break;
                    case 6:
                        startActivity(new Intent(getApplicationContext(), ActivityNgonTinh.class));
                        break;
                    case 7:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(getApplicationContext(), ActivityCungMang.class));
                        break;
                    case 9:
                        startActivity(new Intent(getApplicationContext(), ActivityLuckyCirle.class));
                        break;
                    case 10:
                        if(login) {
                            billingUltiti.actionBilling();
                        }else{
                            Toast.makeText(getApplicationContext(), "login first", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 11:
                        if(login) {
                            new FireBaseWriteDB(mDatabase);
                            new FireBaseReadDB(mDatabase, getApplicationContext());
                        }else{
                            Toast.makeText(getApplicationContext(), "login first", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),index+"",Toast.LENGTH_SHORT).show();
                }


                adsInterstitial.showAds(activity);

               /* Intent intent = new Intent(getApplicationContext(),ContentActivity.class);
                intent.putExtra("Position",index);
                startActivity(intent);*/
               // adsInterstitial.showAds(activity);
            }
        };
        ArrayList<ItemTruyen> arrayList = new ArrayList<>();
        ItemTruyen a0 = new ItemTruyen("bói tính yêu","icon_boitinhyeu");
        arrayList.add(a0);
        ItemTruyen a1 = new ItemTruyen("bói 12 con giáp","icon_boi12congiap");
        arrayList.add(a1);
        ItemTruyen a2 = new ItemTruyen("bói phương đông","icon_boiphuongdong");
        arrayList.add(a2);
        ItemTruyen a3 = new ItemTruyen("bói phương tây","icon_boiphuongtay");
        arrayList.add(a3);
        ItemTruyen a4 = new ItemTruyen("bói vợ chồng","icon_boivochong");
        arrayList.add(a4);
        ItemTruyen a5 = new ItemTruyen("cầu duyên","icon_cauduyen");
        arrayList.add(a5);
        ItemTruyen a6 = new ItemTruyen("ngôn tình","icon_ngontinh");
        arrayList.add(a6);
        ItemTruyen a7 = new ItemTruyen("login","login");
        arrayList.add(a7);
        ItemTruyen a8 = new ItemTruyen("cung mạng","batquai1");
        arrayList.add(a8);
        ItemTruyen a9 = new ItemTruyen("lucky draw","vongxoay");
        arrayList.add(a9);
        ItemTruyen a10 = new ItemTruyen("donate","napvang");
        arrayList.add(a10);
        ItemTruyen a11 = new ItemTruyen("săn gold","icon_sangold");
        arrayList.add(a11);
        //ItemTruyen a7 = new ItemTruyen("boi 12 con giáp","icon_boi12congiap");
        //ItemTruyen a8 = new ItemTruyen("boi 12 con giáp","icon_boi12congiap");


        rc = findViewById(R.id.rc);
        AdapterMain adapterTruyen = new AdapterMain(this,arrayList,listener,this);
        rc.setAdapter(adapterTruyen);
        rc.setLayoutManager(new GridLayoutManager(this,2));
        if (value == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = 2;
        }
        else if (value == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3;
        }
        int spacing_left = 10; // 50px
        int spacing_top=10;

        rc.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing_left, spacing_top));

        /*Button bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(v->{


        });

        Button bt2 = findViewById(R.id.bt2);
        bt2.setOnClickListener(v->{


           //new FireBaseReadDB(mDatabase,this);
        });

        Button bt3 = findViewById(R.id.bt3);
        bt3.setOnClickListener(v->{

        });

        Button bt4 = findViewById(R.id.bt4);
        bt4.setOnClickListener(v->{

        });

        Button bt5 = findViewById(R.id.bt5);
        bt5.setOnClickListener(v->{

        });

        Button bt6 = findViewById(R.id.bt6);
        bt6.setOnClickListener(v->{

        });

        Button bt7 = findViewById(R.id.bt7);
        bt7.setOnClickListener(v->{

        });

        Button bt8 = findViewById(R.id.bt8);
        bt8.setOnClickListener(v->{

        });

        Button bt9 = findViewById(R.id.bt9);
        bt9.setOnClickListener(v->{

        });

        Button bt10 = findViewById(R.id.bt10);
        bt10.setOnClickListener(v->{

        });

        Button bt11 = findViewById(R.id.bt11);
        bt11.setOnClickListener(v->{
           // startActivity(new Intent(this, Bill.class));
            billingUltiti.actionBilling();
        });

        Button bt12 = findViewById(R.id.bt12);
        bt12.setOnClickListener(v->{
            new FireBaseWriteDB(mDatabase);
        });*/

    }
}