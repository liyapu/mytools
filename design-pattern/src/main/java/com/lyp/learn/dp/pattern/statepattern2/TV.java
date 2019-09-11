package com.lyp.learn.dp.pattern.statepattern2;

/**
 *  环境角色
 *  电视
 */
public class TV {

    //电视可以看的角色
    private Channel cctv1 = new CCTV1();
    private Channel cctv2 = new CCTV2();
    private Channel cctv3 = new CCTV3();

    //当前渠道
    private Channel channel;

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    //播放cctv1频道
    public void disCCTV1(){
        setChannel(cctv1);
        channel.display();
    }

    //播放cctv2频道
    public void disCCTV2(){
        setChannel(cctv2);
        channel.display();
    }


    //播放cctv3频道
    public void disCCTV3(){
        setChannel(cctv3);
        channel.display();
    }
}
