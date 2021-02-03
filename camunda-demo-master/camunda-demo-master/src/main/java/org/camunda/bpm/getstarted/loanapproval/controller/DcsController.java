package org.camunda.bpm.getstarted.loanapproval.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api
@RestController
@RequestMapping("/dcs")
public class DcsController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;

    @ApiOperation(value = "开始")
    @GetMapping(value = "/start")
    public boolean ParticipatingProject() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("menuCode", "202");
        ProcessInstance instance = runtimeService.
                startProcessInstanceByKey("dcs_process", variables);
        if (instance == null) {
            return false;
        } else {
            return true;
        }
    }


    @ApiOperation(value = "一般转办")
    @GetMapping(value = "/next")
    public void next1() {
        List<Task> dcs_handleing = taskService.createTaskQuery().taskDefinitionKey("DCS_HANDLEING").list();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("handleStatus", 1);
        variables.put("submitType", 1);
        for (Task task : dcs_handleing) {
            taskService.complete(task.getId(),variables);
        }
    }

    @ApiOperation(value = "一般转办2")
    @GetMapping(value = "/next2")
    public void next2() {
        List<Task> dcs_handleing = taskService.createTaskQuery().taskDefinitionKey("GENERAL_TRANSFER_TWO").list();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("handleStatus", 1);
        variables.put("submitType", 1);
        for (Task task : dcs_handleing) {
            taskService.complete(task.getId(),variables);
        }
    }


    @ApiOperation(value = "一般转办3")
    @GetMapping(value = "/next3")
    public void next3() {
        List<Task> dcs_handleing = taskService.createTaskQuery().taskDefinitionKey("GENERAL_TRANSFER_THREE").list();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("handleStatus", 1);
        variables.put("submitType", 5);
        for (Task task : dcs_handleing) {
            taskService.complete(task.getId(),variables);
        }
    }

    @ApiOperation(value = "一般转办4")
    @GetMapping(value = "/next4")
    public void next4() {
        List<Task> dcs_handleing = taskService.createTaskQuery().taskDefinitionKey("Task_1myhe94").list();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("handleStatus", 1);
        variables.put("submitType", 5);
        for (Task task : dcs_handleing) {
            taskService.complete(task.getId(),variables);
        }
    }

}
