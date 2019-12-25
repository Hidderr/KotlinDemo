package com.example.myapplication;

import android.Manifest;
import android.content.ComponentCallbacks2;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.model.BleGattCharacter;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.model.BleGattService;
import com.inuker.bluetooth.library.search.SearchRequest;

import java.util.UUID;

import static com.inuker.bluetooth.library.Code.REQUEST_SUCCESS;

public class Main2Activity extends AppCompatActivity implements ComponentCallbacks2 {
    private static final String TAG = "Main2Activity";
    // private static final UUID UUID_WRITE_SERVICE        = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private static final UUID serviceUUID        = UUID.fromString("00001801-0000-1000-8000-00805f9b34fb");
    // private static final UUID characterUUID                = UUID.fromString("00001001-0000-1000-8000-00805f9b34fb");
    private static final UUID characterUUID                = UUID.fromString("00002A05-0000-1000-8000-00805F9B34FB");
    public static final String MA = "AC:92:32:26:CF:4D";
//    private static final UUID serviceUUID        = UUID.fromString("0000046a-0000-1000-8000-00805f9b34fb");
//    private static final UUID characterUUID                = UUID.fromString("0000046c-0000-1000-8000-00805f9b34fb");


    public static final String MAC = "AC:92:32:26:CF:4D";
//    public static final String MAC = "DC:74:A8:46:CE:E1";
//    public static final String MAC_READ = "38:BC:1A:40:6D:AA";
    public static final String MAC_READ = "AC:92:32:26:CF:4D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BluetoothClient mClient = new BluetoothClient(this);
        SearchRequest request = new SearchRequest.Builder()
                .searchBluetoothLeDevice(7000, 3)   // 先扫BLE设备3次，每次3s
//                .searchBluetoothClassicDevice(5000) // 再扫经典蓝牙5s
//                .searchBluetoothLeDevice(6000)      // 再扫BLE设备2s
                .build();

        findViewById(R.id.connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClient.connect(MAC_READ, new BleConnectResponse() {
                    @Override
                    public void onResponse(int code, BleGattProfile profile) {
                        if (code == REQUEST_SUCCESS) {
                            if (profile.getServices() != null) {

                            }
                            for (BleGattService service : profile.getServices()) {
                                UUID uuid = service.getUUID();
                                for (BleGattCharacter character : service.getCharacters()) {
                                    UUID u = character.getUuid();
                                    Log.e(TAG, "onResponse: ------------------characterUUID:"+u.toString() );
                                }
                                Log.e(TAG, "======================================================onResponse: serviceUUID:"+uuid.toString() );
                            }
                        }

                        Log.e(TAG, "onResponse: code:"+code );
                    }
                });
            }
        });
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mClient.write(MAC, serviceUUID, characterUUID, "hello".getBytes(), new BleWriteResponse() {
                    @Override
                    public void onResponse(int code) {
                        Log.i(TAG, "onSearchStarted: code： "+code);
                        if (code == REQUEST_SUCCESS) {
                            Log.e(TAG, "-----------------发送指令成功: ");
                            Toast.makeText(Main2Activity.this, "发送指令成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

//        mClient.search(request, new SearchResponse() {
//            @Override
//            public void onSearchStarted() {
//
//                Log.i(TAG, "onSearchStarted: ");
//
//            }
//
//            @Override
//            public void onDeviceFounded(SearchResult device) {
//                Beacon beacon = new Beacon(device.scanRecord);
//                String name = String.format("beacon for %s\n%s", device.getAddress(), beacon.toString());
//                Log.i(TAG, "onDeviceFounded: name: "+name+"  device: "+device.getAddress());
//                BluetoothLog.v(name);
//
//            }
//
//            @Override
//            public void onSearchStopped() {
//
//            }
//
//            @Override
//            public void onSearchCanceled() {
//
//            }
//        });
//
//        mClient.read(MAC_READ, serviceUUID, characterUUID, new BleReadResponse() {
//            @Override
//            public void onResponse(int code, byte[] data) {
//
//                Log.e(TAG, "notify  read[[[[[[[[[[[[[[[[[[[[[: code： "+code);
//                if (code == REQUEST_SUCCESS) {
//                    Log.i(TAG, "notify  ---------------------------接收指令指令成功: ");
//                    Toast.makeText(Main2Activity.this, "收到指令", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        mClient.notify(MAC, serviceUUID, characterUUID, new BleNotifyResponse() {
//            @Override
//            public void onNotify(UUID service, UUID character, byte[] value) {
//                Log.e(TAG, "notify  ---onNotify  read[[[[[[[[[[[[[[[[[[[[[: code： "+value.toString());
//            }
//
//            @Override
//            public void onResponse(int code) {
//                Log.e(TAG, "notify   onResponse---read[[[[[[[[[[[[[[[[[[[[[: code： "+code);
//                if (code == REQUEST_SUCCESS) {
//
//                }
//            }
//        });

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static class DFfagement extends DialogFragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    public void initPermission() {
        //去寻找是否已经有了相机的权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(MainActivity.this,"您申请了动态权限",Toast.LENGTH_SHORT).show();
        } else {
            Log.e("ygame", "initPermission...");
            //否则去请求相机权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }


}
