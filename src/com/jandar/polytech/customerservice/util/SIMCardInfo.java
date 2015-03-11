package com.jandar.polytech.customerservice.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 *  * class name：SIMCardInfo<BR>
 *  * class description：读取Sim卡信息<BR>
 *  * PS： 必须在加入各种权限 <BR>
 *  * Date:2012-3-12<BR>
 *  *  * @version 1.00  * @author CODYY)peijiangping  
 */
public class SIMCardInfo {
	private TelephonyManager telephonyManager;
	private String IMSI;

	public SIMCardInfo(Context context) {
		telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
	}

	/**
	 * 获取当前手机的号码
	 * @return
	 */
	@SuppressLint("NewApi")
	public String getNativePhoneNumber() {
		String NativePhoneNumber = null;
		NativePhoneNumber = telephonyManager.getLine1Number();
		if(NativePhoneNumber == null||NativePhoneNumber.isEmpty())
			return "15065315464";
		else
			return NativePhoneNumber;
	}

	/**
	 * 获取服务商的名称
	 * @return
	 */
	public String getProvidersName() {
		String ProvidersName = null;
		IMSI = telephonyManager.getSubscriberId();
		if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
			ProvidersName = "中国移动";
		} else if (IMSI.startsWith("46001")) {
			ProvidersName = "中国联通";
		} else if (IMSI.startsWith("46003")) {
			ProvidersName = "中国电信";
		}
		return ProvidersName;
	}

}