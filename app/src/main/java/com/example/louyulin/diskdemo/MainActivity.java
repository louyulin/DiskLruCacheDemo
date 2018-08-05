package com.example.louyulin.diskdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.louyulin.diskdemo.disk.DiskCacheManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ArrayList<HomeArticalBean.DataBean.DatasBean> datas;
    private HomeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.lv);
        adapter = new HomeListAdapter();
        listView.setAdapter(adapter);
        datas = new ArrayList<>();
        //无网络环境的时候读取缓存，有网的时候读取网络数据
        if (NetWorkUtil.isNetWorkAvailable(this)) {
            loadNetData();
        } else {
            DiskCacheManager manager = new DiskCacheManager(AppContext.getContext(), Constants.CACHE_PATH);
            ArrayList<HomeArticalBean.DataBean.DatasBean> stories = manager.getSerializable(Constants.CACHE_KEY);
            if (stories != null) {
                adapter.setList(stories);
            } else {
                Log.d("MainActivity", "读取缓存失败");
            }
        }

        loadNetData();
    }

    private void loadNetData() {
        NetTool.getInstance().getApi()
                .getHomeList(1 + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeArticalBean>() {
                    @Override
                    public void accept(HomeArticalBean bean) throws Exception {
                        if (bean.getData().getDatas() != null) {
                            for (int i = 0; i <bean.getData().getDatas().size() ; i++) {
                                datas.add(bean.getData().getDatas().get(i));
                            }
                            adapter.setList(datas);
                            makeCache(datas);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }


    private void makeCache(ArrayList<HomeArticalBean.DataBean.DatasBean> stories) {
        Log.d("MainActivity", "stories.size():" + stories.size());
        DiskCacheManager manager = new DiskCacheManager(AppContext.getContext(), Constants.CACHE_PATH);
        manager.put(Constants.CACHE_KEY, stories);
    }
}
