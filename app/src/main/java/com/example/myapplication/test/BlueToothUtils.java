package com.example.myapplication.test;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.myapplication.LogUtils;
import com.example.myapplication.MyApplication;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/12/25
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
public class BlueToothUtils {
    public static final UUID UUID = java.util.UUID.fromString("00000000-0000-0000-0000-000000000000");
    private final String TAG = this.getClass().getSimpleName();
    private static BlueToothUtils utils = null;
    public static final String MA_HUAWEI = "AC:92:32:26:CF:4D";
    public static final String MA_OTHER = "48:88:79:05:F9:70";
    public static final String MA_SUM = "DC:74:A8:46:CE:E1";
    public static final String MA_MEIZU = "38:BC:1A:40:6D:AA";
    public static final String MA_TV1 = "50:13:95:97:01:AD";
    public static final String MA_TV = "50:13:95:A9:B5:58";
    public static  String MA = MA_TV1;
    private BluetoothAdapter bluetoothAdapter;
    public final UUID SPP_UUID = java.util.UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Executor executor;
    //当前链接蓝牙
    private static BluetoothSocket mSocket;

    private ArrayList<BluetoothDevice> deviceList = new ArrayList<>();//扫描到的远程蓝牙设备

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public static BluetoothDevice mDevice = null;

    private boolean enabled = false;//蓝牙是否可用
    private final int SCANTIME = 15*1000;//扫描时间
    private final int requestCode = 2002;//
    private OutputStream outputStream;
    private boolean isExit;
    public BluetoothServerSocket serverSocket;


    public static BlueToothUtils getInstance(){
        if (utils == null){
            synchronized (BlueToothUtils.class){
                if (utils == null){
                    utils = new BlueToothUtils();
                }
            }
        }
        return utils;
    }

    /**
     * 判断是否打开蓝牙
     * 没有就静默打开
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void isOpen(Handler mHandler){
//        handler = mHandler;
        LogUtils.log(TAG,"isOpen");
        if (createBluetoothAdapterAndRegisterBluetoothReceiver()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LogUtils.log(TAG,"isOpen  bluetoothAdapter=== 22222222222 connetGatt");
            connetGatt();
        }
    }

    public void createTvListenSocket() {
        if (bluetoothAdapter == null) {
            createBluetoothAdapterAndRegisterBluetoothReceiver();
        }

       executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    close();
                    LogUtils.log("BlueToothUtils","开始创建监听服务】】】】】】】】】】】】】】】】】】");
                    /*UUID是通用唯一识别码。对于蓝牙设备，每个服务都有通用，独立，唯一的UUID与之对应。也就是说在同一时间，同一地点不可能有两个不同的UUID标识的不同服务*/
                    serverSocket = bluetoothAdapter
                            .listenUsingRfcommWithServiceRecord("test", UUID);
//                            .listenUsingRfcommWithServiceRecord("test", SPP_UUID);
                    LogUtils.log("BlueToothUtils","开始创建监听服务 222222222 】】】】】】】】】】】】】】】】】】");

