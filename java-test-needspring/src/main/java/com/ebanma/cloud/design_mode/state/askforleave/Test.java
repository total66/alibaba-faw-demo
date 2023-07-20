package com.ebanma.cloud.design_mode.state.askforleave;

import com.ebanma.cloud.design_mode.state.askforleave.datasource.Pool;
import com.ebanma.cloud.design_mode.state.askforleave.entity.AskForLeaveRecord;
import com.ebanma.cloud.design_mode.state.askforleave.entity.Status;
import com.ebanma.cloud.design_mode.state.usemode.IOrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Test {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Test.class,args);

        LeaveService leaveService = (LeaveService) context.getBean("leaveServiceImpl");
        AskForLeaveRecord leaveRecord = new AskForLeaveRecord();
        leaveRecord.setRecordId(1);
        leaveRecord.setUserId(1);
        leaveRecord.setStatus(Status.WRITING);
        Pool.set("1",leaveRecord);
        leaveService.start(1);
        AskForLeaveRecord x = (AskForLeaveRecord) Pool.get("1");
        System.out.println(x.getStatus().getStateCode());

    }
}