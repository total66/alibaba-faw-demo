package com.ebanma.cloud.design_mode.state.askforleave.config;

import com.ebanma.cloud.design_mode.state.askforleave.entity.AskForLeaveRecord;
import com.ebanma.cloud.design_mode.state.askforleave.entity.Event;
import com.ebanma.cloud.design_mode.state.askforleave.entity.Status;
import com.ebanma.cloud.design_mode.state.usemode.Order;
import com.ebanma.cloud.design_mode.state.usemode.OrderStatus;
import com.ebanma.cloud.design_mode.state.usemode.OrderStatusChangeEvent;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

@Component("leaveStateListener")
@WithStateMachine(name = "LeaveStateMachine")
public class LeaveStateListenerImpl{
 
    @OnTransition(source = "WRITING", target = "WAITING_FOR_APPROVAL_1")
    public boolean start(Message<Event> message) {
//        Order order = (Order) message.getHeaders().get("order");
//        order.setStatus(OrderStatus.WAIT_DELIVER);
//        System.out.println("支付，状态机反馈信息：" + message.getHeaders().toString());
        AskForLeaveRecord leaveRecord = (AskForLeaveRecord) message.getHeaders().get("record");
        leaveRecord.setStatus(Status.WAITING_FOR_APPROVAL_1);
        System.out.println("监听生效了-用户提交");
        return true;
    }
 
//    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
//    public boolean deliverTransition(Message<OrderStatusChangeEvent> message) {
//        Order order = (Order) message.getHeaders().get("order");
//        order.setStatus(OrderStatus.WAIT_RECEIVE);
//        System.out.println("发货，状态机反馈信息：" + message.getHeaders().toString());
//        return true;
//    }
//
//    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
//    public boolean receiveTransition(Message<OrderStatusChangeEvent> message){
//        Order order = (Order) message.getHeaders().get("order");
//        order.setStatus(OrderStatus.FINISH);
//        System.out.println("收货，状态机反馈信息：" + message.getHeaders().toString());
//        return true;
//    }
}