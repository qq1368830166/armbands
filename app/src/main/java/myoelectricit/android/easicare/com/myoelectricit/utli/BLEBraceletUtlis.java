package myoelectricit.android.easicare.com.myoelectricit.utli;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import myoelectricit.android.easicare.com.myoelectricit.interfaces.OnCallReaxInterface;
import myoelectricit.android.easicare.com.myoelectricit.interfaces.OnSendDataInterface;

public   class BLEBraceletUtlis {

    /**
     *   获取手环发送的专属Address
     * @param address   蓝牙地址
     * @return
     */

    public static String getAddress(String address){
             if (address==null)
                 return null;
          String[] strarray = address.split(":");
          String addressdata = strarray[5] + strarray[4] + strarray[3] + strarray[2] + strarray[1] + strarray[0];
       return addressdata;
    }


    /**
     *  待机模式指令生成
     * @param address   当前连接蓝牙的地址
     * @return      返回成功的指令
     */
    public static byte[] getStandbyData(String address){
        String enterDJ = "3c3c01" + SystemUtil.getSysDate() + "00" + address + "06" + SystemUtil.getMac() + "0000003e3e";
        byte[] bytes11 = ConvertData.hexStringToBytes(enterDJ);
        return bytes11;

    }
    /**
     *  检查是否同步的指令
     * @return      返回的指令
     */
    public static byte[] getSynchronizationDeta(){
        byte[] bytesa = ConvertData.hexStringToBytes(InstructionBooks.Ins_Synchro);
        return bytesa;
    }

    /**
     *  进入识别模式的指令
     * @return
     */
    public static byte[] getRecognitionData(){
        String sbmode = "3C3C010000000000004E0606060606060c4848484848364F53533E3E";
        byte[] bytes1 = ConvertData.hexStringToBytes(sbmode);
        return bytes1;
    }
    public final static int BLE_SEND_DATA_LEN_MAX = 20;


    /**
     *     //发送数据
     * @param data  传入的指令
     * @param onSendDataInterface    加密后的数据（要发送的数据）
     */
    public static void SendbleData(byte[]data, final OnSendDataInterface onSendDataInterface){

        int nCount = data.length / BLE_SEND_DATA_LEN_MAX;
        if (data.length % BLE_SEND_DATA_LEN_MAX != 0) {//  总长度/20
            nCount++;
        }
        byte[] temp;
        for (int i = 0; i < nCount; i++) {
            if ((i + 1) != nCount) {
                temp = new byte[BLE_SEND_DATA_LEN_MAX];
            } else {
                temp = new byte[data.length - BLE_SEND_DATA_LEN_MAX * i];
            }

            for (int j = 0; j < temp.length; j++) {
                temp[j] = data[i * (BLE_SEND_DATA_LEN_MAX) + j];
            }

            try {
                Thread.sleep(500,0);
                onSendDataInterface.onSendData(temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }

    }

    /**
     *  判断是否同步
     * @return      如果同步返回true  如果不同步返回fasel
     */
    public  static  boolean  IsSynchronization(){
        if (RootUtil.synchro.equals("同步"))
            return  true;
        else  return  false;
    }

    /**
     *  获取当前电量
     * @return  当前电量
     */
    public  static  String getPower(){
        if (        RootUtil.power!=null)
            return         RootUtil.power;

        return null;
    }

    private static boolean isReax=false;   //判断是否是放松手势
    public static void IsGesture(){
        // TODO: 2018/11/26 非常重要的判断 以 放松手势 为过渡判断手势
        RootUtil.setOnCallReaxInterface(new OnCallReaxInterface() {
            @Override
            public void onReaxData(String reax) {
                if (isReax){
                    RootUtil.sendbroad(reax);
                    Log.e("ABCC",reax);
                    isReax=false;
                }else {
                    if (reax.equals("放松手势")||reax.equals("握拳手势")) {
                        Log.e("ABCB", "当前模式" + reax);
                        isReax = true;
                    }
                }
            }
        });
    }
}
