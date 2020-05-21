package com.yyf.mallcache.util;

public class ConfigUtil {
	/**
	 * ��ҳ��ʾ ÿҳ��С
	 */
public final static Integer PAGE_SIZE=8;
	public final static long TimeOut =60000*3;
	public  static String FILEUPLOAD="D:/eeclipse-workspace/ssm-mall/WebContent/";
	static {
		FILEUPLOAD=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
	}
/**
 * ͼƬ����
 */
public final static String Detail ="detail";
public final static String LOUPEBIG ="loupeBig";
public final static String LOUPEMid ="loupeMid";
public final static String LOUPESMALL ="loupeSmall";
	public final static String SEARCHIMAGE ="search";
public final static String USERSTR="user";
// ���׳ɹ�
public final static String PAY_SUCCESS = "1";
// ����ʧ��
public final static String PAY_FAIL = "2";
// ��½�˺Ŵ���
public final static String PAY_LOGINIDFAIL = "3";
//֧���������
public final static String PAY_ACCOUNTNUMBERTFAIL = "4";
//����
public final static String PAY_ACCOUNTNBALANCEFAIL = "5";
//��治��
public final static String PAY_PRODUCTAMOUNTFAIL = "6";
// �����µ�½
public final static String PAY_PLEASE = "7";
public final static String PAY = "pay";
	public final static String DEFAULT_ORDERWAY = "待发货";

	public final static String ORDER_WAYTOPAY = "待支付";
//index����
public final static String PERSON_INDEX="redirect:/person/index";
//ͷ��Ĭ��·��
public final static String HEADIMAGEPATH="images/userHead/";
//�ļ�����·��

public final static String LOGIN = "redirect:login";
//��½�ɹ�
public final static String LOGIN_SUCCESS = "1";
//�û������������
public final static String LOGIN_FAIL = "2";
//��֤�����
public final static String LOGIN_IMAGEFAIL = "3";

public final static String REGISTER = "register";
public final static String HOME = "home";
public final static String SEARCH = "redirect:search";
public final static String ADDRESS = "redirect:person/address";

public final static String UPDATE_SUCCESS = "1";
/**
 * �޸�����
 */
//�޸ĳɹ�
public final static String CHANGE_SUCCESS="0";
//ԭ�������
public final static String CHANGE_OLDFILE="1";
//�������벻һ��
public final static String CHANGE_CONFIRMFAIL="2";
//��������
public final static String CHANGE_ERROR="3";

//ע��ɹ�
public final static String REGISTER_SUCCESS = "0";
//�û����ѱ�ʹ��
public final static String REGISTER_IDFALI = "1";
//�������벻һ��
public final static String REGISTER_REPEATEDFAIL = "2";
//��½ID�͵�½������������6λ
public final static String REGISTER_LENGTHFAIL = "3";
//����
public final static String REGISTER_ERROR = "4";
//Ĭ��֧������
public final static int initAccountNumber=123456;
//�˻���ʼ���
public final static int initAccountBalance=2000;

//��ɱ״̬
//0 ��ʾû�п�ʼ
public final static String SEC_WAYBEFORM="0";
//1��ʾ ���ھ���
public final static String SEC_WAYSTART="1";
//2 ��ʾ�Լ�����
public final static String SEC_WAYEND="2";
//�µ��ɹ� ����֤�ɹ�
public final static String SEC_SUCCESS="500";
//��ת��½����
public final static String SEC_LOGIN="501";
//�����ظ��µ�
public final static String SEC_REPEAT="502";

//��Ʒ�Ѿ��ۿ�
public final static String SEC_SALEALL="503";
//û�е�ַ
public final static String SEC_ADDRESSNULL="504";
//�ȴ���
public final static String SEC_WAIT="505";
//������
public final static String SEC_ILLEGAL="506";
//������
public final static String SEC_ERROR="507";



public static final String COOKIE_NAME_TOKEN="token";

	public final static String ADD_SUCCESS = "1";
	//�û������������
	public final static String ADD_FAIL = "2";
	public final static Integer DEFAULT_HEADIMAGE=25;
}
