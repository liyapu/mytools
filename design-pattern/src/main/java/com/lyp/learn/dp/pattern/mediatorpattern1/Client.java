package com.lyp.learn.dp.pattern.mediatorpattern1;

/**
 *
 */
public class Client {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        mediator.createConcreteMediator();

        Colleague colleague1 = new ConcreteColleague1(mediator);
        mediator.colleagueChanged(colleague1);
    }
}
