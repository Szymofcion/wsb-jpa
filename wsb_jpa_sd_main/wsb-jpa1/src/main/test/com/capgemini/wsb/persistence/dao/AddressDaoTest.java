package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.AddressEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressDaoTest {


    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Test
    public void testShouldFindAddressById() {

        // Tworzenie i zapisywanie nowego obiektu AddressEntity
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1("Mieszczanska");
        addressEntity.setAddressLine2("Lubuska");
        addressEntity.setCity("Wroclaw");
        addressEntity.setPostalCode("40-400");

        AddressEntity savedAddress = addressDao.save(addressEntity);

        // Pobieranie obiektu AddressEntity po ID
        AddressEntity fetchedAddress = addressDao.findOne(savedAddress.getId());

        // Sprawdzenie, czy pobrany obiekt nie jest null i czy kod pocztowy jest poprawny
        assertThat(fetchedAddress).isNotNull();
        assertThat(fetchedAddress.getPostalCode()).isEqualTo("40-400");
    }

    @Test
    public void testShouldSaveAddress() {


        // Tworzenie nowego obiektu AddressEntity
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1("Mieszczanska");
        addressEntity.setAddressLine2("Lubuska");
        addressEntity.setCity("Wroclaw");
        addressEntity.setPostalCode("40-400");

        // Zapisanie liczby encji przed dodaniem nowej encji
        long entitiesNumBefore = addressDao.count();

        // Zapisanie nowego obiektu AddressEntity
        AddressEntity saved = addressDao.save(addressEntity);

        // Sprawdzenie, czy zapisany obiekt nie jest null i czy jego ID nie jest null
        // oraz czy liczba encji zwiększyła się o jeden
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(addressDao.count()).isEqualTo(entitiesNumBefore + 1);
    }

    @Transactional
    @Test
    public void testShouldSaveAndRemoveAddress() {


        // Tworzenie nowego obiektu AddressEntity
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1("Mieszczanska");
        addressEntity.setAddressLine2("Lubuska");
        addressEntity.setCity("Wroclaw");
        addressEntity.setPostalCode("40-400");

        // Zapisanie nowego obiektu AddressEntity
        AddressEntity saved = addressDao.save(addressEntity);
        assertThat(saved.getId()).isNotNull();

        // Pobranie nowo zapisanego obiektu AddressEntity po ID
        AddressEntity newSaved = addressDao.findOne(saved.getId());
        assertThat(newSaved).isNotNull();

        // Usunięcie obiektu AddressEntity po ID
        addressDao.delete(saved.getId());

        // Sprawdzenie, czy usunięty obiekt jest null
        AddressEntity removed = addressDao.findOne(saved.getId());
        assertThat(removed).isNull();
    }
}
