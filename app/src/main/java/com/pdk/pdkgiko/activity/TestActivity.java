package com.pdk.pdkgiko.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.base.BaseActivity;
import com.pdk.pdkgiko.service.MyService;
import com.pdk.pdkgiko.utils.MyDatabaseHelper;

import org.w3c.dom.Node;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by uatql90533 on 2018/3/13.
 */
public class TestActivity extends BaseActivity implements View.OnClickListener {

    private Button startService, stopService, bindService,
            unbindService, bt_create_database,
            bt_add_data, bt_send_broadcast,
            bt_send_localbroadcast;
    private MyService.DownloadBinder downloadBinder;
    private MyDatabaseHelper dbHelper;
    private IntentFilter intentFilter;
    private NetWorkChangeReceive networkChangeReceiver;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        bt_create_database = (Button) findViewById(R.id.bt_create_database);
        bt_add_data = (Button) findViewById(R.id.bt_add_data);
        bt_send_broadcast = (Button) findViewById(R.id.bt_send_boradcast);
        bt_send_localbroadcast = (Button) findViewById(R.id.bt_send_localbroadcast);
        bt_send_broadcast.setOnClickListener(this);
        startService.setOnClickListener(this);
//        startService.setOnClickListener(
//                v -> Toast.makeText(TestActivity.this, "lambda test", Toast.LENGTH_SHORT).show());
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        bt_create_database.setOnClickListener(this);
        bt_add_data.setOnClickListener(this);
        bt_send_localbroadcast.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        super.initData();
//        quickSort(arr, 0, arr.length - 1);
        selectSort();
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetWorkChangeReceive();
        registerReceiver(networkChangeReceiver, intentFilter);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);//获取实例
        IntentFilter intentFilter02 = new IntentFilter();
        intentFilter02.addAction("com.pdk.pdkdemo.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter02);

//        List<String> list = Arrays.asList("aa", "bb", "cc");
//        Collections.sort(list);
////        list.sort((e1, e2) -> e1.compareTo(e2));
//        list.forEach(e -> System.out.println(e));
//        list.forEach(System.out::println);
//        for (String s : list) {
//            System.out.println(s);
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("not lambda");
//            }
//        }).start();
//
//        new Thread(() -> System.out.println("function lambda")).start();
//
//        List<String> strings = Arrays.asList("00", "01", "02", "03");
//        for (String string : strings) {
//            System.out.println(string);
//        }
//
//        strings.forEach(x -> System.out.println(x));
//        strings.forEach(System.out::println);
//
//        List<Double> cost = Arrays.asList(5.0, 6.0, 7.0);
//        cost.stream().map(x -> x + x * 0.5).forEach(x -> System.out.println(x));
//        cost.sort();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.start_service:
//                Intent startIntent = new Intent(this, MyService.class);
//                startService(startIntent);
//                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent binderIntent = new Intent(this, MyService.class);
                bindService(binderIntent, connection, BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.unbind_service:
                unbindService(connection);//解绑服务
                break;

            case R.id.bt_create_database:
                dbHelper.getWritableDatabase();
                break;
            case R.id.bt_add_data:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //组装一条数据
                values.put("name", "PDK Code");
                values.put("author", "PDK");
                values.put("pages", "666");
                values.put("price", "500.00");
                db.insert("Book", null, values);
                values.clear();
                //再组装一条数据
                values.put("name", "WAR");
                values.put("author", "PDK");
                values.put("pages", "686");
                values.put("price", "501.01");
                db.insert("Book", null, values);
                break;
            case R.id.bt_send_boradcast:
                Intent intent = new Intent("com.pdk.pdkdemo.MY_BROADCAST");
                sendBroadcast(intent);//无序广播
                sendOrderedBroadcast(intent, null);//有序广播
                break;
            case R.id.bt_send_localbroadcast:
                Intent intent1 = new Intent("com.pdk.pdkdemo.LOCAL_BRODACAST");
                localBroadcastManager.sendBroadcast(intent1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class NetWorkChangeReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connetivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netWorkInfo = connetivityManager.getActiveNetworkInfo();
            if (netWorkInfo != null && netWorkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }


    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();

        }
    }

    private int[] arr = {5, 6, 9, 3, 2, 9, 1, 8, 0};

    private void quickSort(int[] arr, int low, int hight) {
        int temp, t, i, j;//temp基准位
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        temp = arr[low];
        while (i < j) {
            int kk = arr[j];
            while (temp <= arr[j] && i < j) {
                j--;
            }

            while (temp >= arr[i] && i < j) {
                i++;
            }
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }

        //基准位与i=j位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;

        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, hight);
    }

