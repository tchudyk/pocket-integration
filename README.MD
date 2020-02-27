# Pocket Java library

![GetPocket-logo](https://getpocket.com/i/v3/pocket_logo.png) 

This library allows to communicate with [Pocket API](https://getpocket.com/developer/docs).

License: BSD.

### Maven
Add repository to your `pom.xml`:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add dependency:
```xml
<dependency>
    <groupId>com.github.tchudyk</groupId>
    <artifactId>pocket-integration</artifactId>
    <version>1.1.0</version>
</dependency>
```

## Usage

### Authorization

```java
PocketAuthFactory factory = PocketAuthFactory.create(consumerKey, "https://getpocket.com/");
String authorizationUrl = factory.getAuthUrl();
// Open here your WebBrowser with `authorizationUrl`... 
// ... when user add permission to your app, then create PocketAuth object. 
PocketAuth pocketAuth = factory.create();
```

You can also do this in separate steps:
```java
PocketAuthFactory factory = PocketAuthFactory.create(consumerKey, "https://getpocket.com/");
String authCode = factory.getCode();
String authorizationUrl = factory.getAuthUrl();
```

After that open in your browser `authorizationUrl`... and after received permission:
```java
PocketAuth pocketAuth = PocketAuthFactory.createForCode(consumerKey, authCode);
Pocket pocket = new Pocket(pocketAuth);
```

If authorization succeed, you can store somewhere in your application `accessToken` to use in later:
```java
String accessToken = pocketAuth.getAccessToken();
```


When you have generated `accessToken`, you can use it to login, without asking user for his permission next time:
```java
PocketAuth pocketAuth = PocketAuthFactory.createForAccessToken(consumerKey, accessToken);
Pocket pocket = new Pocket(pocketAuth);
```



### Get List of Pocket items

```java
GetItemsCmd cmd = new GetItemsCmd.Builder()
        .count(5)
        .build();
GetItemsResult getResult = pocket.getItems(cmd);
List<PocketItem> items = getResult.getList();
```



### Add new bookmark to Pocket

```java
pocket.addItem(
        new AddItemCmd.Builder("https://my-bookmark-url.com")
                .tags(Arrays.asList("tag1", "tag2"))
                .title("My bookmark title")
                .build()
);
```



### Modify bookmarks in Pocket

```java
PocketItem item = items.get(0);
pocket.modify(
        new ModifyItemCmd.Builder()
                .action(new TagsAddAction(item.getItemId(), "my-tag"))
                .action(new ArchiveAction(item.getItemId()))
                .build()
);
```