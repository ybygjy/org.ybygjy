package org.ybygjy.pay.paynow;

import org.ybygjy.pay.paynow.dto.AccountBalanceQueryRepDTO;
import org.ybygjy.pay.paynow.dto.AccountBalanceQueryReqDTO;
import org.ybygjy.pay.paynow.dto.AgentPayQueryRepDTO;
import org.ybygjy.pay.paynow.dto.AgentPayQueryReqDTO;
import org.ybygjy.pay.paynow.dto.AgentPayRepDTO;
import org.ybygjy.pay.paynow.dto.AgentPayReqDTO;

/**
 * 现在支付接口
 * @author WangYanCheng
 * @version 2016年8月2日
 */
public interface PayNowService {
    /**
     * 收单
     */
    public void pay();

    /**
     * 收单结果异步通知
     */
    public void payNotify();

    /**
     * 收单结果查询
     */
    public void payQuery();

    /**
     * 退款
     */
    public void refund();

    /**
     * 退款查询
     */
    public void refundQuery();

    /**
     * 退款异步通知
     */
    public void refundNotify();

    /**
     * 代付
     */
    public AgentPayRepDTO agentPay(AgentPayReqDTO agentPayReqDTO);

    /**
     * 代付通知
     */
    public void agentPayNotify();

    /**
     * 代付结果查询
     * @param agentPayReqDTO {@link AgentPayQueryReqDTO}
     * @return rtnRepDTO {@link AgentPayQueryRepDTO}
     */
    public AgentPayQueryRepDTO agentPayQuery(AgentPayQueryReqDTO agentPayReqDTO);

    /**
     * 代付账户查询
     */
    public AccountBalanceQueryRepDTO queryAccountBalance(AccountBalanceQueryReqDTO accountBalanceQueryReqDto);
}
