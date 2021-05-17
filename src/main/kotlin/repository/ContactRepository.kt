package repository

import entity.ContactEntity

class ContactRepository {
    companion object{
        private val contactList = mutableListOf<ContactEntity>()

        fun save(contact: ContactEntity){
            contactList.add(contact)
        }

        fun remove(contact: ContactEntity) {
            for (element in contactList.withIndex()){
                if(element.value.name == contact.name){
                    contactList.removeAt(element.index)
                    break;
                }
            }
        }

        fun isExist(contact: ContactEntity): Boolean{
            var exists : Boolean = false
            for (element in contactList){
                if (element.name == contact.name){
                    exists = true
                    break
                }
            }
            return exists
        }

        fun getList() : List<ContactEntity>{
            return contactList
        }
    }
}