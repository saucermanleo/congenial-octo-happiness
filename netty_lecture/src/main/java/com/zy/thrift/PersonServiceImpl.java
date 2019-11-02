package com.zy.thrift;

public class PersonServiceImpl implements PersonService.Iface{
    @Override
    public Person getPersonByUsername(String username) throws DataException, org.apache.thrift.TException {
        System.out.println("get client param:"+username);
        Person person = new Person();
        person.setAge(1);
        person.setMarried(false).setUsername("zy");
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, org.apache.thrift.TException {
        System.out.println(person);
    }
}
