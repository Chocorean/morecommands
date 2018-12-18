<div align="center">
  <br>
  <img
    alt="DEV"
    src="./src/main/resources/logo.png"
    width=200px
  />
  <br/>
  <h1>More Commands</h1>
  <strong>Server side mod which adds a bunch of useful commands for players and administrators.</strong>
</div>
<br/>
<p align="center">
  <a href="https://travis-ci.com/Chocorean/morecommands">
    <img src="https://travis-ci.com/Chocorean/morecommands.svg?branch=master" alt="build status"/>
  </a>
  <a href="https://sonarcloud.io/dashboard?id=morecommands">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=morecommands&metric=alert_status" alt="build status on sonarcloud"/>
  </a>
  <a href="https://sonarcloud.io/dashboard?id=morecommands">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=morecommands&metric=bugs" alt="bugs"/>
  </a>
  <a href="https://sonarcloud.io/dashboard?id=morecommands">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=morecommands&metric=code_smells" />
  </a>
  <a href="https://sonarcloud.io/dashboard?id=morecommands">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=morecommands&metric=duplicated_lines_density" />
  </a>
  <a href="https://sonarcloud.io/dashboard?id=morecommands">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=morecommands&metric=sqale_rating" />
  </a>
  <a href="https://sonarcloud.io/dashboard?id=morecommands">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=morecommands&metric=vulnerabilities" />
  </a>
  <a href="https://img.shields.io/badge/forge%20version-1.12.2-blue.svg">
    <img src="https://img.shields.io/badge/forge%20version-1.12.2-blue.svg" />
  </a>
  <a href="https://img.shields.io/badge/java-1.8-blue.svg">
    <img src="https://img.shields.io/badge/java-1.8-blue.svg" />
  </a>
</p>

# More Commands

## Table of contents

- [What is More Commands?](#what-is-morecommands)
- [How it works](#how-it-works)
- [Getting started for developers](#getting-started-for-developers)
  - [Requirements](#requirements)
  - [Installation](#installation)
  - [Using the file strategy](#using-the-file-strategy)
  - [Using the database strategy](#using-the-database-strategy)
- [Getting started for administrators](#getting-started-for-administrators)
- [Issues](#issues)
- [Website](#website)
- [Contact](#contact)
- [Contributors](#contributors)

## What is More Commands?
More Commands is a mod which add a bunch of useful commands for players and administrators. It is inspired from the old [Essentials plugin](https://dev.bukkit.org/projects/essentials) created by **essentialsteam** (sources availables [here](https://github.com/essentials/Essentials)).

## How it works

MoreCommands simply adds commands in your minecraft server. You can type `/help` in Minecraft to see which commands have been added to the game.

## Getting started for developers

### Requirements
 - [Gradle](https://gradle.org/): build tool used by the forge community
 - [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed 
 - Your prefered java IDE

### Installation

Here the steps to follow if you want to contribute or hack the project:

1. First step is to clone the repository:
```bash
git clone https://github.com/Chocorean/morecommands
```
2. For this step, you just have to follow [this documentation](https://mcforge.readthedocs.io/en/latest/gettingstarted/) in order to setup you developer environment.

3. Run once the minecraft server in order to generate all the necessary files.
4. Accept the EULA agreement by modifing the file `run/eula.txt`.
5. Modify the `run/server.properties` and switch `online-mode` to `false`.
6. The last step is simply configure the `morecommands.cfg`. An example is available [here](https://github.com/Chocorean/morecommands/blob/master/src/main/resources/morecommands.cfg). In a development environment, this file is located in `run/morecommands.cfg`.

### Using the file strategy

The only thing to do is to modify the `morecommands.cfg` file:
```bash
general {
  # ...
  # S:strategy=DATABASE
  S:strategy=FILE
}
```
And that's it! There is nothing particular to do. When using this strategy, the `morecommands.csv` file will be created in the `config/` folder.
Each row is composed of 4 types of data:
 - The type of data (currently `HOME` or `WARP`)
 - The username of the player
 - The data relative to the `HOME` or `WARP`


### Using the database strategy

If you want to test the `database` strategy, you need a database instance running on your machine. We use by default [mariadb](https://mariadb.org/) but any other classic SQL database should be ok. 

Change the `morecommands.cfg` configuration by modifying this:
```bash
general {
  # ...
  # S:strategy=FILE
  S:strategy=DATABASE
}
```
Don't forget to configure in this file all information related to the database (under the `database {...}` key).
 
The last step is to init the database and tables `home` and `warp`:

```sql
/* Create the database */
CREATE OR REPLACE DATABASE minecraft;

/* Create the table containing the warps data */
CREATE TABLE IF NOT EXISTS minecraft.warps (
  id int(11) NOT NULL AUTO_INCREMENT,
  warpname varchar(255) NOT NULL,
  x int(11) NOT NULL,
  y int(11) NOT NULL,
  z int(11) NOT NULL,
  dimension int(11) NOT NULL,
  yaw float(11) NOT NULL,
  pitch float(11) NOT NULL,
  PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Create the table containing the homes data */
CREATE TABLE IF NOT EXISTS minecraft.homes (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  x int(11) NOT NULL,
  y int(11) NOT NULL,
  z int(11) NOT NULL,
  yaw float(11) NOT NULL,
  pitch float(11) NOT NULL,
  PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Remember table names can be changed in the config */
```

## Getting started for administrators

1. Stop your minecraft server.
2. Add `morecommands-X.X.jar` in the `mods/` directory:
```bash
# This command downloads the latest version of morecommands
# Execute it in the mods/ folder
curl -s https://api.github.com/repos/chocorean/morecommands/releases/latest \
  | grep "browser_download_url.*jar" \
  | cut -d : -f 2,3 \
  | tr -d \" \
  | wget -qi -
```
3. Now, we configure MoreCommands. Go under the `config` folder and download the cfg template file (you can also run the server once and the file will be generated):
```bash
# in the config/ folder
wget https://raw.githubusercontent.com/Chocorean/morecommands/master/src/main/resources/morecommands.cfg
```
4. Edit the `config/morecommands.cfg` file depending on your needs.
5. Restart the server and everything should be ok!

## Issues

[Right here](https://github.com/Chocorean/morecommands/issues).

## Webage

Further informations and downloads links are available on the [Curse project page](https://minecraft.curseforge.com/projects/more-commands-mod).

## Contact

[Mail](mailto:baptiste.chocot@gmail.com)

## Contributors

- Baptiste Chocot ([@Chocorean](https://www.github.com/Chocorean/))
- Yann Prono ([@Mcdostone](https://www.github.com/Mcdostone/))