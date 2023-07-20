package com.ebanma.cloud.design_mode.state.askforleave.config;

import com.ebanma.cloud.design_mode.state.askforleave.entity.Event;
import com.ebanma.cloud.design_mode.state.askforleave.entity.Status;
import com.ebanma.cloud.design_mode.state.usemode.Order;
import com.ebanma.cloud.design_mode.state.usemode.OrderStatus;
import com.ebanma.cloud.design_mode.state.usemode.OrderStatusChangeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * 订单状态机配置
 */
@Configuration
@EnableStateMachine(name = "LeaveStateMachine")
public class LeaveStateMachineConfig extends StateMachineConfigurerAdapter<Status, Event> {
 
    /**
     * 配置状态
     * @param states
     * @throws Exception
     */
    public void configure(StateMachineStateConfigurer<Status, Event> states) throws Exception {
        states
                .withStates()
                .initial(Status.WRITING)
                .states(EnumSet.allOf(Status.class));
    }
 
    /**
     * 配置状态转换事件关系
     * @param transitions
     * @throws Exception
     */
    public void configure(StateMachineTransitionConfigurer<Status, Event> transitions) throws Exception {
        transitions
                .withExternal().source(Status.WRITING).target(Status.WAITING_FOR_APPROVAL_1)
                .event(Event.START)
                .and()
                .withExternal().source(Status.WAITING_FOR_APPROVAL_1).target(Status.WAITING_FOR_APPROVAL_2)
                .event(Event.PASS)
                .and()
                .withExternal().source(Status.WAITING_FOR_APPROVAL_1).target(Status.WRITING)
                .event(Event.BAN)
                .and()
                .withExternal().source(Status.WAITING_FOR_APPROVAL_2).target(Status.PASS)
                .event(Event.PASS)
                .and()
                .withExternal().source(Status.WAITING_FOR_APPROVAL_2).target(Status.WRITING)
                .event(Event.BAN);
    }
 
    /**
     * 持久化配置
     * 在实际使用中，可以配合Redis等进行持久化操作
     * @return
     */
    @Bean
    public DefaultStateMachinePersister persister(){
        return new DefaultStateMachinePersister<>(new StateMachinePersist<Object, Object, Order>() {
            @Override
            public void write(StateMachineContext<Object, Object> context, Order order) throws 	Exception {
                System.out.println("this is writing");
                //此处并没有进行持久化操作
            }
 
            @Override
            public StateMachineContext<Object, Object> read(Order order) throws Exception {
                System.out.println("this is reading");
                //此处直接获取Order中的状态，其实并没有进行持久化读取操作
                return new DefaultStateMachineContext(order.getStatus(), null, null, null);
            }
        });
    }
}