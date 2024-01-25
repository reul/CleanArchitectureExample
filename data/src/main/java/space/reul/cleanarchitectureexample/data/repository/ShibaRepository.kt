package space.reul.cleanarchitectureexample.data.repository

import space.reul.cleanarchitectureexample.data.webapi.ShibaService
import space.reul.cleanarchitectureexample.domain.model.Urls

class ShibaRepository {
    private val webApi = ShibaService()

    suspend fun listShibas(): Urls {
        return webApi.listUrls()
    }
}
