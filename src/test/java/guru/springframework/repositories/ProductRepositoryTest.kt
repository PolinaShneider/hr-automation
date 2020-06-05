package guru.springframework.repositories

import guru.springframework.configuration.RepositoryConfiguration
import guru.springframework.domain.entities.Application
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner


@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [RepositoryConfiguration::class])
class ProductRepositoryTest {

    private var productRepository: ApplicationRepository? = null

    @Autowired
    fun setProductRepository(productRepository: ApplicationRepository) {
        this.productRepository = productRepository
    }

    @Test
    fun testSaveProduct() {
        //setup product
        val product = Application()
        product.candidateId = 1

        //save product, verify has ID value after save
        assertNull(product.id) //null before save
        productRepository!!.save(product)
        assertNotNull(product.id) //not null after save

//        //fetch from DB
//        val fetchedProduct = productRepository!!.findOne(product.getId())
//
//        //should not be null
//        assertNotNull(fetchedProduct)
//
//        //should equal
//        assertEquals(product.getId(), fetchedProduct.getId())
//        assertEquals(product.getDescription(), fetchedProduct.getDescription())
//
//        //update description and save
//        fetchedProduct.setDescription("New Description")
//        productRepository!!.save(fetchedProduct)
//
//        //get from DB, should be updated
//        val fetchedUpdatedProduct = productRepository!!.findOne(fetchedProduct.getId())
//        assertEquals(fetchedProduct.getDescription(), fetchedUpdatedProduct.getDescription())
//
//        //verify count of products in DB
//        val productCount = productRepository!!.count()
//        assertEquals(productCount, 1)
//
//        //get all products, list should only have one
//        val products = productRepository!!.findAll()
//
//        var count = 0
//
//        for (p in products) {
//            count++
//        }
//
//        assertEquals(count, 1)
    }
}