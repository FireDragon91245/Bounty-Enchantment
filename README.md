![Icon](https://github.com/FireDragon91245/Bounty-Enchantment/blob/1.21.3/src/main/resources/assets/bounty_enchantment/icon.png)
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
