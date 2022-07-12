package ru.homeworks.abatsanov;

import java.util.*;

public class PhoneBook {
    private Map<String, List<Contact>> phoneBook = new HashMap<>();

    public void addGroups(String[] groupNames) {
        for (String groupName : groupNames) {
            phoneBook.put(groupName, new ArrayList<>());
        }
    }

    public void addContacts(Contact contact, String... groups) {
        for (String groupName : groups) {
            if (!phoneBook.containsKey(groupName)) {
                System.out.printf(
                        "Группа %s отсутствует в справочнике!%n", groupName);
            } else {
                List<Contact> groupContacts = phoneBook.get(groupName);
                if (groupContacts.contains(contact)) {
                    System.out.printf(
                            "Контакт %s уже присутствует в группе %s справочника!%n",
                            contact, groupName);
                } else {
                    groupContacts.add(contact);
                }
            }
        }
    }

    public Contact findContactByPhoneNumber(String phoneNumber) {
        int matchesPresence = 0;
        Contact foundContact = null;
        for (Map.Entry<String, List<Contact>> groups : phoneBook.entrySet()) {
            for (Contact contact : groups.getValue()) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    foundContact = contact;
                    matchesPresence++;
                }
            }
            if (matchesPresence != 0) {
                break;
            }
        }
        return foundContact;
    }

    private void printGroupContent(String groupName) {
        List<Contact> groupContacts = phoneBook.get(groupName);
        groupContacts.sort(Comparator.comparing(Contact::toString));
        for (Contact contact : groupContacts) {
            System.out.println(contact);
        }
    }

    public void printPhoneBook() {
        Map<String, List<Contact>> sortedMap = new TreeMap<>
                (Comparator.comparing(String::toString));
        sortedMap.putAll(phoneBook);
        for (Map.Entry<String, List<Contact>> groups : sortedMap.entrySet()) {
            String groupName = groups.getKey();
            System.out.println("Группа " + groupName + ":");
            printGroupContent(groupName);
            System.out.println("-----------------");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhoneBook))
            return false;
        PhoneBook p = (PhoneBook) o;
        if (p.size() != size())
            return false;
        try {
            for (Map.Entry<String, List<Contact>> e : entrySet()) {
                String key = e.getKey();
                List<Contact> value = e.getValue();
                if (value == null) {
                    if (!(p.get(key) == null && p.containsKey(key)))
                        return false;
                } else {
                    if (value.size() != (p.get(key)).size())
                        return false;
                }
                assert value != null;
                return value.containsAll(p.get(key));
            }
        } catch (ClassCastException | NullPointerException ex) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(keySet()) ^ Objects.hashCode(values());
    }

    public void put(String key, List<Contact> list) {
        phoneBook.put(key, list);
    }

    public Set<String> keySet() {
        return phoneBook.keySet();
    }

    public Collection<List<Contact>> values() {
        return phoneBook.values();
    }

    public boolean containsKey(String key) {
        return phoneBook.containsKey(key);
    }

    public Set<Map.Entry<String, List<Contact>>> entrySet() {
        return phoneBook.entrySet();
    }


    public int size() {
        return phoneBook.size();
    }

    public List<Contact> get(String key) {
        List<Contact> list = phoneBook.get(key);
        list.sort(Comparator.comparing(Contact::toString));
        return list;
    }
}


