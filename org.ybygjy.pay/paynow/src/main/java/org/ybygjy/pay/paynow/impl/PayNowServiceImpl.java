package org.ybygjy.pay.paynow.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ybygjy.pay.paynow.PayNowService;
import org.ybygjy.pay.paynow.dto.AccountBalanceQueryRepDTO;
import org.ybygjy.pay.paynow.dto.AccountBalanceQueryReqDTO;
import org.ybygjy.pay.paynow.dto.AgentPayQueryRepDTO;
import org.ybygjy.pay.paynow.dto.AgentPayQueryReqDTO;
import org.ybygjy.pay.paynow.dto.AgentPayRepDTO;
import org.ybygjy.pay.paynow.dto.AgentPayReqDTO;
import org.ybygjy.pay.paynow.dto.TradeStatusEnum;
import org.ybygjy.pay.paynow.util.HttpClient;
import org.ybygjy.pay.paynow.util.MessageUtils;

/**
 * 现在支付服务实现
 * @author WangYanCheng
 * @version 2016年8月2日
 */
public class PayNowServiceImpl implements PayNowService {

    public void pay() {
        // TODO Auto-generated method stub
    }

    public void payNotify() {
        // TODO Auto-generated method stub
    }

    public void payQuery() {
        // TODO Auto-generated method stub
    }

    public void refund() {
        // TODO Auto-generated method stub
    }

    public void refundQuery() {
        // TODO Auto-generated method stub
    }

    public void refundNotify() {
        // TODO Auto-generated method stub
    }

