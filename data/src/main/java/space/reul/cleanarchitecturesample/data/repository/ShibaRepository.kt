package space.reul.cleanarchitectureexample.data.repository

import space.reul.cleanarchitectureexample.data.webapi.ShibaService

class ShibaRepository {
    private val webApi = ShibaService()

    suspend fun listShibas(): List<String> {
        return webApi.listUrls()
    }
}
