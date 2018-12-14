package myoelectricit.android.easicare.com.armbands.utli;

/**
 * Created by fl on 2016/11/18.
 */
public class InstructionBooks {

    static String headCommand = "3c3c01" +SystemUtil.getSysDate()+"00"+ "";//起始符+01+标记时间
    public static String Ins_Connect = headCommand +"00" + SystemUtil.getMac() + "0000003e3e";//连接
    public static String Ins_DisConnect = headCommand +"01" + SystemUtil.getMac() + "0000003e3e";//断开连接
    public static String Ins_QueryStatus = headCommand +"02" + SystemUtil.getMac() + "0000003e3e";//查询状态
    public static String Ins_RequestCharacter = headCommand +"03" + SystemUtil.getMac() + "0000003e3e";//请求发送特征数据
    public static String Ins_Synchro = headCommand +"0f" + SystemUtil.getMac() + "0000003e3e";//检测是否佩戴正确
    public static String Ins_StopCharacter = headCommand +"04" + SystemUtil.getMac() + "0000003e3e";//停止发送特征数据
    public static String Ins_ReName = headCommand +"05" + SystemUtil.getMac() + "0000003e3e";//重新命名
    public static String Ins_Standby = headCommand +"06" + SystemUtil.getMac() + "0000003e3e";//待机
    public static String Ins_WakeUp = headCommand +"07" + SystemUtil.getMac() + "0000003e3e";//唤醒
    public static String Ins_ChooseUniversalMode = headCommand +"08" + SystemUtil.getMac() + "0000003e3e";//选择通用模型
    public static String Ins_ChooseSpecificMode = headCommand +"09" + SystemUtil.getMac() + "0000003e3e";//选择固有模型
    public static String Ins_Vibration = headCommand +"0a" + SystemUtil.getMac() + "0000003e3e";//震动
    public static String Ins_FlashLight = headCommand +"0b" + SystemUtil.getMac() + "0000003e3e";//闪灯
    public static String Ins_BeginIdentify = headCommand +"0c" + SystemUtil.getMac() + "0000003e3e";//开始识别
    public static String Ins_RequestDiagData = headCommand +"0d" + SystemUtil.getMac() + "0000003e3e";//请求发送诊断数据
    public static String Ins_StopDiagData = headCommand +"0e" + SystemUtil.getMac() + "0000003e3e";//停止发送诊断数据
    public static String Ins_CheckWear = headCommand +"0f" + SystemUtil.getMac() + "0000003e3e";//检测是否佩戴正确
}