//    private int[] arr = {5, 1, 2, 5, 3, 4, 8, 2};

    public void bubblingSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    public void selectSort() {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length - 1; j++) {
                if (arr[minIndex] > arr[j + 1]) {
                    minIndex = j + 1;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        int[] a = arr;
        System.out.println();

    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public @interface MyTag {
        String name() default "李 四";//也可以指定默认值

        int age() default 18;
    }

    class Test {
        @MyTag(name = "张三", age = 18)
        public void initInfo() {
        }

        @MyTag//如果指定了默认值就可以为这些成员变量指定值而是使用的默认值
        public void initInfo01() {
        }

    }

    /**
     * 遍历
     *
     * @param viewGroup
     * @param id
     * @return
     */
    private static View find(ViewGroup viewGroup, int id) {
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = viewGroup.getChildAt(i);
            if (v.getId() == id) {
                return v;
            }
            if (v instanceof ViewGroup) {
                View temp = find((ViewGroup) v, id);
                //再次循环遍历view是否还有子view
                if (temp != null) {
                    return temp;
                }
            }
        }
        return null;

    }

    /**
     * 使用AsyncTask 重写的4个方法
     *
     * onPreExecute() 后台任务执行前调用，通常用于一些界面的初始化工作，比如显示一个进度条对话框
     *
     * doInBackground(Params...) 该方法内的所有代码都会在子线程中运行，我们在这里处理所有的耗时任务
     * 任务一旦完成，就可以通过return语句来将任务的执行结果返回，如果AsynTask的第三个泛型参数指定的是void，就可以不返回执行结果
     * 因为这个方法中的代码都是在子线程中运行的所以不能进行更新UI的操作，如若想更新UI比如反馈当前任务进度，可以调用publishProgress(Progress...)方法来完成
     *
     * onProgressUpadte(Progress...) 当后台任务调用了publishProgress(Progress...)方法，
     * onProgressUpdate(Progress...)运行在主线程方法很快就会被调用该方法中携带的参数是后台任务
     * 传递过来的。在这个方法中中可以对UI进行操作，利用参数中的数值就可以对界面元素进行相应的更新
     *
     * onPostExecute() 运行在主线程当后台任务执行完毕并通过return语句进行返回时，这个方法很快就会别调用。返回的数据会作为参数传递到此方法中，可以利用返回的数据进行一些UI操作
     * 比如说：提醒任务的执行结果，以及关闭进度条对话框等
     *
     *
     *
     */

    class MyTaak extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            return null;
        }
    }

    /**
     * 线程池
     */
    //缓存线程池newCachedThreadPool; 如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
    private void testCachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

    //定长线程池newFixedThreadPool 可控制线程最大并发数，超出的线程会在队列中等待
    private void testFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    //支持定时以及周期性执行任务的定长线程池 newScheduledThreadPool
    private void testScheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {//延迟3秒执行
            @Override
            public void run() {
                System.out.println("delay 3 seconds");

            }
        }, 3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {//延迟1秒后每3秒执行一次
            @Override
            public void run() {
                System.out.println("delay 1 seconds，and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    //单线程池 newSingleThreadPool 它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO,LIFO,优先级)执行
    private void testSingleThreadPool() {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    //CoreThreadPool核心线程池
    //拒绝策略当线程池和workQueue都满了的情况下对新任务采取的策略，四种默认实现
    //
    //
    //
    //

}
