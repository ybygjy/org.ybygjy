package org.ybygjy.basic.basic;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leye on 2017/1/11.
 */
public class StationDataReport {
    /**
     * 站点地址映射
     */
    private Map<String, String> stationAddressMap = new HashMap<String, String>();
    /**
     * 站点学校映射
     */
    private Map<String, String> stationSchoolRelationMap = new HashMap<String, String>();
    /**
     * 用户站点关系
     */
    private Map<String, WelfareUserStationEntity> stationUserRelationMap = new HashMap<String, WelfareUserStationEntity>();

    public static void main(String[] args) {
        StationDataReport stationDataReport = new StationDataReport();
        stationDataReport.doWork();
    }

    public void doWork() {
        //初始化站点地址信息
        this.initStationAddressMap(this.stationAddressMap, new File("/Users/leye/tmp/101_welfare/103_station_school_address"));
        //初始化站点学校关系
        this.initStationAddressMap(this.stationSchoolRelationMap, new File("/Users/leye/tmp/101_welfare/104_stationrelation"));
        //初始化站点用户关联信息
        this.initStationUserRelation(this.stationUserRelationMap, new File("/Users/leye/tmp/101_welfare/102_welfare_data"));
        //读报表文件写数据
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("/Users/leye/tmp/101_welfare/105_welfare_summer")));
            bufferedWriter = new BufferedWriter(new FileWriter(new File("/Users/leye/tmp/101_welfare/106_welfare_summer"), false));
            String dataLine = null;
            while ((dataLine = bufferedReader.readLine()) != null) {
                String[] dataArr = dataLine.split(",");
                WelfareSummerEntity welfareSummerEntity = new WelfareSummerEntity(dataArr);
                this.getStationAddress(welfareSummerEntity);
                bufferedWriter.write(welfareSummerEntity.toString());
                bufferedWriter.write(System.getProperty("line.separator", "\n"));
            }
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getStationAddress(WelfareSummerEntity welfareSummerEntity) {
        //查用户关联站点
        WelfareUserStationEntity welfareUserStationEntity = this.stationUserRelationMap.get(welfareSummerEntity.getUserId());
        if (null != welfareUserStationEntity) {
            String stationId = welfareUserStationEntity.getStationId();
            String stationAddr = this.stationAddressMap.get(stationId);
            welfareSummerEntity.setSchoolName(welfareUserStationEntity.getSchoolId());
            if (null != stationAddr) {
                welfareSummerEntity.setStationAddress(stationAddr);
            } else {
                //依据学校查站点
                stationId = this.stationSchoolRelationMap.get(welfareUserStationEntity.getSchoolId());
                stationAddr = this.stationAddressMap.get(stationId);
                if (null != stationAddr) {
                    welfareSummerEntity.setStationAddress(stationAddr);
                } else {
                    welfareSummerEntity.setStationAddress("VALID_SCHOOLSTATION");
                    welfareSummerEntity.setSchoolName("VALID_SCHOOLSTATION");
                }
            }
        } else {
            welfareSummerEntity.setStationAddress("VALID_USERSTATION");
            welfareSummerEntity.setSchoolName("VALID_USERSTATION");
        }
    }

    private boolean initStationAddressMap(Map<String, String> dataMap, File dataFile) {
        BufferedReader bufferedReader = null;
        String dataLine = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(dataFile));
            while ((dataLine = bufferedReader.readLine()) != null) {
                String[] tmpArr = dataLine.split(";");
                dataMap.put(tmpArr[0], tmpArr[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private boolean initStationUserRelation(Map<String, WelfareUserStationEntity> dataMap, File dataFile) {
        BufferedReader bufferedReader = null;
        String dataLine = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(dataFile));
            while ((dataLine = bufferedReader.readLine()) != null) {
                String[] tmpArr = dataLine.split(";");
                WelfareUserStationEntity welfareUserStationEntity = new WelfareUserStationEntity();
                welfareUserStationEntity.setUserId(tmpArr[0]);
                welfareUserStationEntity.setStationId(tmpArr[1]);
                welfareUserStationEntity.setSchoolId(tmpArr[2]);
                dataMap.put(welfareUserStationEntity.getUserId(), welfareUserStationEntity);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    class WelfareUserStationEntity {
        private String userId;
        private String stationId;
        private String schoolId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public String getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(String schoolId) {
            this.schoolId = schoolId;
        }
    }

    class WelfareSummerEntity {
        private String userId;
        private String userName;
        private String userPhone;
        private String userAddress;
        private String getRedeemDate;
        private String usedRedeemDate;
        private String stationAddress;
        private String schoolName;

        public WelfareSummerEntity(String[] dataArr) {
            this.userId = dataArr[0];
            this.userName = dataArr[1];
            this.userPhone = dataArr[2];
            this.userAddress = dataArr[3];
            this.getRedeemDate = dataArr[4];
            this.usedRedeemDate = dataArr.length == 6 ? dataArr[5] : null;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getGetRedeemDate() {
            return getRedeemDate;
        }

        public void setGetRedeemDate(String getRedeemDate) {
            this.getRedeemDate = getRedeemDate;
        }

        public String getUsedRedeemDate() {
            return usedRedeemDate;
        }

        public void setUsedRedeemDate(String usedRedeemDate) {
            this.usedRedeemDate = usedRedeemDate;
        }

        public String getStationAddress() {
            return stationAddress;
        }

        public void setStationAddress(String stationAddress) {
            this.stationAddress = stationAddress;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        @Override
        public String toString() {
            StringBuilder sbud = new StringBuilder();
            sbud.append(userId).append(";").append(userName).append(";").append(userPhone).append(";").append(userAddress).append(";").append(getRedeemDate).append(";").append(usedRedeemDate).append(";").append(stationAddress).append(";").append(schoolName);
            return sbud.toString();
        }
    }
}
