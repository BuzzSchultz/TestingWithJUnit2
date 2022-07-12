package ru.homeworks.abatsanov;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookTest {
    private PhoneBook expected;
    private PhoneBook actual;
    private String[] groupName;
    private String[] groupName2;
    private Contact contact;
    private Contact contact1;
    private Contact contact2;

    @BeforeEach
    public void setUp() {
        expected = new PhoneBook();
        actual = new PhoneBook();
        groupName = new String[]{"Футбол"};
        groupName2 = new String[]{"Работа"};
        contact = new Contact("Tom", "55555");
        contact1 = new Contact("Jim", "66666");
        contact2 = new Contact("John", "77777");
    }

    @Test
    public void testAddGroups() {
        // given: initialized in setUp()
        // when:
        expected.put("Футбол", new ArrayList<>());
        expected.put("Работа", new ArrayList<>());
        actual.addGroups(groupName);
        actual.addGroups(groupName2);
        // then:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAddContacts() {
        // given: initialized in setUp() +
        List<Contact> list = new ArrayList<>();
        List<Contact> list2 = new ArrayList<>();
        list.add(contact1);
        list.add(contact);
        list2.add(contact2);
        list2.add(contact);
        // when:
        expected.put("Футбол", list);
        expected.put("Работа", list2);
        actual.addGroups(groupName);
        actual.addGroups(groupName2);
        actual.addContacts(contact, groupName);
        actual.addContacts(contact1, groupName);
        actual.addContacts(contact, groupName2);
        actual.addContacts(contact2, groupName2);
        // then:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindContactByPhoneNumber() {
        // given: initialized in setUp()
        // when:
        actual.addGroups(groupName);
        actual.addGroups(groupName2);
        actual.addContacts(contact, groupName);
        actual.addContacts(contact1, groupName);
        actual.addContacts(contact, groupName2);
        actual.addContacts(contact2, groupName2);
        Contact foundContact = actual.findContactByPhoneNumber("55555");
        // then:
        Assertions.assertEquals(contact, foundContact);
    }
}
