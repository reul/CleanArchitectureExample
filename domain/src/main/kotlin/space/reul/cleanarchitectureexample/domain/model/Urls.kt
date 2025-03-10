package space.reul.cleanarchitectureexample.domain.model

typealias Cats = ArrayList<Cat>

data class Cat(
    val id: String,
    val tags: List<String>,
    val mimetype: Mimetype,
    val createdAt: String,
) {
    val url: String
        get() = "https://cataas.com/cat/$id"
}

enum class Mimetype {
    ImageGIF,
    ImageJPEG,
    ImagePNG,
}
