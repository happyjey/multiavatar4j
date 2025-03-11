# Multiavatar4j #
<img src="https://raw.githubusercontent.com/multiavatar/Multiavatar/main/logo.png?v=001" width="65"  alt="logo"/>

[Multiavatar](https://multiavatar.com) is a multicultural avatar maker.<br/>
Multiavatar represents people from multiple races, multiple cultures, multiple age groups, multiple worldviews and walks of life.<br/>
In total, it is possible to generate **12,230,590,464** unique avatars.<br/>

`multiavatar4j` is a dependency created as a Java alternative for [this JavaScript project](https://github.com/multiavatar/Multiavatar).

It supports generating avatars based on a seed string, and also allows for customizing the avatar by choosing a specific theme and color.
The output is an SVG string, or byte array of the generated avatar converted to a PNG image.
### Installation ###

To include `multiavatar4j` in your project, add the following repository and dependency:

- To your `build.gradle` file:
```groovy
repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
}
```
```groovy
dependencies {
    implementation 'com.github.sabazed:multiavatar4j:{version}'
}
```

- To your `pom.xml` file:
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```xml
	<dependency>
	    <groupId>com.github.sabazed</groupId>
	    <artifactId>multiavatar4j</artifactId>
	    <version>{version}</version>
	</dependency>
```

Use the release you wish to install instead of `version`


## Usage
Use AvatarGenerator class object to generate avatars. Example usage:

```java
import com.sabazed.AvatarGenerator;
import com.sabazed.model.ThemeType;

public class Main {
  public static void main(String[] args) {
    AvatarGenerator avatarGenerator = new AvatarGenerator();
    String avatarSvg = avatarGenerator.generate("seed");
    String customAvatarSvg = avatarGenerator.generate("seed", "01", ThemeType.C, true);
  }
}
```

### Info ###
There are initial unique 48 (16x3) avatar characters designed to work as the source from which all 12 billion avatars are generated.
Every avatar consists of 6 parts:
- Environment
- Clothes
- Head
- Mouth
- Eyes
- Top

Also, there are different versions of different parts. In some final avatars, additional parts are hidden with transparency (`none` for the color) 
to create different shapes by re-using the same code. Also, each avatar has 3 unique color themes that are defined in the script.<br/>
In total, there are: `16 characters x 3 versions/themes = 48 initial unique avatars`.<br/>
You can see them all by running the unit tests which generate html and png files of the initial avatars.

To create new avatars, the Multiavatar script mixes different parts of different avatars, and different color themes.
The total number of unique avatars: 48^6 = 12,230,590,464
One of the main Multiavatar functions is to work as an identicon. Every unique avatar can be identified by the unique string of characters, associated with the avatar.

The string of characters is also the input for the Multiavatar script, which converts the provided string into a 6 double-digit numbers (range 00-47), each representing an individual part of the final avatar.
`000000000000` - this string of numbers represents the very first avatar + its A theme. You can also read it like this: `00 00 00 00 00 00`.
`474747474747` - this is the 12,230,590,464th avatar (or the 16th initial avatar + its "C" color theme).


### License ###
You can use Multiavatar for free, as long as the conditions described in the [LICENSE](LICENSE) are followed.


### More info ###
For additional information and extended functionality, visit the [original repository](https://github.com/multiavatar/Multiavatar) of the project.

<img src="https://multiavatar.com/press/img/screenshots/screenshot-03.png?v=001" width="50%" alt="avatars">