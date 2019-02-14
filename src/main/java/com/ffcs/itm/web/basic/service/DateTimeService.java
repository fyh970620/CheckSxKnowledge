package com.ffcs.itm.web.basic.service;

import com.ffcs.itm.web.basic.repository.DateTimeMapper;
import com.ffcs.itm.web.common.entity.dto.DataTimeDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DateTimeService {

    private static Logger log = Logger.getLogger(DateTimeService.class);

    @Autowired
    private DateTimeMapper dateTimeMapper;

    public String getDBCurrentDateTime(String format) {
        return dateTimeMapper.getDBCurrentDateTime(format);
    }

    public Date getDBCurrentDate(String format) {
        String currentDateTime = getDBCurrentDateTime("YYYY-MM-DD HH24:MI:SS");
        DateFormat dtformat = new SimpleDateFormat(format);
        Date currentDate = null;
        try {
            currentDate = dtformat.parse(currentDateTime);
        } catch (ParseException e) {
            log.error("日期转换失败!string=" + currentDateTime + ", format=" + format, e);
            currentDate = new Date();
        }
        return currentDate;
    }

    /**
     *
     *  获取指定年份全年所有周的开始结束时间
     *  @param year
     *
     * */
    public static List<DataTimeDto> getYearWeekDateList(String year) {
        List<DataTimeDto> dataTimeDtoList = new ArrayList<DataTimeDto>();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        sdf1.setLenient(false);
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEE");
        DataTimeDto weekDataTimeDto = new DataTimeDto();
        for (int month = 1; month < 13; month++) {
            for (int day = 1; day < 32; day++) {
                try {
                    Date date = sdf1.parse(year + "/" + month + "/" + day);

                    if (month == 1 && day == 1) {
                        weekDataTimeDto.setBeginDate(date);
                    } else if (sdf2.format(date).equals("星期一")) {
                        weekDataTimeDto.setBeginDate(date);
                    }

                    if (sdf2.format(date).equals("星期日")) {
                        weekDataTimeDto.setEndDate(date);
                        dataTimeDtoList.add(weekDataTimeDto);
                        weekDataTimeDto = new DataTimeDto();
                    }

                    if (month == 13 && day == 31) {
                        weekDataTimeDto.setEndDate(date);
                        dataTimeDtoList.add(weekDataTimeDto);
                    }
                } catch (ParseException e) {

                }
            }
        }
        return dataTimeDtoList;
    }

}
