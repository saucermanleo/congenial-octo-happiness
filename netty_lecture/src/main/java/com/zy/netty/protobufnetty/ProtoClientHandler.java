package com.zy.netty.protobufnetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class ProtoClientHandler extends SimpleChannelInboundHandler<AddressBookProtos.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        AddressBookProtos.Student student = AddressBookProtos.Student.newBuilder().setEmail("1006180871@qq.com").setId(10086).setName("zy").build();
        AddressBookProtos.Cat cat = AddressBookProtos.Cat.newBuilder().setName("tom").setAge(1).build();
        AddressBookProtos.Dog dog = AddressBookProtos.Dog.newBuilder().setAge(2).setName("jerry").build();
        AddressBookProtos.MyMessage myMessage ;
        int randomint = new Random().nextInt(3);
        if(randomint == 0){
            myMessage = AddressBookProtos.MyMessage.newBuilder().setDataType(AddressBookProtos.MyMessage.DataType.PersonType).setStudent(student).build();
        }else if(randomint ==1){
            myMessage = AddressBookProtos.MyMessage.newBuilder().setDataType(AddressBookProtos.MyMessage.DataType.CatType).setCat(cat).build();
        }else{
            myMessage = AddressBookProtos.MyMessage.newBuilder().setDataType(AddressBookProtos.MyMessage.DataType.DogType).setDog(dog).build();
        }

        ctx.channel().writeAndFlush(myMessage);
    }
}
