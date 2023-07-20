package com.ebanma.cloud.design_mode.state.askforleave.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private int userId;

    private List<AskForLeaveRecord> leaveRecords;

}
