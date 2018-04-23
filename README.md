# oauth2-provider-resource
Open sourcing my oauth2 [server](https://github.com/julesbond007/oauth2-provider-resource/tree/master/provider) and [resource](https://github.com/julesbond007/oauth2-provider-resource/tree/master/resource) as an example. The responsibility of the oauth2 provider is to issue, verify, refresh and revoke tokens from client apps on behalf of a protected resource/api.

In this example, the resource and provider are completely isolated and use different databases. The reason for this design is so that if the provider is compromised, then the resource is still protected and vice versa. 

The provider database stores user credentials only: username, password, and roles. As far as the resource is concerned, a valid token and role is all it needs to respond to requests from a client application.

To start:

1. Install docker and docker-compose if not already installed:

```brew install docker && brew install docker-compose```

2. Build application:

```./gradlew clean build```

3. Run:

```docker-compose up```

The 2 APIs should be successfully deployed and available at:
Authentication Provider API: 

```http://localhost:8080/provider/api/v1/build```

Resource API: 

```http://localhost:8080/resource/api/v1/build

The protected resource:

[BuildResource](https://github.com/julesbond007/oauth2-provider-resource/blob/master/resource/src/main/java/com/medviv/rest/v1/BuildResource.java): 

Example with no oauth2 token:

```curl -XGET http://localhost:8080/resource/api/v1/build```

Response:

```
{
    "error": "unauthorized",
    "error_description": "Full authentication is required to access this resource"
}
```

On the other hand the [BuildResource](https://github.com/julesbond007/oauth2-provider-resource/blob/master/provider/src/main/java/com/medviv/auth/api/v1/BuildResource.java) in the provider is NOT protected:

Example:

```curl -XGET http://localhost:8080/provider/api/v1/build```

Response:

```
1.0.0-SNAPSHOT
```





--more details to come
