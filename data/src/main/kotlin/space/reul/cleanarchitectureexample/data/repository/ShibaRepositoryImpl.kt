package space.reul.cleanarchitectureexample.data.repository

import space.reul.cleanarchitectureexample.data.webapi.ShibaService
import space.reul.cleanarchitectureexample.domain.model.Urls
import space.reul.cleanarchitectureexample.domain.usecase.ListShibas

class ShibaRepositoryImpl(
    private val webApi: ShibaService = ShibaService()
) : ListShibas.Repository {

    override suspend fun listShibas(): Urls {
        return webApi.listUrls()
    }
}