                    BluetoothSocket socket = serverSocket.accept();
                    LogUtils.log("BlueToothUtils","开始创建监听服务 成功3333333333】】】】】】】】】】】】】】】】】】");
                    if(socket != null){
                        PrintStream out = new PrintStream(socket.getOutputStream());
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        if (in != null) {
                            while (true) {

                                String msg = null;
                                while ((msg = in.readLine()) != null) {
                                    LogUtils.log("BlueToothUtils","监听接受的数据：msg "+msg);

                                }
                            }
                        }else {
                            LogUtils.log("BlueToothUtils","创建监听服务失败 ： BufferedReader 为null");
                        }

                    }
                    LogUtils.log("BlueToothUtils","创建监听服务成功");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    close();
                    LogUtils.log("BlueToothUtils","创建监听服务失败 ： "+e.getMessage());
                }
            }
        });


    }


    private boolean createBluetoothAdapterAndRegisterBluetoothReceiver() {
        BluetoothManager bluetoothManager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bluetoothManager = (BluetoothManager) MyApplication.context.getSystemService(Context.BLUETOOTH_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bluetoothAdapter = bluetoothManager.getAdapter(); // null:表示不支持蓝牙
        }
        if (bluetoothAdapter == null){
            LogUtils.log(TAG,"isOpen  bluetoothAdapter=== NULL");
            return true;
        }
        hasBond = false;
        initIntentFilter();
        executor = Executors.newCachedThreadPool();
        return false;
    }

    /**
     * 链接已配对蓝牙设备
     */
    public void connetGatt(){
        if (bluetoothAdapter != null){
            // true:处于打开状态, false:处于关闭状态
            enabled = bluetoothAdapter.isEnabled();
            if (!enabled){
                LogUtils.log(TAG,"connetGatt  蓝牙未打开");
//                MyApplication.activity.startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),
//                        requestCode);
            }else {//获取已配对蓝牙
                Set<BluetoothDevice> devicesSet = bluetoothAdapter.getBondedDevices();
                deviceList.clear();
                LogUtils.log(TAG,"获取配对的设备个数： " + devicesSet.size());
                boolean findDevice = false;
                for (BluetoothDevice device : devicesSet) {
//                    deviceList.add(device);
                    if (handler != null){
//                        handler.sendEmptyMessage(BluetoothActivity.SCAN_SUCC);
                    }
                    LogUtils.log(TAG,"已配对蓝牙" + device.getName()+" address: "+device.getAddress());
//                    LogUtils.log(TAG,"已配对蓝牙" + device.getName());


                    if (device.getAddress().equals(MA)){
                        if (bluetoothAdapter.isDiscovering()){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                scanning(false);
                            }
                        }
                        LogUtils.log(TAG,"找到笃定已配对蓝牙" + device.getName()+" address: "+device.getAddress());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                            connet(device);
                        }
                        findDevice = true;
                        break;
                    }


                }

                if (!findDevice) {
                    LogUtils.log(TAG,"connetGatt  开始搜索蓝牙");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        scanning(true);
                    }
                }
            }
        }
    }

    /**
     * 扫描设备
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void scanning(boolean enable){
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
            if (handler != null){
//                handler.sendEmptyMessage(BluetoothActivity.SCAN_OPEN);
            }
            return;
        }
        if (bluetoothAdapter != null){
            if (enable){
                if (handler != null){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (bluetoothAdapter != null) {
                                bluetoothAdapter.cancelDiscovery();
                            }

                            if (deviceList != null) {
                                for (BluetoothDevice device : deviceList) {
                                    if (device.getAddress().equals(MA)) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                            device.createBond();
                                            LogUtils.log(TAG,"》》》》》》》》》》》》》》》》》》》》》》》开始配对" + device.getName()+" address: "+device.getAddress());

                                        }
                                        break;
                                    }
                                }
                            }
//                            handler.sendEmptyMessage(BluetoothActivity.SCAN_CLOSE);
                            LogUtils.log("BlueToothUtils", "停止扫描设备  "+deviceList);
                        }
                    }, SCANTIME);
                }
                deviceList.clear();
//                connetGatt();
                // 寻找蓝牙设备，android会将查找到的设备以广播形式发出去
                this.bluetoothAdapter.startDiscovery();
                LogUtils.log("BlueToothUtils", "开始扫描设备");
            }else {
                if (handler != null){
                    handler.removeCallbacksAndMessages(null);
//                    handler.sendEmptyMessage(BluetoothActivity.SCAN_CLOSE);
                }
                bluetoothAdapter.cancelDiscovery();
                LogUtils.log("BlueToothUtils", "停止扫描设备");
            }
        }
    }

    /**
     * 注册广播
     */
    public void initIntentFilter() {
        // 设置广播信息过滤
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        // 注册广播接收器，接收并处理搜索结果
        MyApplication.context.registerReceiver(receiver, intentFilter);

    }

    /**
     * 销毁广播
     */
    public void unregisterReceiver() {
        MyApplication.context.unregisterReceiver(receiver);
    }

    /**
     * 蓝牙广播接收器
     */
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

