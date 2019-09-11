package com.lyp.learn.dp.pattern.mediatorpattern2;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        //一个房主、一个租房者、一个中介机构
        ConcreteMediator mediator = new ConcreteMediator();

        //房主和租房者只需要知道中介机构即可
        HouseOwner houseOwner = new HouseOwner("张三", mediator);
        Tenant tenant = new Tenant("李四", mediator);

        //中介结构要知道房主和租房者
        mediator.setHouseOwner(houseOwner);
        mediator.setTenant(tenant);

        tenant.action("听说你那里有三室的房主出租.....");
        houseOwner.action("是的!请问你需要租吗?");
    }
}
