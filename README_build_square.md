In order to build maven artifacts, first update the version in the build.xml
file, e.g.:

```xml
<property name="version" value="3.4.9-square-ssl-patch-2" />
```

And then build it with ant:

```
% ant clean package
```

This will compile everything and build maven artifacts, which will be placed in
a versioned directory, e.g.:

```bash
./build/zookeeper-3.4.9-square-ssl-patch-2/dist-maven/zookeeper-3.4.9-square-ssl-patch-2-javadoc.jar
./build/zookeeper-3.4.9-square-ssl-patch-2/dist-maven/zookeeper-3.4.9-square-ssl-patch-2-sources.jar
./build/zookeeper-3.4.9-square-ssl-patch-2/dist-maven/zookeeper-3.4.9-square-ssl-patch-2.jar
./build/zookeeper-3.4.9-square-ssl-patch-2/dist-maven/zookeeper-3.4.9-square-ssl-patch-2.pom
```

These can then be uploaded to our [Nexus repo](https://nexus.corp.squareup.com).

Note that __each of the four files__ need to be uploaded to Nexus for the jar
to be used correctly in downstream builds. There are certain exclusions that
must be honored, most notably for log4j.

********************************************************************************

Release version info:

3.4.9-square-ssl-patch:

  - this version is rebased off the released 3.4.9 source
  - patch-2 fixes a packaging issue that inadventently excluded the pom.xml
    file

3.4.8-square-ssl-patch-5:

  - this version is rebased off the released 3.4.8 source
  - patch-5 fixes a packaging issue so the correct version is displayed correctly, on startup
