package myoelectricit.android.easicare.com.armbands.utli;

import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import myoelectricit.android.easicare.com.armbands.app.MyApplication;
import myoelectricit.android.easicare.com.armbands.interfaces.OnCallReaxInterface;
import myoelectricit.android.easicare.com.armbands.interfaces.OnSynchronizationInterface;


/**
 * Created by wang on 2018/11/9.
 */
public class RootUtil {
    public static int CurrentGesture = 8;
    public static String gestureData="暂无";  //当前手势
    public static String power;     //当前电量

    //识别手势数据
    public static void GestureDecogn(byte[] recvData) {
        if (recvData.length == 33 && recvData[0] == 0x3c && recvData[1] == 0x3c
                && recvData[2] == 0x03 && recvData[32] == 0x3e && recvData[31] == 0x3e) {
            CurrentGesture = recvData[25];
            power = (recvData[17] - recvData[16]) + "%";
            if (recvData[24] == 0x00) {
//                Log.e("--NJX--", "识别到了==锁定");
//                gestureData="锁定";
            } else if (recvData[24] == 0x01) {
//                Log.e("--NJX--", "识别到了==解锁");
//                gestureData="解锁";
            }
            if (recvData[25] == 0x01) {
                LogUtil.e("--NJX--", "识别到了==放松手势");
                gestureData="放松手势";
                onCallReaxInterface.onReaxData("放松手势");
        }
            if (recvData[25] == 0x02) {
                LogUtil.e("--NJX--", "识别到了==握拳手势");
                gestureData="握拳手势";
                onCallReaxInterface.onReaxData("握拳手势");
            }
            if (recvData[25] == 0x03) {
                LogUtil.e("--NJX--", "识别到了==伸掌手势");
                gestureData="伸掌手势";
                onCallReaxInterface.onReaxData("伸掌手势");
            }
            if (recvData[25] == 0x04) {
                LogUtil.e("--NJX--", "识别到了==外摆手势");
                gestureData="外摆手势";
                onCallReaxInterface.onReaxData("外摆手势");
            }
            if (recvData[25] == 0x05) {
                LogUtil.e("--NJX--", "识别到了==内摆手势");
                gestureData="内摆手势";
                onCallReaxInterface.onReaxData("内摆手势");
            }
            if (recvData[25] == 0x02 && recvData[24] == 0x03) {
                gestureData = "握拳向上";
                LogUtil.e("--NJX--", "====握拳向上");
                onCallReaxInterface.onReaxData("握拳向上");
            } else if (recvData[25] == 0x02 && recvData[24] == 0x04) {
                gestureData = "握拳向下";
                LogUtil.e("--NJX--", "====握拳向下");
                onCallReaxInterface.onReaxData("握拳向下");
            } else if (recvData[25] == 0x02 && recvData[24] == 0x05) {
                gestureData = "握拳向右";
                LogUtil.e("--NJX--", "====握拳向右");
                onCallReaxInterface.onReaxData("握拳向右");
            } else if (recvData[25] == 0x02 && recvData[24] == 0x06) {
                gestureData = "握拳向左";
                LogUtil.e("--NJX--", "====握拳向左");
                onCallReaxInterface.onReaxData("握拳向左");
            }
            if (recvData[25] == 0x03 && recvData[24] == 0x05) {
                gestureData = "伸掌+向右";
                LogUtil.e("--NJX--", "====伸掌+向右");
            } else if (recvData[25] == 0x03 && recvData[24] == 0x06) {
                gestureData = "伸掌+向左";
                LogUtil.e("--NJX--", "====伸掌+向左");
            }
            if (recvData[24] == 0x08) {
                gestureData = "内旋";
                LogUtil.e("--NJX--", "====手势为内旋Y-");
            } else if (recvData[24] == 0x07) {
                gestureData = "外旋";
                LogUtil.e("--NJX--", "====手势为外旋Y+");
            }
        }
    }
    public static OnCallReaxInterface onCallReaxInterface=null;
    public static void setOnCallReaxInterface(OnCallReaxInterface onCallReaxInterface) {
        RootUtil.onCallReaxInterface = onCallReaxInterface;
    }

