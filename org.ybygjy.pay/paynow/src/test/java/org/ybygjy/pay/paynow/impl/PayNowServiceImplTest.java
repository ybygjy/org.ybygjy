package org.ybygjy.pay.paynow.impl;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ybygjy.pay.paynow.PayNowService;
import org.ybygjy.pay.paynow.dto.AbstractPayRepDTO;
import org.ybygjy.pay.paynow.dto.AccountBalanceQueryRepDTO;
import org.ybygjy.pay.paynow.dto.AccountBalanceQueryReqDTO;
import org.ybygjy.pay.paynow.dto.AgentPayQueryRepDTO;
import org.ybygjy.pay.paynow.dto.AgentPayQueryReqDTO;
import org.ybygjy.pay.paynow.dto.AgentPayReqDTO;
import org.ybygjy.pay.paynow.dto.TradeStatusEnum;
import org.ybygjy.pay.paynow.util.Config;
import org.ybygjy.pay.paynow.util.DateUtil;

/**
 * 现在支付
 * @author WangYanCheng
 * @version 2016年8月2日
 */
public class PayNowServiceImplTest {
    private PayNowService payNowService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.payNowService = new PayNowServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAgentPaySuccess() {
        try {
            String mhtOrderNo = String.valueOf((int) (Math.random() * 1000)).concat("#")
                    .concat(String.valueOf((int) (Math.random() * 1000)));
            AgentPayReqDTO agentPayReqDto = new AgentPayReqDTO();
            agentPayReqDto.setServiceUri(Config.getInstance().getAttr(Config.KEY_AGENTPAY_URL));
            agentPayReqDto.setAppId(Config.getInstance().getAttr(Config.KEY_APPID));
            agentPayReqDto.setMhtOrderNo(mhtOrderNo);
            agentPayReqDto.setMhtReqTime(DateUtil.getStringFromDate(new Date(), DateUtil.FORMAT_TRADETIME));
            agentPayReqDto.setMhtOrderAmt(1);
            agentPayReqDto.setPayeeAccType("01");
            agentPayReqDto.setPayeeName("有限公司");
            agentPayReqDto.setPayeeCardNo(Config.getInstance().getAttr(Config.KEY_PAYEECARDNO));
            agentPayReqDto.setPayeeCardUnionNo(Config.getInstance().getAttr(Config.KEY_PAYEECARDUNIONNO));
            agentPayReqDto.setAgentPayMemo("付款备注");
            AbstractPayRepDTO agentPayRepDTO = this.payNowService.agentPay(agentPayReqDto);
            Assert.assertNotNull(agentPayRepDTO);
            Assert.assertEquals(agentPayRepDTO.getRtnStatus(), TradeStatusEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAgentPayFail() {
        String mhtOrderNo = String.valueOf((int) (Math.random() * 1000)).concat("#").concat(String.valueOf((int) (Math.random() * 1000)));
        AgentPayReqDTO requestDto = new AgentPayReqDTO();
        requestDto.setServiceUri(Config.getInstance().getAttr(Config.KEY_AGENTPAY_URL));
        requestDto.setAppId(Config.getInstance().getAttr(Config.KEY_APPID));
        requestDto.setMhtOrderNo(mhtOrderNo);
        requestDto.setMhtReqTime(DateUtil.getStringFromDate(Calendar.getInstance().getTime(), DateUtil.FORMAT_TRADETIME));
        requestDto.setMhtOrderAmt(1);
        requestDto.setPayeeAccType("01");
        requestDto.setPayeeName("有限公司");
        requestDto.setPayeeCardNo(Config.getInstance().getAttr(Config.KEY_PAYEECARDNO));
        requestDto.setAgentPayMemo("付款备注");
        AbstractPayRepDTO responseDto = this.payNowService.agentPay(requestDto);
        Assert.assertNotNull(responseDto);
        Assert.assertEquals(responseDto.getRtnStatus(), TradeStatusEnum.FAIL);
    }

    @Test
    public void testAgentPayNotify() {
    }

    @Test
    public void testAgentPayQuery() {
        AgentPayQueryReqDTO requestDto = new AgentPayQueryReqDTO();
        requestDto.setAppId(Config.getInstance().getAttr(Config.KEY_APPID));
        requestDto.setBusinessCode("HIGO");
        requestDto.setServiceUri(Config.getInstance().getAttr(Config.KEY_AGENTPAY_QUERY_URL));
        requestDto.setRequestTime(new Date());
        requestDto.setMhtOrderNo("433#129");
        requestDto.setMhtReqTime("20160804215238");
        AgentPayQueryRepDTO responseDto = this.payNowService.agentPayQuery(requestDto);
        System.out.println(responseDto);
        Assert.assertNotNull(responseDto);
        Assert.assertEquals(responseDto.getRtnStatus(), TradeStatusEnum.SUCCESS);
    }

    @Test
    public void testQueryAgentPayAccount() {
        AccountBalanceQueryReqDTO requestDto = new AccountBalanceQueryReqDTO();
        requestDto.setServiceUri(Config.getInstance().getAttr(Config.KEY_ACCOUNTBALANCE_QUERY_URL));
        requestDto.setAccountType("AT01");
        requestDto.setRequestTime(new Date());
        requestDto.setMhtOrderNo(String.valueOf((int) (Math.random() * 1000000)));
        requestDto.setMhtReqTime(DateUtil.getStringFromDate(new Date(), DateUtil.FORMAT_TRADETIME));
        AccountBalanceQueryRepDTO responseDto = this.payNowService.queryAccountBalance(requestDto);
        System.out.println(responseDto);
        Assert.assertNotNull(responseDto);
        Assert.assertEquals(responseDto.getRtnStatus(), TradeStatusEnum.SUCCESS);
    }

    @Test
    public void testPrintBase64Str() {
        int j = 1;
        for (int i = 'A'; i <= 'Z'; i++) {
            this.parseInt(i);
            if (((j++) % 10) == 0) {
                System.out.println();
            }
        }
        j = 1;
        for (int i = 'a'; i <= 'z'; i++) {
            this.parseInt(i);
            if (((j++) % 10) == 0) {
                System.out.println();
            }
        }
        System.out.println();
        j = 1;
        for (int i = 0; i <= 9; i++) {
            this.parseInt(i);
            if (((j++) % 10) == 0) {
                System.out.println();
            }
        }
        j = 1;
        char[] tmpChar = { '+', '/' };
        for (int i = 0; i < tmpChar.length; i++) {
            this.parseInt(tmpChar[i]);
            if (((j++) % 10) == 0) {
                System.out.println();
            }
        }
    }

    @Test
    public void testCheckNum() {
        int[] arr = new int[] { 77, 97, 110 };
        int[] output = new int[arr.length / 3 * 4];
        int p = 0;
        this.parseInt(arr[p]);
        int tmpV = (arr[p] & 0xff);
        this.parseInt(tmpV);
        tmpV = (tmpV << 16);
        this.parseInt(tmpV);
        output[0] = tmpV;
        this.parseInt(arr[p + 1]);
        tmpV = (arr[p + 1] & 0xff);
        this.parseInt(tmpV);
        tmpV = tmpV << 8;
        this.parseInt(tmpV);
        output[1] = tmpV;
        this.parseInt(arr[p + 2]);
        tmpV = arr[p + 2] & 0xff;
        this.parseInt(tmpV);
        output[2] = tmpV;
        output[3] = output[0] | output[1] | output[2];
        this.parseInt(output[3]);

        output[p] = (output[3] >> 18) & 0x3f;
        this.parseInt(output[p]);
        output[p + 1] = (output[3] >> 12) & 0x3f;
        this.parseInt(output[p + 1]);
        output[p + 2] = (output[3] >> 6) & 0x3f;
        this.parseInt(output[p + 2]);
        output[p + 3] = (output[3]) & 0x3f;
        this.parseInt(output[p + 3]);
    }

    private void parseInt(int s) {
        String str = Integer.toBinaryString(s);
        int segment = 32 - str.length();
        if (segment != 0) {
            for (int i = 0; i < segment; i++) {
                str = "0".concat(str);
            }
        }
        segment = str.length() / 8;
        int j = 0;
        for (int i = 1; i <= segment; i++) {
            int limit = i * 8;
            System.out.print(str.substring(j, limit) + " ");
            j = limit;
        }
        System.out.println("(" + (char) s + ")");
    }

    @Test
    public void printHex() {
        int[] i = { 31056, 31057, 31058 };
        for (int it = 0; it < i.length; it++) {
            System.out.println(Integer.toHexString(i[it]));
        }
    }
}
