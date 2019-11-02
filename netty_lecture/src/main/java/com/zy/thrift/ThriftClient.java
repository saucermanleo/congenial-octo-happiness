package com.zy.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftClient {
    public static void main(String[] args) {
        TTransport tTransport = new TFramedTransport(new TSocket("localhost",8899),600);
        TProtocol tProtocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(tProtocol);
        try{
            tTransport.open();
            Person person = client.getPersonByUsername("zy");
            System.out.println(person);

            Person person1 = new Person();
            person1.setUsername("wd").setAge(18).setMarried(false);
            client.savePerson(person1);


        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (DataException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            tTransport.close();
        }
    }
}
