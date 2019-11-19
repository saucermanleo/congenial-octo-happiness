package com.zy.netty.protobufnetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtoServerHandler extends SimpleChannelInboundHandler<AddressBookProtos.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.MyMessage msg) throws Exception {
        AddressBookProtos.MyMessage.DataType dataType = msg.getDataType();
        if(dataType == AddressBookProtos.MyMessage.DataType.PersonType){
            AddressBookProtos.Student student=  msg.getStudent();
            System.out.println(student.getName());
            System.out.println(student.getId());
            System.out.println(student.getEmail());
        }else if(dataType ==  AddressBookProtos.MyMessage.DataType.DogType){
            AddressBookProtos.Dog dog = msg.getDog();
            System.out.println(dog.getName());
            System.out.println(dog.getAge());
        }else{
            AddressBookProtos.Cat cat = msg.getCat();
            System.out.println(cat.getName());
            System.out.println(cat.getAge());
        }
    }
}
