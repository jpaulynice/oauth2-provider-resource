# oauth2-provider-resource
Open sourcing my oauth2 [server](https://github.com/julesbond007/oauth2-provider-resource/tree/master/provider) and [resource](https://github.com/julesbond007/oauth2-provider-resource/tree/master/resource) as an example. The responsibility of the oauth2 provider is to issue, verify, refresh and revoke tokens from client apps on behalf of a protected resource/api.

In this example, the resource and provider are completely isolated and use different databases. The reason for this design is so that if the provider is compromised, then the resource is still protected and vice versa. 

The provider database stores user credentials only: username, password, and roles. As far as the resource is concerned, a valid token and role is all it needs to respond to requests from a client application.

Setup
-----

1. Install docker and docker-compose if not already installed: ```brew install docker && brew install docker-compose```
2. Build application: ```./gradlew clean build```
3. Run: ```docker-compose up```

The 2 APIs should be successfully deployed and available at:

Authentication Provider API: ```http://localhost:8080/provider/api/v1/*```

Resource API: ```http://localhost:8080/resource/api/v1/*```

Example
-------

No oauth2 token:

```curl -XGET http://localhost:8080/resource/api/v1/build```

Response:

```
{
    "error": "unauthorized",
    "error_description": "Full authentication is required to access this resource"
}
```

On the other hand the build version of the provider is unprotected:

Example:

```curl -XGET http://localhost:8080/provider/api/v1/build```

Response:

```
1.0.0-SNAPSHOT
```

Access protected resource:
--------------------------

1. Create a user

```POST http://localhost:8080/provider/api/v1/accounts```

Body:

```
{
	"email": "jay@gmail.com",
	"password":"abc123"
}
```

Response:

```
{
    "id": 1,
    "email": "jay@gmail.com",
    "enabled": true,
    "roles": [
        {
            "name": "ROLE_USER"
        }
    ]
}
```

2. Obtain a token from the provider:

```curl -XGET http://localhost:8080/provider/oauth/token?username=jay@gmail.com&password=abc123&grant_type=password&client_id=app&client_secret=secret```

Once we have a token, we can access the protected resource by passing the token in an Authorization header:

```
curl -XGET http://localhost:8080/resource/api/v1/build \
-H "Authorization: Bearer token"
```

--more details to come
