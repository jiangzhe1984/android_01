package com.example.jiangz.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jiangz.entity.Userinfo;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by JiangZ on 2015-09-07.
 */
public class UserinfoAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private int layoutID;

    private String flag[];

    private int itemIDs[];

    private List<Userinfo> userinfoList;

    public UserinfoAdapter(Context context,List<Userinfo> userinfoList,int layoutID, String flag[], int itemIDs[]){
        this.mInflater = LayoutInflater.from(context);
        this.userinfoList = userinfoList;
        this.layoutID = layoutID;
        this.flag = flag;
        this.itemIDs = itemIDs;
    }

    @Override
    public int getCount() {
        return userinfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return userinfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(layoutID, null);

        for (int i = 0; i < flag.length; i++) {//备注1
            if (convertView.findViewById(itemIDs[i]) instanceof ImageView) {
                ImageView iv = (ImageView) convertView.findViewById(itemIDs[i]);
             /*   iv.setBackgroundResource((Integer) userinfoList.get(position).get(
                        flag[i]));*/
            } else if (convertView.findViewById(itemIDs[i]) instanceof TextView) {
                TextView tv = (TextView) convertView.findViewById(itemIDs[i]);
                Userinfo userinfo = userinfoList.get(position);
                try {
                    Method method = Userinfo.class.getMethod("get" + flag[i].substring(0, 1).toUpperCase() + flag[i].substring(1), null);
                    String value = (String)method.invoke(userinfo);
                    tv.setText(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{
                //...备注2
                Log.i("TAG", "else");
            }
        }
        return convertView;
    }


    public static void main(String[] args){
        System.err.println("get"+"username".substring(0, 1).toUpperCase()+"username".substring(1));
    }

}
