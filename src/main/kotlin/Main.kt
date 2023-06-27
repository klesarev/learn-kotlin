
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.common.mapper.TypeRef
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification

fun main(args: Array<String>) {

    val post = BaseClientRa()
        .configure("https://jsonplaceholder.typicode.com/posts/1")
        .execute("", HttpMethod.GET)

    val res = post.extractFromPath<HashMap<String, Any>>("").also { println(it) }

}
data class Post(
    val userId: Int = 1,
    val id: Int = 1,
    val title: String? = null,
    val body: String? = null
)
enum class HttpMethod {
    GET, POST
}

class BaseClientRa {

    private var baseRequestSpec: RequestSpecification = RequestSpecBuilder().build()
    private var baseResponseSpec: ResponseSpecification = ResponseSpecBuilder().build()

    private var url: String = ""
    private var path: String = ""

    /**
     * Set default base header's values
     */
    private var accept: ContentType = ContentType.JSON
    private var contentType: ContentType = ContentType.JSON

    /**
     * Set empty other headers
     */
    private var headers: HashMap<String, String> = HashMap<String, String>()

    /**
     * Configuration for our client
     *
     * This method be used when you want to configure client for new request
     *
     * baseUrl - API url for current test
     * schema - Swagger schema in string (for set some client properties)
     */
    fun configure(baseUrl: String): BaseClientRa {
        url = baseUrl
        return this
    }

    /**
     * Execution method for interface realisation
     *
     * There we create all of requests for our API
     * Use specifications for set your custom client as params as required your tests
     *
     * request - payload object for request body
     * type - object for body matching
     */
     fun <R> execute(request: R?, method: HttpMethod): Response {
        // Prepare request
        val response: Response
        var requestSpecification: RequestSpecification = baseRequestSpec.accept(accept)
        requestSpecification = if (request != null) requestSpecification.contentType(contentType).body(request) else requestSpecification
        // set headers and reponse specs
        val sender = RestAssured.given(requestSpecification.headers(headers), baseResponseSpec)
        // send request
        response = when (method) {
            HttpMethod.GET-> sender.get(url + path)
            HttpMethod.POST -> sender.post(url + path)
        }
        return response
    }

}

inline fun <reified T> Response.extractAs(): T {
    return this.body.`as`(T::class.java)
}
inline fun <reified T> Response.extractFromPath(path: String = ""): T {
    return this.body.jsonPath().getObject(path, T::class.java)
}