//                LogUtils.log(TAG,"搜索到蓝牙设备地址:" + device.getAddress());
//                deviceList.add(device);

            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                LogUtils.log(TAG,"搜索蓝牙设备中...");

            }



            //【1】【开、关】蓝牙开、关状态
            int bluetooth_state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
            int bluetooth_previous_state =intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_STATE, -1);
            if(action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)){
                if (bluetoothAdapter == null) {
                    return;
                }
                if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_ON) {
                    LogUtils.log(TAG,"打开蓝牙");
                    enabled = bluetoothAdapter.isEnabled();
                    connetGatt();
                } else if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_OFF) {
                    LogUtils.log(TAG,"关闭蓝牙");
                }
            }

            //【2】【搜索】蓝牙搜索状态
            if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)){
                deviceList.clear();
                Log.d(TAG, "bluetooth discovery started");
            }else if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){//取消搜索或者搜索默认时间到了会触发
                Log.d(TAG, "bluetooth  discovery finished");
            }

            //【2.1】每搜索到一个新设备都会发送一次ACTION_FOUND广播
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    deviceList.add(device);
                    if (handler != null){
//                        handler.sendEmptyMessage(BluetoothActivity.SCAN_SUCC);
                    }
                }
                LogUtils.log(TAG,"2222222222222搜索到蓝牙设备名称:" + device.getName()+"  device.getBondState() :");

            }

            //【3】【配对】配对状态改变
            if(BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                int bondState = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE,BluetoothDevice.ERROR);
                if(bondState == BluetoothDevice.BOND_BONDED){//已配对
                    LogUtils.log(TAG, " 配对成功add paired device:"+device.getName() +"  hasBond: "+hasBond);
                    if (device != null && MA.equals(device.getAddress()) && !hasBond) {

                        LogUtils.log(TAG, " 配对成功 开始连接  add paired device:"+device.getName());
                        connetGatt();
                    }

                }else if(bondState == BluetoothDevice.BOND_NONE){//已取消配对
                    LogUtils.log(TAG, " 取消配对 paired device:"+device.getName());

                }else if(bondState == BluetoothDevice.BOND_BONDING){//正在配对中
                    LogUtils.log(TAG, " 正在配对。。。。。。。。。。。add paired device:"+device.getName());
                }
            }

        }

    };
    boolean hasBond = false;



    /**
     * 选择链接蓝牙设备
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public synchronized void connet(BluetoothDevice device){
        hasBond = true;
        if (bluetoothAdapter != null && device != null){
            LogUtils.log(TAG,"开始连接蓝牙--" + device.getName()+" socket: "+mSocket);
            try {
             final BluetoothSocket socket = device.createRfcommSocketToServiceRecord(UUID); //加密传输，Android系统强制配对，弹窗显示配对码
//                final BluetoothSocket socket = device.createInsecureRfcommSocketToServiceRecord(SPP_UUID); //明文传输(不安全)，无需配对
                Log.e("BlueToothUtils", "-----------run  --> 11111111111111开启线程关闭Socket连接：---------");
                close();
                mSocket = socket;
                mDevice = mSocket.getRemoteDevice();


                // 开启子线程
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            receiveDate(mSocket); //循环读取
                            asynWhile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Throwable e) {
                Log.e("BlueToothUtils", "-----------connet  --> 异常关闭Socket连接：---------");
                close();
            }
        }
    }

    /**
     * 发送数据
     *
     */
    public void sendData(byte[] by) {
        LogUtils.log(TAG, "开始写入数据");
        if (!isConnected(null)){
            LogUtils.log(TAG, "请先打开蓝牙 发送数据失败");
            return;
        }
        if (null != outputStream) {
            try {  LogUtils.log(TAG, "开始写入数据  111111111");

                outputStream.write(by, 0, by.length);
                LogUtils.log(TAG, "开始写入数据 2222222222222");
            } catch (IOException e) {
                LogUtils.log( TAG,"BlueToothUtils SerialPortThread--蓝牙通讯失败 "+e.getMessage());
                e.printStackTrace();
            }
            LogUtils.log( TAG,"BlueToothUtils SerialPortThread--发送数据 成功");
        } else {
            LogUtils.log("BlueToothUtils SerialPortUtils", "蓝牙通讯失败");
        }
    }

    /**
     * 当前设备与指定设备是否连接
     */
    public boolean isConnected(BluetoothDevice dev) {
        boolean connected = (mSocket != null && mSocket.isConnected());
        if (dev == null) {
            return connected;
        }
        return connected && mSocket.getRemoteDevice().equals(dev);
    }

    /**
     * 关闭链接蓝牙设备
     */
    public void cancle(){
        try {
            if (bluetoothAdapter != null){
                bluetoothAdapter.cancelDiscovery();
//                bluetoothAdapter.disable();
                bluetoothAdapter = null;
            }
            close();
//            ReceiveUtils.getInstance().close();
//            unregisterReceiver();
            utils = null;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void receiveDate(BluetoothSocket socket){
        try {
            mSocket = socket;
            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mSocket.connect();
                LogUtils.log(TAG,"client端socet创建成功--" );
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out，用于解决连接超时，前提是TV端已经建立了socket
                try {
                    Log.e("BlueToothUtils","00000000000  "+connectException.toString());
                    Method m = mDevice.getClass().getMethod("createRfcommSocket", new Class[] {int.class});
                    mSocket = (BluetoothSocket) m.invoke(mDevice, 1);
                    mSocket.connect();
                } catch (Exception e) {
                    Log.e("BlueToothUtils","11111111111  "+e.toString());
                    try{
                        mSocket.close();
                    }catch (IOException ie){}
                }
                return;
            }




        }catch (Exception e){
            LogUtils.log(TAG, "蓝牙连接失败" + e.getMessage());
//            close();
            e.printStackTrace();
        }
    }

    private synchronized void asynWhile() throws IOException, InterruptedException {
        LogUtils.log(TAG,"client端 asynWhile 流  创建成功--" );
        outputStream = new DataOutputStream(mSocket.getOutputStream());
        DataInputStream in = new DataInputStream(mSocket.getInputStream());
        int size = 0;
        byte[] buffer;
        isExit = false;
        while (!isExit){
            buffer = new byte[1080];
//            try {
//                if (in != null) {
//                    size = in.read(buffer);
//
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            Thread.sleep(10);
        }
    }

    /**
     * 关闭Socket连接
     */
    public void close() {
        try {
            Log.e("BlueToothUtils", "-----------关闭Socket连接：---------");
            isExit = true;
            if (mSocket != null){
                mSocket.close();
                Log.e("BlueToothUtils", "-----------成功关闭Socket连接：---------");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            Log.e("BlueToothUtils", "---------关闭Socket连接：异常  ---------"+e.getMessage());
        }

        if (serverSocket != null) {
            try {
                Log.e("BlueToothUtils", "---------关闭 serverSocket 连接：异常  ---------");
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("BlueToothUtils", "---------关闭 serverSocket 连接：异常  ---------"+e.getMessage());
            }
        }
    }
}
