package com.ebanma.cloud.design_mode.state.askforleave;

import com.ebanma.cloud.design_mode.state.askforleave.entity.AskForLeaveRecord;

public interface LeaveService {

    AskForLeaveRecord start(int id);

    AskForLeaveRecord approve(int id);



}