    /**
     *   广播发送手势到对应的应用
     * @param data      当前手势
     */
    public static void sendbroad(String data) {
        SPUtils spUtils=new SPUtils(MyApplication.context,"ACT");

        onSynchronizationInterface.onsynchronizationdata(data);
        String broadcastIntent = spUtils.getString("action","测试");//自己自定义

        if (broadcastIntent!=""){
                    if (data.equals("放松手势")){}
              else{
                    Intent intent = new Intent(broadcastIntent);
                    intent.putExtra("key", data);
                        LogUtil.e("ABCC",data);
                    MyApplication.context.sendBroadcast(intent);
                   }
              }
    }

    //接口回调 以判断的手势操作  显示到页面
    public static OnSynchronizationInterface onSynchronizationInterface=null;

    public static void setOnSynchronizationInterface(OnSynchronizationInterface onSynchronizationInterface) {
        RootUtil.onSynchronizationInterface = onSynchronizationInterface;
    }

    /**
     * 将多条数据合并成完整的一条数据
     */
    private static List<Byte> list = new ArrayList<>();
    public static void process(byte[] data) {
        List<Byte> byteList = new ArrayList<>();
        for (byte b : data) {
            byteList.add(b);
        }
        if (data[0] == 0x3c && data[1] == 0x3c) {//判断数据是否是该条数据的开始
            list.clear();
            list.addAll(byteList);
            if (data[data.length - 2] == 0x3e && data[data.length - 1] == 0x3e) {
                //这是一条数据
                List<Byte> dataList = new ArrayList<>();
                dataList.addAll(list);
                abc(dataList);
            }
        } else {
            list.addAll(byteList);
            if (data[data.length - 2] == 0x3e && data[data.length - 1] == 0x3e) {
                //这是一条数据
                List<Byte> dataList = new ArrayList<>();
                dataList.addAll(list);
                abc(dataList);
            }
        }

    }
    /**
     * 上面那个方式是用来处理蓝牙数据的，拼接起来，只要设备返回数据就会拼接。
     * 你肯定是没办法写成带返回值的，因为方法要传入数据的呀，你怎么传入data那个参数。
     * 然后就是拼接成功了，调用abc这个的方法，将数据发出来。因为不能主动调用，所以要用广播的方式传递给别的页面，
     * 原来service里接收数据的广播就可以注释掉了（跟下面一个发送广播一样的代码），这样接受到的数据每次都是一条完整的。
     */
    public static String synchro = "";
    public static int synNum = 0;
    public static int preNum = 0;
    public static String preheat = "";
    public static int preSucNum = 0;
    private static void abc(List<Byte> dataList) {
        byte[] recvData = new byte[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            recvData[i] = dataList.get(i);
        }


        Log.e("一条完整的数据   ", ConvertData.bytesToHexString(recvData, false));
        if (recvData.length == 32) {
            LogUtil.e("aaaaa每三秒检测是否同步", "rec[18]==" + recvData[18]);
            LogUtil.e("aaaaa每三秒检测是否正在预热中", "rec[19]==" + recvData[19]);
            if (recvData[18] == 0x0a) {
                synNum++;
                LogUtil.e("--BleBack--", "====synNum====" + synNum);
                synchro = "未同步";
                LogUtil.e("--BleBackground", "未同步");
                if (synNum == 3) {
                    synNum = 0;
                }
            } else if (recvData[18] == 0x0c) {
                LogUtil.e("--BleBackground", "rec[18]接收到0c,不作处理");
            } else {
                synchro = "同步";
                Log.e("--BleBackground", "同步了~~~");
                synNum = 0;
            }
            LogUtil.e("当前指示   ", synchro+"啦啦啦?");
            if (recvData[19] == 0x04) {//记录设备预热情况
                preNum++;
                preheat = "预热中";
                if (preNum == 4) {
                    preNum = 0;
                }
            } else if (recvData[19] == 0x05) {
                preSucNum++;
                preheat = "预热成功";
                if (preSucNum == 2) {
                    preSucNum = 0;
                }
                preNum = 0;
            }
        }
        RootUtil.GestureDecogn(recvData);

    }
}