package org.ybygjy.pay.zhima;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ybygjy.pay.dto.TradeStatusEnum;
import org.ybygjy.pay.dto.ZhiMaRepDTO;
import org.ybygjy.pay.dto.ZhiMaReqDTO;
import org.ybygjy.pay.util.SequenceNumber;

import com.antgroup.zmxy.openplatform.api.internal.util.WebUtils;

public class ZhiMaServiceImplTest {
    private String serviceUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
    private String appId = "1000765";
    private String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIjfNWUXqCNtlaBFpv4KrBTVQb4btX9Gi+UPuz7AMvwbSXmtIxV2awkz4Rg0Q3PtTkuAUpuW958u3uNxypcsCJD3o4qphgqSMVpe33bJ1bZaeL/h8WT9KNBiIxGsYQco44JbJkjrHTAI3Wt+RNhHyn+nKI9wCaeYPCY7ZipVpmL3AgMBAAECgYBn/BxWx1hIQjMQ5pnuGzGNSk9+HRMQtQoHZqI9FEwn2JtDw9QJtEOxZCa4+svcQQfguIcKCfHqj/NqHMNrglqmmJhKrut+ilUt8rqJCmvRSh8AdG7ZnqzxQYdTrrEEKgO932WqPOI98X2H+2cPzLfIOgxN7FhCDg+lVi4CBdfJ8QJBANosNf2b4f50AgAkstAKX1Hqqsb+k5L5reoF9e6eQufPXIpSrbME3cETyRMMLKX+YfhHpv66fpfg0X4JiHZIpdsCQQCgmmPNWs2nbvHY3Eo3xuYs/KJRIltgYaAd9+CCjpCTbU21afqyGkK7zibHfLADh+m5GOkOZ5IFn5Alhg1NQNgVAkBTX/PeEDVEPWcKUPv4nw4gSvKqi10wHLSGq3J5lwdweQEfZ0s0D5cDEyGTYuKpKNadwBwkWnbIacUFSnVY5phjAkAt4Ix73+F5X77kROFKl52u4if350mU+a5EgUd35AO2qXWWSgTcFZZUkaoQODULfSqtvkjs3Xcf9hm2LlnkZI6VAkEAl8+0+a6Rjz119mxJXy9JWKTbWirhja+ijyndLVEAmxTYjWTuNKbRdTYe59ekRYhmMWkiKzggP+PKjXHCHV6eRQ==";
    private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI3zVlF6gjbZWgRab+CqwU1UG+G7V/RovlD7s+wDL8G0l5rSMVdmsJM+EYNENz7U5LgFKblvefLt7jccqXLAiQ96OKqYYKkjFaXt92ydW2Wni/4fFk/SjQYiMRrGEHKOOCWyZI6x0wCN1rfkTYR8p/pyiPcAmnmDwmO2YqVaZi9wIDAQAB";
    private String zhiMaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxRk1NwWkCaznjegnSRNBy2SvDBaqyKV26PORelFDlcqcs+VauV84xHJ5jAQ6k0VBwjFdxlYnh9aZJLw9/Ny14GMBx7qijwHJE89E1GRetK7iFAUEHxT16fKsptlkNXtMK9e44D1+owDHsyJCnIOnv+sGvr+fsipt1LEqQeTef9wIDAQAB";
    private ZhiMaService zhiMaService;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    @Before
    public void setUp() throws Exception {
        this.zhiMaService = new ZhiMaService();
    }
    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testCertifyInitial() {
        ZhiMaReqDTO requestData = new ZhiMaReqDTO();
        requestData.setBusinessCode("HIGO");
        requestData.setAppId(this.appId);
        requestData.setServiceUri(this.serviceUrl);
        requestData.setMethod("zhima.customer.certify.initial");
        requestData.setTransactionId(SequenceNumber.getInstance().generateTransactionId());
        requestData.setContractFlag("si201605010005096005");
        requestData.setProductCode("w1010100400000000001");
        requestData.setIdentityType("BY_CERTNO_AND_NAME");
        requestData.setIdentityParam("{\"certNo\":\"371581198810205777\",\"name\":\"王延成\",\"certType\":\"IDENTITY_CARD\"}");
        requestData.setState("{\"user_id\":\"user_ids\"}");
        requestData.setBizParams("{\"verifyScene\":\"WSECURITY\"}");
        requestData.setSourceType("h5");
        requestData.setPageUrl("http://v.lehe.com/order_pay/zhimacallback");
        requestData.setZhiMaPublicKey(this.zhiMaPublicKey);
        requestData.setPrivateKey(this.privateKey);
        ZhiMaRepDTO zhimaRepDTO = this.zhiMaService.certifyInitial(requestData);
System.out.println(zhimaRepDTO.getToken());
        Assert.assertTrue(zhimaRepDTO.getRtnStatus() == TradeStatusEnum.SUCCESS);
    }
    @Test
    public void testCertifyApply() {
        ZhiMaReqDTO zhimaReqDTO = new ZhiMaReqDTO();
        zhimaReqDTO.setBusinessCode("HIGO");
        zhimaReqDTO.setServiceUri(this.serviceUrl);
        zhimaReqDTO.setMethod("zhima.customer.certify.apply");
        zhimaReqDTO.setAppId(this.appId);
        zhimaReqDTO.setZhiMaPublicKey(this.zhiMaPublicKey);
        zhimaReqDTO.setPrivateKey(this.privateKey);
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("ext_token", "ZM201609123000000636300149518035");
        zhimaReqDTO.setExtDataMap(dataMap);
        ZhiMaRepDTO zhimaRepDto = this.zhiMaService.certifyApply(zhimaReqDTO);
        System.out.println(zhimaRepDto.getRtnContent());
        Assert.assertTrue(zhimaRepDto.getRtnStatus() == TradeStatusEnum.SUCCESS);
    }
    @Test
    public void testCallback() {
        String sign = "i7B2PyF%2BmHJn1suGoZItZ%2Fzx75Mco1KQSbEd%2F5XYL4UGvYPI7Kvp57pth2A%2BPQB%2BDp4Thbwrv2Nms8vdsnI%2BDiiGjK1vVFg%2BDV64Wb1IMTWFTMxVzRdoHu7bAGEkF2I53fE32ajE%2B3nJ7D%2BycPtIOYb7o%2FVPuan2kjvxbm1D8ko%3D";
        String data = "Qidl5SVE9g7zh%2F8TSm0niyFsyQg9zIAxz7aqK7AlOFgrauVLUKXCiDwIyqFRM7F0l5vKw4L%2BwGkEcyW%2Fivz5Z%2FvocQYLXjCnkufjIn8EaY%2B533iZWukEwUl4V%2FOz5%2FI62ORLPR7JG4%2BdNkieV%2F1MKdH4tibWO8caZl7w9xDgCDQ45L9hMXc4zJf%2BahDRxa1Y42WHMw%2BcoCRTeWt0RBls6y6ktW4vJkrk%2Bj9TuWzTaBtquQElIyAhqOs0EbTHatZbsCB%2FjuhylPVOusKTIx4%2BDVVw1sxU%2Bleb0zTbX3FCSTGu1onGwYSNIWWHsXJg4untDV7G9Xtlu3C%2BDdpIGRAUCw%3D%3D";
        ZhiMaReqDTO zhimaReqDTO = new ZhiMaReqDTO();
        zhimaReqDTO.setBusinessCode("HIGO");
        zhimaReqDTO.setServiceUri(this.serviceUrl);
        zhimaReqDTO.setPrivateKey(this.privateKey);
        zhimaReqDTO.setZhiMaPublicKey(this.zhiMaPublicKey);
        Map<String, String> extDataMap = new HashMap<String, String>();
        extDataMap.put("sign", WebUtils.decode(sign));
        extDataMap.put("data", WebUtils.decode(data));
        zhimaReqDTO.setExtDataMap(extDataMap);
        ZhiMaRepDTO zhimaRepDTO = this.zhiMaService.callback(zhimaReqDTO);
        Assert.assertEquals(zhimaRepDTO.getRtnStatus(), TradeStatusEnum.SUCCESS);
    }
}
