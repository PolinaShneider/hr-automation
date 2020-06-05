package guru.springframework.services.position

import guru.springframework.domain.entities.Position
import guru.springframework.domain.entities.Status
import guru.springframework.repositories.PositionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class PositionServiceImpl : PositionService {
    private var positionRepository: PositionRepository? = null

    @Autowired
    fun setPositionRepository(positionRepository: PositionRepository) {
        this.positionRepository = positionRepository
    }

    override fun listAllPositions(): Iterable<Position> {
        return positionRepository!!.findAll()
    }

    override fun listOpenPositions(): Iterable<Position> {
        return positionRepository!!.findAll().filter {
            it.isOpened
        }
    }

    override fun getPositionById(id: Int?): Position {
        return positionRepository!!.findOne(id)
    }

    override fun savePosition(position: Position): Position {
        return positionRepository!!.save(position)
    }
}
