package com.zy.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        AddressBookProtos.Student student = AddressBookProtos.Student.newBuilder().setId(10086)
                .setEmail("1006180871@qq.com").setName("zy").build();

        byte[] student2ByteArray = student.toByteArray();

        AddressBookProtos.Student student1 = AddressBookProtos.Student.parseFrom(student2ByteArray);
        System.out.println(student1.getId());
        System.out.println(student1.getName());
        System.out.println(student1.getEmail());
    }
}
