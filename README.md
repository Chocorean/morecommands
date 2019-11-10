<div align="center">
  <br>
  <img
    alt="MoreCommands logo"
    src="./src/main/resources/assets/morecommands/textures/logo.png"
    width=200px
  />
  <br/>
  <h1>More Commands</h1>
  <strong>Server-side mod which adds a bunch of useful commands for players and administrators.</strong>
</div>

## Table of contents

- [What is More Commands?](#what-is-more-commands)
- [How it works](#how-it-works)
- [Getting started for developers](#getting-started-for-developers)
  - [Installation](#installation)
  - [Using the file strategy](#using-the-file-strategy)
  - [Using the database strategy](#using-the-database-strategy)
- [Getting started for administrators](#getting-started-for-administrators)
- [Issues](#issues)
- [Website](#website)
- [Contact](#contact)
- [Contributors](#contributors)

## What is More Commands?
More Commands is a mod which add a bunch of useful commands for players and administrators. It is inspired from the old [Essentials plugin](https://dev.bukkit.org/projects/essentials) created by **essentialsteam** (sources availables [here](https://github.com/essentials/Essentials)). This is a server-side mod, which means you only need to add this mod in your server mods folder. Something cool about this is Vanilla clients are able to connect to a Forge Minecraft server using MoreCommands.

## How it works

MoreCommands simply adds several commands for your Minecraft server. Red commands are for OPs only.
*  /sethome
*  /home
*  /spawn
*  /tpa
*  /tpahere
*  /back
*  /warp
*  /warps
*  <span style="color: red">/setspawn</span>
*  <span class="redtext">/setwarp</span>
*  <span class="redtext">/vanish</span>
*  <span class="redtext">/invsee</span>

## Getting started

1. Stop your Minecraft server.
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
3. Run once your server in order to generate the configuration file.
4. Edit the `./config/morecommands.cfg` file depending on your needs (see [file and database strategies](#strategies)).
5. Restart the server. It should be working now.

## Strategies

### File strategy

Edit the `./config/morecommands.cfg` file as following:
```bash
general {
  # ...
  # S:strategy=DATABASE
  S:strategy=FILE
}
```
When using this strategy, the `morecommands.csv` file will be created in the `./data/` folder.
Each row is composed of 4 types of data:
 - The type of data (currently `HOME` or `WARP`)
 - The username of the player
 - The data relative to the `HOME` or `WARP`

### Database strategy

Edit the `./config/morecommands.cfg` file:
```bash
general {
  # ...
  # S:strategy=FILE
  S:strategy=DATABASE
}
```
Don't forget to fill in this file all the information related to your database (under the `database {...}` key).
 
The last step is to initialize the database `morecommands` with tables `home` and `warp`:

```sql
/* Create the database */
CREATE OR REPLACE DATABASE morecommands;

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

## Issues

[Right here](https://github.com/Chocorean/morecommands/issues).

## Website

Further informations and downloads links are available on the [Curse project page](https://minecraft.curseforge.com/projects/more-commands-mod).

## Contact

[Mail](mailto:baptiste.chocot@gmail.com)

## Contributors

- Baptiste Chocot ([@Chocorean](https://www.github.com/Chocorean/))
- Yann Prono ([@Mcdostone](https://www.github.com/Mcdostone/))