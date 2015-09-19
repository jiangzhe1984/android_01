package com.example.jiangz.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.jiangz.adapters.AccountCollectListAdapter;
import com.example.jiangz.entity.Account;
import com.example.jiangz.entity.AccountCollectByMonth;
import com.example.jiangz.service.impl.AccountServiceImpl;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JiangZ on 2015-09-19.
 */
@EActivity(R.layout.activity_accountlist)
public class AccountListViewActivity extends Activity {
    @ViewById(R.id.accountCollect_listView)
    ListView accountCollectListView;

    @ViewById(R.id.accounting)
    Button accounting;

    @Bean
    AccountServiceImpl accountService;

    @AfterViews
    public void init(){

        List<AccountCollectByMonth> accountCollects = new ArrayList<>();

        for(int i=1;i<=12;i++){
            Map filter = new HashMap<>();
            filter.put("currentMonth",i);
            List<Account> list = accountService.fetchAccountByMap(filter);

            Double monthTotal = 0.0;

            for (Account  account: list) {
                monthTotal += account.getPrice();
            }

            AccountCollectByMonth accountCollectByMonth = new AccountCollectByMonth();
            accountCollectByMonth.setCurrentMonth(String.valueOf(i));
            accountCollectByMonth.setMonthTotal(monthTotal);
            accountCollectByMonth.setTodayTotal(0.0);

            accountCollects.add(accountCollectByMonth);
        }

        String flags[] = {"currentMonth","monthTotal","todayTotal"};
        int itemIDs[] = {R.id.currentMonth,R.id.monthAccount,R.id.todayAccount};

        if(accountCollects.isEmpty()){
            AccountCollectByMonth accountCollectByMonth = new AccountCollectByMonth();
            accountCollectByMonth.setCurrentMonth("9");
            accountCollectByMonth.setMonthTotal(200.0);
            accountCollectByMonth.setTodayTotal(0.0);

            accountCollects.add(accountCollectByMonth);
        }

        AccountCollectListAdapter accountCollectListAdapter = new AccountCollectListAdapter(this,accountCollects,R.layout.layout_accountcollect,flags,itemIDs);

        accountCollectListView.setAdapter(accountCollectListAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
