package com.example.springbootbatchserver.controller.home;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Date : 2022-06-17
 * Author : zilet
 * Project : springboot-batchServer
 * Description :
 */

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ObjectMapper objectMapper;

    private final JobLauncher jobLauncher;

    private final JobOperator jobOperator;

    private JobExecution execution;

    private final Job myJob;



    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @ResponseBody
    public String start(HttpServletRequest request, @RequestBody String body) throws JsonProcessingException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        HashMap<String, Object> bodyMap = objectMapper.readValue(body, new TypeReference<>() { });
        HashMap<String, JobParameter> jobParameterMap = Maps.newHashMap();
        for ( String key : bodyMap.keySet() ) {
            jobParameterMap.put(key, new JobParameter((String)bodyMap.get(key)));
        }
        JobParameters jobParameters = new JobParameters(jobParameterMap);
        execution = jobLauncher.run(myJob, jobParameters);
        return "success";
    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    @ResponseBody
    public String stop(HttpServletRequest request, @RequestBody String body) throws NoSuchJobExecutionException, JobExecutionNotRunningException, JsonProcessingException {
        HashMap<String, Object> bodyMap = objectMapper.readValue(body, new TypeReference<>() { });
        long id = Long.parseLong((String) bodyMap.get("id"));
        if ( jobOperator.stop(id) )
            return "success";
        else return "not found jobExecution id ";
    }

    @RequestMapping(value = "/jobStatus", method = RequestMethod.POST)
    @ResponseBody
    public JsonNode jobStatus(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, Exception {

        if ( execution == null) {
            throw new Exception("no running job ... ");
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        HashMap<String, Object> jobExecutionMap = Maps.newHashMap();
        jobExecutionMap.put("id", execution.getJobId());
        jobExecutionMap.put("status", execution.getStatus().isRunning());
        jobExecutionMap.put("startTime", dateFormat.format(execution.getStartTime()));
        jobExecutionMap.put("endTime", dateFormat.format(execution.getEndTime()));

        String json = objectMapper.writeValueAsString(jobExecutionMap);
        return objectMapper.readTree(json);
    }

}
