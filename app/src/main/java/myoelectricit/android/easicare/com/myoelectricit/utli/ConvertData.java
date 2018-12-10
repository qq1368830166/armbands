package myoelectricit.android.easicare.com.myoelectricit.utli;


import java.util.List;

public class ConvertData
{
    /**
     * 将byte数组转换为16进制格式的字符串
     *
     * @param bytes byte数组
     * @param bSpace  是否在每两个数组中间添加空格
     *
     * @return 返回16进制格式的字符串
     */
	public static String bytesToHexString(byte[] bytes, boolean bSpace)
	{
		if(bytes == null || bytes.length <= 0)
			return null;
		
		StringBuffer stringBuffer = new StringBuffer(bytes.length);
		String sTemp;
		
		for (int i = 0; i < bytes.length; i++) 
		{
			sTemp = Integer.toHexString(0xFF & bytes[i]);
			
			if (sTemp.length() < 2)
				stringBuffer.append(0);
			
			stringBuffer.append(sTemp);
			
			if(bSpace)
				stringBuffer.append(" ");
		}
		return stringBuffer.toString();
	}
	
    /**
     * 将字符串转换为byte数组
     *
     * @param hexString 16进制格式的字符串（仅包含0-9，a-f,A-F,且长度为偶数)
     *
     * @return 返回转换后的byte数组
     */
	public static byte[] hexStringToBytes(String hexString)
	{
		if(hexString == null)
			return null;
		
		hexString = hexString.replace(" ", "");
		hexString = hexString.toUpperCase();
		
		int len = (hexString.length() / 2);
		if(len <= 0)
			return null;
		
		byte[] result = new byte[len];
		char[] achar = hexString.toCharArray();
		for (int i = 0; i < len; i++) 
		{
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		
		return result;
	}
	
    /**
     * 将一个数组拷贝到另一个数组上
     *
     * @param dst 目标数组
     * @param dstOffset 目标数组偏移
     * @param src 源数组
     * @param srcOffset 目标数组偏移
     * @param length 拷贝的长度
     *
     * @return 成功返回true，否则false
     */
	public static boolean cpyBytes(byte[] dst, int dstOffset, byte[] src, int srcOffset, int length)
	{
		if(dst == null || src == null || 
				dstOffset > dst.length || srcOffset > src.length ||
				length > (dst.length-dstOffset) || length > (src.length-srcOffset))
		{
			return false;
		}
		
		for (int i = 0; i < length; i++)
		{
			dst[i+dstOffset] = src[i+srcOffset];
		}
		
		return true;
	}
	
    /**
     * 两个数组比较
     *
     * @param data1 数组1
     * @param data2 数组2
     *
     * @return 相等返回true，否则返回false
     */
	public static boolean cmpBytes(byte[] data1, byte[] data2)
	{
		if (data1 == null && data2 == null)
		{
			return true;
		}
		if (data1 == null || data2 == null) 
		{
			return false;
		}
		if (data1 == data2)
		{
			return true;
		}
		if(data1.length != data2.length)
		{
			return false;
		}
		
		int len = data1.length;
		for (int i = 0; i < len; i++)
		{
			if(data1[i] != data2[i])
				return false;
		}
		
		return true;
	}
	
	private static int toByte(char c) 
	{
	    byte b = (byte) "0123456789ABCDEF".indexOf(c);
	    return b;
	 }

	public static String bytesToHexString(List<Byte> bytes, boolean bSpace) {
		if (bytes == null || bytes.size() <= 0)
			return null;

		StringBuffer stringBuffer = new StringBuffer(bytes.size());
		String sTemp;

		for (int i = 0; i < bytes.size(); i++) {
			sTemp = Integer.toHexString(0xFF & bytes.get(i));

			if (sTemp.length() < 2)
				stringBuffer.append(0);

			stringBuffer.append(sTemp);

			if (bSpace)
				stringBuffer.append(" ");
		}
		return stringBuffer.toString();
	}

	public static byte[] getBytes(float data)
	{
		int intBits = Float.floatToIntBits(data);
		return getBytes(intBits);
	}

	/**
	 * 浮点转换为字节
	 *
	 * @param f
	 * @return
	 */
	public static byte[] float2byte(float f) {

		// 把float转换为byte[]
		int fbit = Float.floatToIntBits(f);

		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (fbit >> (24 - i * 8));
		}

		// 翻转数组
		int len = b.length;
		// 建立一个与源数组元素类型相同的数组
		byte[] dest = new byte[len];
		// 为了防止修改源数组，将源数组拷贝一份副本
		System.arraycopy(b, 0, dest, 0, len);
		byte temp;
		// 将顺位第i个与倒数第i个交换
		for (int i = 0; i < len / 2; ++i) {
			temp = dest[i];
			dest[i] = dest[len - i - 1];
			dest[len - i - 1] = temp;
		}

		return dest;

	}

}
