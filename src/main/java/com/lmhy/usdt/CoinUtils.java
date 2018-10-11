package com.lmhy.usdt;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;


import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.apache.commons.codec.binary.Base64;

public class CoinUtils {


    private static CoinUtils instance;

    private static void init() throws Throwable {
        if (null == instance) {
            instance = new CoinUtils();
        }
    }

    private JsonRpcHttpClient client;

    public CoinUtils() throws Throwable {
        // 身份认证
//        String cred = Base64.encodeBytes((Constants.RPC_USER + ":" + Constants.RPC_PASSWORD).getBytes());
        Base64 base64 = new Base64();
        String auth = Constants.RPC_USER + ":" + Constants.RPC_PASSWORD;
        byte[] textByte = auth.getBytes("UTF-8");
        String cred = base64.encodeToString(textByte);

        Map<String, String> headers = new HashMap<String, String>(1);
        headers.put("Authorization", "Basic " + cred);
        client = new JsonRpcHttpClient(new URL("http://" + Constants.RPC_ALLOWIP + ":" + Constants.RPC_PORT), headers);
    }


    public static CoinUtils getInstance() throws Throwable {
        init();
        return instance;
    }


    /**
     * 验证地址是否存在
     *
     * @param address
     * @return
     * @throws Throwable
     */
    public String validateaddress(String address) throws Throwable {
        return (String) client.invoke("validateaddress", new Object[]{address}, Object.class).toString();
    }


    /**
     * 如果钱包加密需要临时解锁钱包
     *
     * @param password
     * @param time
     * @return
     * @throws Throwable
     */
    public String walletpassphase(String password, int time) throws Throwable {
        return (String) client.invoke("walletpassphase", new Object[]{password, time}, Object.class).toString();
    }

    /**
     * 转账到制定的账户中
     *
     * @param address
     * @param amount
     * @return
     * @throws Throwable
     */
    public String sendtoaddress(String address, double amount) throws Throwable {
        return (String) client.invoke("sendtoaddress", new Object[]{address, amount}, Object.class).toString();
    }

    /**
     * 查询账户下的交易记录
     *
     * @param account
     * @param count
     * @param offset
     * @return
     * @throws Throwable
     */
    public String listtransactions(String account, int count, int offset) throws Throwable {
        return (String) client.invoke("listtransactions", new Object[]{account, count, offset}, Object.class).toString();
    }

    /**
     * 获取地址下未花费的币量
     *
     * @param minconf
     * @param maxconf
     * @param address
     * @return
     * @throws Throwable
     */
    public String listunspent(int minconf, int maxconf, String address) throws Throwable {
        String[] addresss = new String[]{address};
        return (String) client.invoke("listunspent", new Object[]{minconf, maxconf, addresss}, Object.class).toString();
    }

    /**
     * 生成新的接收地址
     *
     * @return
     * @throws Throwable
     */
    public String getNewaddress() throws Throwable {
        return (String) client.invoke("getnewaddress", new Object[]{}, Object.class).toString();
    }

    /**
     * 获取钱包信息
     *
     * @return
     * @throws Throwable
     */
    public String getInfo() throws Throwable {
        return client.invoke("getinfo", new Object[]{}, Object.class).toString();
    }

}


