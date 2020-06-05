package guru.springframework.repositories

import guru.springframework.configuration.RepositoryConfiguration
import guru.springframework.domain.entities.Application
import guru.springframework.domain.entities.Status
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [RepositoryConfiguration::class])
class ApplicationRepositoryTest {

    private var applicationRepository: ApplicationRepository? = null

    @Autowired
    fun setRepository(productRepository: ApplicationRepository) {
        this.applicationRepository = productRepository
    }

    @Test
    fun testSaveApp() {
        val application = Application()
        application.candidateId = 1

        assertNull(application.id) //null before save
        applicationRepository!!.save(application)
        assertNotNull(application.id) //not null after save

        //fetch from DB
        val fetchedApp = applicationRepository!!.findOne(application.id)

        //should not be null
        assertNotNull(fetchedApp)

        //should equal
        assertEquals(application.id, fetchedApp.id)
        assertEquals(application.status, fetchedApp.status)

        //update status and save
        fetchedApp.status = Status.INTERVIEW_UPCOMING
        applicationRepository!!.save(fetchedApp)

        //get from DB, should be updated
        val fetchedUpdatedApp = applicationRepository!!.findOne(fetchedApp.id)
        assertEquals(fetchedApp.status, fetchedUpdatedApp.status)

        //verify count of apps in DB
        val appsCount = applicationRepository!!.count()
        assertEquals(appsCount, 1)

        //get all products, list should only have one
        val apps = applicationRepository!!.findAll()

        var count = 0

        for (p in apps) {
            count++
        }

        assertEquals(count, 1)
    }
}