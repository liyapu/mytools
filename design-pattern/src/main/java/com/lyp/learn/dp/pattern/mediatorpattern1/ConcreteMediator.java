package com.lyp.learn.dp.pattern.mediatorpattern1;

/**
 * 具体中介者
 */
public class ConcreteMediator implements Mediator {

    private ConcreteColleague1 concreteColleague1;
    private ConcreteColleague2 concreteColleague2;

    //创建同事对象
    public void createConcreteMediator(){
        concreteColleague1 = new ConcreteColleague1(this);
        concreteColleague2 = new ConcreteColleague2(this);
    }
    @Override
    public void colleagueChanged(Colleague colleague) {
        concreteColleague1.action();
        concreteColleague2.action();
    }

    public ConcreteColleague1 getConcreteColleague1() {
        return concreteColleague1;
    }

    public ConcreteColleague2 getConcreteColleague2() {
        return concreteColleague2;
    }
}
