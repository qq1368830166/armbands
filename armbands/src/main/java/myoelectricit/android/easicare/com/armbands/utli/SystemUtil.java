package myoelectricit.android.easicare.com.armbands.utli;

import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Enumeration;

/**
 * Created by fl on 2016/11/14.
 */
public class SystemUtil {
    static int Year ,Month,Day,Hour,Minute,Sec;
    static String year,month,day,hour,minute,sec;
    static Calendar now;
    static String[] getData = new String[6];


    public static String getSysDate(){
        now = Calendar.getInstance();
        Year = now.get(Calendar.YEAR);
        Month = now.get(Calendar.MONTH)+1;
        Day =  now.get(Calendar.DAY_OF_MONTH);
        Hour = now.get(Calendar.HOUR_OF_DAY);
        Minute = now.get(Calendar.MINUTE);
        Sec = now.get(Calendar.SECOND);

        System.out.println("日期为========== "+Year+"/"+Month+"/"+Day+"/"+Hour+"/"+Minute+"/"+Sec);

        String str ="00";
        getData[0] = Integer.toHexString(Year-1900);
        getData[1] = Integer.toHexString(Month);
        getData[2] = Integer.toHexString(Day);
        getData[3] = Integer.toHexString(Hour);
        getData[4] = Integer.toHexString(Minute);
        getData[5] = Integer.toHexString(Sec);
        year = str.substring(0, 2-getData[0].length())+getData[0];
        month = str.substring(0, 2-getData[1].length())+getData[1];
        day = str.substring(0, 2-getData[2].length())+getData[2];
        hour = str.substring(0, 2-getData[3].length())+getData[3];
        minute = str.substring(0, 2-getData[4].length())+getData[4];
        sec = str.substring(0, 2-getData[5].length())+getData[5];

        return year+month+day+hour+minute+sec;
    }


    public static String getMac() {
        String macSerial = null;
        String str = "";
        String[] mac = new String[6];
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str;) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        GetLocalMacAddress();
        Log.e("--SystemUtil--","mac地址为========"+macSerial);
        if(macSerial!=null) {
            mac = macSerial.split(":");
            return mac[0]+mac[1]+mac[2]+mac[3]+mac[4]+mac[5];
        }else{
            return "000000000000";
        }


    }

    public static String GetLocalMacAddress() {
       /* BluetoothAdapter btAda = BluetoothAdapter.getDefaultAdapter();
        //开启蓝牙
        if (btAda.isEnabled() == false) {
            if (btAda.enable()) {
                while (btAda.getState() == BluetoothAdapter.STATE_TURNING_ON
                        || btAda.getState() != BluetoothAdapter.STATE_ON) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }*/
        String mac = "";
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (interfaces.hasMoreElements()) {
            NetworkInterface iF = interfaces.nextElement();

            byte[] addr = new byte[0];
            try {
                addr = iF.getHardwareAddress();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            if (addr == null || addr.length == 0) {
                continue;
            }

            StringBuilder buf = new StringBuilder();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            mac = buf.toString();
            Log.d("----蓝牙c", "interfaceName="+iF.getName()+", mac="+mac);
        }
        return  mac;
        /*BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
        m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        String m_szBTMAC = m_BluetoothAdapter.getAddress();
        Log.e("--SystemUtil--","蓝牙地址===m_szBTMAC=="+m_szBTMAC);
        return m_szBTMAC;*/
    }


}
