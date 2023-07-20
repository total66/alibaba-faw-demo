package com.ebanma.cloud.design_mode.state.askforleave.entity;

import lombok.Data;

@Data
public class AskForLeaveRecord {

    private int userId;

    private int recordId;

    private Status status;

}
