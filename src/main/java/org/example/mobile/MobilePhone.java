package org.example.mobile;

import java.util.ArrayList;
import java.util.List;

public class MobilePhone {
    private final String myNumber;
    private final ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    public MobilePhone(String myNumber, List<Contact> contacts) {
        this.myNumber = myNumber;
        this.myContacts = (contacts == null)
                ? new ArrayList<>()
                : new ArrayList<>(contacts);
    }

    public String getMyNumber() {
        return myNumber;
    }

    public ArrayList<Contact> getMyContacts() {
        return myContacts;
    }

    // 1) Yeni kişi ekle: aynı isim varsa ekleme
    public boolean addNewContact(Contact contact) {
        if (contact == null) return false;
        if (findContact(contact.getName()) >= 0) return false; // isim tekil
        myContacts.add(contact);
        return true;
    }

    // 2) Kişi güncelle (old -> new): old listede olmalı
    public boolean updateContact(Contact oldContact, Contact newContact) {
        if (oldContact == null || newContact == null) return false;

        int oldIndex = findContact(oldContact);
        if (oldIndex < 0) return false; // eski kişi yoksa

        int clash = findContact(newContact.getName());
        if (clash >= 0 && clash != oldIndex) return false; // başka kayıtla isim çakışması

        myContacts.set(oldIndex, newContact);
        return true;
    }

    // 3) Kişi sil
    public boolean removeContact(Contact contact) {
        if (contact == null) return false;
        int index = findContact(contact);
        if (index < 0) return false;
        myContacts.remove(index);
        return true;
    }

    // 4) Bul (Contact) -> index (isim üzerinden)
    public int findContact(Contact contact) {
        if (contact == null) return -1;
        return findContact(contact.getName());
    }

    // 5) Bul (String name) -> index (baştan tarama)
    public int findContact(String name) {
        if (name == null) return -1;
        for (int i = 0; i < myContacts.size(); i++) {
            Contact c = myContacts.get(i);
            if (c != null && name.equals(c.getName())) {
                return i;
            }
        }
        return -1;
    }

    // 6) Sorgu (ismi verilen kişiyi döndür) -> listeden aynı referans (equals varsa alan bazlı da geçer)
    public Contact queryContact(String name) {
        int idx = findContact(name);
        return (idx >= 0) ? myContacts.get(idx) : null;
    }

    // 7) Yazdır
    public void printContact() {
        System.out.println("Contact List:");
        for (Contact c : myContacts) {
            System.out.println(c.getName() + " -> " + c.getPhoneNumber());
        }
    }
}
