package space.reul.cleanarchitectureexample.data.repository

import space.reul.cleanarchitectureexample.data.webapi.ShibaService
import space.reul.cleanarchitectureexample.domain.model.Urls
import space.reul.cleanarchitectureexample.domain.usecase.ListShibas

class ShibaRepositoryImpl: ListShibas.ShibasRepository {
    private val webApi = ShibaService()

    override suspend fun listShibas(): Urls {
        return webApi.listUrls()
    }
}
