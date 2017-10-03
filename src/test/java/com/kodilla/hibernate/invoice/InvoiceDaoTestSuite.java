package com.kodilla.hibernate.invoice;

import com.kodilla.hibernate.invoice.dao.InvoiceDao;
import com.kodilla.hibernate.invoice.dao.ItemDao;
import com.kodilla.hibernate.invoice.dao.ProductDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceDaoTestSuite {

    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private ProductDao productDao;

    @After
    public void cleanDatabase() {
       //cleanUp
        invoiceDao.deleteAll();
        itemDao.deleteAll();
        productDao.deleteAll();
    }

    @Test
    public void testInvoiceDaoSave() {

        //Given
        Product banana = new Product("banana");
        Product blackberry = new Product("blackberry");
        Item bananas = new Item(new BigDecimal(115), 2, new BigDecimal(123));
        Item blackberries = new Item(new BigDecimal(89), 12, new BigDecimal(13));
        bananas.setProduct(banana);
        blackberries.setProduct(blackberry);
        Invoice invoice = new Invoice("183091902");
        invoice.getItems().add(bananas);
        invoice.getItems().add(blackberries);
        bananas.setInvoice(invoice);
        blackberries.setInvoice(invoice);

        //When
        invoiceDao.save(invoice);

        //Then
        int id = invoice.getId();
        Invoice readInvoice = invoiceDao.findOne(id);
        Assert.assertEquals(id, readInvoice.getId());

    }

}
