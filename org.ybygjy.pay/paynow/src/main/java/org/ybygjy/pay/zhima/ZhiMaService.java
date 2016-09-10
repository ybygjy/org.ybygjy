package org.ybygjy.pay.zhima;

import java.util.Date;

import org.ybygjy.pay.dto.TradeStatusEnum;
import org.ybygjy.pay.dto.ZhiMaRepDTO;
import org.ybygjy.pay.dto.ZhiMaReqDTO;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCustomerCertifyApplyRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCustomerCertifyInitialRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerCertifyApplyResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerCertifyInitialResponse;

/**
 * 芝麻服务接口
 * @author WangYanCheng
 * @version 2016年9月7日
 */
public class ZhiMaService {
    /**
     * 芝麻认证服务数据初始化接口
     * @param zhiMaReqDto {@link ZhiMaReqDTO}
     * @return rtnObj {@link ZhiMaRepDTO}
     */
    public ZhiMaRepDTO certifyInitial(ZhiMaReqDTO zhiMaReqDto) {
        ZhimaCustomerCertifyInitialRequest req = new ZhimaCustomerCertifyInitialRequest();
        req.setChannel(zhiMaReqDto.getChannel());
        req.setPlatform(zhiMaReqDto.getPlateform());
        req.setTransactionId(zhiMaReqDto.getTransactionId());
        req.setContractFlag(zhiMaReqDto.getContractFlag());
        req.setProductCode(zhiMaReqDto.getProductCode());
        req.setIdentityType(zhiMaReqDto.getIdentityType());
        req.setIdentityParam(zhiMaReqDto.getIdentityParam());
        req.setState(zhiMaReqDto.getState());
        req.setBizParams(zhiMaReqDto.getBizParams());
        req.setSourceType(zhiMaReqDto.getSourceType());
        req.setPageUrl(zhiMaReqDto.getPageUrl());
        req.setSchemaUrl(zhiMaReqDto.getSchemaUrl());
        DefaultZhimaClient zhimaClient = new DefaultZhimaClient(zhiMaReqDto.getServiceUri(), zhiMaReqDto.getAppId(), zhiMaReqDto.getPrivateKey(), zhiMaReqDto.getZhiMaPublicKey());
        ZhiMaRepDTO zhimaRepDto = new ZhiMaRepDTO();
        try {
            ZhimaCustomerCertifyInitialResponse response = zhimaClient.execute(req);
            zhimaRepDto.setRtnDate(new Date());
            zhimaRepDto.setRtnStatus(response.isSuccess() ? TradeStatusEnum.SUCCESS : TradeStatusEnum.FAIL);
            zhimaRepDto.setRtnContent(response.getBody());
            zhimaRepDto.setErrorCode(response.getErrorCode());
            zhimaRepDto.setErrorMessage(response.getErrorMessage());
            zhimaRepDto.setToken(response.getToken());
        } catch (ZhimaApiException e) {
            zhimaRepDto.setRtnStatus(TradeStatusEnum.FAIL);
            zhimaRepDto.setErrorMessage(e.getMessage());
        }
        return zhimaRepDto;
    }
    /**
     * 芝麻认证服务引导认证接口
     * @param zhimaReqDto
     * @return rtnObj {@link ZhiMaRepDTO}
     */
    public ZhiMaRepDTO certifyApply(ZhiMaReqDTO zhimaReqDto) {
        ZhiMaRepDTO zhimaRepDto = new ZhiMaRepDTO();
        ZhimaCustomerCertifyApplyRequest zhimaRequest = new ZhimaCustomerCertifyApplyRequest();
        zhimaRequest.setApiVersion(zhimaReqDto.getVersion());
        zhimaRequest.setChannel(zhimaReqDto.getChannel());
        zhimaRequest.setPlatform(zhimaReqDto.getPlateform());
        zhimaRequest.setExtParams(zhimaReqDto.getExtParams());
        zhimaRequest.setToken(zhimaReqDto.getExtDataMap().get("ext_token"));
        DefaultZhimaClient zhimaClient = new DefaultZhimaClient(zhimaReqDto.getServiceUri(), zhimaReqDto.getAppId(), zhimaReqDto.getPrivateKey(), zhimaReqDto.getZhiMaPublicKey());
        try {
            ZhimaCustomerCertifyApplyResponse zhimaResponse = zhimaClient.execute(zhimaRequest);
            zhimaRepDto.setRtnStatus(zhimaResponse.isSuccess() ? TradeStatusEnum.SUCCESS : TradeStatusEnum.FAIL);
            zhimaRepDto.setRtnContent(zhimaResponse.getBody());
            zhimaRepDto.setRtnDate(new Date());
            zhimaRepDto.setErrorCode(zhimaResponse.getErrorCode());
            zhimaRepDto.setErrorMessage(zhimaResponse.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            zhimaRepDto.setRtnStatus(TradeStatusEnum.FAIL);
            zhimaRepDto.setErrorMessage(e.getMessage());
        }
        return zhimaRepDto;
    }
}
