# Inventory Library for Android

![Flyve MDM banner](https://user-images.githubusercontent.com/663460/26935464-54267e9c-4c6c-11e7-86df-8cfa6658133e.png)

[![License](https://img.shields.io/github/license/flyve-mdm/android-inventory-library.svg?&label=License)](https://github.com/flyve-mdm/android-inventory-library/blob/master/LICENSE.md)
[![Follow twitter](https://img.shields.io/twitter/follow/FlyveMDM.svg?style=social&label=Twitter&style=flat-square)](https://twitter.com/FlyveMDM)
[![Telegram Group](https://img.shields.io/badge/Telegram-Group-blue.svg)](https://t.me/flyvemdm)
[![Project Status: Active](http://www.repostatus.org/badges/latest/active.svg)](http://www.repostatus.org/#active)
[![Conventional Commits](https://img.shields.io/badge/Conventional%20Commits-1.0.0-yellow.svg)](https://conventionalcommits.org)
[![Greenkeeper badge](https://badges.greenkeeper.io/flyve-mdm/android-inventory-library.svg)](https://greenkeeper.io/)
[![GitHub release](https://img.shields.io/github/release/flyve-mdm/android-inventory-library.svg)](https://github.com/flyve-mdm/android-inventory-library/releases)
[![Maven Central](https://img.shields.io/maven-central/v/com.flyvemdm/inventory.svg)](https://bintray.com/flyve-mdm/inventory/android-inventory-library/)

Flyve MDM is a Mobile device management software that enables you to secure and manage all the mobile devices of your business or family via a web-based console.

To get started, check out [Flyve MDM Website](https://flyve-mdm.com/)!

## Table of contents
* [Synopsis](#synopsis)
* [Build Status](#build-status)
* [Installation](#installation)
* [Code Example](#code-example)
* [Documentation](#documentation)
* [Versioning](#versioning)
* [Contribute](#contribute)
* [Contact](#contact)
* [Professional Services](#professional-services)
* [Copying](#copying)

## Synopsis

This library helps you to create a complete inventory of your Android devices: both hardware and software informations are collected. You get the data about processor, memory, drives, sensors, etc., and also the list and description of installed application on any devices in a beautiful XML as protocol compatible with FusionInventory for GLPI.

You can find more information about the Inventory Protocol here:
<http://fusioninventory.org/documentation/dev/spec/protocol/inventory.html>

**What's included?**

* Hardware
* Bios
* Memory
* Inputs
* Sensors
* Drives
* Cpus
* Simcards
* Videos
* Cameras
* Networks
* Envs
* Jvm
* Softwares
* Usb
* Battery

Visit our [website](http://flyve.org/android-inventory-library/) for more information.

## Build Status

| **LTS** | **Bleeding Edge** |
|:---:|:---:|
| [![Build Status](https://circleci.com/gh/flyve-mdm/android-inventory-library/tree/master.svg?style=svg)](https://circleci.com/gh/flyve-mdm/android-inventory-library/tree/master) | [![Build Status](https://circleci.com/gh/flyve-mdm/android-inventory-library/tree/develop.svg?style=svg)](https://circleci.com/gh/flyve-mdm/android-inventory-library/tree/develop) |

## Installation

Download the latest JAR, grab via Maven, insert on `build.gradle` at app level or use Apache Ivy.

### Maven

```xml
<dependency>
  <groupId>org.flyve</groupId>
  <artifactId>inventory</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

### Gradle

```groovy
compile 'org.flyve:inventory:1.0.0'
```

### Apache Ivy

```
<dependency org='org.flyve' name='inventory' rev='0.1.0'>
  <artifact name='inventory' ext='pom' ></artifact>
</dependency>
```

You can also find us on [**Bintray repository**](https://bintray.com/flyve-mdm/inventory/android-inventory-library).

## Code Example

It's easy to implement in your code, as you can see in the following examples

### Java

```java
InventoryTask inventoryTask = new InventoryTask(MainActivity.this, "Agent_v1.0", new InventoryTask.OnTaskCompleted() {
  @Override
  public void onTaskCompleted(String data) {
    Log.d("XML", data);
  }
});

inventoryTask.execute();
```

### Kotlin

```kotlin
val inventoryTask = InventoryTask(this@MainActivity, "Agent_v1.0", object : InventoryTask.OnTaskCompleted() {
    override fun onTaskCompleted(data: String) {
        Log.d("XML", data)
    }
})

inventoryTask.execute()
```

## Documentation

We maintain a detailed documentation of the project on the website, check the [How-tos](http://flyve.org/android-inventory-library/howtos/) and [Development](http://flyve.org/android-inventory-library/) section.

## Versioning

In order to provide transparency on our release cycle and to maintain backward compatibility, Flyve MDM is maintained under [the Semantic Versioning guidelines](http://semver.org/). We are committed to following and complying with the rules, the best we can.

See [the tags section of our GitHub project](http://github.com/flyve-mdm/android-inventory-library/tags) for changelogs for each release version of Flyve MDM. Release announcement posts on [the official Teclib' blog](http://www.teclib-edition.com/en/communities/blog-posts/) contain summaries of the most noteworthy changes made in each release.

## Contribute

Want to file a bug, contribute some code, or improve documentation? Excellent! Read up on our
guidelines for [contributing](./CONTRIBUTING.md) and then check out one of our issues in the [Issues Dashboard](https://github.com/flyve-mdm/android-inventory-library/issues).

## Contact

For notices about major changes and general discussion of Flyve MDM development, subscribe to the [/r/FlyveMDM](http://www.reddit.com/r/FlyveMDM) subreddit.
You can also chat with us via IRC in [#flyve-mdm on freenode](http://webchat.freenode.net/?channels=flyve-mdm) or [@flyvemdm on Telegram](https://t.me/flyvemdm).
Ping me @rafaelje in the IRC chatroom if you get stuck.

## Professional Services

The Flyve MDM and GLPI Network services are available through our [Partner's Network](http://www.teclib-edition.com/en/partners/). We provide special training, bug fixes with editor subscription, contributions for new features, and more.

Obtain a personalized service experience, associated with benefits and opportunities.

## Copying

* **Name**: [Flyve MDM](https://flyve-mdm.com/) is a registered trademark of [Teclib'](http://www.teclib-edition.com/en/).
* **Code**: you can redistribute it and/or modify
    it under the terms of the GNU General Public License ([GPLv3](https://www.gnu.org/licenses/gpl-3.0.en.html)).
* **Documentation**: released under Attribution 4.0 International ([CC BY 4.0](https://creativecommons.org/licenses/by/4.0/)).
