package com.getir.readingisgood.log;

import com.getir.readingisgood.util.MessageCodeEnum;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    private LogUtil(){

    }

    public static void log(LogDTO logDTO)
    {
        if(ObjectUtils.isEmpty(logDTO.logType))
            logDTO.logType=LogTypeEnum.INFO;

        if(ObjectUtils.isEmpty(logDTO.application))
            logDTO.application= ApplicationTypeEnum.GETIR_MAIN_API;

        infoLog(logDTO);
    }

    private static void infoLog(LogDTO logDTO)
    {
        JSONObject jsonObject = new JSONObject(logDTO);
        logger.info(String.valueOf(jsonObject));
    }

    private static void warnLog(LogDTO logDTO, @Nullable Exception ex)
    {
        JSONObject jsonObject = new JSONObject(logDTO);
        logger.warn(String.valueOf(jsonObject), ex);
    }

    private static void errorLog(LogDTO logDTO, @Nullable Exception ex)
    {
        JSONObject jsonObject = new JSONObject(logDTO);
        logger.error(String.valueOf(jsonObject), ex);
    }
    public static void exceptionLog(RuntimeException exception, List<String> messageList, MessageCodeEnum messageCode, WebRequest request)
    {
        LogDTO logDTO = new LogDTO();

        String message = "";
        for(int i=0 ; i<messageList.size() ; i++){
            message += messageList.get(i);
            message += " - ";
        }
        logDTO.message = message;
        logDTO.messageDate = new Date();

        if(ObjectUtils.isEmpty(logDTO.application))
            logDTO.application=ApplicationTypeEnum.GETIR_MAIN_API;

        if(messageCode == MessageCodeEnum.ERROR)
        {
            logDTO.logType = LogTypeEnum.ERROR;
            errorLog(logDTO, exception);
        }
        else if(messageCode == MessageCodeEnum.WARNING)
        {
            logDTO.logType = LogTypeEnum.WARNING;
            warnLog(logDTO, exception);
        }
        else
        {
            logDTO.logType = LogTypeEnum.INFO;
            infoLog(logDTO);
        }
    }

}
