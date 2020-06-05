package guru.springframework.services.interview

import guru.springframework.domain.entities.Interview


interface InterviewService {
    fun update(interview: Interview): Interview

    fun getAllByIds(ids: Iterable<Int>): Iterable<Interview>

    fun getInterview(id: Int): Interview?
}
