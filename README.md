![Icon](https://github.com/FireDragon91245/Bounty-Enchantment/blob/1.21.3/src/main/resources/assets/bounty_enchantment/icon.png)  
[![Modrinth-Name](https://img.shields.io/badge/dynamic/json?labelColor=grey&color=grey&label=&query=title&url=https://api.modrinth.com/v2/project/E6BBrRK0&style=flat-square&logo=data:image/svg+xml;base64,PHN2ZyBmaWxsPSIjMDBBRjVDIiByb2xlPSJpbWciIHZpZXdCb3g9IjAgMCAyNCAyNCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48dGl0bGU+TW9kcmludGg8L3RpdGxlPjxwYXRoIGQ9Ik0xMi4yNTIuMDA0YTExLjc4IDExLjc2OCAwIDAgMC04LjkyIDMuNzMgMTEgMTAuOTk5IDAgMCAwLTIuMTcgMy4xMSAxMS4zNyAxMS4zNTkgMCAwIDAtMS4xNiA1LjE2OWMwIDEuNDIuMTcgMi41LjYgMy43Ny4yNC43NTkuNzcgMS44OTkgMS4xNyAyLjUyOWExMi4zIDEyLjI5OCAwIDAgMCA4Ljg1IDUuNjM5Yy40NC4wNSAyLjU0LjA3IDIuNzYuMDIuMi0uMDQuMjIuMS0uMjYtMS43bC0uMzYtMS4zNy0xLjAxLS4wNmE4LjUgOC40ODkgMCAwIDEtNS4xOC0xLjggNS4zNCA1LjM0IDAgMCAxLTEuMy0xLjI2YzAtLjA1LjM0LS4yOC43NC0uNWEzNy41NzIgMzcuNTQ1IDAgMCAxIDIuODgtMS42MjljLjAzIDAgLjUuNDUgMS4wNi45OGwxIC45NyAyLjA3LS40MyAyLjA2LS40MyAxLjQ3LTEuNDdjLjgtLjggMS40OC0xLjUgMS40OC0xLjUyIDAtLjA5LS40Mi0xLjYzLS40Ni0xLjctLjA0LS4wNi0uMi0uMDMtMS4wMi4xOC0uNTMuMTMtMS4yLjMtMS40NS40bC0uNDguMTUtLjUzLjUzLS41My41My0uOTMuMS0uOTMuMDctLjUyLS41YTIuNyAyLjcgMCAwIDEtLjk2LTEuN2wtLjEzLS42LjQzLS41N2MuNjgtLjkuNjgtLjkgMS40Ni0xLjEuNC0uMS42NS0uMi44My0uMzMuMTMtLjA5OS42NS0uNTc5IDEuMTQtMS4wNjlsLjktLjktLjctLjctLjctLjctMS45NS41NGMtMS4wNy4zLTEuOTYuNTMtMS45Ny41My0uMDMgMC0yLjIzIDIuNDgtMi42MyAyLjk3bC0uMjkuMzUuMjggMS4wM2MuMTYuNTYuMyAxLjE2LjMxIDEuMzRsLjAzLjMtLjM0LjIzYy0uMzcuMjMtMi4yMiAxLjMtMi44NCAxLjYzLS4zNi4yLS4zNy4yLS40NC4xLS4wOC0uMS0uMjMtLjYtLjMyLTEuMDMtLjE4LS44Ni0uMTctMi43NS4wMi0zLjczYTguODQgOC44MzkgMCAwIDEgNy45LTYuOTNjLjQzLS4wMy43Ny0uMDguNzgtLjEuMDYtLjE3LjUtMi45OTkuNDctMy4wMzktLjAxLS4wMi0uMS0uMDItLjItLjAzWm0zLjY4LjY3Yy0uMiAwLS4zLjEtLjM3LjM4LS4wNi4yMy0uNDYgMi40Mi0uNDYgMi41MiAwIC4wNC4xLjExLjIyLjE2YTguNTEgOC40OTkgMCAwIDEgMi45OSAyIDguMzggOC4zNzkgMCAwIDEgMi4xNiAzLjQ0OSA2LjkgNi45IDAgMCAxIC40IDIuOGMwIDEuMDcgMCAxLjI3LS4xIDEuNzNhOS4zNyA5LjM2OSAwIDAgMS0xLjc2IDMuNzY5Yy0uMzIuNC0uOTggMS4wNi0xLjM3IDEuMzgtLjM4LjMyLTEuNTQgMS4xLTEuNyAxLjE0LS4xLjAzLS4xLjA2LS4wNy4yNi4wMy4xOC42NCAyLjU2LjcgMi43OGwuMDYuMDZhMTIuMDcgMTIuMDU4IDAgMCAwIDcuMjctOS40Yy4xMy0uNzcuMTMtMi41OCAwLTMuNGExMS45NiAxMS45NDggMCAwIDAtNS43My04LjU3OGMtLjctLjQyLTIuMDUtMS4wNi0yLjI1LTEuMDZaIi8+PC9zdmc+)](https://modrinth.com/mod/bounty-enchantment)
[![Modrinth](https://badges.moddingx.org/modrinth/versions/E6BBrRK0?style=flat)](https://modrinth.com/mod/bounty-enchantment)


# Bounty Enchantment

A simple server-sided enchantment that increases the amount of dropped XP from mobs.
The multipliers for levels 1–5 are configurable in the `bounty_enchantment.json` inside the server’s config directory.

## Config
```json
{
  "levelMultipliers": [
    1.25,
    1.5,
    1.75,
    2.0,
    2.5
  ],
  "maxXpCap": 32767,
  "allowOffhand": false,
  "debug": false
}
```
`levelMultipliers`: Configure the multipliers for each level, where the index is the level  
`maxXpCap`: A hard cap to limit XP per mob (must not exceed a god particle’s worth (`2^15-1` or `32767`))  
`allowOffhand`: If set to true, allows the use of the offhand item bug. (Offhanded item takes over enchantments from main-hand item (for example, bow takes Looting from sword); in this case, ranged weapons would also take over the Bounty enchantment if an item with Bounty is held in the main hand)  
`debug`: Print calculated XP amounts in the server logs (only for debugging)
