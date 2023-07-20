package com.ebanma.cloud.design_mode.state.askforleave;

import com.ebanma.cloud.design_mode.state.askforleave.datasource.Pool;
import com.ebanma.cloud.design_mode.state.askforleave.entity.AskForLeaveRecord;
import com.ebanma.cloud.design_mode.state.askforleave.entity.Event;
import com.ebanma.cloud.design_mode.state.askforleave.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;


@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private StateMachine<Status, Event> leaveStateMachine;

    @Autowired
    private StateMachinePersister<Status, Event, AskForLeaveRecord> persister;

    public AskForLeaveRecord start(int id){
        AskForLeaveRecord askForLeaveRecord = getOneRecord(id);
        Message message = MessageBuilder.withPayload(Event.START).
                setHeader("record", askForLeaveRecord).build();
        if (!sendEvent(message, askForLeaveRecord)) {
            System.out.println("流转失败"+id);
        }
        return getOneRecord(id);
    }

    @Override
    public AskForLeaveRecord approve(int id) {
        return null;
    }

    public AskForLeaveRecord getOneRecord(int id){
        return (AskForLeaveRecord) Pool.get(id+"");
    }

    /**
     * 发送订单状态转换事件
     *
     * @param message
     * @param record
     * @return
     */
    private synchronized boolean sendEvent(Message<Event> message, AskForLeaveRecord record) {
        boolean result = false;
        try {
            leaveStateMachine.start();
//            //尝试恢复状态机状态
//            persister.restore(leaveStateMachine, record);
//            //添加延迟用于线程安全测试
//            Thread.sleep(1000);
            result = leaveStateMachine.sendEvent(message);
//            //持久化状态机状态
//            persister.persist(leaveStateMachine, record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            leaveStateMachine.stop();
        }
        return result;
    }

}
