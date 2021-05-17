package business

import entity.ContactEntity
import repository.ContactRepository

class ContactBusiness {

    private fun validate(contact: ContactEntity){
        when{
            contact.name   == ""               -> throw Exception("Nome não pode ser vazio!")
            contact.adress == ""               -> throw Exception("Endereco não pode ser vazio!")
            contact.phone  == ""               -> throw Exception("Telefone não pode ser vazio!")
            ContactRepository.isExist(contact) -> throw Exception("Contato já cadastrado!")
        }
    }

    private fun validateRemove(contact: ContactEntity){
        if(contact.name == ""){
            throw Exception("Selecione um registro para remover!")
        }
    }

    fun save(contact: ContactEntity){
        validate(contact)
        ContactRepository.save(contact)
    }

    fun delete(contact: ContactEntity) {
        validateRemove(contact)
        ContactRepository.remove(contact)
    }

    fun getList(): List<ContactEntity> {
        return ContactRepository.getList()
    }

    fun getTextContactsCounter(): String {
        val list = getList()
        return when{
            list.isEmpty() -> "0 Contato"
            list.size == 1 -> "1 Contato"
            else -> "${list.size} Contatatos"

        }
    }
}