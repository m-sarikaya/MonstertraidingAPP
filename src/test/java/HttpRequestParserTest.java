import org.example.server.http.Method;
import org.example.server.http.Request;
import org.example.server.util.HttpRequestParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HttpRequestParserTest {

    private final static String HTTP_GET = """
            GET /home HTTP/1.1
            Host: localhost:10001
            Authentication: Bearer example-token
            \n""".replace("\n","\r\n");

    private final static String HTTP_POST = """
            POST /users HTTP/1.1
            Host: localhost:8080
            Content-Type: application/json
            Content-Length: 31
            
            {
                "username": "example"
            }""".replace("\n","\r\n");

    private final HttpRequestParser requestParser = new HttpRequestParser();

    @Test
    public void given_httpGetRequest_when_useParser_then_parseMethod() {
        Request request = requestParser.parse(HTTP_GET);

        Assertions.assertEquals(Method.GET, request.getMethod());
    }

    @Test
    public void given_httpGetRequest_when_useParser_then_parsePath() {
        Request request = requestParser.parse(HTTP_GET);

        Assertions.assertEquals("/home", request.getPath());
    }

    @Test
    public void given_httpGetRequest_when_useParser_then_parseHostHeader() {
        Request request = requestParser.parse(HTTP_GET);

        Assertions.assertEquals("localhost:10001", request.getHeader("Host"));
    }

    @Test
    public void given_httpGetRequest_when_useParser_then_parseAuthToken() {
        Request request = requestParser.parse(HTTP_GET);

        Assertions.assertEquals("Bearer example-token", request.getHeader("Authentication"));
    }

    @Test
    public void given_httpPostRequest_when_useParser_then_parseMethod() {
        Request request = requestParser.parse(HTTP_POST);

        Assertions.assertEquals(Method.POST, request.getMethod());
    }

    @Test
    public void given_httpPostRequest_when_useParser_then_parseContentLength() {
        Request request = requestParser.parse(HTTP_POST);

        Assertions.assertEquals("31", request.getHeader("Content-Length"));
    }

    @Test
    public void given_httpPostRequest_when_useParser_then_parseContentType() {
        Request request = requestParser.parse(HTTP_POST);

        Assertions.assertEquals("application/json", request.getHeader("Content-Type"));
    }

    @Test
    public void given_httpPostRequest_when_useParser_then_parseBody() {
        Request request = requestParser.parse(HTTP_POST);

        String body = """
                {
                    "username": "example"
                }""".replace("\n", "\r\n");
        Assertions.assertEquals(body, request.getBody());
    }
}