    @SuppressWarnings("rawtypes")
    public AgentPayRepDTO agentPay(AgentPayReqDTO agentPayReqDto) {
        AgentPayRepDTO agentPayRepDto = new AgentPayRepDTO();
        MessageUtils messageUtils = new MessageUtils();
        try {
            String requestBody = messageUtils.packageMessage(agentPayReqDto.getFunCode(), agentPayReqDto.toDataMap());
            System.out.println("请求报文＝》" + requestBody);
//            String response = HttpPost.http(agentPayReqDto.getServiceUri(), requestBody);
            String response = (new HttpClient()).send(agentPayReqDto.getServiceUri(), requestBody, agentPayReqDto.getInputCharset());
            List responseList = messageUtils.resolveMessage(response, "UTF-8");
            System.out.println("响应报文＝》" + responseList);
            if ("00".equals(responseList.get(0))) {
                agentPayRepDto.setRtnStatus(TradeStatusEnum.SUCCESS);
                agentPayRepDto.setRtnDate(new Date());
                Map data = (Map) responseList.get(1);
                agentPayRepDto.setAppId(String.valueOf(data.get("appId")));
                agentPayRepDto.setFunCode(String.valueOf(data.get("funcode")));
                agentPayRepDto.setMhtOrderNo(String.valueOf(data.get("mhtOrderNo")));
                agentPayRepDto.setNowPayOrderNo(String.valueOf(data.get("nowPayOrderNo")));
                agentPayRepDto.setResponseCode(String.valueOf(data.get("responseCode")));
                agentPayRepDto.setResponseMsg(String.valueOf(data.get("responseMsg")));
                agentPayRepDto.setResponseTime(String.valueOf(data.get("responseTime")));
                agentPayRepDto.setTradeStatus(String.valueOf(data.get("tradeStatus")));
            } else {
                agentPayRepDto.setRtnStatus(TradeStatusEnum.FAIL);
                agentPayRepDto.setRtnDate(new Date());
                agentPayRepDto.setRtnContent(String.valueOf(responseList.get(0)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentPayRepDto;
    }

    public void agentPayNotify() {
        // TODO Auto-generated method stub
    }

    @SuppressWarnings("rawtypes")
    public AgentPayQueryRepDTO agentPayQuery(AgentPayQueryReqDTO agentPayQueryReqDto) {
        AgentPayQueryRepDTO agentPayRepDto = new AgentPayQueryRepDTO();
        agentPayRepDto.setRtnDate(new Date());
        MessageUtils messageUtils = new MessageUtils();
        try {
            String requestStr = messageUtils.packageMessage(agentPayQueryReqDto.getFunCode(), agentPayQueryReqDto.toDataMap());
            System.out.println("代付查询请求报文＝》" + requestStr);
            String responseStr = new HttpClient().send(agentPayQueryReqDto.getServiceUri(), requestStr, agentPayQueryReqDto.getInputCharset());
            List responseList = messageUtils.resolveMessage(responseStr, "UTF-8");
            System.out.println("代付查询响应报文＝》" + responseList);
            String rtnCode = (String) responseList.get(0);
            if ("00".equals(rtnCode)) {
                Map dataMap = (Map) responseList.get(1);
                agentPayRepDto.setAppId(String.valueOf(dataMap.get("appId")));
                agentPayRepDto.setFunCode(String.valueOf(dataMap.get("funcode")));
                agentPayRepDto.setMhtOrderNo(String.valueOf(dataMap.get("mhtOrderNo")));
                agentPayRepDto.setNowPayOrderNo(String.valueOf(dataMap.get("nowPayOrderNo")));
                agentPayRepDto.setResponseTime(String.valueOf(dataMap.get("responseTime")));
                agentPayRepDto.setResponseCode(String.valueOf(dataMap.get("responseCode")));
                agentPayRepDto.setResponseMsg(String.valueOf(dataMap.get("responseMsg")));
                agentPayRepDto.setPayeeAccType(String.valueOf(dataMap.get("payeeAccType")));
                agentPayRepDto.setPayeeCardNo(String.valueOf(dataMap.get("payeeCardNo")));
                agentPayRepDto.setPayeeCardUnionNo(String.valueOf(dataMap.get("payeeCardUnionNo")));
                agentPayRepDto.setPayeeName(String.valueOf(dataMap.get("payeeName")));
                agentPayRepDto.setTradeStatus(String.valueOf(dataMap.get("tradeStatus")));
                agentPayRepDto.setRtnStatus(TradeStatusEnum.SUCCESS);
            } else {
                agentPayRepDto.setRtnStatus(TradeStatusEnum.FAIL);
                agentPayRepDto.setRtnContent(String.valueOf(responseList.get(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            agentPayRepDto.setRtnStatus(TradeStatusEnum.FAIL);
        }
        return agentPayRepDto;
    }

    @SuppressWarnings("rawtypes")
    public AccountBalanceQueryRepDTO queryAccountBalance(AccountBalanceQueryReqDTO requestDto) {
        MessageUtils messageUtils = new MessageUtils();
        AccountBalanceQueryRepDTO responseDto = new AccountBalanceQueryRepDTO();
        responseDto.setRtnDate(Calendar.getInstance().getTime());
        String requestStr = null;
        String responseStr = null;
        try {
            requestStr = messageUtils.packageMessage(requestDto.getFunCode(), requestDto.toDataMap());
            System.out.println("查询余额请求报文＝》" + requestStr);
            responseStr = new HttpClient().send(requestDto.getServiceUri(), requestStr, requestDto.getInputCharset());
            System.out.println("查询余额响应报文＝》" + responseStr);
            List resolvedResponse = messageUtils.resolveMessage(responseStr, "UTF-8");
            System.out.println("查询余额响应报文解析＝》" + resolvedResponse);
            String rtnCode = (String) resolvedResponse.get(0);
            if ("00".equals(rtnCode)) {
                Map dataMap = (Map) resolvedResponse.get(1);
                responseDto.setFunCode((String) dataMap.get("funcode"));
                responseDto.setAppId((String) dataMap.get("appId"));
                responseDto.setMhtOrderNo((String) dataMap.get("mhtOrderNo"));
                responseDto.setNowPayOrderNo((String) dataMap.get("nowPayOrderNo"));
                responseDto.setResponseTime((String) dataMap.get("responseTime"));
                responseDto.setResponseCode((String) dataMap.get("responseCode"));
                responseDto.setResponseMsg((String) dataMap.get("responseMsg"));
                responseDto.setAccountBalance(Integer.parseInt((String) dataMap.get("accountBalance")));
                responseDto.setRtnStatus(TradeStatusEnum.SUCCESS);
            } else {
                responseDto.setRtnStatus(TradeStatusEnum.FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setRtnStatus(TradeStatusEnum.FAIL);
        }
        return responseDto;
    }
}
