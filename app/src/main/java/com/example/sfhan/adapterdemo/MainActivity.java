package com.example.sfhan.adapterdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private Context mContext =MainActivity.this;

    private List<infoData> list=new ArrayList<>();
    private List<String> list1=new ArrayList<>();
    private List<String> list2=new ArrayList<>();
    private List<String> list3=new ArrayList<>();
    private List<String> list4=new ArrayList<>();

    private ListView lv_activity;
    private CommonAdapter adapter;

    private final int DADEDR=10001;
    private final int ADACAB=10002;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case DADEDR:
                    list.add(new infoData(true,"CK",list1));
                    list.add(new infoData(false,"LXD",list2));
                    list.add(new infoData(false,"LC",list3));
                    list.add(new infoData(false,"CL",list4));
                    getDataAdapter();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_activity=findViewById(R.id.lv);

        initView();
    }

    private void initView() {
        for (int i=0;i<5;i++) {
            list1.add("ck"+i);
            list2.add("lxd"+i);
            list3.add("lc"+i);
            list4.add("cl"+i);
        }

        Message msg=new Message();
        msg.what=10001;
        mHandler.sendMessage(msg);

    }


    private void getDataAdapter(){
        if (adapter == null ){

            adapter=new CommonAdapter(mContext,list,R.layout.item_family_info_lv) {
                @Override
                public void convert(CommonViewHolder holder, final Object item, final int position) {
                    LinearLayout linearLayout=holder.getView(R.id.ll_item_family_info_title);
                    ImageView imageView=holder.getView(R.id.iv_item_family_info_title);
                    final TextView textView=holder.getView(R.id.tv_item_family_info_title);
                    textView.setText(list.get(position).getTitle());
                    ListView lv=holder.getView(R.id.lv_item_fmt_family_info);
                    if (list.get(position).isAa()){
                        imageView.setImageResource(R.drawable.sj1);
                        lv.setVisibility(View.VISIBLE);
                    }else {
                        imageView.setImageResource(R.drawable.sj);
                        lv.setVisibility(View.GONE);
                    }

                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (list.get(position).isAa()){
                                list.get(position).setAa(false);
                            }else {
                                for (infoData info : list){
                                    info.setAa(false);
                                }
                                list.get(position).setAa(!list.get(position).isAa());
                            }

                            adapter.notifyDataSetChanged();
                        }
                    });

                    lv.setAdapter(new CommonAdapter<String>(mContext,list.get(position).getList(),R.layout.lv_list_zi) {
                        @Override
                        public void convert(CommonViewHolder holder, String item, int position) {
                            TextView textView=holder.getView(R.id.zi_tv);
                            textView.setText(item);
                        }
                    });

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(mContext,"你点击的是"+item,Toast.LENGTH_LONG).show();

                        }
                    });

                }
            };
            lv_activity.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }




    }

}
